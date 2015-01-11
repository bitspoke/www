package services

import com.mongodb.casbah.Imports._
import com.github.fakemongo.Fongo

class FakeDatabase extends DatabaseService {
  val collection = new MongoDB(new Fongo("fongo").getDB("bitspoke"))
}