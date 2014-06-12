package modules

import services._
import scaldi.Module
import scaldi.play.condition.{inDevMode, inTestMode, inProdMode}

class ServiceModule extends Module {

  binding to new MongoArticleService

  // binding to new CommentMongoService


  bind [ArticleService] /*when (inProdMode)*/ to new MongoArticleService

  // bind [ArticleService] /*when (inDevMode or inTestMode)*/ to new FakeArticleService


  bind [Mongo] /*when (inProdMode)*/ to new RealMongo

  // bind [MongoDatabase] /*when (inDevMode or inTestMode)*/ to new FakeMongoDatabase
}
