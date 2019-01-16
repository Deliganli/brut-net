package com.deliganli.brutnet.salary

import java.time.{Month, Year}

import com.deliganli.brutnet.util.IntExt.IntAug

case class MinimumWage(year: Year, till: Month, gross: Gross, incomeTax: Double)

object MinimumWage {

  val data = List(
    MinimumWage(2000.year, 4.month, Gross(109.8), 13.9),
    MinimumWage(2000.year, 1.month, Gross(109.8), 13.9),
    MinimumWage(2001.year, 7.month, Gross(139.95), 17.8),
    MinimumWage(2001.year, 8.month, Gross(146.9475), 17.8),
    MinimumWage(2001.year, 1.month, Gross(167.94), 17.8),
    MinimumWage(2002.year, 7.month, Gross(222.00075), 28.3),
    MinimumWage(2002.year, 1.month, Gross(250.875), 28.3),
    MinimumWage(2003.year, 1.month, Gross(306), 39),
    MinimumWage(2004.year, 7.month, Gross(423), 53.9),
    MinimumWage(2004.year, 1.month, Gross(444.15), 53.93),
    MinimumWage(2005.year, 1.month, Gross(488.7), 62.31),
    MinimumWage(2006.year, 1.month, Gross(531), 67.7),
    MinimumWage(2007.year, 7.month, Gross(562.5), 71.7),
    MinimumWage(2007.year, 1.month, Gross(585), 71.7),
    MinimumWage(2008.year, 7.month, Gross(608.4), 77.57),
    MinimumWage(2008.year, 1.month, Gross(638.7), 77.57),
    MinimumWage(2009.year, 7.month, Gross(666), 84.92),
    MinimumWage(2009.year, 1.month, Gross(693), 84.92),
    MinimumWage(2010.year, 7.month, Gross(729), 92.95),
    MinimumWage(2010.year, 1.month, Gross(760.5), 92.95),
    MinimumWage(2011.year, 7.month, Gross(796.5), 101.55),
    MinimumWage(2011.year, 1.month, Gross(837), 101.55),
    MinimumWage(2012.year, 7.month, Gross(886.5), 113.03),
    MinimumWage(2012.year, 1.month, Gross(940.5), 113.03),
    MinimumWage(2013.year, 7.month, Gross(978.6), 124.77),
    MinimumWage(2013.year, 1.month, Gross(1021.5), 124.77),
    MinimumWage(2014.year, 7.month, Gross(1071), 136.55),
    MinimumWage(2014.year, 1.month, Gross(1134), 136.55),
    MinimumWage(2015.year, 7.month, Gross(1201.5), 153.19),
    MinimumWage(2015.year, 1.month, Gross(1273.5), 153.19),
    MinimumWage(2016.year, 1.month, Gross(1647), 209.99),
    MinimumWage(2017.year, 1.month, Gross(1777.5), 226.63),
    MinimumWage(2018.year, 1.month, Gross(2029.5), 258.76),
  )
}
