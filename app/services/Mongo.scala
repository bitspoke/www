package services



trait Mongo {
  import com.mongodb.casbah.MongoDB
  def db: MongoDB
}




class RealMongo extends Mongo {
  import com.mongodb.casbah.{MongoClientURI, MongoClient}
  import play.api.Play

  val db = MongoClient(
    MongoClientURI(
      Play.current.configuration.getString("db.default.uri").getOrElse("mongodb://localhost")
    )
  )("bitspoke")
}





