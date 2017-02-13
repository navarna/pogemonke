/*
*
*  Karim YAHIAOUI
*  Test unitaires -----------
*
*/


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


	// TEST USINE POKEMON -------------------------------------	

	var pokemon_bulbizare = Usine_a_pokemon.generate_pokemon(Constantes_pokemon.BULBIZARRE);
	if(pokemon_bulbizare.nom == Constantes_pokemon.BULBIZARRE) { 
		Console.println("[OK] création bulbizarre ");
	}else{Console.println("[ERROR] création bulbizarre ");}


	// TEST AJOUT / SUPPRESSION OBJET --------------------------
	var baie = new Baie("Baie", 39.9f, 1.1f);
	var shop = new Pokeshop();
    shop.coordonneeX = 10;
    shop.coordonneeY = 20;
    var size_before = (shop.objets).size;
    shop.ajouter(baie);
    var size_after = (shop.objets).size;

    if(size_before != size_after) { 
		Console.println("[OK] Ajout SHOP ");
	}else{Console.println("[ERROR] Ajout SHOP");}


	shop.supprimer(baie);
 	
 	size_after = (shop.objets).size;
 	if(size_before == size_after) { 
		Console.println("[OK] supprimer SHOP ");
	}else{Console.println("[ERROR] supprimer SHOP");}

	// --------------------------------------------------------



  }
}