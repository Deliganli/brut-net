package com.deliganli.main.tax

import java.time.Year

import com.deliganli.main.util.IntExt.IntAug

import scala.collection.SortedSet

case class IncomeTax(year: Year, brackets: SortedSet[Bracket]) {

  def bracketsUntil(base: BigDecimal) = {
    brackets.filter(_.ceil.exists(_ < base)) + bracketFor(base)
  }

  def bracketFor(base: BigDecimal) = {
    brackets.find(_.ceil.forall(_ >= base)).get
  }

  def taxUntil(bracket: Bracket) = {
    brackets.until(bracket).map(bracket => bracket.ceil.get * bracket.ratio).sum
  }

  def previousBracket(bracket: Bracket) = {
    brackets.until(bracket).lastOption
  }
}

case class Bracket(ratio: BigDecimal, ceil: Option[BigDecimal])

object Bracket {
  val first = BigDecimal(0.15)
  val second = BigDecimal(0.20)
  val third = BigDecimal(0.27)
  val fourth = BigDecimal(0.35)

  implicit val bracketOrder: Ordering[Bracket] = Ordering.fromLessThan(_.ratio < _.ratio)

}

object IncomeTax {

  import Bracket._

  val data = Seq(
    IncomeTax(2005.year, SortedSet(b(first, 6600), b(second, 15000), b(third, 44700), b(fourth, None))),
    IncomeTax(2006.year, SortedSet(b(first, 7000), b(second, 18000), b(third, 40000), b(fourth, None))),
    IncomeTax(2007.year, SortedSet(b(first, 7500), b(second, 19000), b(third, 43000), b(fourth, None))),
    IncomeTax(2008.year, SortedSet(b(first, 7800), b(second, 19800), b(third, 44700), b(fourth, None))),
    IncomeTax(2009.year, SortedSet(b(first, 8700), b(second, 22000), b(third, 50000), b(fourth, None))),
    IncomeTax(2010.year, SortedSet(b(first, 8800), b(second, 22000), b(third, 76200), b(fourth, None))),
    IncomeTax(2011.year, SortedSet(b(first, 9400), b(second, 23000), b(third, 80000), b(fourth, None))),
    IncomeTax(2012.year, SortedSet(b(first, 10000), b(second, 25000), b(third, 88000), b(fourth, None))),
    IncomeTax(2013.year, SortedSet(b(first, 10700), b(second, 26000), b(third, 94000), b(fourth, None))),
    IncomeTax(2014.year, SortedSet(b(first, 11000), b(second, 27000), b(third, 97000), b(fourth, None))),
    IncomeTax(2015.year, SortedSet(b(first, 12000), b(second, 29000), b(third, 106000), b(fourth, None))),
    IncomeTax(2016.year, SortedSet(b(first, 12600), b(second, 30000), b(third, 110000), b(fourth, None))),
    IncomeTax(2017.year, SortedSet(b(first, 13000), b(second, 30000), b(third, 110000), b(fourth, None))),
    IncomeTax(2018.year, SortedSet(b(first, 14800), b(second, 34000), b(third, 80000), b(fourth, None))),
  )

  def of(year: Year) = data.find(_.year == year)

  private def b(ratio: BigDecimal, ceil: Option[BigDecimal]) = Bracket(ratio, ceil)

  private def b(ratio: BigDecimal, ceil: BigDecimal) = Bracket(ratio, Option(ceil))
}
