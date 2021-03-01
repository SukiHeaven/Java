
#PROJET IN404 : Echiquier


----------


Le projet est un jeu d'échecs codé en Java. Certaines règles du jeu ont été omises.


----------


##Manuel Utilisateur
###Contenu de l'archive

L'archive est constituée des fichiers suivants :
- un fichier README.md
- une documentation javadoc
- un package projet.java

### Ouverture de l'archive

Décompressez l'archive sous Linux avec la commande suivante.

	jar -x ProjetJava.jar 

Le package doit ensuite être ouvert dans un IDE Java pour pouvoir être compiler. 

###Exécution de l'échiquier

Lors de l'exécution l'utilisateur doit rentrer le nombre d'IA lors de la partie : 0,1 ou 2. 
Si au moins l'un des joueurs est humain celui-ci doit rentrer les coordonnées de la pièce qu'il souhaite déplacer ainsi que les coordonnées de la case ou il souhaite aller. Lors de la promotion d'un Pion l'utilisateur doit choisir en quoi promouvoir son Pion.
Pour quitter la partie en cours le joueur doit rentrer "quit" ou "exit" lors de son tour.

##Manuel Technique
###Classes de l'échiquier
Les classes suivante sont nécessaire pour le fonctionnement du jeu : 
-Partie
-Plateau
-Joueur
-Piece (abstraite) étendue par : 
		*Pion
		*Cavalier
		*Fou
		*Reine
		*Roi
		*Tour
		
###Fonctionnement de l'échiquier
La partie est initialisé dans le Main. Le jeu est géré dans la classe Partie. Au début de la partie, chaque case vide du plateau prends la valeur d'un Pion mort qui ne peux être joué.

Si l'un des joueurs est humain, ses entrées sont prisent en compte à chaque tour et traduites (gestionTour). Si les coordonnées rentrées correspondes à l'une des ses pièces, la fonction deplacerPiece dans la classe Plateau est appelée et vérifie si le mouvement est possible grâce à verifDeplacement qui est unique à chaque type de pièces. Si celui-ci est impossible il est demandé au joueur de rentrer d'autres coordonnées et dans le cas contraire c'est au joueur suivant de jouer. La partie se finie si il y a échec et mat ou si le joueur la quitte avant la fin.


### L'IA
La fonction gestionTourIA choisis aléatoirement des valeurs jusqu'à ce que celle-ci soit des coordonnées valables et que la pièce sélectionnée aléatoirement puisse être bougée. Une fonction promotionIA existe également dans la fonction Pion et choisis aléatoirement en quoi promouvoir le pion.

###Les pièces 
La classe abstraite Piece permet de regrouper les différents type de pièces. A chacune des pièces, une couleur est attribuée (noir ou blanc) ainsi qu'une valeur isAlive qui nous permet de savoir si une pièce vivante est présente dans une case de l'échiquier ou non.

###L'affichage
Les fonctions affichePiece et afficheOnePiece dans la classe Plateau permettent d'afficher à l'écran les pièces vivantes ainsi que leur coordonnées sur l'échiquier.
