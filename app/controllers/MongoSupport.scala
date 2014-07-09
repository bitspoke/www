package controllers

import com.mongodb.casbah.Imports._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * NOTE: don't forget to add
 *
 *    ``import com.mongodb.casbah.Imports._``
 *
 * in your controller!
 */
trait MongoSupport {

  def parse_dbObject = play.api.mvc.BodyParsers.parse.json.map { jsValue =>
    new MongoDBObject(
      com.mongodb.util.JSON.parse(jsValue.toString).asInstanceOf[com.mongodb.DBObject]
    )
  }

  def serialize(dbObj:MongoCollection) = {
    com.mongodb.util.JSON.serialize(dbObj)
  }

}
