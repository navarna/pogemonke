import scala.swing._

import scala.collection.mutable.ListBuffer
import java.awt.image.BufferedImage
import java.io.FileInputStream
import javax.imageio.ImageIO

class Pokeshop(X: Int, Y: Int) extends Lieu {
  coordonneeX = X;
  coordonneeY = Y;

  bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokeshop.jpg"))

  override def activer(joueur: T): Unit = {
    val res = Dialog.showConfirmation(null,
      "Sorry we have nothing except SUPERBALL. Do you want to buy some?",
      optionType = Dialog.Options.YesNo,
      title = "Sorry, we are nearly out of business!")
    if (res == Dialog.Result.Ok) {
      Dialog.showMessage(null, "Thank you!", title = "The merchant!")
      val p: Objet = Usine_a_objet.generate_objet(Constantes_objet.SUPERBALL);
      joueur.ajouterObjet(p);
    }
  }

  var objets = new ListBuffer[Objet]();

  var duree: Int = 1800;

  def acheter(o: Objet, j: Joueur): Unit = {
    println(j.nom + "a acheté: " + o.nom);
  }

  def vendre(o: Objet, j: Joueur): Unit = {
    println(j.nom + "a vendu: " + o.nom);
  }

  /*
  *
  * KARIM YH 
  *
  */
  def ajouter(o: Objet): Unit = {
    objets.+=(o);
  }

}