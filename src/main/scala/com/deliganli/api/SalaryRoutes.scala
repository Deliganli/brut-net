package com.deliganli.api

import java.time.{Month, Year}

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.deliganli.api.model.{SalaryRequest, SalaryResponse}
import com.deliganli.main.salary.{Gross, SalaryCalculator}
import io.circe.generic.auto._

import scala.collection.SortedMap

trait SalaryRoutes extends JsonSupport {
  implicit def system: ActorSystem

  lazy val salaryRoutes: Route = pathPrefix("salaries") {
    concat(pathEnd {
      post {
        entity(as[SalaryRequest]) { body =>
          val normalizedSalaries = SortedMap(
            Month
              .values()
              .map(month => month -> body.salaries.getOrElse(month, Gross.empty)): _*
          )

          val result = SalaryCalculator.annual(Year.of(2018), normalizedSalaries)
          val response = result.values.map(SalaryResponse(_))
          complete((StatusCodes.OK, response))
        }
      }
    })
  }
}
