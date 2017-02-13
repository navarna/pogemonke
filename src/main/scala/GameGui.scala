import scala.swing._
import java.awt.{ Graphics2D, Color }
import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }

import java.awt.image.BufferedImage
import java.io.FileInputStream
import javax.imageio.ImageIO

class GameUI(val joueur: Joueur,poke:PokedexInMemory[String, Pokemon] ) extends Frame {
  var server: ActorRef = null;

  title = "Pogemonke"
  preferredSize = new Dimension(1024, 768)

  // interaction
  var focus: Activable[Joueur] = null
  var interactionButton: Button = Button("Interagir") { // Fonction interaction avec environnement 
    interract()
  }
  // Fin interaction

  // Instanciation Sac
  joueur.ajouterPokemon(Usine_a_pokemon.generate_pokemon(Constantes_pokemon.BULBIZARRE))
  joueur.ajouterPokemon(Usine_a_pokemon.generate_pokemon(Constantes_pokemon.SALAMECHE))
  joueur.ajouterPokemon(Usine_a_pokemon.generate_pokemon(Constantes_pokemon.CARAPUCE))
  joueur.ajouterPokemon(Usine_a_pokemon.generate_pokemon(Constantes_pokemon.TORTANK))
  joueur.ajouterPokemon(Usine_a_pokemon.generate_pokemon(Constantes_pokemon.RONFLEX))

  // Fin instanciation Joueur et Sac

  var carte = new MapCanvas(joueur)
  // Ajout de ClientActor pour MMO
  val system = ActorSystem("pogomonkeclient")
  val inbox = Inbox.create(system)

  // Listen to Close element
  listenTo(carte)
  reactions += {
    case CloseElementEvent(focused) => {
      focus = focused
      println("Lieu activable")
    }
    case NoElementEvent => {
      focus = null
    }
    case PlayerMoved => {
      println(joueur.nom + " Moved")
      inbox.send(server, UpdatePosition)
    }
  }

  // Fin Listen

  contents = new BorderPanel {
    border = Swing.MatteBorder(8, 8, 8, 8, Color.white)
    add(carte, BorderPanel.Position.Center)
    val buttons = new BoxPanel(Orientation.Horizontal) {
      border = Swing.EmptyBorder(8, 0, 0, 0)
      background = Color.white
      contents += interactionButton
      contents += Swing.HGlue
      contents += Button("Sac") { // Fonction affichage sac   // Instance Sac
        var sac = new SacGui(joueur);
        sac.modal = true
        sac.open()
      }
      contents += Swing.HGlue
      contents += Button("Pokemons") { // Fonction affichage pokemon 
        var pokemon = new PokemonGui(joueur);
        pokemon.modal = true
        pokemon.open()
      }
      contents += Swing.HGlue
      contents += Button("Pokedex") { // Fonction affichage pokedex 
        var pokedex = new PokedexGui(poke);
        pokedex.modal = true
        pokedex.open()
      }
       contents +=Button("Ajouter Pokemon"){
        var nouveauPokemonG  = new nouveauPokemonGui(poke)  
        nouveauPokemonG.modal = true
        nouveauPokemonG.open()  
      }
      contents +=Button("Ajouter Objet personalisé"){
        var nouveauObjetGui  = new nouveauObjetGui(joueur)  
        nouveauObjetGui.modal = true
        nouveauObjetGui.open() 
      }
      contents += Swing.HGlue
      contents += Button("Quit") { dispose() }
    }
    add(buttons, BorderPanel.Position.South)
  }

  def interract(): Unit = {
    if (this.focus == null) {
      println(focus)
      Dialog.showMessage(carte, "Nothing to interact with!", "No interaction possible")
    } else {
      println(this.focus)
      focus.activer(joueur)
    }
  }
}