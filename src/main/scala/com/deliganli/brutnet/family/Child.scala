package com.deliganli.brutnet.family

import com.deliganli.brutnet.family.Gender.Gender
import org.joda.time.{LocalDate, Years}

case class Child(dateOfBirth: LocalDate, gender: Gender, isStudent: Boolean) {

  def isAllowanceEligibleAt(date: LocalDate): Boolean = {
    val threshold = if (isStudent) 25 else 18
    Years.yearsBetween(dateOfBirth, date).getYears <= threshold
  }

  def shouldSupportOn(date: LocalDate): Boolean = {
    val threshold = if (gender.isFemale) 20 else 18
    Years.yearsBetween(dateOfBirth, date).getYears <= threshold
  }
}
