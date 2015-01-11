package bitspoke.play.security

trait AuthService {
  def authenticate(username: String, password: String): Option[User]
}


object InMemoryAuthService extends AuthService {
  private var users = List(new User("admin", "admin"))
  def save(u: User) = { users ::= u }
  def authenticate(username: String, password: String): Option[User] =
    users.find(u => u.username == username && u.password == password)
}
