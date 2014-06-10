package controllers

import play.api.mvc._
import scaldi.Injector
import scaldi.Injectable._
import services.DatabaseService

class ApplicationController(implicit val injector:Injector) extends Controller {

  val db = inject[DatabaseService]

  def index = Action {
    Ok(views.html.index(db.status))
  }

}