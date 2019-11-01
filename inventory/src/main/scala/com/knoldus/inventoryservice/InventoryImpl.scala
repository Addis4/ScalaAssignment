package com.knoldus.inventoryservice

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.knoldus.connection.HttpConnection._
import com.knoldus.connection.MySQLImpl
import com.knoldus.connector.Routes
import com.knoldus.services.InventoryServices

object InventoryImpl {
  def main(args: Array[String]): Unit = {

    lazy val userRepo: InventoryServices = new InventoryServices with MySQLImpl
    lazy val routes: Route = new Routes(userRepo).routes

    Http().bindAndHandle(routes, httpHost, httpPort)

  }
}
