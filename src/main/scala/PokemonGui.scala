import scala.swing._
import java.awt.{ Graphics2D, Color }
import scala.swing.ListView._

class PokemonGui(joueur: Joueur) extends Dialog {
  contents = new BorderPanel {
    border = Swing.MatteBorder(8, 8, 8, 8, Color.white)
    val list = new ListView(joueur.listPokemon) {
      selection.intervalMode = ListView.IntervalMode.Single
    }
    add(new ScrollPane(list), BorderPanel.Position.South)
  }
}