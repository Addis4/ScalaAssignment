package com.knoldus.data.services

import com.knoldus.connection.DB
import com.knoldus.data.model.{Inventory, MasterInventory}
import slick.jdbc
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

/**
 * This class holds the services which can be used by user to go through the component
 */
class InventoryServices(implicit ec: ExecutionContext = ExecutionContext.global) {
  this: DB =>
  val itemService: jdbc.MySQLProfile.api.Query[MasterInventory, Inventory, Seq] = TableQuery[MasterInventory]

  /**
   * itemList is method which takes no input.
   *
   * @return it will return the list of items present in inventory.
   */
  def itemList: Future[Seq[Inventory]] = db.run {
    itemService.sortBy(item => item.rating.asc.nullsFirst).result
  }

  /**
   * fetch item is method to search desired item.
   *
   * @param itemCategory is the specification of item that we want to search for.
   * @return it will return the list of items present with the same specification.
   */
  def fetchItem(itemCategory: String): Future[Seq[Inventory]] = db.run {
    itemService.filter(item => item.itemCategory === itemCategory ||
      item.itemName === itemCategory ||
      item.itemDetail === itemCategory).result
  }

  /**
   * addItem method is used to add the item in inventory.
   *
   * @param itemValue is the value which will be added in inventory.
   * @return it will add the provided value in inventory
   */
  def addItem(itemValue: Inventory): Future[Int] = db.run {
    itemService += itemValue
  }

  /**
   * sortPrice is the method to arrange the item list in specified order.
   *
   * @param order it is the order in which user want to arrange the item list.
   * @return will return the list in order you want.
   */
  def sortPrice(order: String): Future[Seq[Inventory]] = db.run {
    if (order == "ascending" || order == "asc" || order == "lowtohigh") {
      itemService.sortBy(_.price.asc.nullsFirst).result
    }
    else if (order == "descending" || order == "desc" || order == "hightolow") {
      itemService.sortBy(_.price.desc.nullsLast).result
    }
    else {
      itemService.sortBy(_.price.asc.nullsFirst).result
    }
  }

  /**
   * sortRating is the method to arrange the item list in specified order.
   *
   * @param order it is the order in which user want to arrange the item list.
   * @return will return the list in order you want.
   */
  def sortRating(order: String): Future[Seq[Inventory]] = db.run {
    if (order == "ascending") {
      itemService.sortBy(_.rating.asc.nullsFirst).result
    }
    else if (order == "descending" || order == "desc" || order == "hightolow") {
      itemService.sortBy(_.price.desc.nullsLast).result
    }
    else {
      itemService.sortBy(_.rating.desc.nullsLast).result
    }
  }

  /**
   * pageLimit is the method to show the no. of items present in the single page.
   *
   * @param limit the limit user want to see
   * @return will give the specified no. of items
   */
  def pageLimit(limit: Int): Future[Seq[Inventory]] = db.run {
    itemService.take(limit).result
  }

}
