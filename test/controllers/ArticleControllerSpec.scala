package controllers


import com.mongodb.casbah.commons.Imports._
import org.specs2.execute.{AsResult, Result}
import org.specs2.matcher.JsonMatchers
import org.specs2.mutable._
import play.api.http.HeaderNames.ACCEPT
import play.api.http.MimeTypes.JSON
import play.api.libs.json.{JsArray, Json}
import play.api.test.Helpers._
import play.api.test._
import services.FakeDatabase
import utils.TestUtils

class ArticleControllerSpec extends Specification with JsonMatchers with TestUtils {

  "ArticleController" should {

    "list all articles" in withApplication {
      val result = route(
        FakeRequest(GET, "/articles")
          .withHeaders(ACCEPT -> JSON)
      ).get

      status(result) must beEqualTo(OK)
      contentType(result) must beSome(JSON)
      val jsonVal = contentAsJson(result)
      jsonVal must haveClass[JsArray]
      jsonVal.asInstanceOf[JsArray].value must haveSize(2)
      val jsonStr = contentAsString(result)
      jsonStr must /#(0) /("_id") /("$oid" -> "53bf100e3004eb66c3f7e5fe")
      jsonStr must /#(0) /("title" -> "Christmas Programming")
      jsonStr must /#(0) /("author" -> "paolo")
      jsonStr must /#(0) /("date") /("$date" -> "2012-12-25T23:40:56.123Z")
      jsonStr must /#(1) /("_id") /("$oid" -> "53bc77c63004d90c724e91d1")
      jsonStr must /#(1) /("title" -> "Beauty of Life")
      jsonStr must /#(1) /("author" -> "nicholas")
      jsonStr must /#(1) /("date") /("$date" -> "2012-02-09T09:36:33.987Z")
      // TODO jsonStr must /#(1) /("content" -> "???")
    }



    "create a new article" in withApplication {

      val author = "giuseppe"
      val title = "Smart developers"
      val summary = "HIUwbiBOAEA2CWBrE0AuB7aB3S9Uo0wFsBXAYwAtoz0ATEIA"
      val content = "HIUwbiBOAEA2CWBrEBnaAXA9tA7pe6IGm2AtgK4DGAFtJZgCYhAAAA=="
      
      val result = route(
        FakeRequest(POST, "/articles")
          .withJsonBody(Json.obj(
            "author" -> author,
            "title" -> title,
            "summary" -> summary,
            "content" -> content
        ))
      ).get

      status(result) must beEqualTo(OK)
      contentType(result) must beSome(JSON)
      contentAsString(result) must /("_id" -> sha1)
      fakeDbArticles must have size(3)

      val article = fakeDbArticles.findOne(Map("title" -> title))
      article must beSome
      val jsonStr = article.get.toString
      jsonStr must /("author" -> author)
      jsonStr must /("title" -> title)
      jsonStr must /("date") /("$date" -> iso8601)
      jsonStr must /("summary" -> summary)
      jsonStr must /("content" -> content)
    }


    "read an article by id" in {

      "resulting Ok when it exists" in withApplication {
        val result = route(
          FakeRequest(GET, "/articles/53bc77c63004d90c724e91d1")
            .withHeaders(ACCEPT -> JSON)
        ).get

        status(result) must beEqualTo(OK)
        contentType(result) must beSome(JSON)
        val jsonStr = contentAsString(result)
        jsonStr must /("_id") /("$oid" -> "53bc77c63004d90c724e91d1")
        jsonStr must /("title" -> "Beauty of Life")
        jsonStr must /("author" -> "nicholas")
        jsonStr must /("date") /("$date" -> "2012-02-09T09:36:33.987Z")
        // TODO json must /("content" -> "???")
      }

      "resulting NotFound when it doesn't" in withApplication {
        val result = route(
          FakeRequest(GET, "/articles/507c7f79bcf86cd7994f6c0e")
            .withHeaders(ACCEPT -> JSON)
        ).get

        status(result) must beEqualTo(NOT_FOUND)
        contentType(result) must beNone
        contentAsString(result) must beEmpty
      }
    }

    "update an article" in withApplication {
      todo
    }

    "delete an article" in withApplication {
      todo
    }
  }


  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  val fakeDb = new FakeDatabase

  val fakeDbArticles = fakeDb.collection("articles")

  val fakeApp = FakeApplication(withGlobal = Some(new play.api.GlobalSettings() {
    override def getControllerInstance[A](controllerClass: Class[A]) = {
      new ArticleController(fakeDb).asInstanceOf[A]
    }
  }))

  val withApplication = new WithApplication(fakeApp) {
    override def around[T: AsResult](t: => T): Result = {
      fakeDbArticles += Map(
        "_id" -> objectId("53bf100e3004eb66c3f7e5fe"),
        "title" -> "Christmas Programming",
        "author" -> "paolo",
        "date" -> date("2012-12-25T23:40:56.123Z")
        // TODO "content" -> "???"
      )
      fakeDbArticles += Map(
        "_id" -> objectId("53bc77c63004d90c724e91d1"),
        "title" -> "Beauty of Life",
        "author" -> "nicholas",
        "date" -> date("2012-02-09T09:36:33.987Z")
        // TODO "content" -> "???"
      )
      super.around(t)
    }
  }

  // TODO use specs2 fixtures
}
