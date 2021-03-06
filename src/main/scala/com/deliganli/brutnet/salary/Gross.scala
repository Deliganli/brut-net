package com.deliganli.brutnet.salary

import com.deliganli.brutnet.tax.Ratios

case class Gross(amount: BigDecimal) {

  def base(ratios: Ratios) = amount * (1 - ratios.unemployment - ratios.worker)
}

object Gross {
  val empty = Gross(0)
}
