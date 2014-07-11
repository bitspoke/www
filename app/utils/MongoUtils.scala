package utils

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.{Logging => AnyDBObject}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * NOTE: don't forget to add
 *
 *    ``import com.mongodb.casbah.Imports._``
 *
 * in your controllers!
 */
trait MongoUtils {

  def parse_dbObject = play.api.mvc.BodyParsers.parse.json.map { jsValue =>
    new MongoDBObject(
      com.mongodb.util.JSON.parse(jsValue.toString).asInstanceOf[com.mongodb.DBObject]
    )
  }

  def serialize(dbObj: AnyDBObject) = {
    com.mongodb.util.JSON.serialize(dbObj)
  }

}
