package com.deliganli.main.allowance

import com.deliganli.main.allowance.LivingAllowanceScenario.ChildRatios
import com.deliganli.main.family.EmploymentStatus
import com.deliganli.main.family.EmploymentStatus.EmploymentStatus

sealed trait LivingAllowanceScenario {
  def getRatio: Double
}

object LivingAllowanceScenario {

  object ChildRatios {
    val first2 = 0.075
    val third = 0.1
    val last2 = 0.05
  }

}

case class MarriedScenario(spouseEmploymentStatus: EmploymentStatus = EmploymentStatus.unemployed, childCount: Int = 0)
    extends LivingAllowanceScenario {
  override def getRatio: Double = {
    val base = spouseEmploymentStatus match {
      case EmploymentStatus.unemployed => 0.6
      case EmploymentStatus.employed   => 0.5
    }

    val childRatio = childCount match {
      case c if c < 3  => c * ChildRatios.first2
      case c if c == 3 => 2 * ChildRatios.first2 + ChildRatios.third
      case c           => 2 * ChildRatios.first2 + ChildRatios.third + (c.min(5) - 3) * ChildRatios.last2
    }

    base + childRatio.min(0.85)
  }
}

case class SingleScenario() extends LivingAllowanceScenario {
  override def getRatio: Double = {
    0.5
  }
}