import java.io.FileInputStream
import scala.swing._
import javax.imageio.ImageIO



import scala.collection.mutable.ListBuffer

object Constantes_pokemon {

  val BULBIZARRE: String = "BULBIZARRE";
  val HERBIZARRE: String = "HERBIZARRE";
  val FLORIZARRE: String = "FLORIZARRE";
  val SALAMECHE: String = "SALAMÈCHE";
  val REPTINCEL: String = "REPTINCEL";
  val DRACAUFEU: String = "DRACAUFEU";
  val CARAPUCE: String = "CARAPUCE";
  val CARABAFFE: String = "CARABAFFE";
  val TORTANK: String = "TORTANK";
  val MEWTWO: String = "MEWTWO";
  val RONFLEX: String = "RONFLEX";
}

object Constantes_objet {
  val SUPER_BONBON: String = "SUPER_BONBON";
  val BAIE: String = "BAIE";
  val ENCENS: String = "ENCENS";
  val LEURRE: String = "LEURRE";
  val POKEBALL: String = "POKEBALL";
  val SUPERBALL: String = "SUPERBALL";
  val MASTERBALL: String = "MASTERBALL";

}

object Usine_a_objet {
  def generate_objet(nom: String): Objet = {
    if (nom == Constantes_objet.SUPER_BONBON) { return new Bonbon(Constantes_objet.SUPER_BONBON, 1500f) }
    if (nom == Constantes_objet.BAIE) { return new Baie(Constantes_objet.BAIE, 50f); }
    if (nom == Constantes_objet.ENCENS) { return new Encens(Constantes_objet.ENCENS, 500f, 1800); }
    if (nom == Constantes_objet.LEURRE) { return new Leurre(Constantes_objet.LEURRE, 500f, 1800); }
    if (nom == Constantes_objet.POKEBALL) { return new Pokeball(Constantes_objet.POKEBALL, 250f); }
    if (nom == Constantes_objet.SUPERBALL) { return new Superball(Constantes_objet.SUPERBALL, 500f); }
    if (nom == Constantes_objet.MASTERBALL) { return new Masterball(Constantes_objet.MASTERBALL, 3000f); }

    return null;
  }
}

/*
*
* Genere un Pokémon selon le nom donné en argument
* Fonction de création de Pokémon
* Return : Objet de type Pokémon
*/

object Usine_a_pokemon {
  def generate_pokemon(nom: String): Pokemon = {
    if (nom == Constantes_pokemon.BULBIZARRE) {
      return new Pokemon(Constantes_pokemon.BULBIZARRE, 1, 0.9f, 15, true, 1, 2) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons1.png"))
      };
    }
    if (nom == Constantes_pokemon.HERBIZARRE) {
      return new Pokemon(Constantes_pokemon.HERBIZARRE, 2, 0.8f, 16, true, 2, 3) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons2.png"))
      };
    }
    if (nom == Constantes_pokemon.FLORIZARRE) {
      return new Pokemon(Constantes_pokemon.FLORIZARRE, 3, 0.7f, 32, true, 3, 0) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons3.png"))
      };
    }
    if (nom == Constantes_pokemon.SALAMECHE) {
      return new Pokemon(Constantes_pokemon.SALAMECHE, 4, 0.9f, 15, true, 4, 5) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons4.png"))
      };
    }
    if (nom == Constantes_pokemon.REPTINCEL) {
      return new Pokemon(Constantes_pokemon.REPTINCEL, 5, 0.8f, 16, true, 5, 6) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons5.png"))
      };
    }
    if (nom == Constantes_pokemon.DRACAUFEU) {
      return new Pokemon(Constantes_pokemon.DRACAUFEU, 6, 0.7f, 32, true, 6, 0) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons6.png"))
      };
    }
    if (nom == Constantes_pokemon.CARAPUCE) {
      return new Pokemon(Constantes_pokemon.CARAPUCE, 7, 0.9f, 15, true, 7, 8) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons7.png"))
      };
    }
    if (nom == Constantes_pokemon.CARABAFFE) {
      return new Pokemon(Constantes_pokemon.CARABAFFE, 8, 0.8f, 16, true, 8, 9) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons8.png"))
      };
    }
    if (nom == Constantes_pokemon.TORTANK) {
      return new Pokemon(Constantes_pokemon.TORTANK, 9, 0.7f, 32, true, 9, 0) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons9.png"))
      };
    }
    if (nom == Constantes_pokemon.MEWTWO) {
      return new Pokemon(Constantes_pokemon.MEWTWO, 10, 0.1f, 70, true, 10, 0) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons10.png"))
      };
    }
    if (nom == Constantes_pokemon.RONFLEX) {
      return new Pokemon(Constantes_pokemon.RONFLEX, 11, 0.5f, 30, true, 11, 0) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/pokemons11.png"))
      };
    }
    return null;
  }

  def ajoutPokemon(poke:Pokemon) : Pokemon =  {
    var p = new Pokemon(poke.nom, poke.id, poke.tauxCapture, poke.niveau, true, poke.evolution, poke.nextevolution) {
        bufferedImage = ImageIO.read(new FileInputStream("src/main/scala/test2.png"))
      };
      return p ; 
  } 
}

/*
*
* Genere un Pokédex Minimal
*
*/

object Usine_a_pokedex {

  def initial_fill(pokedex: PokedexInMemory[String, Pokemon]): Unit = {
    /* Création de Pokémon */
    pokedex.insert(Constantes_pokemon.BULBIZARRE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.BULBIZARRE));
    pokedex.insert(Constantes_pokemon.HERBIZARRE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.HERBIZARRE));
    pokedex.insert(Constantes_pokemon.FLORIZARRE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.FLORIZARRE));
    pokedex.insert(Constantes_pokemon.SALAMECHE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.SALAMECHE));
    pokedex.insert(Constantes_pokemon.REPTINCEL, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.REPTINCEL));
    pokedex.insert(Constantes_pokemon.DRACAUFEU, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.DRACAUFEU));
    pokedex.insert(Constantes_pokemon.CARAPUCE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.CARAPUCE));
    pokedex.insert(Constantes_pokemon.CARABAFFE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.CARABAFFE));
    pokedex.insert(Constantes_pokemon.TORTANK, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.TORTANK));
    pokedex.insert(Constantes_pokemon.MEWTWO, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.MEWTWO));
    pokedex.insert(Constantes_pokemon.RONFLEX, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.RONFLEX));
  }

  def generate_pokedex(): PokedexInMemory[String, Pokemon] = {
    val pokedex = new PokedexInMemory[String, Pokemon]();
    initial_fill(pokedex);
    return pokedex;
  }

  def generate_pokedex(pokemons: List[String]): PokedexInMemory[String, Pokemon] = {
    val pokedex = new PokedexInMemory[String, Pokemon]();
    for (i <- 0 to pokemons.length - 1) {
      pokedex.insert(pokemons(i), Usine_a_pokemon.generate_pokemon(pokemons(i)));
    }
    return pokedex;
  }

}

object Usine_a_spawn {
  def generate_spawn(pokemonsGenerable: List[String]): Spawn = {
    var spawn = new Spawn();
    var i = 0;
    var s = new ListBuffer[String]()
    var p = new ListBuffer[Pokemon]()

    for (i <- 0 to pokemonsGenerable.length - 1) {
      Console.println(pokemonsGenerable(i));
      s += (pokemonsGenerable(i));
      p += (Usine_a_pokemon.generate_pokemon(pokemonsGenerable(i)));
    }

    spawn.pokemonsGenerable = s.toList;
    spawn.pokemons = p.toList;

    return spawn;
  }
}