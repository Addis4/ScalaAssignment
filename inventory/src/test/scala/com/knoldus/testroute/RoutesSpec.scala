package com.knoldus.testroute

import scala.concurrent.Future

class RoutesSpec extends WordSpec with ScalatestRouteTest with Matchers with JSONSupport with MockitoSugar {

  val mockObj: InventoryServices = mock[InventoryServices]
  val testRoutes: Route = new Routes(mockObj).routes

  "/inventory/insert route" should {
    when(mockObj.addItem(Inventory(3, "Branded Shirt", "From a branded company most trusted and good working",
      4.0, 500.0, "Unknown", 3462584833L, "Shirts"))).thenReturn(Future[Int](1))
    "add items in inventory" in {
      val postRequest =
        ByteString(
          """{"itemNo":3,
            |"itemName":"Branded Shirt",
            |"itemDetail":"From a branded company most trusted and good working",
            |"rating":4.0,
            |"price":500.0,
            |"vendorName":"Unknown",
            |"vendorContact":3462584833,
            |"itemCategory":"Shirts"}""".stripMargin)
      Post("/inventory/insert", HttpEntity(ContentTypes.`application/json`, postRequest)) ~> testRoutes ~> check {
        assert(responseAs[String] === "Item Inserted")
      }
    }
  }

  "/inventory route" should {
    "give list of items in inventory" in {
      when(mockObj.itemList).thenReturn(Future[Seq[Inventory]](Seq(testItem2)))
      Get("/inventory") ~> testRoutes ~> check {
        assert(responseAs[Vector[Inventory]] === Vector(testItem2))
      }
    }
  }

  "/inventory/search/Electronics route" should {
    "give list of items in inventory listed with the search content" in {
      when(mockObj.fetchItem("Electronics")).thenReturn(Future[Seq[Inventory]](Seq(testItem2)))
      Get("/inventory/search/Electronics") ~> testRoutes ~> check {
        assert(responseAs[Vector[Inventory]] === Vector(testItem2))
      }
    }
  }

  "/inventory/sortByPrice/ascending route" should {
    "Sort list of items in inventory listed with the search content" in {
      when(mockObj.sortPrice("ascending")).thenReturn(Future[Seq[Inventory]](Seq(testItem3, testItem2)))
      Get("/inventory/sortByPrice/ascending") ~> testRoutes ~> check {
        assert(responseAs[Vector[Inventory]] == Vector(testItem3, testItem2))
      }
    }
  }

  "/inventory/sortByRating/ascending route" should {
    "Sort list of items in inventory listed with the search content" in {
      when(mockObj.sortRating("ascending")).thenReturn(Future[Seq[Inventory]](Seq(testItem3, testItem2)))
      Get("/inventory/sortByRating/ascending") ~> testRoutes ~> check {
        assert(responseAs[Vector[Inventory]] === Vector(testItem3, testItem2))
      }
    }
  }

  "/inventory/pageLimit/2 route" should {
    "Sort list of items in inventory listed with the search content" in {
      when(mockObj.pageLimit(2)).thenReturn(Future[Seq[Inventory]](Seq(testItem1, testItem2)))
      Get("/inventory/pageLimit/2") ~> testRoutes ~> check {
        assert(responseAs[Vector[Inventory]] === Vector(testItem1, testItem2))
      }
    }
  }

}

