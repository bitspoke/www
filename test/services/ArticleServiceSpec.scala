package services

import org.specs2.mutable._
import com.mongodb.casbah.Imports._
import modules.TestModule


trait ArticleServiceInjector {
  implicit val injector =  new TestModule
  val service = new MongoArticleService
}


class ArticleServiceSpec extends Specification with ArticleServiceInjector {

  "ArticleServiceSpec".title

  args(sequential = true)

  "This is a 'unit specification by example' for the ArticleService component".txt

  step {
    // initialize the database
    service.mongo.db("articles") += MongoDBObject(
      "author" -> "author",
      "title" -> "title",
      "epoch" -> 0,
      "summary" -> "summary",
      "content" -> "content"
    )
    success
  }

  "The ArticleService" should {

    "fetch all articles when listing" in {
      service.list must have size(1)
    }

    "read article properties when listing" in {
      val a = service.list.head
      a.oid must beSome and a.author === "author" and a.title === "title" and a.epoch === 0L and a.summary === "summary" and a.content === "content"
    }
  }
}


/*
import modules.{ServiceModule, ControllerModule, TestModule}
import play.api.GlobalSettings
import scaldi.play.ScaldiSupport

object TestGlobal extends GlobalSettings with ScaldiSupport {
  def applicationModule = new TestModule :: new ControllerModule :: new ServiceModule
}*/

