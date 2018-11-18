package com.deliganli.main.tax
import io.circe.generic.auto._

case class TaxedAmounts(worker: BigDecimal, unemployment: BigDecimal, stamp: BigDecimal, income: BigDecimal) {
  def total = income + stamp
}
