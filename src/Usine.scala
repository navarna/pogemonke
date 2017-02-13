  


/*
*  Karim YAHIAOUI -----------------------------------------------------------
*
*/


/*
* Fonction de création de Pokémon
* Return : Objet de type Pokémon
*/

import scala.collection.mutable.ListBuffer

object Constantes_pokemon{

  val BULBIZARRE:String = "BULBIZARRE";
  val HERBIZARRE:String = "HERBIZARRE";
  val FLORIZARRE:String = "FLORIZARRE";

  val SALAMECHE:String = "SALAMÈCHE";
  val REPTINCEL:String = "REPTINCEL";
  val DRACAUFEU:String = "DRACAUFEU";

  val CARAPUCE:String = "CARAPUCE";
  val CARABAFFE:String = "CARABAFFE";
  val TORTANK:String = "TORTANK";
  
  val MEWTWO:String = "MEWTWO";
  val RONFLEX:String = "RONFLEX";
}




/*
*
* Genere un Pokémon selon le nom donné en argument
*
*/

object Usine_a_pokemon {
  def generate_pokemon(nom:String):Pokemon = {
    if(nom == Constantes_pokemon.BULBIZARRE ){ return  new Pokemon(Constantes_pokemon.BULBIZARRE, 1, 0.9f, 15, true, 1, 2);}
    if(nom == Constantes_pokemon.HERBIZARRE ){ return new Pokemon(Constantes_pokemon.HERBIZARRE, 2, 0.8f, 16, true, 2, 3);}
    if(nom == Constantes_pokemon.FLORIZARRE ){ return new Pokemon(Constantes_pokemon.FLORIZARRE, 3, 0.7f, 32, true, 3, 0) ;}
    if(nom == Constantes_pokemon.SALAMECHE ){ return new Pokemon(Constantes_pokemon.SALAMECHE, 4, 0.9f, 15, true, 4, 5);}
    if(nom == Constantes_pokemon.REPTINCEL ){ return  new Pokemon(Constantes_pokemon.REPTINCEL,5, 0.8f, 16, true, 5, 6);}
    if(nom == Constantes_pokemon.DRACAUFEU ){ return new Pokemon(Constantes_pokemon.DRACAUFEU, 6, 0.7f, 32, true, 6, 0);}
    if(nom == Constantes_pokemon.CARAPUCE ){ return new Pokemon(Constantes_pokemon.CARAPUCE, 7, 0.9f , 15, true, 7, 8);}
    if(nom == Constantes_pokemon.CARABAFFE ){ return new Pokemon(Constantes_pokemon.CARABAFFE, 8, 0.8f, 16, true, 8, 9);}
    if(nom == Constantes_pokemon.TORTANK ){ return new Pokemon(Constantes_pokemon.TORTANK, 9, 0.7f, 32, true, 9, 0);}
    if(nom == Constantes_pokemon.MEWTWO ){ return new Pokemon(Constantes_pokemon.MEWTWO, 10, 0.1f, 70, true, 10, 0);}
    if(nom == Constantes_pokemon.RONFLEX ){ return new Pokemon(Constantes_pokemon.RONFLEX, 11, 0.5f, 30, true, 11, 0);}

    return null;
  }
}


/*
*
* Genere un Pokédex Minimal
*
*/

object Usine_a_pokedex {

  def initial_fill(pokedex:PokedexInMemory[String,Pokemon]): Unit = {
    /* Création de Pokémon */
    pokedex.insert(Constantes_pokemon.BULBIZARRE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.BULBIZARRE)) ;
    pokedex.insert(Constantes_pokemon.HERBIZARRE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.HERBIZARRE)) ;
    pokedex.insert(Constantes_pokemon.FLORIZARRE,Usine_a_pokemon.generate_pokemon(Constantes_pokemon.FLORIZARRE)) ;
    pokedex.insert(Constantes_pokemon.SALAMECHE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.SALAMECHE)) ;
    pokedex.insert(Constantes_pokemon.REPTINCEL, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.REPTINCEL)) ;
    pokedex.insert(Constantes_pokemon.DRACAUFEU,Usine_a_pokemon.generate_pokemon(Constantes_pokemon.DRACAUFEU)) ;
    pokedex.insert(Constantes_pokemon.CARAPUCE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.CARAPUCE)) ;
    pokedex.insert(Constantes_pokemon.CARABAFFE, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.CARABAFFE)) ;
    pokedex.insert(Constantes_pokemon.TORTANK, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.TORTANK)) ;
    pokedex.insert(Constantes_pokemon.MEWTWO, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.MEWTWO)) ;
    pokedex.insert(Constantes_pokemon.RONFLEX, Usine_a_pokemon.generate_pokemon(Constantes_pokemon.RONFLEX)) ;
  }

 def generate_pokedex():PokedexInMemory[String, Pokemon] = {
    val pokedex = new PokedexInMemory[String, Pokemon]();
    initial_fill(pokedex);
    return pokedex;
 }
   

}



object Usine_a_spawn {
    def generate_spawn(pokemonsGenerable: List[String]):Spawn = {
     var spawn = new Spawn();
     

     var i = 0 ;
     var s = new ListBuffer[String]()
     var p = new ListBuffer[Pokemon]()

     for ( i <- 0 to pokemonsGenerable.length-1){
        Console.println(pokemonsGenerable(i) );
         s += (pokemonsGenerable(i));
         p += (Usine_a_pokemon.generate_pokemon(pokemonsGenerable(i)));
     }

    spawn.pokemonsGenerable = s.toList;
    spawn.pokemons = p.toList;



     return spawn;
    }
}