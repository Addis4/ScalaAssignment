package com.knoldus.inventoryservice

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.knoldus.connection.HttpConnection._
import com.knoldus.connection.MySQLImpl
import com.knoldus.data.services.InventoryServices
import com.knoldus.routes.Routes

object InventoryImpl {
  def main(args: Array[String]): Unit = {

    lazy val userRepo: InventoryServices = new InventoryServices with MySQLImpl
    lazy val routes: Route = new Routes(userRepo).routes

    Http().bindAndHandle(routes, httpHost, httpPort)

  }
}
