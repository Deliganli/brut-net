package com.deliganli.main.salary
import com.deliganli.main.tax.Ratios

case class Gross(amount: BigDecimal) {

  def base(ratios: Ratios) = amount * (1 - ratios.unemployment - ratios.worker)
}
