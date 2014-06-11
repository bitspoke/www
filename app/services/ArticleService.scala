package services

import models.Article



trait ArticleService {

  def save(a: Article): Unit

  def list: Iterable[Article]
}



import scaldi.Injector
import scaldi.Injectable.inject
import com.mongodb.casbah.Imports._

class ArticleMongoService(implicit val i: Injector) extends ArticleService {
  val mongo = inject[MongoDatabase]
  val articles = mongo.db("articles")

  def save(a: Article) = articles += MongoDBObject (
    "series" -> a.series,
    "title" -> a.title,
    "summary" -> a.summary,
    "author" -> a.author
  )


  def list: Iterable[Article] =
    for (article <- articles) yield newArticle(article)


  def newArticle(dbObj: DBObject) =
    new Article(
      dbObj.as[Any]("_id").toString,
      dbObj.as[String]("series"),
      dbObj.getAs[String]("title"), // returns an Option[String]
      dbObj.getAs[String]("summary"), // returns an Option[String]
      dbObj.as[String]("author"),
      0L, /*new DateTime(article.as[Date]("uploadDate")),*/
      "???"
    )
}



