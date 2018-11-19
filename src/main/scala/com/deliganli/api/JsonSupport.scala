package com.deliganli.api

import java.time.{Month, Year}

import cats.syntax.either._
import com.deliganli.main.salary.Gross
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.generic.auto._
import io.circe.{Decoder, KeyDecoder}

trait JsonSupport extends ErrorAccumulatingCirceSupport {
  implicit val yearDecoder: Decoder[Year] = Decoder.decodeInt.emap { v =>
    Either.catchNonFatal(Year.of(v)).leftMap(_ => "Invalid year")
  }

  implicit val monthDecoder: Decoder[Month] = Decoder.decodeInt.emap { v =>
    Either.catchNonFatal(Month.of(v)).leftMap(_ => "Invalid month")
  }

  implicit val decodeGross: Decoder[Gross] = Decoder.decodeBigDecimal.emap { bd =>
    Either.catchNonFatal(Gross(bd)).leftMap(_ => "Invalid salary amount")
  }

  implicit val monthKeyDecoder: KeyDecoder[Month] = KeyDecoder.decodeKeyInt.map(key => Month.of(key))
}
