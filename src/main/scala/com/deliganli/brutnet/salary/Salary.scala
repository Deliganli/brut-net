package com.deliganli.brutnet.salary

import com.deliganli.brutnet.allowance.LivingAllowance
import com.deliganli.brutnet.tax.TaxedAmounts

case class Salary(base: BigDecimal, taxes: TaxedAmounts, allowance: Option[LivingAllowance]) {
  def net: BigDecimal = base - taxes.total + allowance.map(_.amount).getOrElse(0d)
}
