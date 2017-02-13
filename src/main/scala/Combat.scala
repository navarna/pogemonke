
/**
  * Abstract class to refer a combat in Pokemon world
  *
  */
abstract class Combat(j: Joueur, p: Pokemon) {
  val joueur: Joueur = j;
  val pokemon: Pokemon = p;

  /**
    * @param pokeball
    * @return Returns whether or not the pokeball has captured the pokemon.
    */
  def capture(pokeball: Pokeball): Boolean = {
    if (pokeball.utiliser(pokemon, joueur) == 1) {
      joueur.ajouterPokemon(pokemon);
      return true
    }
    println("erreur");
    return false;

  }

  /**
    * @param nourriture
    * @return
    */
  def nourrir(nourriture: Nourriture): Boolean = {
    return nourriture.utiliser(pokemon, joueur) == 1;
  }

  def fuir(): Boolean = true;

  /**
    * According to wat this method is returning the fight evolute
    *
    * @param result, that represents the result of the last action
    * @return
    */
  def action(result: Boolean): Any

  def fight(): Unit = {
    var loop: Boolean = true;
    var result: Boolean = false;

    while (loop) {
      action(result) match {
        case pokeball: Pokeball =>
          result = capture(pokeball);
        case nourriture: Nourriture =>
          result = nourrir(nourriture)
        case _ =>
          fuir();
          loop = false;
      }
    }
  }
}

/**
  * Demonstrate how to use a combat
  *
  * The first step is to override 'action' is basically how the
  * 'Combat' is going. The input parameter 'result' represent the last
  * action result.  A according to what this method is returning
  * (Pokeball, Nourriture, Nothing) the event loop will address an
  * action.
  *
  */
object TestCombat extends App {
  val bonbon = Usine_a_objet.generate_objet(Constantes_objet.SUPER_BONBON);
  val masterball = Usine_a_objet.generate_objet(Constantes_objet.MASTERBALL);

  val joueur = new Joueur("Sasha", 1, 5, 5);
  //joueur.ajouterObjet(bonbon);
  //joueur.ajouterObjet(masterball);

  val bulb = Usine_a_pokemon.generate_pokemon(Constantes_pokemon.BULBIZARRE);

  var next: String = "try-capture";
  val combat = new Combat(joueur, bulb) {
    override def action(result: Boolean): Any = {
      println("Executing actions: " + next
        + " with previous result: " + result);

      next match {
        case "try-capture" =>
            println("Trying to capture the Pokemon...")
            next = "result-capture";
            return masterball; //joueur.sac.get("MasterBall")

        case "result-capture" =>
          if (result) {
            println("Pokemon caught !");
            next = "finish"
          } else {
            println("Pokemon missed, trying to give to him a 'bonbon'...");
            next = "result-nourrir"
            return bonbon; // joueur.sac.get("Bonbon");
          }

        case "result-nourrir" =>
            println("Pokemon says 'miamiam' ");
            next = "finish"

        case "finish" =>
          println("Combat terminated");
      }
    }
  }.fight();
}
