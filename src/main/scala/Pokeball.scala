

 
class Pokeball(n:String,p:Float) extends ObjetPokemon(n,p)  {
 


 val tauxCapture:Int = 33;

 override def utiliser(p:Pokemon,j:Joueur): Int = {
    println("taaaux capture : " + tauxCapture);
  	val r = scala.util.Random
  	var res = r.nextInt(100);

    println(" aleatoire " + res);
   	j.supprimerObjet(this);
   if(res <= this.tauxCapture){
    println(" capture succes");
   	p.changementEtat(true);
   	return 1;
   }
   else{
    println("erreur");
   	return -1
   }
}




}


class Superball(n:String,p:Float) extends Pokeball(n,p)  {
	 override val tauxCapture = 66;
}


class Masterball(n:String,p:Float) extends Pokeball(n,p)  {
 	 override val tauxCapture = 100;

}