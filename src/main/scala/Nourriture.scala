

abstract class Nourriture(n:String,p:Float) extends ObjetPokemon(n,p)  {
 

}


class Bonbon(n:String,p:Float) extends Nourriture(n,p)  {

    override def utiliser(p:Pokemon,j:Joueur): Int = {
   	j.supprimerObjet(this)
   	p.fortifier;

   	return 1;
   }

}


class Baie(n:String,p:Float) extends Nourriture(n,p)  {
  	

    override def utiliser(p:Pokemon,j:Joueur): Int = {
    p.tauxCapture /= 2;
    j.supprimerObjet(this)
   		return 1;
   }

}

