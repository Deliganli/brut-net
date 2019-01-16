package com.deliganli

import java.time.Year

import com.deliganli.brutnet.tax.Bracket._
import com.deliganli.brutnet.tax.IncomeTax
import org.scalatest.{Matchers, WordSpec}

import scala.collection.SortedSet

class IncomeTaxTest extends WordSpec with Matchers {

  "2017 bracket for" should {
    val incomeTax = IncomeTax.of(Year.of(2014)).get

    "5k should be 0.15" in (incomeTax.bracketFor(5000).ratio shouldBe first)
    "13k should be 0.15" in (incomeTax.bracketFor(13000).ratio shouldBe first)
    "13001 should be 0.20" in (incomeTax.bracketFor(13001).ratio shouldBe second)
    "110k should be 0.27" in (incomeTax.bracketFor(110000).ratio shouldBe third)
    "120k should be 0.35" in (incomeTax.bracketFor(120000).ratio shouldBe fourth)
  }

  "2014 tax brackets ratios up to" should {
    val incomeTax = IncomeTax.of(Year.of(2014)).get

    "0.15 for 5k" in {
      incomeTax.bracketsUntil(5000).map(_.ratio) shouldBe SortedSet(first)
    }

    "0.20 for 12k" in {
      incomeTax.bracketsUntil(12000).map(_.ratio) shouldBe SortedSet(first, second)
    }

    "0.27 for 30k" in {
      incomeTax.bracketsUntil(30000).map(_.ratio) shouldBe SortedSet(first, second, third)
    }

    "0.35 for 99k" in {
      incomeTax.bracketsUntil(99000).map(_.ratio) shouldBe SortedSet(first, second, third, fourth)
    }
  }
}
