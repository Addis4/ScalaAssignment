package com.knoldus.inventoryservice

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.knoldus.data._
import com.knoldus.http.HttpConnection._
import com.knoldus.routes.Routes

object Inventory {
  def main(args: Array[String]): Unit = {

    lazy val userRepo: Services = new Services
    lazy val routes: Route = new Routes(userRepo).routes

    Http().bindAndHandle(routes, httpHost, httpPort)
    println("\n\nServer online \t http://localhost:8080/\nPress RETURN to stop...\n\n")

  }
}
