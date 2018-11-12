package com.deliganli.main.salary
import java.time.{Month, Year}

import com.deliganli.main.tax.{Bracket, IncomeTax, Tax, TaxedAmounts}

import scala.collection.SortedMap

object SalaryCalculator {

  def annual(year: Year, salaries: SortedMap[Month, Gross]) = {
    val tax = Tax.of(year).get
    val incomeTax = IncomeTax.of(year).get

    val bases = salaries.values.map(gross => gross.base(tax.ratios))

    val accumulatedBases = bases.scan(BigDecimal(0)) { case (sum, next) => if (next == 0d) 0 else sum + next }

    val incomeTaxAmounts = bases.zip(accumulatedBases).map {
      case (base, accumulatedBase) =>
        incomeTax
          .bracketsUntil(base + accumulatedBase)
          .keysIteratorFrom(incomeTax.bracketFor(accumulatedBase))
          .foldLeft((BigDecimal(0), BigDecimal(0)))(partialIncomeCalculator(accumulatedBase, base))
          ._2
    }

    val taxedAmounts = salaries.toList.zip(incomeTaxAmounts).map {
      case ((month, gross), incomeTaxAmount) =>
        TaxedAmounts(
          worker = gross.amount * tax.ratios.worker,
          unemployment = gross.amount * tax.ratios.unemployment,
          stamp = gross.amount * tax.ratios.stamp,
          income = incomeTaxAmount
        )
    }

    val detailedSalaries = bases
      .zip(taxedAmounts)
      .map { case (base, taxedAmount) => Salary(base, taxedAmount, None) }

    SortedMap(salaries.keys.zip(detailedSalaries).toArray: _*)
  }

  def partialIncomeCalculator(
    accumulatedBase: BigDecimal,
    base: BigDecimal
  ): ((BigDecimal, BigDecimal), Bracket) => (BigDecimal, BigDecimal) = {
    case ((processedInPreviousBucket, lastIncomeTax), bracket) =>
      val ceil: BigDecimal = bracket.ceil.getOrElse(Double.MaxValue)
      val amountUpperLimit = (accumulatedBase + base).min(ceil)
      val bracketBase = amountUpperLimit - processedInPreviousBucket.max(accumulatedBase)
      val incomeTax = bracketBase * bracket.ratio

      (accumulatedBase + bracketBase, lastIncomeTax + incomeTax)
  }
}
