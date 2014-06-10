import modules.{ControllerModule, ServiceModule}
import play.api.GlobalSettings
import scaldi.play.ScaldiSupport

object Global extends GlobalSettings with ScaldiSupport {
  def applicationModule = new ControllerModule :: new ServiceModule
}