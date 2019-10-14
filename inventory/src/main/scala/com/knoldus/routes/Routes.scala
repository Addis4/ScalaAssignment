package com.knoldus.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.data._
import com.knoldus.data.model.Items
import com.knoldus.inventoryservice.util.JSONParser

import scala.concurrent.ExecutionContext

class Routes(repo: Services)(implicit ex: ExecutionContext)
  extends JSONParser {

  val routes: Route =
    get {
      path("inventory") {
        println("Get Route hit")
        complete(repo.itemList)
      }
    } ~
      get {
        pathPrefix("searchByCategory" / Segment) { category =>
          println("Get Route hit")
          complete(repo.fetchItem(category))
        }
      } ~
      get {
        pathPrefix("sortByPrice" / Segment) { order =>
          println("Get Route hit")
          complete(repo.sortPrice(order))
        }
      } ~
      get {
        pathPrefix("sortByPrice" / Segment) { order =>
          println("Get Route hit")
          complete(repo.sortRating(order))
        }
      } ~
      get {
        pathPrefix("pagignate" / IntNumber) { limit =>
          println("Get Route hit")
          complete(repo.pagignate(limit))
        }
      } ~
      post {
        path("insert") {
          println("Route hit")
          entity(as[Items]) { item =>
            val stored = repo.addItem(item)
            onComplete(stored) {
              done => complete("Item Inserted")
            }
          }
        }
      }
}
