package services



trait MongoService {
  def db: com.mongodb.casbah.MongoDB
}



class RealMongoService extends MongoService {
  import com.mongodb.casbah.{MongoClientURI, MongoClient}
  import play.api.Play

  val uri = MongoClientURI(Play.current.configuration.getString("db.default.uri").getOrElse("mongodb://localhost/bitspoke"))
  val client = MongoClient(uri)
  val db = client(uri.underlying.getDatabase)
}





