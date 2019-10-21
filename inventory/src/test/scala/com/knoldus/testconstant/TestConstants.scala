package com.knoldus.testconstant

import com.knoldus.connection.DB
import com.knoldus.data.model.Inventory
import com.knoldus.data.services.InventoryServices
import com.knoldus.testconnection.H2Impl

object TestConstants {
  this: DB =>

  val testObj: InventoryServices = new InventoryServices with H2Impl
  val testItem1: Inventory =
    Inventory(9, "Branded Things", "From a branded company most trusted and good working", 3.0, 3500.0, "Shahfahed", (2346584832L), "Electronics")
  val testItem2: Inventory =
    Inventory(1, "Branded Earphones", "From a branded company most trusted and good working", 5.0, 3500.0, "Shahfahed", 2346584832L, "Electronics")
  val testItem3: Inventory =
    Inventory(3, "Branded Shirt", "From a branded company most trusted and good working", 4.0, 500.0, "Unknown", 3462584833L, "Shirts")

}
