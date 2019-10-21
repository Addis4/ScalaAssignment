package com.knoldus.connection

import slick.jdbc.JdbcProfile

trait DB {
  val driver: JdbcProfile

  val db: driver.backend.DatabaseDef
}
