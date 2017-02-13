

 
// AUGMENTATIONS SUR LES POKEBALLS ---------------------------------------------

trait Trait_Augmented_Pokeball{
	var augmentation:String = "NONE" ;
}

class Augmented_Pokeball(n:String,p:Float,aug:String) extends Pokeball(n,p) with Trait_Augmented_Pokeball {
	
	augmentation = aug;

	override def utiliser(p:Pokemon,j:Joueur): Int = {
		if(augmentation == "NONE"){
			println("Pokeball speciale utilisee ");
		}
		else{
			println("Pokeball speciale utilisee TYPE:" + augmentation);
		}
		 return super.utiliser(p,j);
	}
}



// AUGMENTATIONS SUR LES SUPERBALL ---------------------------------------------

trait Trait_Augmented_SuperBall{
	var augmentation:String = "NONE" ;
}

class Augmented_SuperBall(n:String,p:Float,aug:String) extends Superball(n,p) with Trait_Augmented_SuperBall {
	
	augmentation = aug;

	override def utiliser(p:Pokemon,j:Joueur): Int = {
		if(augmentation == "NONE"){
			println("Pokeball speciale utilisee ");
		}
		else{
			println("Pokeball speciale utilisee TYPE:" + augmentation);
		}
		 return super.utiliser(p,j);
	}
}




// AUGMENTATIONS SUR LES MASTERBALL ---------------------------------------------

trait Trait_Augmented_MasterBall{
	var augmentation:String = "NONE" ;
}

class Augmented_MasterBall(n:String,p:Float,aug:String) extends Masterball(n,p) with Trait_Augmented_MasterBall {
	
	augmentation = aug;

	override def utiliser(p:Pokemon,j:Joueur): Int = {
		if(augmentation == "NONE"){
			println("Pokeball speciale utilisee ");
		}
		else{
			println("Pokeball speciale utilisee TYPE:" + augmentation);
		}
		 return super.utiliser(p,j);
	}
}






// AUGMENTATIONS SUR LA NOURRITURE ---------------------------------------------

trait Trait_Augmented_Accelerateur{
	var augmentation:String = "NONE" ;

	
}

class Augmented_Accelerateur(n:String,p:Float,aug:String) extends Nourriture(n,p) with Trait_Augmented_Accelerateur {
		
		augmentation = aug;

		def utiliser(p:Pokemon ,j:Joueur): Int = {
			
			if (aug == "PV_PLUS"){
				p.Points_de_Vie += 10;
				j.supprimerObjet(this)
			}

			if (aug == "PROTEINE"){
				p.Attaque += 10;
				j.supprimerObjet(this)
			}

			if (aug == "FER"){
				p.Defense += 10;
				j.supprimerObjet(this)
			}

			if (aug == "CALCIUM"){
				p.Attaque_speciale += 10;
				j.supprimerObjet(this)
			}

			if (aug == "CARBONE"){
				p.Vitesse += 10;
				j.supprimerObjet(this)
			}
			if (aug == "ZINC"){
				p.Defense_speciale += 10;
				j.supprimerObjet(this)
			}

			
			return 1;
		}

}
