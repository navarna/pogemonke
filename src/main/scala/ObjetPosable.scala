

abstract class ObjetPosable(n:String,p:Float,val param_duree:Int) extends Objet(n,p)  {
 
  var duree:Int = param_duree;

  override type O = Lieu;
  override type C = Joueur; 
 
}



class Encens(n:String,p:Float,d:Int) extends ObjetPosable(n,p,d)  {
 
    override def utiliser(l:Lieu,j:Joueur): Int = {
    //l.activer();
   	j.supprimerObjet(this);
   		return 1;
  }

 
}



class Leurre(n:String,p:Float,d:Int) extends ObjetPosable(n,p,d)  {
 
    override def utiliser(l:Lieu,j:Joueur): Int = {
    //l.activer();
   j.supprimerObjet(this);
   		return 1;
  }

 
}
