trait Activable[Activator] {
  type T = Activator;
  def estProche(t: T): Boolean = {
    return false;
  }

  def activer(t: T): Unit = {
    println(this)
    println("a été activé")
  }
}