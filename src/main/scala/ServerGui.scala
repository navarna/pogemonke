import scala.swing._
import java.awt.{ Graphics2D, Color }
import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }

import java.awt.image.BufferedImage
import java.io.FileInputStream
import javax.imageio.ImageIO

class ServerGui extends MainFrame {
  title = "Pogemonke Server"
  preferredSize = new Dimension(640, 480)

  val log = new TextArea(48, 64)
  log.editable = false

  // Joueur Number
  var i = 1
  var pokedex = MapGenerator.generatePokedex()

  // Ajout de ServerActor pour MMO
  val system = ActorSystem("pogomonke")
  val server = system.actorOf(Props(new ServerActor(system)), name = "server")
  val inbox = Inbox.create(system)

  contents = new BorderPanel {
    border = Swing.MatteBorder(8, 8, 8, 8, Color.white)
    add(log, BorderPanel.Position.Center)
    val buttons = new BoxPanel(Orientation.Horizontal) {
      border = Swing.EmptyBorder(8, 0, 0, 0)
      background = Color.white
      contents += Swing.HGlue
      contents += Button("New Player") {
        addNewPlayer()
      }
      contents += Swing.HGlue
      contents += Button("Quit") { System.exit(0) }
    }
    add(buttons, BorderPanel.Position.South)
  }

  def addNewPlayer(): Unit = {
    val gameLoop = new Thread(new Runnable {
      def run() {
        val nom = "Sasha" + i
        i += 1
        // Instanciation Joueur et Sac
        var joueur = new Joueur(nom, 1, 0.0f, 0.0f) {
          bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/red.png"))
        }

        val ui = new GameUI(joueur, pokedex)
        ui.visible = true
        ui.server = server

        println("adding new player " + ui.joueur.nom)
        // add the client to the server
        val client = system.actorOf(Props(new ClientActor(ui.carte, ui.joueur.nom)), name = ui.joueur.nom)
        inbox.send(server, NewGame(ui.joueur.nom))
        system.eventStream.subscribe(client, UpdatePositions.getClass)
      }
    })
    gameLoop.start()
  }
}

object ServerGui {
  def main(args: Array[String]) {
    val ui = new ServerGui
    ui.visible = true
  }
}