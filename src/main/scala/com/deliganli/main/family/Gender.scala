package com.deliganli.main.family

object Gender extends Enumeration {
  type Gender = Value
  val male, female = Value

  implicit class GenderExtensions(g: Gender) {
    def isMale: Boolean = g == Gender.male

    def isFemale: Boolean = g == Gender.female
  }

  implicit class GenderAugmentations(gender: Gender) {

    def opposite = gender match {
      case g if g == male   => female
      case g if g == female => male
    }
  }

}
