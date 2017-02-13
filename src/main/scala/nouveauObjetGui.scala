import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import java.awt.Choice;

class nouveauObjetGui(joueur:Joueur) extends Dialog {

    val liste_dobjet_ameliorable = List(Constantes_objet.POKEBALL,Constantes_objet.SUPERBALL,Constantes_objet.MASTERBALL,"Nourriture") ;
    val liste_amelioration_Pokeball = List(" ","Rare","Competition","Honneur","Enchantee") ;
    val liste_amelioration_Nourriture = List(" ","PV_PLUS","PROTEINE","FER","CALCIUM","CARBONE","ZINC") ;

    val ComboBox_liste_dobjet_ameliorable = new ComboBox(liste_dobjet_ameliorable)  ;
    val ComboBox_liste_amelioration_Pokeball = new ComboBox(liste_amelioration_Pokeball)  ;
    val ComboBox_liste_amelioration_Nourriture = new ComboBox(liste_amelioration_Nourriture)  ;
 
    var objet_a_ameliorer = " ";
    var amelioration_pokeball = " " ;
    var amelioration_nourriture = " ";

    var affiche = new Label() ; 
	var grid = new GridPanel (10,2){
                contents += ComboBox_liste_dobjet_ameliorable;
                contents += ComboBox_liste_amelioration_Pokeball;
                contents += ComboBox_liste_amelioration_Nourriture;

                contents+=Button("valider"){
     				ajouter_au_sac_du_joueur(joueur:Joueur) ;
     				
     			}
     			contents += affiche ; 
     		}

    contents = new BorderPanel{
    	layout(grid) = Center
    }

    /*reactions += {
      case SelectionChanged(ComboBox_liste_dobjet_ameliorable) => update_objet()
      case SelectionChanged(ComboBox_liste_amelioration_Pokeball) => update_amelioration_pokeball()
      case SelectionChanged(ComboBox_liste_amelioration_Nourriture) => update_amelioration_nourriture()
    }
    */
    listenTo(ComboBox_liste_dobjet_ameliorable.selection)
    listenTo(ComboBox_liste_amelioration_Pokeball.selection)
     listenTo(ComboBox_liste_amelioration_Nourriture.selection)

 
    /*
        def update_objet():Int = {
            println(this.objet_a_ameliorer);
            return 0;
        }

        def update_amelioration_pokeball():Int = {
            this.amelioration_pokeball =  ComboBox_liste_amelioration_Pokeball.selection.item;
             println(this.amelioration_pokeball);
            return 0;
        }  

        def update_amelioration_nourriture():Int = {
            this.amelioration_nourriture = ComboBox_liste_amelioration_Nourriture.selection.item;
             println(this.amelioration_nourriture);
            return 0;
        } 
    */

    def ajouter_au_sac_du_joueur(joueur:Joueur):Int = {

        this.objet_a_ameliorer = ComboBox_liste_dobjet_ameliorable.selection.item;
        this.amelioration_pokeball =  ComboBox_liste_amelioration_Pokeball.selection.item;
        this.amelioration_nourriture = ComboBox_liste_amelioration_Nourriture.selection.item;

        println(this.objet_a_ameliorer + " "  + this.amelioration_pokeball + " " + this.amelioration_nourriture );
        
        if(objet_a_ameliorer == Constantes_objet.POKEBALL){
                if( this.amelioration_pokeball != " "){
                    joueur.ajouterObjet(new Augmented_Pokeball(this.objet_a_ameliorer, 250f,amelioration_pokeball));
                    affiche.text = "Un nouvel Objet est dans le sac !" ;
                }else{
                    joueur.ajouterObjet(Usine_a_objet.generate_objet(Constantes_objet.POKEBALL));
                    affiche.text = "Un nouvel Objet est dans le sac !" ;
                }
        }


    if(objet_a_ameliorer == Constantes_objet.SUPERBALL ){
                if( this.amelioration_pokeball != " "){
                    joueur.ajouterObjet(new Augmented_SuperBall(this.objet_a_ameliorer, 250f,amelioration_pokeball));
                    affiche.text = "Un nouvel Objet est dans le sac !" ;
                }else{
                    joueur.ajouterObjet(Usine_a_objet.generate_objet(Constantes_objet.SUPERBALL));
                    affiche.text = "Un nouvel Objet est dans le sac !" ;
                }
        }



    if(objet_a_ameliorer == Constantes_objet.MASTERBALL){
                if( this.amelioration_pokeball != " "){
                    joueur.ajouterObjet(new Augmented_MasterBall(this.objet_a_ameliorer, 250f,amelioration_pokeball));
                    affiche.text = "Un nouvel Objet est dans le sac !" ;
                }else{
                    joueur.ajouterObjet(Usine_a_objet.generate_objet(Constantes_objet.MASTERBALL));
                    affiche.text = "Un nouvel Objet est dans le sac !" ;
                }
        

    }


    if(objet_a_ameliorer == "Nourriture"){
                if( this.amelioration_nourriture != " "){
                    joueur.ajouterObjet(new Augmented_Accelerateur(this.amelioration_nourriture, 250f,amelioration_nourriture));
                    affiche.text = "Un nouvel Objet est dans le sac !" ;
                }else{
                    joueur.ajouterObjet(Usine_a_objet.generate_objet(Constantes_objet.BAIE));
                    affiche.text = "Un nouvel Objet est dans le sac !" ;
                }
        }

 
       return 0;

   }
}

