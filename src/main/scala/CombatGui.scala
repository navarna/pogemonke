import scala.swing._
import java.awt.{ Graphics2D, Color }
import scala.swing.ListView._

class CombatGui(j: Joueur, p: Pokemon) extends Dialog {
  val combat = new Combat(j, p) {
    override def action(result: Boolean): Any = {

    }
  }

  title = "Rencontre avec " + p.nom

  val s = new Dimension(220, 140)
  minimumSize = s
  maximumSize = s
  
  var capture = false;
  var tentative = false;
  val pokemon = new PokemonCanvas(p)
  val command = new BoxPanel(Orientation.Vertical) {
    contents += Swing.VGlue
    contents += Button("Attraper") {
      // récupérer un pokeball du joueur
      var ball: Objet = j.sac.getString(Constantes_objet.POKEBALL);
      if (ball == null) {
          ball = j.sac.getString(Constantes_objet.SUPERBALL);
           if (ball == null) {
              ball = j.sac.getString(Constantes_objet.MASTERBALL);
               if (ball == null) {
                   Dialog.showMessage(null, "Plus de Pokeballs! Va En chercher au marché!", title = "Oups!")
               }
               else{
                 capture = combat.capture(ball.asInstanceOf[Masterball])
                tentative = true;
               }
           }
           else{
               capture = combat.capture(ball.asInstanceOf[Superball])
               tentative = true;
           }
      }else{
         capture = combat.capture(ball.asInstanceOf[Pokeball])
         tentative = true;
      }

      if(tentative != false){ 
        if (capture == true) {
          Dialog.showMessage(null, p.nom + "a été capturé", title = "Pokemon capturé!")
          close()
        } else {
          println("erreur");
          Dialog.showMessage(null, "Raté! Essaie encore!", title = "Echec!")
        }
      }

    }

    contents += Swing.VGlue
    contents += Button("Nourrir") {
      // récupérer une baie du joueur
      val baie: Objet = j.sac.getString(Constantes_objet.BAIE)
      if (baie != null) {
        val nouris = combat.nourrir(baie.asInstanceOf[Nourriture])
        if (nouris) {
          Dialog.showMessage(null, p.nom + "a aimé", title = "Pokemon seduit!")
        } else {
          Dialog.showMessage(null, "Raté! Essaie encore!", title = "Echec!")
        }
      } else {
        Dialog.showMessage(null, "Plus de Baies! Va En chercher au marché!", title = "Oups!")
      }
    }
  }

  contents = new BorderPanel {
    add(pokemon, BorderPanel.Position.Center)
    add(command, BorderPanel.Position.East)
    add(Button("Fuir") {
      close()
    }, BorderPanel.Position.South)
  }
}