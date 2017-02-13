import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random


class nouveauPokemonGui (pokedex:PokedexInMemory[String, Pokemon]) extends Dialog {
	var repNom1 = new TextArea ()
    var repNom2 = new TextArea ()
    var repNom3 = new TextArea ()
    var repNiv1 = new TextArea ()
    var repNiv2 = new TextArea ()
    var repNiv3 = new TextArea ()
    var repCap1 = new TextArea ()
    var repCap2 = new TextArea ()
    var repCap3 = new TextArea ()
    var affiche = new Label() ; 
	val grid = new GridPanel (10,2){
				border = Swing.MatteBorder(8, 8, 8, 8, Color.white)
				contents += new Label("nom Pokemon Evo1") ;
				contents += repNom1 
     			contents += new Label("taux Capture ") ;
				contents += repCap1 
     			contents += new Label("niveau ") ;
				contents += repNiv1
     			contents += new Label("nom Pokemon Evo2") ;
				contents += repNom2
     			contents += new Label("taux Capture") ;
				contents += repCap2
     			contents += new Label("niveau") ;
				contents += repNiv2
     			contents += new Label("nom Pokemon Evo3") ;
				contents += repNom3
     			contents += new Label("taux Capture") ;
				contents += repCap3
     			contents += new Label("niveau") ;
				contents += repNiv3
     			contents+=Button("valider"){
     				val e = ajouter()
     				affiche.text = e +" Pokemon ajoutés"
     				if(e != 0 ){}

     			}
     			contents += affiche ; 
     		}

    contents = new BorderPanel{
    	layout(grid) = Center
    }


    def stringenint (ele:String): Int = {
    	try {
    		val rep = Some(ele.toInt).getOrElse(0)
    		return rep 
  		} 
  		catch {
    		case e: Exception => return -1
  		}

  		return -1
    }

    def stringenfloat (ele:String): Float = {
    	try {
    		val rep = ele.toFloat
    		return rep 
  		} 
  		catch {
    		case e: Exception => return -1.0.toFloat
  		}

  		return 1.0.toFloat;
    }

    def ajouter():Int = {
    	var compteur = pokedex.getsize() ; 
    	var num = 0 ; 
    	val nom1 =repNom1.text;
    	if(nom1 == "") {return 0 ; }
		val cap1 = stringenfloat(repCap1.text);
		if(cap1 == -1.0) {return 0 ; }
		val niv1 = stringenint(repNiv1.text);
		
		if(niv1 == -1) {return 0 ;
		}
		var nouveau:Pokemon =new Pokemon(nom1,compteur,cap1,niv1,true,compteur , 0)
		if(pokedex.contains(nom1) ){
			return 0 ; 
		}
		val nom2 =repNom2.text;
    	if(nom2 == "") {
    		pokedex.insert(nom1,nouveau);
    		return 1 ; 
    	}
		val cap2 = stringenfloat(repCap2.text);
		if(cap2 == -1.0) {pokedex.insert(nom1,nouveau);return 1 ; }
		val niv2 = stringenint(repNiv2.text);
		
		if(niv2 == -1) {pokedex.insert(nom1,nouveau);return 1 ;
		}
		var nouveau2:Pokemon =new Pokemon(nom2,compteur+1,cap2,niv2,true,compteur+1 , 0)
		if(pokedex.contains(nom2) ){
			pokedex.insert(nom1,nouveau);
			return 1 ; 
		}
		else {
			nouveau.nextevolution = compteur + 1 ; 
			pokedex.insert(nom1,nouveau);
		}
		val nom3 =repNom3.text;
    	if(nom3 == "") {
    		pokedex.insert(nom2,nouveau2);
    		return 2 ; 
    	}
		val cap3 = stringenfloat(repCap3.text);
		if(cap3 == -1.0) {pokedex.insert(nom2,nouveau2);return 2 ; }
		val niv3 = stringenint(repNiv3.text);
		
		if(niv3 == -1) {pokedex.insert(nom2,nouveau2);return 2 ;
		}
		var nouveau3:Pokemon =new Pokemon(nom3,compteur+2,cap3,niv3,true,compteur+2 , 0)
		if(pokedex.contains(nom3) ){
			pokedex.insert(nom2,nouveau2);
			return 2 ; 
		}
		else {
			nouveau2.nextevolution = compteur + 2 ; 
			pokedex.insert(nom2,nouveau2);
			pokedex.insert(nom3,nouveau3);
			return 3 ; 		
		}

    }

	
}

