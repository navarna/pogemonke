import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import java.awt.image.BufferedImage
import java.io.FileInputStream
import javax.imageio.ImageIO

class ClientActor(carte: MapCanvas, joueurName: String) extends Actor {
  def receive = {
    case UpdatePositions => {
      carte.repaint()
      println("moving a player done")
    }
    case ShowAllMapElement(affichables) => {
      carte.affichables = affichables
      carte.affichables += carte.joueur
      carte.repaint()
      println("client initialisation done")
    }
  }
}