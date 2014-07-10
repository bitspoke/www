package controllers

import com.google.inject.{Inject, Singleton}
import com.mongodb.casbah.Imports._
import play.api.mvc.{Action, Controller}
import services.MongoService

@Singleton()
class ArticleController @Inject()(mongo: MongoService) extends Controller with MongoSupport {

  val articles = mongo.db("articles") // mongo db collection


  def create = Action(parse_dbObject) { request =>
    articles += (
      request.body
        += ("date" -> new java.util.Date)
        // TODO += ("author" -> request.user)
    )
    Ok
  }


  def list = Action { implicit request =>
    render {
      case Accepts.Html() => Ok(views.html.articles())
      case Accepts.Json() => Ok(serialize(articles)).as(JSON)
    }
  }

}
