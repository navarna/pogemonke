import scala.swing._
import java.awt.{ Graphics2D, Color }
import scala.swing.ListView._

class PokedexGui (poke:PokedexInMemory[String, Pokemon])extends Dialog {
  
  val pokedex = poke ;
  def getPokedexValue ():List[Pokemon] = {
    var e:List[Pokemon] = List(); 
      pokedex.recuperation.foreach{
        case (key, value) => e = value::e  
      } 
      return e ; 
  }
  
  contents = new BorderPanel {
    border = Swing.MatteBorder(8, 8, 8, 8, Color.white)
    val list = new ListView(getPokedexValue()) {
      selection.intervalMode = ListView.IntervalMode.Single
      //renderer = ListView.Renderer(_.title)
    }
    add(new ScrollPane(list), BorderPanel.Position.South)
  }
}