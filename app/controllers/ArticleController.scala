package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.{Json, Writes}
import scaldi.Injector
import scaldi.Injectable.inject
import services.ArticleService
import models.Article

class ArticleController(implicit val i: Injector) extends Controller {

  val service = inject[ArticleService]

  def guiList = Action {
    Ok(views.html.articles())
  }

  def apiList = Action {
    Ok(Json.toJson(service.list))
  }


  implicit val articleWrites = new Writes[Article] {
    def writes(article: Article) = Json.obj(
      "title" -> article.title,
      "author" -> article.author,
      "epoch" -> article.epoch,
      "summary" -> article.summary,
      "content" -> article.content
    )
  }
}
