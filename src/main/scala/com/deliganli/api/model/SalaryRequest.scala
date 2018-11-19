package com.deliganli.api.model

import java.time.{Month, Year}

import com.deliganli.main.salary.Gross
import io.circe.generic.auto._

case class SalaryRequest(year: Year, salaries: Map[Month, Gross])
