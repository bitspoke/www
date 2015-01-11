package services

import com.mongodb.casbah.Imports._

trait DatabaseService {
  def collection: MongoDB
}


class MongoDatabaseService extends DatabaseService {
  import play.api.Play.current

  // If "db.default.uri" doesn't exists then it's a bug!
  // Default value MUST be defined in classpath:conf/application.conf
  val conf = current.configuration.getString("db.default.uri").get

  val uri = MongoClientURI(conf)
  val client = MongoClient(uri)
  val collection = client(uri.underlying.getDatabase)
}





