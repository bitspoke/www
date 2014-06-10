package services

import models.Article



trait ArticleService {

  def save(a: Article): Unit

  def list: Iterable[Article]
}




class ArticleMongoService extends ArticleService {
  import services.MongoDatabase.{db, gridfs}
  import com.mongodb.casbah.gridfs.Imports._

  def save(a: Article) = ???

  def list: Iterable[Article] = {
    for (file <- gridfs)
    yield newArticle(file, fetch = false)
  }

  def newArticle(file:GridFSDBFile, fetch:Boolean) = {
    val buffer = new java.io.ByteArrayOutputStream()
    if (fetch) file.writeTo(buffer)

    new Article(
      file.id.toString,// file.as[String]("id"),
      file.as[String]("series"),
      file.getAs[String]("title"), // returns an Option[String]
      file.getAs[String]("summary"), // returns an Option[String]
      file.as[String]("author"),
      0L, /*new DateTime(file.as[Date]("uploadDate")),*/
      new String(buffer.toByteArray)
    )
  }
}




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



