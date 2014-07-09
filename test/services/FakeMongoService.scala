package services


class FakeMongoService extends MongoService {
  import com.github.fakemongo.Fongo
  import com.mongodb.casbah.MongoDB

  // see https://github.com/fakemongo/fongo
  val db = new MongoDB (
    new Fongo("mongo server 1").getDB("bitspoke")
  )
}