package modules

import scaldi.Module
import scaldi.play.condition.{inDevMode, inTestMode, inProdMode}
import services.{FakeArticleService, ArticleService}

class UserModule extends Module {
  bind [ArticleService] when (inDevMode or inTestMode) to new FakeArticleService
  bind [ArticleService] when inProdMode to new FakeArticleService
}
