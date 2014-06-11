package controllers

import play.api.mvc.{Action, Controller}
import scaldi.Injector
import scaldi.Injectable.inject
import services.ArticleService


class ArticleController(implicit val i: Injector) extends Controller {

  val service = inject[ArticleService]

  def list = Action {
    Ok(views.html.articles(service.list))
  }
}
