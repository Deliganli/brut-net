package com.deliganli

import java.time.{Month, Year}

import com.deliganli.brutnet.TRSalary
import com.deliganli.brutnet.salary.Gross
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

import scala.collection.SortedMap
import scala.math.BigDecimal.RoundingMode

class TRSalaryTest extends WordSpec with Matchers with ScalaFutures {

  "SalaryCalculator" should {
    "calculate arbitrary gross salaries" should {
      val salaries = SortedMap(
        Month.of(1)  -> Gross(2000),
        Month.of(2)  -> Gross(2000),
        Month.of(3)  -> Gross(3000),
        Month.of(4)  -> Gross(3000),
        Month.of(5)  -> Gross(5000),
        Month.of(6)  -> Gross(5000),
        Month.of(7)  -> Gross(5000),
        Month.of(8)  -> Gross(7000),
        Month.of(9)  -> Gross(7000),
        Month.of(10) -> Gross(7000),
        Month.of(11) -> Gross(3000),
        Month.of(12) -> Gross(3000)
      )

      val amounts = TRSalary.annual(Year.of(2016), salaries)

      "with correct worker contributions" in {
        val expected       = Seq(280, 280, 420, 420, 700, 700, 700, 980, 980, 980, 420, 420)
        val roundedResults = amounts.values.map(_.taxes.worker).map(_.setScale(2, RoundingMode.FLOOR))

        roundedResults shouldBe expected
      }

      "with correct unemployment contributions" in {
        val expected       = Seq(20, 20, 30, 30, 50, 50, 50, 70, 70, 70, 30, 30)
        val roundedResults = amounts.values.map(_.taxes.unemployment).map(_.setScale(2, RoundingMode.FLOOR))

        roundedResults shouldBe expected
      }

      "with correct income taxes" in {
        val expected       = Seq(255, 255, 382.5, 382.5, 645, 850, 850, 1190, 1410.5, 1606.5, 688.5, 688.5)
        val roundedResults = amounts.values.map(_.taxes.income).map(_.setScale(2, RoundingMode.FLOOR))

        roundedResults shouldBe expected
      }

      "with correct net amounts" in {
        val expected = Seq(
          1429.82, 1429.82, 2144.73, 2144.73, 3567.05, 3362.05, 3362.05, 4706.87, 4486.37, 4290.37, 1838.73, 1838.73
        )

        val roundedResults = amounts.values.map(_.net).map(_.setScale(2, RoundingMode.FLOOR))

        roundedResults shouldBe expected
      }
    }
  }
}
