package controllers

import bitspoke.play.security.BasicAccessAuth
import com.google.inject.{Inject, Singleton}
import com.mongodb.casbah.Imports._
import play.api.mvc.{Action, Controller}
import services.DatabaseService
import utils.MongoUtils


@Singleton()
class ArticleController @Inject()(db: DatabaseService)
  extends Controller
  with MongoUtils with BasicAccessAuth {

  val articles = db.collection("articles")


  def create = AuthAction(parse_dbObject) { request =>
    val article = request.body
    article += ("date" -> new java.util.Date)
    request.user.foreach (u => article += ("author" -> u.username))

    articles += article
    Ok
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


  def update(id: String) = AuthAction(parse_dbObject) { request =>
    val article = request.body
    article += ("date" -> new java.util.Date)

    articles.findAndModify(Map("_id" -> new ObjectId(id)), article)
    Ok
  }


  def delete(id: String) = Action {
    articles.findAndRemove(Map("_id" -> new ObjectId(id)))
    Ok
  }


  def list = Action { implicit request =>
    render {
      case Accepts.Html() => Ok(views.html.articles())
      case Accepts.Json() => Ok(serialize(articles)).as(JSON) // TODO do not return contents
    }
  }
}
