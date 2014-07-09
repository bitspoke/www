package controllers

import com.mongodb.casbah.Imports._
import play.api.mvc.{Action, Controller}
import scaldi.Injectable.inject
import scaldi.Injector
import services.MongoService


class ArticleController(implicit val i: Injector) extends Controller with MongoSupport {

  val mongo = inject[MongoService]

  val articles = mongo.db("articles") // collection


  def create = Action(parse_dbObject) { request =>
    articles += (
      request.body
        += ("date" -> new java.util.Date)
        // TODO += ("author" -> request.user)
    )
    Ok
  }


  def read = Action { implicit request =>
    render {
      case Accepts.Html() => Ok(views.html.articles())
      case Accepts.Json() => Ok(serialize(articles)).as(JSON)
    }
  }

}
