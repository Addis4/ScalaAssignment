package com.knoldus.testroute

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.ActorMaterializer
import akka.util.ByteString
import com.knoldus.data.model.Inventory
import com.knoldus.inventoryservice.util.JSONParser
import com.knoldus.routes.Routes
import com.knoldus.testconstant.TestConstants._
import org.scalatest.WordSpec

import scala.concurrent.ExecutionContextExecutor

class RoutesSpec extends WordSpec with ScalatestRouteTest with JSONParser {

  implicit lazy val testSystem: ActorSystem = ActorSystem()
  implicit lazy val testMaterializer: ActorMaterializer = ActorMaterializer()
  implicit val dispatcher: ExecutionContextExecutor = system.dispatcher
  val testRoutes: Route = new Routes(testObj).routes
  testObj.addItem(testItem2)
  testObj.addItem(testItem3)
  "/inventory/insert route" should {
    "add items in inventory" in {
      val postRequest = ByteString("""{"itemNo":3,"itemName":"Branded Shirt","itemDetail":"From a branded company most trusted and good working","rating":4.0,"price":500.0,"vendorName":"Unknown","vendorContact":3462584833,"itemCategory":"Shirts"}""")
      Post("/inventory/insert", HttpEntity(ContentTypes.`application/json`, postRequest)) ~> testRoutes ~> check {
        responseAs[String] === "Item Inserted"
      }
    }
  }
  "/inventory route" should {
    "give list of items in inventory" in {
      Get("/inventory") ~> testRoutes ~> check {
        responseAs[Vector[Inventory]] === Vector(testItem2, testItem3)
      }
    }
  }
  "/inventory/search/Electronics route" should {
    "give list of items in inventory listed with the search content" in {
      Get("/inventory/search/Electronics") ~> testRoutes ~> check {
        responseAs[Vector[Inventory]] === Vector(testItem2)
      }
    }
  }
  "/inventory/sortByPrice/ascending route" should {
    "Sort list of items in inventory listed with the search content" in {
      Get("/inventory/sortByPrice/ascending") ~> testRoutes ~> check {
        responseAs[Vector[Inventory]] === Vector(testItem3, testItem2)
      }
    }
  }
  "/inventory/sortByRating/ascending route" should {
    "Sort list of items in inventory listed with the search content" in {
      Get("/inventory/sortByRating/ascending") ~> testRoutes ~> check {
        responseAs[Vector[Inventory]] === Vector(testItem3, testItem2)
      }
    }
  }
  "/inventory/pageLimit/2 route" should {
    "Sort list of items in inventory listed with the search content" in {
      Get("/inventory/pageLimit/2") ~> testRoutes ~> check {
        testObj.addItem(testItem1)
        responseAs[Vector[Inventory]] === Vector(testItem1, testItem2)
      }
    }
  }
}
