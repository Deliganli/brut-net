package com.deliganli.brutnet.allowance

import com.deliganli.brutnet.allowance.AllowanceScenario.ChildRatios
import com.deliganli.brutnet.family.EmploymentStatus
import com.deliganli.brutnet.family.EmploymentStatus.EmploymentStatus

sealed trait AllowanceScenario {
  def getRatio: Double
}

object AllowanceScenario {

  object ChildRatios {
    val first2 = 0.075
    val third  = 0.1
    val last2  = 0.05
  }

}

case class MarriedScenario(spouseEmploymentStatus: EmploymentStatus = EmploymentStatus.unemployed, childCount: Int = 0)
    extends AllowanceScenario {
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

case class SingleScenario(a: String = "") extends AllowanceScenario {
  override def getRatio: Double = {
    0.5
  }
}
