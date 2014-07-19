package controllers

import com.google.inject.{Inject, Singleton}
import com.mongodb.casbah.Imports._
import play.api.mvc.{Action, Controller}
import services.Database
import utils.MongoUtils


@Singleton()
class ArticleController @Inject()(db: Database) extends Controller with MongoUtils {

  val articles = db.collection("articles")


  def create = Action(parse_dbObject) { request =>
    val article = request.body
    article += ("date" -> new java.util.Date)
    // TODO += ("author" -> request.user)
    articles += article

    Ok(s"""{"_id": "${article("_id")}"}""").as(JSON)
  }


  def read(id: String) = Action { implicit request =>
    render {
      case Accepts.Json() => {
        articles.findOneByID(new ObjectId(id)).map { article =>
          Ok(serialize(article)).as(JSON)
        }.getOrElse(NotFound)
      }
    }
  }


  def list = Action { implicit request =>
    render {
      case Accepts.Html() => Ok(views.html.articles())
      case Accepts.Json() => Ok(serialize(articles)).as(JSON) // TODO do not return contents
    }
  }


  def delete(id: String) = Action {
    articles.findAndRemove(Map("_id" -> new ObjectId(id)))
    Ok
  }
}
