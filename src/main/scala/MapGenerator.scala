import scala.collection.mutable.ListBuffer


object Time {
  private val form = new java.text.SimpleDateFormat("HH:mm:ss")
  def current = form.format(java.util.Calendar.getInstance().getTime)
}

object Timer {
  def apply(interval: Int, repeats: Boolean = true)(op: => Unit) {
    val timeOut = new javax.swing.AbstractAction() {
      def actionPerformed(e : java.awt.event.ActionEvent) = op
    }
    val t = new javax.swing.Timer(interval, timeOut)
    t.setRepeats(repeats)
    t.start()
  }
}

object MapGenerator {
  
  var pokedex: PokedexInMemory[String, Pokemon] = null

  val pokemons: List[String] = List(Constantes_pokemon.BULBIZARRE, Constantes_pokemon.HERBIZARRE,
    Constantes_pokemon.CARAPUCE, Constantes_pokemon.SALAMECHE,
    Constantes_pokemon.DRACAUFEU, Constantes_pokemon.CARABAFFE,
    Constantes_pokemon.RONFLEX, Constantes_pokemon.MEWTWO)

  def generatePokedex(): PokedexInMemory[String, Pokemon] = {
    if(pokedex == null) {
      pokedex = Usine_a_pokedex.generate_pokedex(pokemons)
    }
    return pokedex;
  }

  var spawn = Usine_a_spawn.generate_spawn(pokemons)

  def generateAffichables(): ListBuffer[Affichable] = {
    var affichables = new ListBuffer[Affichable]();

    // Instanciation 3 Lieux 
    var shop = new Pokeshop(200, 300);

    var stop = new Pokestop(0, 600);
    spawn.coordonneeX = 768;
    spawn.coordonneeY = 50;
    spawn.width = 150;
    spawn.heigth = 568;

    affichables += shop;
    affichables += stop;
    affichables += spawn;

    // Fin Instanciation 3 Lieux 
    return affichables;
  }

   Timer(500) {  
      val pokemons = pokedex.recuperation();
      pokemons.foreach {
        case (key, values) => if (!spawn.pokemonsGenerable.contains(key)) {
          var pokemon = Usine_a_pokemon.ajoutPokemon(values)
          spawn.pokemonsGenerable =key::spawn.pokemonsGenerable  
          spawn.pokemons = values::spawn.pokemons
          print("__________________________ pokemon ajouter_________________________________ " + pokemon.nom)
        }
   }
 }

}