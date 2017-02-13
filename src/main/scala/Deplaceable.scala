trait Deplaceable extends Affichable {

  val pas: Int = 32;

  // permet de deplacer
  def seDeplacer(direction: Int) {
    //  0 == droite
    if (direction == Deplaceable.DROITE) {
      coordonneeX += pas;
    } // 1 == haut
    else if (direction == Deplaceable.HAUT) {
      coordonneeY += pas;
    } // 2 == gauche
    else if (direction == Deplaceable.GAUCHE) {
      coordonneeX -= pas
    } // 3  == bas 
    else if (direction == Deplaceable.BAS) {
      coordonneeY -= pas;
    }
  }
}

object Deplaceable {
  val DROITE = 0
  val HAUT = 1
  val GAUCHE = 2
  val BAS = 3
}