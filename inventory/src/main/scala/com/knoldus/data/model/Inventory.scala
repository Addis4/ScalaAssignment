package com.knoldus.data.model

/**
 * Inventory case class is there to give a represent the model of our inventory
 *
 * @param itemNo        is the unique identity of each item in inventory.
 * @param itemName      is the particular name of item.
 * @param itemDetail    contains the detail and specification of items.
 * @param rating        is to be sure to use popular component.
 * @param price         is the amount of the item.
 * @param vendorName    shows the name of person who is going to sell it to you
 * @param vendorContact is to contact vendor so the communication gap should never be the problem.
 * @param itemCategory  is the category of item which it belongs.
 */
case class Inventory(
  itemNo: Int,
  itemName: String,
  itemDetail: String,
  rating: Double,
  price: Double,
  vendorName: String,
  vendorContact: Long,
  itemCategory: String)

