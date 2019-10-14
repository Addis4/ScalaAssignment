package com.knoldus.data.model

import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

class MasterData(tag: Tag) extends Table[Items](tag, "Item") {
  def * : ProvenShape[Items] = (itemNo, itemName, itemDetail, rating, price, vendorName, vendorContact, itemCategory).<>(Items.tupled, Items.unapply)

  def itemNo: Rep[Int] = column[Int]("itemNo", O.PrimaryKey)

  def itemName: Rep[String] = column[String]("itemName")

  def itemDetail: Rep[String] = column[String]("itemDetail")

  def rating: Rep[Double] = column[Double]("rating")

  def price: Rep[Double] = column[Double]("price")

  def vendorName: Rep[String] = column[String]("vendorName")

  def vendorContact: Rep[Long] = column[Long]("vendorContact")

  def itemCategory: Rep[String] = column[String]("itemCategory")
}
