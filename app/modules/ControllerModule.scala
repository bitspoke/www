package modules

import scaldi.Module
import controllers.{ApplicationController, ArticleController}

class ControllerModule extends Module {

  binding to new ApplicationController

  binding to new ArticleController
}
