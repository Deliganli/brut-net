package com.deliganli.brutnet.util

import java.time.{Month, Year}

object IntExt {

  implicit class IntAug(number: Int) {
    def year = Year.of(number)

    def month = Month.of(number)
  }

}
