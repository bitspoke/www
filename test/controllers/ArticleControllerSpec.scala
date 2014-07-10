package controllers

import com.mongodb.casbah.commons.MongoDBObject
import org.specs2.execute.{AsResult, Result}
import org.specs2.matcher.JsonMatchers
import org.specs2.mutable._
import play.api.http.HeaderNames.ACCEPT
import play.api.http.MimeTypes.JSON
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
import services.FakeMongoService

class ArticleControllerSpec extends Specification with JsonMatchers {

  val iso8601 = "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$".r

  val fakeMongo = new FakeMongoService

  val fakeArticles = fakeMongo.db("articles")

  val fakeApp = FakeApplication(withGlobal = Some(new play.api.GlobalSettings() {
    override def getControllerInstance[A](controllerClass: Class[A]) = {
      new ArticleController(fakeMongo).asInstanceOf[A]
    }
  }))

  abstract class WithFakeData(app: FakeApplication) extends WithApplication(app) {
    override def around[T: AsResult](t: => T): Result = {
      fakeArticles.drop()
      fakeArticles += MongoDBObject(
        "title" -> "my test title"
      )
      super.around(t)
    }
  }



  "ArticleController" should {

    "list all articles" in new WithFakeData(fakeApp) {
      val result = route(
        FakeRequest(GET, "/articles")
          .withHeaders(ACCEPT -> JSON)
      ).get

      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == JSON)
      contentAsString(result) must */("title" -> "my test title") and */("date" -> iso8601)
    }


    "create a new article" in new WithFakeData(fakeApp) {
      val result = route(
        FakeRequest(POST, "/articles")
          .withJsonBody(Json.obj(
            "title" -> "new article",
            "author" -> "paolo"))
      ).get

      status(result) must equalTo(OK)
      contentType(result) must beNone
      contentAsString(result) must beEmpty
      fakeArticles must have size(2)
    }


    "read an article by id" in new WithFakeData(fakeApp) {
      val result = route(
        FakeRequest(GET, "/articles/4d190356b9d8ba42efa80898")
          .withHeaders(ACCEPT -> JSON)
      ).get

      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == JSON)
      contentAsString(result) must */("my test title")
    }
  }
}
