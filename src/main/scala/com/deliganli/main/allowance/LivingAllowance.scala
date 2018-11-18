package com.deliganli.main.allowance

import io.circe.generic.auto._

case class LivingAllowance(amount: BigDecimal, scenario: AllowanceScenario)
