package com.deliganli.main.salary

import com.deliganli.main.allowance.LivingAllowance
import com.deliganli.main.tax.TaxedAmounts
import io.circe.generic.auto._

case class Salary(base: BigDecimal, taxes: TaxedAmounts, allowance: Option[LivingAllowance]) {
  def net: BigDecimal = base - taxes.total + allowance.map(_.amount).getOrElse(0d)
}
