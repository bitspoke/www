package services

import models.Article

trait ArticleService {
  def list: List[Article]

}


class MongoArticleService extends ArticleService {
  override def list: List[Article] = ???
}


class FakeArticleService extends ArticleService {
  def list: List[Article] = List(
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content"),
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content"),
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content"),
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content"),
    new Article("id", "series", Some("title"), Some("summary"), "paolo", 0L, "content")
  )
}


