

abstract class Objet(val param_nom:String,val param_prix:Float) {
 
  type O;
  type C;

  var nom:String = param_nom;
  var prix:Float = param_prix;


  def utiliser(o:O,c:C):Int;
}
