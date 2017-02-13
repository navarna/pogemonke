import java.awt.image.BufferedImage
import scala.swing._

trait Affichable extends Activable[Joueur] {
  val bloc_size: Int = 64;

  // coordonnee
  var coordonneeX: Int = 0;
  var coordonneeY: Int = 0;

  var g: Graphics2D = null
  var bufferedImage: BufferedImage = null

  def afficher(): Unit = {
    println("X=" + coordonneeX + ";Y=" + coordonneeY);
    g.drawImage(bufferedImage, coordonneeX, coordonneeY, null)
  }

  override def estProche(t: T): Boolean = {
    if ((math.abs(coordonneeX - t.coordonneeX) < bloc_size) && (math.abs(coordonneeY - t.coordonneeY) < bloc_size)) {
      return true
    }
    return false;
  }
}