package services

import com.mongodb.casbah.{MongoClientURI, MongoClient}
import com.mongodb.casbah.gridfs.Imports._
import play.api.Play
import models.Database

object MongoDatabase {

  val client = MongoClient(
    MongoClientURI(
      Play.current.configuration.getString("db.default.uri").getOrElse("mongodb://localhost")
    )
  )

  val db = client("bitspoke")

  val gridfs = GridFS(db)

  def status =
    new Database(
      "MongoDB",
      client.version,
      client.address.toString,
      true)
}





