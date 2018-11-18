package com.deliganli.main.family

import io.circe.{Decoder, Encoder}

object EmploymentStatus extends Enumeration {
  type EmploymentStatus = Value
  val employed, unemployed = Value

  implicit val decoder: Decoder[EmploymentStatus.Value] = Decoder.enumDecoder(EmploymentStatus)
  implicit val encoder: Encoder[EmploymentStatus.Value] = Encoder.enumEncoder(EmploymentStatus)
}
