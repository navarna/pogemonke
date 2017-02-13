
import scala.collection.mutable
import scala.swing._
class Sac () extends Container() {
  override type UniqId = Objet;
    override type Kind = Int; 
  var sacJoueur: mutable.Map[String,Kind] =
      new mutable.LinkedHashMap();
  var testSac : mutable.Map[String,UniqId] =
      new mutable.HashMap();

  override def insert(nomObjet: UniqId, nbObjet: Kind): Unit = {
    if(sacJoueur.contains(nomObjet.nom)){
      sacJoueur(nomObjet.nom) = sacJoueur(nomObjet.nom)+nbObjet ; 
    }
    else {
     sacJoueur(nomObjet.nom) = nbObjet;
     testSac(nomObjet.nom) = nomObjet ; 
   }
  }


  override def get(UnObjet:UniqId): Kind = {
    return sacJoueur(UnObjet.nom);
  }

  def getObjetList(nomObjet:String):UniqId = {
    return testSac(nomObjet);
  }

  def getString(nomObjet:String): UniqId = {
      if(testSac.contains(nomObjet)){
        val o = testSac(nomObjet);
        val nb = sacJoueur(nomObjet);
        if(nb-1 ==0){
          remove(o);
        }
        else {
          sacJoueur(nomObjet) = sacJoueur(nomObjet) -1 ; 
        }
        return o; 
      }
      
      return null ; 
    }
  override def remove(nomObjet: UniqId): Unit = {
    sacJoueur.remove(nomObjet.nom) ;
    testSac.remove(nomObjet.nom); 
  }

  def removeNB (nomObjet:UniqId, nb:Int) : Unit = {
    sacJoueur(nomObjet.nom) = sacJoueur(nomObjet.nom)-nb ;
    if(sacJoueur(nomObjet.nom) <=0){
      remove(nomObjet);
    }
  }
  
}


class Joueur (Nom:String , ID:Int , X:Float ,Y:Float)extends Etre(Nom) with Deplaceable{
	//ID du Joueur
	val id:Int = ID ;
	
	val pokedex = Usine_a_pokedex.generate_pokedex();
	val sac = new Sac () ;
	var listPokemon:List[Pokemon] = List()  ;
	var argent:Float = 0  ; 

	// interagir avec un pokemon
	def interagir (pokemon:Pokemon){

	}

	// ajouter ou enlever de l'argent
	def modifierArgent (montant:Float) {
		argent +=  montant ; 
	}

	// ajouter un pokemon
	def ajouterPokemon(nouveauPokemon:Pokemon){
		listPokemon = nouveauPokemon::listPokemon;
	} 


	// ajouterUnObjet
	def ajouterObjet(nouveauObjet:Objet){
	 	sac.insert(nouveauObjet,1);
	}

	// modifier le pokedex
	def modifierPokedex () {
      
	}
	// supprimer un objet
	def supprimerObjet(nouveauObjet:Objet){
	 	sac.remove(nouveauObjet);
	}

	def getObjet (nouveauObjetnom:String):Objet = {
		return sac.getString(nouveauObjetnom);
	}

  def getKeySac ():List[String] = {
    var e:List[String] = List(); 
      sac.testSac.foreach{
      case (key, value) => e = key::e  
      } 
      return e ; 
  }

  def getObjet() :List[Objet] = {
    var e:List[Objet] = List(); 
      sac.testSac.foreach{
      case (key, value) => e = value::e  
      } 
      return e ; 
  }

  def getNb() : List[Int] = {
    var e:List[Int] = List(); 
      sac.sacJoueur.foreach{
      case (key, value) => e = value::e  
      } 
      return e ; 
  }
  
  override def activer(joueur: T): Unit = {
    val res = Dialog.showConfirmation(null,
      "Do you want to give " + joueur.nom + " randomly one of your object?",
      optionType = Dialog.Options.YesNo,
      title = "Be Nice!")
    if (res == Dialog.Result.Ok) {
      val listObject = joueur.getKeySac()
      val r = scala.util.Random
      if (listObject.length > 0) {
        val i = r.nextInt(listObject.length)
        val objectName = listObject(i)
        val cadeau = joueur.getObjet(objectName)
        joueur.supprimerObjet(cadeau)
        ajouterObjet(cadeau)
        Dialog.showMessage(null, "You give me :" + objectName + "? Thank you!", title = joueur.nom + " said")
      }
      else 
        Dialog.showMessage(null, "Your sac is empty you can't give anything", title = joueur.nom + " said")
    }
  }

}

// Test class Sac
object TestSac extends App {

  val pokedex = new Sac();
  val p:Pokeball = new Pokeball("Pokeball",10) ; 
  val p2:Pokeball = new Pokeball("Pokeball",10) ;
  pokedex.insert(p, 1);
  pokedex.insert(p2,1) ;
  val p3:Objet = pokedex.getString("Pokeball");
  p match {
  case p3: Pokeball => p3
  case _ => null 
}
println(p3.nom + " " + p3.prix);
val p4:Objet = pokedex.getString("Pokeball");
if(p4 != null) println(p4.nom + "  : " + p4.prix) ; 
else print("erreur p4"); 
 val p5:Objet = pokedex.getString("Pokeball");
if(p5 != null) println("erreur")
else println("OK"); 
val p6:Masterball  = new Masterball("Masterball",10) ;
val j = new Joueur("lolo",1,0,0);
j.ajouterObjet(p6);
j.ajouterObjet(p2); 
var listString = j.getKeySac() ;
var listObjet = j.getObjet() ; 
var listNum = j.getNb() ;
println("Numero ") ;  
listNum.foreach{println;};
println("String :");
listString.foreach{println;};
println("Objet : ");
listObjet.foreach{x => println(x.nom + " " + x.prix)};
  //TODO(sahid): Add tests if key does not exists.
}