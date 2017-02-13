import java.awt.{ Graphics2D, Color }
import scala.swing._
import scala.swing.event._

import java.awt.image.BufferedImage
import java.io.FileInputStream
import javax.imageio.ImageIO

import scala.collection.mutable.ListBuffer

case class CloseElementEvent(o: Activable[Joueur]) extends Event
case object NoElementEvent extends Event
case object PlayerMoved extends Event

class MapCanvas(val joueur: Joueur) extends Component {
  var bg: BufferedImage = ImageIO.read(new FileInputStream("src/main/scala/ground.png"))

  var affichables = new ListBuffer[Affichable]();
  var interractionPossible: Boolean = false

  listenTo(mouse.clicks)
  reactions += {
    case MouseClicked(_, p, _, _, _) => mouseClick(p.x, p.y)
  }
  override def paintComponent(g: Graphics2D) {
    publish(NoElementEvent)
    interractionPossible = false
    g.drawImage(bg, 0, 0, null)

    for (affichable <- affichables) {
      affichable.g = g
      affichable.afficher()
      if (affichable != joueur && affichable.estProche(joueur) && !interractionPossible) {
        publish(new CloseElementEvent(affichable))
      }
    }
    
    g.drawString("You!", joueur.coordonneeX, joueur.coordonneeY)
  }
  private def mouseClick(x: Int, y: Int) {
    if (joueur.coordonneeX > x) {
      joueur.seDeplacer(Deplaceable.GAUCHE)
    }
    if (joueur.coordonneeX < x) {
      joueur.seDeplacer(Deplaceable.DROITE)
    }

    if (joueur.coordonneeY > y) {
      joueur.seDeplacer(Deplaceable.BAS)
    }
    if (joueur.coordonneeY < y) {
      joueur.seDeplacer(Deplaceable.HAUT)
    }
    this.repaint()
    publish(PlayerMoved)
  }
}