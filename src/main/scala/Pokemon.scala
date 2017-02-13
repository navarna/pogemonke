
import scala.util.control._
import java.io.FileInputStream
import scala.swing._
import javax.imageio.ImageIO

class Pokemon(Nom: String, ID: Int, Taux: Float, Niveau: Int, Etat: Boolean, Evolution: Int, NextEvolution: Int) extends Etre(Nom) with Deplaceable {
  var id: Int = ID;
  // Taux de capture d'un pokemon
  var tauxCapture: Float = Taux;
  // niveau d'un pokemon
  var niveau: Int = Niveau;
  // Etat d'un pokemon false = sauvage , true  = attraper 
  var etat: Boolean = Etat;
  // evolution du pokemon 
  var evolution: Int = Evolution;
  // Prochaine evolution du pokemon 
  var nextevolution = NextEvolution;
  // Fonction qui permet de fortifier un pokemon 
  def fortifier() {
    niveau += 1;
  }

  // Ajouté par Karim ------------------
  var Points_de_Vie = 50 ;
  var Attaque = 50 ;
  var Defense = 50 ;
  var Attaque_speciale = 50 ;
  var Vitesse  = 50 ;
  var Defense_speciale = 50 ;
  // -----------------------------------

  val random = scala.util.Random
  coordonneeX = random.nextInt(800)
  coordonneeY = random.nextInt(600)

  bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemon.png"))

  // fonction qui permet de faire evoluer un pokemom
  def evoluer(pokedex: PokedexInMemory[String, Pokemon]) {
    val pokemons = pokedex.recuperation();
    if (nextevolution != 0) {
      val loop = new Breaks;
      loop.breakable {
        pokemons.foreach {
          case (key, values) => if (values.evolution == nextevolution) {
            nom = values.nom;
            id = values.id;
            tauxCapture = values.tauxCapture;
            evolution = values.evolution;
            nextevolution = values.nextevolution;
            niveau = niveau + values.niveau;
            loop.break;
          }
        }
      }
    }
  }

  // fonction qui permet de changer le taux de capture d'un pokemom
  def changementTaux(change: Float) {
    tauxCapture += change;
  }

  // fonction qui permet de mettre un pokemon sauvage en pokemon attapé
  def changementEtat(change: Boolean) {
    etat = change;
  }

}

object TestPokemon extends App {
  var p = new Pokemon("test", 1, 1, 5, true, 1, 2);
  val pokedex = Usine_a_pokedex.generate_pokedex();
  def affichage(poke: Pokemon) {
    println(poke.nom + "  : " + " id -> " + poke.id + " taux -> " + poke.tauxCapture + " niveau :  " + poke.niveau + " evolution : " + poke.evolution + " nextevolution : " + poke.nextevolution);
  }
  affichage(p);
  p.changementTaux(1);
  println("test changement de taux + 1.0");
  affichage(p);
  p.fortifier();
  println("test augmentation de niveau (fortifier) +1");
  affichage(p);
  p.evoluer(pokedex);
  println("test evolution : ");
  affichage(p);

}