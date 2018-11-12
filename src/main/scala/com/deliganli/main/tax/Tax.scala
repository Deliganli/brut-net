package com.deliganli.main.tax

import java.time.{Month, Year}

import com.deliganli.main.util.IntExt.IntAug

case class Tax(year: Year, ratios: Ratios, ceilings: Ceilings)

object Tax {

  val data = Seq(
    Tax(2005.year, Ratios(0.14, 0.01, 0.0066, 0.205, 0.02), Ceilings(6360.9, 6649.9)),
    Tax(2006.year, Ratios(0.14, 0.01, 0.006, 0.205, 0.02), Ceilings(3451.5, 3451.5)),
    Tax(2007.year, Ratios(0.14, 0.01, 0.006, 0.205, 0.02), Ceilings(3656.4, 3802.5)),
    Tax(2008.year, Ratios(0.14, 0.01, 0.006, 0.205, 0.02), Ceilings(3954.6, 4151.7)),
    Tax(2009.year, Ratios(0.14, 0.01, 0.006, 0.205, 0.02), Ceilings(4329, 4504.5)),
    Tax(2010.year, Ratios(0.14, 0.01, 0.0066, 0.205, 0.02), Ceilings(4738.5, 4943.4)),
    Tax(2011.year, Ratios(0.14, 0.01, 0.0066, 0.205, 0.02), Ceilings(5177.4, 5440.5)),
    Tax(2012.year, Ratios(0.14, 0.01, 0.0066, 0.205, 0.02), Ceilings(5762.4, 6113.4)),
    Tax(2013.year, Ratios(0.14, 0.01, 0.00759, 0.205, 0.02), Ceilings(6360.9, 6639.9)),
    Tax(2014.year, Ratios(0.14, 0.01, 0.00759, 0.205, 0.02), Ceilings(6961.5, 7371)),
    Tax(2015.year, Ratios(0.14, 0.01, 0.00759, 0.205, 0.02), Ceilings(7809.9, 8277.9)),
    Tax(2016.year, Ratios(0.14, 0.01, 0.00759, 0.205, 0.02), Ceilings(10705.5, 10705.5)),
    Tax(2017.year, Ratios(0.14, 0.01, 0.00759, 0.205, 0.02), Ceilings(13331.4, 13331.4)),
    Tax(2018.year, Ratios(0.14, 0.01, 0.00759, 0.205, 0.02), Ceilings(15221.4, 15221.4)),
  )

  def of(years: Year) = {
    data.find(_.year == years)
  }
}

case class Ratios(
  worker: Double,
  unemployment: Double,
  stamp: Double,
  employerWorker: Double,
  employerUnemployment: Double,
)

case class Ceilings(firstHalf: BigDecimal, secondHalf: BigDecimal) {

  def getAt(month: Month) = {
    if (month.getValue < Month.AUGUST.getValue) firstHalf else secondHalf
  }
}
