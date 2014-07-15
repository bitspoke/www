import java.io.{StringWriter, PrintWriter}

import com.google.inject.Guice
import com.tzavellas.sse.guice.ScalaModule
import play.api.GlobalSettings
import play.api.mvc.RequestHeader
import play.api.mvc.Results.InternalServerError
import services.{Database, RealDatabase}

import scala.concurrent.Future

object Global extends GlobalSettings {

  val injector = Guice.createInjector(new ScalaModule {
    def configure(): Unit = {
      bind[Database].to[RealDatabase]
    }
  })

  /**
   * Controllers must be resolved through the application context.
   * There is a special method of GlobalSettings that we can override to resolve
   * a given controller. This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A =
    injector.getInstance(controllerClass)


  override def onError(request: RequestHeader, ex: Throwable) =
    Future.successful(
      InternalServerError(ex.getMessage)
    )
}