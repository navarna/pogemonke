import java.awt.{ Graphics2D, Color }
import java.awt.image.BufferedImage
import java.io.FileInputStream
import javax.imageio.ImageIO
import scala.swing._

class Spawn extends Lieu {

  var pokemonsGenerable: List[String] = List();

  var pokemons: List[Pokemon] = List();

  var nearPokemon: Pokemon = null

  var duree: Int = 1800;

  // coordonnee
  var width: Int = 0;
  var heigth: Int = 0;
  var brush: BufferedImage = ImageIO.read(new FileInputStream("src/main/scala/brush.png"))  

  override def estProche(t: T): Boolean = {
    for (pokemon <- pokemons) {
      if ((math.abs(pokemon.coordonneeX - t.coordonneeX) < bloc_size) && (math.abs(pokemon.coordonneeY - t.coordonneeY) < bloc_size)) {
        nearPokemon = pokemon
        return true
      }
    }
    nearPokemon = null;
    return false;
  }

  override def activer(joueur: T): Unit = {
    println("le spawn")
    println(this)
    println("le pokemon")
    println(nearPokemon)
    
    val combat: CombatGui = new CombatGui(joueur, nearPokemon);
    combat.modal = true
    combat.open
  }

  override def afficher(): Unit = {
    // Fill the area
    var x: Int = 0;
    var y: Int = 0;

    var brushW = brush.getWidth
    var brushH = brush.getHeight

    while (y < heigth) {
      while (x < width) {
        g.drawImage(brush, x + coordonneeX, y + coordonneeY, null)
        x += brushW
      }
      x = 0
      y += brushH
    }

    for (pokemon <- pokemons) {
      print (" pokemon dans spawn  : " +pokemon.nom)
      pokemon.g = g
      pokemon.afficher()
      movePokemon(pokemon)
      jailInBoundary(pokemon)
    }

    def jailInBoundary(affichable: Affichable): Unit = {
      if (affichable.coordonneeX > coordonneeX + width) {
        affichable.coordonneeX = coordonneeX + width
      }
      if (affichable.coordonneeX < coordonneeX) {
        affichable.coordonneeX = coordonneeX
      }
      if (affichable.coordonneeY > coordonneeY + heigth) {
        affichable.coordonneeY = coordonneeY + heigth
      }
      if (affichable.coordonneeX < coordonneeX) {
        affichable.coordonneeY = coordonneeY
      }
    }

    def movePokemon(deplaceable: Deplaceable): Unit = {
      val r = scala.util.Random
      deplaceable.seDeplacer(r.nextInt(5));
    }
  }
}

