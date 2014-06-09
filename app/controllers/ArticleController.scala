package controllers

import play.api.mvc.{Action, Controller}
import scaldi.{Injectable, Injector}
import services.ArticleService


class ArticleController(implicit val injector:Injector) extends Controller with Injectable {

  val service = inject[ArticleService]

  def list = Action {
    Ok(views.html.articles(service.list))
  }
}
