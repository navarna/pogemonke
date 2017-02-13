

import scala.collection.mutable

/**
  * An abstract keys/values container to store objects
  */
abstract class Container {
  type UniqId;
  type Kind;

  def insert(key: UniqId, thing: Kind): Unit;
  def get(key: UniqId): Kind;
  def remove(key: UniqId): Unit;
}

/**
  * A Pokedex is basically a container of Pokemons
  *
  * @tparam PokemonId
  * @tparam Pokemon
  */
abstract class Pokedex[PokemonId, Pokemon] extends Container {
  override type UniqId = PokemonId;
  override type Kind = Pokemon;
}

/**
  * A in-memory Pokedex representation
  *
  * @tparam PokemonId
  * @tparam Pokemon
  */
class PokedexInMemory[PokemonId, Pokemon] extends Pokedex[PokemonId, Pokemon]  {

  private val pokemons: mutable.Map[PokemonId, Pokemon] =
    new mutable.HashMap();

  /**
    * Store in the in-memory system @thing in @key, any existing keys would
    * be overrided.
    *
    * @param key
    * @param thing
    */
  override def insert(key: PokemonId, thing: Pokemon): Unit = {
    pokemons(key) = thing;
  }

  /**
    * Retrieve a Pokemon by is associated key. This method is raising an
    * exception whether the key does not exist.
    *
    * @param key
    * @return
    */
  override def get(key: PokemonId): Pokemon = {
    return pokemons(key);
  }

  /**
    * Remove an existing key from the storage. This method is raising an
    * exception whether the key does not exist.
    *
    * @param key
    */
  override def remove(key: PokemonId): Unit = {
    pokemons.remove(key);
  }

  def getsize(): Int = {
    return pokemons.size ;
  }

  def recuperation():mutable.Map[PokemonId, Pokemon] = {
    return pokemons ; 
  }

  def contains (key:PokemonId): Boolean = {
    if(pokemons.contains(key)) { return true ; }
    else return false ;
  }
/*
*
* Karim YH to Said : Tu avais déclaré pokemons en private , impossible dy acceder autrement :)
*
*/
  


}

object TestPokedex extends App {
  type PokemonId = String;
  type Pokemon = Int;

  val pokedex = new PokedexInMemory[PokemonId, Pokemon]();
  pokedex.insert("one", 1);
  pokedex.insert("two", 2);
  assert(pokedex.get("one") != 0);
  assert(pokedex.get("two") != 0);

  //TODO(sahid): Add tests if key does not exists.
}