package services

import bitspoke.play.security.{User, AuthService}

class ConfigAuthService extends AuthService {

  override def authenticate(username: String, password: String): Option[User] = None
  
}
