package services

import com.mongodb.casbah.Imports._

trait Database {
  def collection: MongoDB
}


class RealDatabase extends Database {
  import play.api.Play.current

  val conf = current.configuration.getString("db.default.uri").getOrElse("mongodb://localhost:27017/bitspoke")
  val uri = MongoClientURI(conf)
  val client = MongoClient(uri)
  val collection = client(uri.underlying.getDatabase)
}





