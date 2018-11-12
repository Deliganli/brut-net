package com.deliganli.main.tax

case class TaxedAmounts(worker: BigDecimal, unemployment: BigDecimal, stamp: BigDecimal, income: BigDecimal) {
  def total = income + stamp
}
