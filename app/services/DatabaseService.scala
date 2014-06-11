package services



trait MongoDatabase {
  import com.mongodb.casbah.MongoDB
  def db: MongoDB
}




class RealMongoDatabase extends MongoDatabase {
  import com.mongodb.casbah.{MongoClientURI, MongoClient}
  import play.api.Play

  val db = MongoClient(
    MongoClientURI(
      Play.current.configuration.getString("db.default.uri").getOrElse("mongodb://localhost")
    )
  )("bitspoke")
}



class FakeMongoDatabase extends MongoDatabase {
  // see https://github.com/fakemongo/fongo

  val db = ???
}



