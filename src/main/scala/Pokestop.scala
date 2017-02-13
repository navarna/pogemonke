import scala.swing._

import java.awt.image.BufferedImage
import java.io.FileInputStream
import javax.imageio.ImageIO

class Pokestop(X: Int, Y: Int) extends Lieu {
  coordonneeX = X;
  coordonneeY = Y;

  bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokestop.jpg"))

  override def activer(joueur: T): Unit = {
    println("activation du pokestop")
    var baie = Usine_a_objet.generate_objet(Constantes_objet.BAIE);
    var bonbon = Usine_a_objet.generate_objet(Constantes_objet.SUPER_BONBON);
    var masterBall = Usine_a_objet.generate_objet(Constantes_objet.MASTERBALL);
    var leurre = Usine_a_objet.generate_objet(Constantes_objet.LEURRE);
    var encens = Usine_a_objet.generate_objet(Constantes_objet.ENCENS);
    joueur.ajouterObjet(baie);
    joueur.ajouterObjet(bonbon);
    joueur.ajouterObjet(masterBall);
    joueur.ajouterObjet(leurre);
    joueur.ajouterObjet(encens);

    Dialog.showMessage(null, "You have got a lot of Gift! Check your back pack", title = "Surprise!")
  }
}