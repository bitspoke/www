package modules

import services._
import scaldi.Module
import scaldi.play.condition.{inDevMode, inTestMode, inProdMode}

class ServiceModule extends Module {

  binding to new MongoArticleService

  bind [DatabaseService] /*when (inProdMode)*/ to new MongoDatabaseService

  // bind [DatabaseService] when (inDevMode or inTestMode) to new InMemoryDatabaseService
}
