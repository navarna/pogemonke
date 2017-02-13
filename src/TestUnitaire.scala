/*
*
*  Karim YAHIAOUI
*  Test unitaires -----------
*
*/



/*
*
* Karim YH to Said : Tu avais déclaré pokemons en private , impossible dy acceder autrement :)
*
*/
  def getsize():Int = {
    return pokemons.size;
  }




object TestUnitaire {
  def main(args: Array[String]) {
  
  var pokemonsGenerable: List[String] = Constantes_pokemon.BULBIZARRE :: Nil;

  var pokemons: List[Pokemon] = List();


  	// TEST USINE SPAWN ----------------------------------------
	 
	var spawn:Spawn = Usine_a_spawn.generate_spawn(pokemonsGenerable);

	if(pokemonsGenerable(0) == Constantes_pokemon.BULBIZARRE) { 
		Console.println("[OK] Création de la Liste String Pokemon Générable");
	}else{Console.println("[ERROR] Création de la Liste String Pokemon Générable");}

	if(spawn.pokemonsGenerable.length == 1) { 
		Console.println("[OK] generate_spawn : Liste String Pokemon Générable");
	}else{Console.println("[ERROR] generate_spawn : Liste String Pokemon Générable");}

	if(spawn.pokemons.length == 1) { 
		Console.println("[OK] generate_spawn : Liste Pokemon Générable");
	}else{Console.println("[ERROR] generate_spawn : Liste Pokemon Générable");}


	// TEST USINE POKEDEX -------------------------------------	


	val pokedex = Usine_a_pokedex.generate_pokedex();
    
    if(pokedex.getsize() == 11) { 
		Console.println("[OK] generate_pokedex : size = 11 ");
	}else{Console.println("[ERROR] generate_spawn : size != 11 ");}


  }
}