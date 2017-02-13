object Game {
  def main(args: Array[String]) {
   // Instanciation Joueur
    var joueur = new Joueur("Sasha", 1, 5, 5);
    var bulb = new Pokemon("bulb", 1, 3, 4, true, 6, 4);
    var pika = new Pokemon("pika", 4, 1, 4, true, 3, 2);

    // Instanciation 3 Lieux 
    var shop = new Pokeshop(10,20);

    var stop = new Pokestop(0,20);

    var spawn = new Spawn();
    spawn.coordonneeX = 10;
    spawn.coordonneeY = 0;

    // Instanciation de quelques Objets  

    // *************   Modifié le 22 Novembre par Karim
    var baie = Usine_a_objet.generate_objet(Constantes_objet.BAIE);
    var bonbon = Usine_a_objet.generate_objet(Constantes_objet.SUPER_BONBON);
    var masterBall = Usine_a_objet.generate_objet(Constantes_objet.MASTERBALL);
    var leurre = Usine_a_objet.generate_objet(Constantes_objet.LEURRE);
    var encens = Usine_a_objet.generate_objet(Constantes_objet.ENCENS);
    
    // Ajout des Objets dans le shop
    shop.objets.+=(baie);
    shop.objets.+=(bonbon);
    shop.objets.+=(masterBall);
    shop.objets.+=(leurre);
    shop.objets.+=(encens);

    val carte = new Carte() {
      override def afficherElements(): Unit = {
        // TODO(sahid): Needs to be implemented.
      }
    }
    carte.joueurs:+ joueur;
    carte.lieux:+ shop;
    carte.lieux:+ stop;
    carte.lieux:+ spawn;


    // Karim YH :  Cette ligne creer et rempli un pokedex avec un ensemble de Pokémon préféfini
    val pokedex = Usine_a_pokedex.generate_pokedex();
    Console.println(pokedex.get(Constantes_pokemon.BULBIZARRE).nom);



    /**
     * @implement a proper way to exit the game
     */
    val gameLoop = new Thread(new Runnable {
      def run() {
        while (true) {
          Thread.sleep(1000);
          joueur.afficher();
          shop.afficher();
          stop.afficher();
          spawn.afficher();
        }
      }
    })
    gameLoop.start()

    val gameUpdate = new Thread(new Runnable {
      def run() {
        while (true) {
          Thread.sleep(2000);
          val r = scala.util.Random
          // deplacer le joueur ou pas
          joueur.seDeplacer(r.nextInt(5));

          // Activer le pokeshop de temps en temps
          if (r.nextInt(100) < 30) {
            shop.activer(joueur);
            shop.acheter(baie, joueur);
            shop.acheter(masterBall, joueur);
          }

          // Activer le pokestop de temps en temps
          if (r.nextInt(100) < 40) {
            stop.activer(joueur);
          }
        }
      }
    })
    gameUpdate.start()



  }
}