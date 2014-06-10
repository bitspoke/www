package services

import models.Database

trait DatabaseService {

  def status: Database

}


class InMemoryDatabaseService extends DatabaseService {

  def status: Database =
    new Database(
      "InMemory",
      "n.a.",
      "n.a.",
      true
    )
}



import com.mongodb.casbah.{MongoClientURI, MongoClient}
import play.api.Play

class MongoDatabaseService extends DatabaseService {

  val client = MongoClient(
    MongoClientURI(
      Play.current.configuration.getString("db.default.uri").getOrElse("mongodb://localhost")
    )
  )

  def status =
    new Database(
      "MongoDB",
      client.version,
      client.address.toString,
      true)
}





