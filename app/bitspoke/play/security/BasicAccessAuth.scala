package bitspoke.play.security

import play.api.mvc._
import scala.concurrent.Future



trait BasicAccessAuth {

  val authService = InMemoryAuthService


  object AuthAction extends ActionBuilder[AuthRequest] {
    def invokeBlock[A](request: Request[A], block: (AuthRequest[A]) => Future[Result]): Future[Result] =
      authenticate(request).map { user =>
        block(new AuthRequest(request, Some(user)))
      }.getOrElse{
        Future.successful(challenge())
      }


    def authenticate[A](request: Request[A]) : Option[User] = {
      for {
        authorization <- request.headers.get("Authorization")
        credentials <- BasicAuthorization.unapply(authorization)
        (username, password) <- BasicCredentials.unapply(credentials)
        user <- authService.authenticate(username, password)
      } yield {
        user
      }
    }


    // extract Authorization header (e.g. "Basic dW5rb3duOmludmFsaWQ=")
    object BasicAuthorization {
      def unapply(authorization:String) : Option[String] = {
        val parts = authorization.split(" ")
        if (parts.length == 2 && parts(0) == "Basic") Some(parts(1)) else None
      }
    }


    // extract and decode "username:password"
    object BasicCredentials {
      val decoder = new sun.misc.BASE64Decoder()
      def unapply (credentials:String) : Option[(String, String)] = {
        val parts = new String(decoder.decodeBuffer(credentials)).split(":")
        if (parts.length == 2) Some(parts(0), parts(1)) else None
      }
    }



    import play.api.mvc.Results.Unauthorized
    def challenge() : Result =
      // TODO realm name should be configurable
      Unauthorized.withHeaders("WWW-Authenticate" -> """Basic realm="myRealm" """)
  }
}
