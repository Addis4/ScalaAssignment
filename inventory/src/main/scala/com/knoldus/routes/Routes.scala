package com.knoldus.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.data.model.Inventory
import com.knoldus.data.services.InventoryServices
import com.knoldus.inventoryservice.util.JSONParser

import scala.concurrent.ExecutionContext

/**
 * Routes is class which contain details of all routes to be used
 *
 * @param repo is to connect with InventoryServices methods,
 * @param ex   is execution context
 */
class Routes(repo: InventoryServices)(implicit ex: ExecutionContext)
  extends JSONParser {

  val routes: Route =
    get {
      path("inventory") {
        complete(repo.itemList)
      }
    } ~
      get {
        pathPrefix("inventory" / "search" / Segment) { value =>
          complete(repo.fetchItem(value))
        }
      } ~
      get {
        pathPrefix("inventory" / "sortByPrice" / Segment) { order =>
          complete(repo.sortPrice(order))
        }
      } ~
      get {
        pathPrefix("inventory" / "sortByRating" / Segment) { order =>
          complete(repo.sortRating(order))
        }
      } ~
      get {
        pathPrefix("inventory" / "pageLimit" / IntNumber) { limit =>
          complete(repo.pageLimit(limit))
        }
      } ~
      post {
        path("inventory" / "insert") {
          entity(as[Inventory]) { item =>
            val stored = repo.addItem(item)
            onComplete(stored) {
              done => complete("Item Inserted")
            }
          }
        }
      }
}
