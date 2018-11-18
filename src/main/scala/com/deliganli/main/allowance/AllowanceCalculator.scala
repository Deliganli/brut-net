package com.deliganli.main.allowance

import java.time.Year

import com.deliganli.main.salary.MinimumWage

class AllowanceCalculator {

  def get(scenario: AllowanceScenario, year: Year) = {
    val minimumWage = MinimumWage.data.find(_.year == year).get
    val monthlyBaseAllowance = minimumWage.gross.amount * scenario.getRatio
    val monthlyAllowance = monthlyBaseAllowance * 0.15

    monthlyAllowance.min(minimumWage.incomeTax)
  }
}
