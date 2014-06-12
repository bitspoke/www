package services

import models.Article


trait ArticleService {

  def save(a: Article): Unit

  def list: Iterable[Article]
}



import scaldi.Injector
import scaldi.Injectable.inject
import com.mongodb.casbah.Imports._

class MongoArticleService(implicit val injector: Injector) extends ArticleService {
  val mongo = inject[Mongo]

  def save(a: Article) = {
    val obj = MongoDBObject (      
      "title" -> a.title,
      "author" -> a.author,      
      "epoch" -> a.epoch,
      "summary" -> a.summary,
      "content" -> a.content
    )
    if (a.oid.isDefined) obj.put("_oid", new ObjectId(a.oid.get))
    (mongo.db("articles") += obj)
  }


  def list: Iterable[Article] =
    for (obj <- mongo.db("articles"))
    yield
      new Article (
        obj.getAs[Any]("_id").map(_.toString), // TODO shouldn't it be as[String] ???        
        obj.as[String]("author"),
        obj.as[String]("title"),
        obj.as[Number]("epoch").longValue(),
        obj.as[String]("summary"),
        obj.as[String]("content")
      )
}



