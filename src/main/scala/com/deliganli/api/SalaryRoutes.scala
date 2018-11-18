package com.deliganli.api

import java.time.{Month, Year}

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{get, post}
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.deliganli.api.model.SalaryResponse
import com.deliganli.main.salary.{Gross, SalaryCalculator}
import io.circe.generic.auto._

import scala.collection.SortedMap

trait SalaryRoutes extends JsonSupport {
  implicit def system: ActorSystem

  lazy val salaryRoutes: Route = pathPrefix("salaries") {
    concat(pathEnd {
      concat(get {
        complete("aldın hadi")
      }, post {
        entity(as[Seq[BigDecimal]]) { salaries =>
          val normalizedSalaries = SortedMap(Month.values().zip(salaries.map(Gross)): _*)
          val result = SalaryCalculator.annual(Year.of(2018), normalizedSalaries)
          val response = result.values.map(SalaryResponse(_))
          complete((StatusCodes.OK, response))
        }
      })
    })
  }
}
