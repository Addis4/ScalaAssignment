package com.knoldus.data.model

import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

/**
 * This class is the main data manipulating class of inventory.
 *
 */
class MasterInventory(tag: Tag) extends Table[Inventory](tag, "Item") {
  def * : ProvenShape[Inventory] =
    (itemNo, itemName, itemDetail, rating, price, vendorName, vendorContact, itemCategory).<>(Inventory.tupled, Inventory.unapply)

  def itemNo: Rep[Int] = column[Int]("itemNo", O.PrimaryKey)

  def itemName: Rep[String] = column[String]("itemName")

  def itemDetail: Rep[String] = column[String]("itemDetail")

  def rating: Rep[Double] = column[Double]("rating")

  def price: Rep[Double] = column[Double]("price")

  def vendorName: Rep[String] = column[String]("vendorName")

  def vendorContact: Rep[Long] = column[Long]("vendorContact")

  def itemCategory: Rep[String] = column[String]("itemCategory")
}
