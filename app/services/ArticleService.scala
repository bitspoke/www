package services

import models.Article


trait ArticleService {

  def save(a: Article): Unit

  def list: List[Article]
}



import scaldi.Injector
import scaldi.Injectable._

class MongoArticleService(implicit val i: Injector) extends ArticleService {

  val db = inject[DatabaseService]

  def save(a: Article) = ???

  def list: List[Article] = ???
}


/*
class FakeArticleService extends ArticleService {

  def save(a: Article) = ???

  def list: List[Article] = List(
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content"),
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content"),
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content"),
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content"),
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content")
  )
}
*/


