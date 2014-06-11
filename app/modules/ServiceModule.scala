package modules

import services._
import scaldi.Module
import scaldi.play.condition.{inDevMode, inTestMode, inProdMode}

class ServiceModule extends Module {

  binding to new ArticleMongoService

  // binding to new CommentMongoService


  bind [ArticleService] /*when (inProdMode)*/ to new ArticleMongoService

  // bind [ArticleService] /*when (inDevMode or inTestMode)*/ to new FakeArticleService


  bind [MongoDatabase] /*when (inProdMode)*/ to new RealMongoDatabase

  // bind [MongoDatabase] /*when (inDevMode or inTestMode)*/ to new FakeMongoDatabase
}
