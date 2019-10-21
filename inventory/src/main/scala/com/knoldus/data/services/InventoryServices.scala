package com.knoldus.data.services

import com.knoldus.connection.DB
import com.knoldus.data.model.{ Inventory, MasterInventory }
import slick.jdbc
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ ExecutionContext, Future }

class InventoryServices(implicit ec: ExecutionContext = ExecutionContext.global) {
  this: DB =>
  val itemService: jdbc.MySQLProfile.api.Query[MasterInventory, Inventory, Seq] = TableQuery[MasterInventory]

  def itemList: Future[Seq[Inventory]] = db.run {
    itemService.sortBy(item => item.rating.asc.nullsFirst).result
  }

  def fetchItem(itemCategory: String): Future[Seq[Inventory]] = db.run {
    itemService.filter(item => item.itemCategory === itemCategory ||
      item.itemName === itemCategory ||
      item.itemDetail === itemCategory).result
  }

  def addItem(user: Inventory): Future[Int] = db.run {
    itemService += user
  }

  def sortPrice(order: String): Future[Seq[Inventory]] = db.run {
    if (order == "ascending") {
      itemService.sortBy(_.price.asc.nullsFirst).result
    } else {
      itemService.sortBy(_.price.desc.nullsLast).result
    }
  }

  def sortRating(order: String): Future[Seq[Inventory]] = db.run {
    if (order == "ascending") {
      itemService.sortBy(_.rating.asc.nullsFirst).result
    } else {
      itemService.sortBy(_.rating.desc.nullsLast).result
    }
  }

  def pageLimit(limit: Int): Future[Seq[Inventory]] = db.run {
    itemService.take(limit).result
  }

}
