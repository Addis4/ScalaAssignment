package com.knoldus.testservice

import com.knoldus.testconstant.TestConstants._
import org.scalatest.AsyncWordSpec
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{ Millis, Seconds, Span }

class InventoryServicesSpec extends AsyncWordSpec with ScalaFutures {

  implicit def defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  testObj.addItem(testItem2)
  testObj.addItem(testItem3)
  "Add New Item " should {
    "add the items" in {
      val response = testObj.addItem(testItem1)
      whenReady(response) { y =>
        assert(y === 1)
      }
    }
  }

  "Get Item list" should {
    "get the items" in {
      val itemList = testObj.itemList
      whenReady(itemList) { result =>
        assert(result.head == testItem1)
      }
    }
  }

  "fetchItem" should {
    "give the items when present" in {
      val itemList = testObj.fetchItem("Electronics")
      whenReady(itemList) { result =>
        assert(result.head == testItem2)
      }
    }
    "be empty if item not present " in {
      val itemList = testObj.fetchItem("Shoes")
      whenReady(itemList) { result =>
        assert(result.isEmpty)
      }
    }
  }

  "sortByPrice" should {
    "sort in ascending order" in {
      val itemList = testObj.sortPrice("ascending")
      whenReady(itemList) { result =>
        assert(result == Vector(testItem3, testItem2, testItem1))
      }
    }
    "sort in descending order" in {
      val itemList = testObj.sortPrice("descending")
      whenReady(itemList) { result =>
        assert(result == Vector(testItem2, testItem1, testItem3))
      }
    }
  }
  "sortByRating" should {
    "sort in ascending order" in {
      val itemList = testObj.sortRating("ascending")
      whenReady(itemList) { result =>
        assert(result == Vector(testItem1, testItem3, testItem2))
      }
    }
    "sort in descending order" in {
      val itemList = testObj.sortRating("descending")
      whenReady(itemList) { result =>
        assert(result == Vector(testItem2, testItem3, testItem1))
      }
    }
  }
  "pagignate" should {
    "give no. of rows we want" in {
      val itemList = testObj.pageLimit(2)
      whenReady(itemList) { result =>
        assert(result == List(testItem2, testItem3))
      }
    }
  }
}
