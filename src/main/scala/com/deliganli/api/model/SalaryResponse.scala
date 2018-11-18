package com.deliganli.api.model

import com.deliganli.main.allowance.LivingAllowance
import com.deliganli.main.salary.Salary
import com.deliganli.main.tax.TaxedAmounts
import io.circe.generic.auto._

case class SalaryResponse(base: BigDecimal, net: BigDecimal, taxes: TaxResponse, allowance: Option[LivingAllowance])

object SalaryResponse {

  def apply(salary: Salary): SalaryResponse = {
    new SalaryResponse(salary.base, salary.net, TaxResponse(salary.taxes), salary.allowance)
  }
}

case class TaxResponse(worker: BigDecimal, unemployment: BigDecimal, stamp: BigDecimal, income: BigDecimal)

object TaxResponse {

  def apply(taxes: TaxedAmounts): TaxResponse = {
    new TaxResponse(
      worker = taxes.worker,
      unemployment = taxes.unemployment,
      stamp = taxes.stamp,
      income = taxes.income
    )
  }
}
