package modules

import services._
import scaldi.Module
import scaldi.play.condition.{inDevMode, inTestMode, inProdMode}

class ServiceModule extends Module {

  bind [MongoService] /*when (inProdMode)*/ to new RealMongoService

  // bind [MongoDatabase] /*when (inDevMode or inTestMode)*/ to new FakeMongoDatabase
}
