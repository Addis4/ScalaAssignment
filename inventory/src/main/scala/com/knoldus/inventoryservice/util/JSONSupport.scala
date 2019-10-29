package com.knoldus.inventoryservice.util

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.data.model.Inventory
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JSONSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val itemFormat: RootJsonFormat[Inventory] = jsonFormat8(Inventory)

}
