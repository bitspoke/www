package services

class FakeMongoService extends MongoService {
  val db = new com.mongodb.casbah.MongoDB (
    // see https://github.com/fakemongo/fongo
    new com.github.fakemongo.Fongo("fongo").getDB("bitspoke")
  )
}