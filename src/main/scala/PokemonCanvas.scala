import java.awt.{ Graphics2D, Color }
import scala.swing._

class PokemonCanvas(pokemon: Pokemon) extends Component {
  pokemon.coordonneeX = 50
  pokemon.coordonneeY = 40

  override def paintComponent(g: Graphics2D) {
    // clear background
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, 120, 120)

    // draw pokemon name
    g.setColor(new Color(0, 128, 0))
    g.drawString(pokemon.nom, 20, 20)

    pokemon.g = g
    pokemon.afficher()
  }
}