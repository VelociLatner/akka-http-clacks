package com.velocilatner

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.directives.RespondWithDirectives
import akka.http.scaladsl.server.Directive0
import akka.stream.scaladsl.Flow

private object Pratchett {
  // http://www.gnuterrypratchett.com/
  // Sir Terry Pratchett (1948-2015)

  val header = RawHeader("X-Clacks-Overhead", "GNU Terry Pratchett")
}

object ClacksOverhead {
  def passAlong(flow: Flow[HttpRequest, HttpResponse, Any]): Flow[HttpRequest, HttpResponse, Any] = {
    flow.map{ _.addHeader(Pratchett.header) }
  }
}

trait ClacksOverhead extends RespondWithDirectives {
  def withClacks: Directive0 = respondWithHeader(Pratchett.header)
}
