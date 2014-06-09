package modules

import scaldi.Module
import controllers.ArticleController

class WebModule extends Module {

  binding to new ArticleController
}
