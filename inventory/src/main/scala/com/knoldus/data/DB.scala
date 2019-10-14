package com.knoldus.data

import slick.jdbc.JdbcProfile

trait DB {
  val driver: JdbcProfile = slick.jdbc.MySQLProfile

  import driver.api._

  lazy val db: driver.backend.DatabaseDef = Database.forConfig("mysql")
}

object DB extends DB
