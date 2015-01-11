package bitspoke.play.security

import play.api.mvc.{WrappedRequest, Request}

class AuthRequest[A](request: Request[A], val user: Option[User]) extends WrappedRequest[A](request)
