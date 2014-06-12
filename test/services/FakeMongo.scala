package services


class FakeMongo extends Mongo {
  import com.github.fakemongo.Fongo
  import com.mongodb.casbah.MongoDB

  // see https://github.com/fakemongo/fongo
  val db = new MongoDB (
    new Fongo("mongo server 1").getDB("bitspoke")
  )
}