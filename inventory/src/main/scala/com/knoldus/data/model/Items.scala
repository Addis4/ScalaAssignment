package com.knoldus.data.model

case class Items(
                      itemNo: Int,
                      itemName: String,
                      itemDetail: String,
                      rating: Double,
                      price: Double,
                      vendorName: String,
                      vendorContact: Long,
                      itemCategory: String)
