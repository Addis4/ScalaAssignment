package com.knoldus.data

import com.knoldus.data.model.{MasterData, Items}
import slick.jdbc
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class Services(implicit ec: ExecutionContext) extends DB {
  val item: jdbc.MySQLProfile.api.Query[MasterData, Items, Seq] = TableQuery[MasterData]

  def itemList: Future[Seq[Items]] = db.run {
    item.sortBy(x => x.rating.asc.nullsFirst).result
  }

  def fetchItem(itemCategory: String): Future[Seq[Items]] = db.run {
    item.filter(x => x.itemCategory === itemCategory).result
  }

  def addItem(user: Items): Future[Int] = db.run {
    item += user
  }

  def sortPrice(order: String): Future[Seq[Items]] = db.run {
    order match {
      case ascending || asc => item.sortBy(_.price.asc.nullsFirst).result
      case descending || desc => item.sortBy(_.price.desc.nullsLast).result
    }
  }

  def sortRating(order: String): Future[Seq[Items]] = db.run {
    order match {
      case ascending || asc => item.sortBy(_.rating.asc.nullsFirst).result
      case descending || desc => item.sortBy(_.rating.desc.nullsLast).result
    }
  }

  def pagignate(limit: Int): Future[Seq[Items]] = db.run {
    item.take(limit).result
  }
}
