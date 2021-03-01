package projet.piece;
import java.util.Scanner;
import java.util.ArrayList;



public class Partie {

	/**
	 * Initialisation des deux joueurs
	 */
	private Joueur j1;
	private Joueur j2;
	
	/**
	 * Initialisation du plateau de jeu
	 */
	private Plateau pl;
	private ArrayList<String> historique;
	
	/**
	 * Numéro du tour
	 */
	int turn;
	
	/**
	 * Nombre d'IA dans la partie
	 */
	int ia;
	
	/**
	 * Scanner qui permet d'analyser ce que le joueur rentre
	 */
	Scanner sc;
	
	public Partie() {
		this.j1 = new Joueur();
		this.j1.setColor("Blanc");
		this.j2 = new Joueur();
		this.j2.setColor("Noir");
		this.pl = new Plateau();
		this.pl.initializePlateau();
		this.historique = new ArrayList<String>();
		this.j1.initKingPos();
		this.j2.initKingPos();
		this.turn = 0;
		this.sc = new Scanner(System.in);
		this.ia = setIa();
		
	}
	
	/**
	 * Demande a l'utilisateur combien d'IA il y aura lors de la partie.
	 * 
	 * @return Le nombre d'IA lors de la partie
	 */
	private int setIa() {
		int nbIA;
		System.out.println("Entrée le nombre d'IA qu'il y aura dans la partie (entre 0 et 2)");
		
		String res =  this.sc.nextLine();
		nbIA = res.charAt(0) - '0';
		
		while(nbIA > 2 || nbIA < 0) {
			System.out.println("Ce nombre n'est pas valide veuillez rentrez un chiffre entre 0 et 2");
			res =  this.sc.nextLine();
			nbIA = res.charAt(0) - '0';
		}
		return nbIA;
	}
	
	/**
	 * @return Le joueur 1
	 */
	public Joueur getJoueur1() {
		return this.j1;
	}
	
	/**
	 * @return Le joueur 2
	 */
	public Joueur getJoueur2() {
		return this.j2;
	}
	
	/**
	 * Demande au joueur de saisir une action ou a l'IA de jouer.
	 * 
	 * @return true
	 */
	public boolean gestionPartie() {
		if( this.ia == 0) {
			if(this.turn%2==0) {
				System.out.println("J1 veuillez saisir une action : ");
			}
			else {
				System.out.println("J2 veuillez saisir une action : ");
			}
			String str = this.sc.nextLine();
			
			while(str.equals("quit") == false && str.equals("exit") == false) {
				if (str.equals("undo")) {
					System.out.println("Combien de retour voulez-vous opérer");
					str = sc.nextLine();
					this.gestionUndo(str);
					
				}
				
				if(this.gestionTour(str) ==  true) {
					
					
					if(this.turn%2==0) {
						System.out.println("J1 veuillez saisir une action : ");
					}
					else {
						System.out.println("J2 veuillez saisir une action : ");
					}
					
					str = sc.nextLine();
				}
				else { if(this.turn%2==0 && str.equals("undo") == false) {
						System.out.println("Action invalide, J1 veuillez saisir une action : ");
					}
					else if(str.equals("undo") == false){
						System.out.println("Action invalide, J2 veuillez saisir une action : ");
						
					}
				str = sc.nextLine();
				}
			}
		}
		
		else if(this.ia == 1){
			System.out.println("J1 veuillez saisir une action : ");
			String str = this.sc.nextLine();
			
			while(str.equals("quit") == false && str.equals("exit") == false ) {
				// Si l'action du joueur est bonne ou si l'IA a jouée (action true)
				if(this.gestionTour(str) ==  true || this.turn%2 == 1) {
					if(str.equals("position")) {
						this.pl.affichePieces();
					}
					
					else if(this.turn%2==0) {
						System.out.println("J1 veuillez saisir une action : ");
					}
					else{
						System.out.println("L'IA joue");
						
						while(this.gestionTourIA(this.j2)== false);
					}
						
					str = sc.nextLine();
				}
				
				// Si l'action du joueur est impossible
				else {
					if(this.turn%2==0)
						System.out.println("Action invalide, J1 veuillez saisir une action : ");
					str = sc.nextLine();
				}
				
			}
			
		}
		if(this.ia == 2){
			String str = this.sc.nextLine();
			while(str.equals("quit") == false && str.equals("exit") == false && this.turn != 100){
				if(str.equals("position")) {
					this.pl.affichePieces();
				}
				
				else if(this.turn%2 == 0){
					System.out.println("Le joueur 1 joue");
					while(this.gestionTourIA(this.j1) ==  false);
				}
				else {
				System.out.println("Le joueur 2 joue");
				while(this.gestionTourIA(this.j2) == false);
				}
				str =this.sc.nextLine();
			}
		}
		this.sc.close();
		return true;
	}
	
	/**
	 * Analyse la chaine de caractere prise en entree et agis en fonction.
	 * Affiche les pieces si cette action est demandee.
	 * Transforme les caracteres en entier pour avoir les coordonnes de depart et destination.
	 * Apelle la fonction qui verifie si le deplacement est possible.
	 * Affiche si le roi est en echec.
	 * 
	 * @param s	Chaine de caractere
	 * 
	 * @return true si l'action ou deplacement s'est deroulee sans probleme, false sinon.
	 */
	public boolean gestionTour(String s) {
		
		if(s.equals("position")) {
			this.pl.affichePieces();
			return true;
		}
		else if(s.length() != 4) {
			return false;
		}
		
		else {
			if(s.charAt(0) <= 'h' && s.charAt(0) >= 'a' && s.charAt(1) < '9' && s.charAt(1) > '0' && s.charAt(2) <= 'h' && s.charAt(2) >= 'a' && s.charAt(3) < '9' && s.charAt(3) > '0' ) {
				int depx,depy,desx,desy;
				String oldPiece ;
				
				depx = Character.getNumericValue(s.charAt(0)) - Character.getNumericValue('a');
				depy = Character.getNumericValue(s.charAt(1)) - Character.getNumericValue('1');
				desx = Character.getNumericValue(s.charAt(2)) - Character.getNumericValue('a');
				desy = Character.getNumericValue(s.charAt(3)) - Character.getNumericValue('1');
				if(this.pl.T[desx][desy].isAlive) {
					oldPiece = this.pl.T[desx][desy].getName();
				}
				else oldPiece = "N";
				
				if((this.pl.T[depx][depy].couleur == this.j1.couleur && this.turn%2 == 0) || (this.pl.T[depx][depy].couleur == this.j2.couleur && turn%2 == 1)){
					
					if(this.pl.deplacerPiece(depx, depy, desx, desy, this.ia) == true) {
						
						if(this.pl.T[depx][depy].getName().equals("Pion") && (desy == 0 || desy == 7)) {
							this.historique.add(desx+""+desy+""+depx+""+depy+"@"+oldPiece+"@"+"P");
						}
						else 
							this.historique.add(desx+""+desy+""+depx+""+depy+"@"+oldPiece+"@"+"N");
						if(this.pl.T[depx][depy].getName().equals("Roi")){
							
							if(this.turn%2 == 0 ) {
								this.j1.kposx = desx;
								this.j1.kposy = desy;
							}
							if(this.turn%2 == 1 ) {
								this.j2.kposx = desx;
								this.j2.kposy = desy;
							}
						}
						
						
						if(this.j1.echec == 2 && this.echec(this.j1) && this.turn %2 == 0) {
							
							this.turn += 1;
							this.gestionUndo("1");
							System.out.println("Le roi noir est toujours en echec, l'action precedente est annulé .Choisissez une action qui le sorte de cette position");
							return false;
							
						}
					
						
				
						if(this.j2.echec == 2 && this.echec(this.j2)== true && this.turn %2 == 1) {
							this.turn += 1;
							
							this.gestionUndo("1");
							System.out.println("Le roi noir est toujours en echec, l'action precedente est annulé .Choisissez une action qui le sorte de cette position");
							return false;
						}
						if(this.echec(this.j1) == true) {
							System.out.println("Le roi blanc est en echec");
							this.j1.echec = 1;
						}
						else {
							this.j1.echec = 0;
						}
						
						if(this.echec(this.j2) ==  true) {
							System.out.println("Le roi noir est en echec");
							this.j2.echec = 1;
						}
						else {
							this.j2.echec = 0;
						}
						
						if(this.j1.echec == 1) {
							if(this.echecEtMat(this.j1)) {
								System.out.println("Echec et mat, J2 a gagné !");
								System.exit(1);
							}
							else {
								
								this.j1.echec = 2;
								
							}
						}
						if(this.j2.echec == 1) {
							
							
							if(this.echecEtMat(this.j2)) {
								System.out.println("Echec et mat, J1 a gagné !");
								System.exit(1);
							}
							else {
								this.j2.echec = 2;
								System.out.println(this.turn+" "+this.j2.echec);
							}
							
						}
						this.turn += 1;
						return true;
					}
					else {
						return false;
					}
				}
			}
			else {
				return false;
			}
		}
		return false ;
	}
	
	/**
	 * Test pour le joueur pris en parametre si son roi est bloque ou non.
	 * 
	 * @param j L'un des deux joueurs
	 * 
	 * @return true si le joueur est en echec, false sinon
	 */
	public boolean echec(Joueur j) {
		int i,k;
		for(i=0;i<8;++i) {
			for(k=0;k<8;++k) {
				if(this.pl.T[i][k].couleur != j.couleur && this.pl.T[i][k].isAlive) {
					if(this.pl.T[i][k].verifDeplacement(i, k, j.kposx,j.kposy, this.pl) ==  true) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Test si en fonction de la position du roi si il y a Echec et Mat
	 * 
	 * @param j L'un des deux joueurs
	 * 
	 * @return true si le joueur est en position d'echec et mat
	 */
	public boolean echecEtMat(Joueur j) {
		int i,k,l,m,kx,ky,x, y;
		String oldPiece = "N";
		kx = j.kposx;
		ky = j.kposy;
		for(i=0;i<3;++i) {
			for(k=0;k<3;++k) {
				x= kx+i-1;
				y = ky+k-1;
				if(this.pl.T[kx][ky].verifDeplacement(kx, ky, x, y, this.pl)) {
					if(this.pl.T[x][y].isAlive){
						oldPiece = this.pl.T[x][y].getName();
					}
					if(	this.pl.deplacerPieceEchecEtMat(kx,ky,x,y)){
						
						this.historique.add(x+""+y+""+kx+""+ky+"@"+oldPiece+"@"+"N");
						j.kposx = x;
						j.kposy = y;
					
						if(echec(j) == false) {
							
							this.turn+=1;
							this.gestionUndo("1");
							System.out.println(this.pl.T[x][y].toString()+ "1");
							j.kposx = kx;
							j.kposy = ky;
							return false;
						}
						else {
							this.turn+=1;
							this.gestionUndo("1");
							j.kposx = kx;
							j.kposy = ky;
						}
					}
				}
			}
		}
		for(i=0;i<8;++i) {
			for(k=0;k<8;++k) {
				if(this.pl.T[i][k].couleur == j.couleur){
					for(l=0;l<8;++l) {
						for(m=0;m<8;++m) {
							if(this.pl.T[i][k].verifDeplacement(i,k,l,m,this.pl)){
								
								if(this.pl.T[l][m].isAlive){
									oldPiece = this.pl.T[l][m].getName();
								}
								this.pl.deplacerPieceEchecEtMat(i,k,l,m);
								this.historique.add(l+""+m+""+i+""+k+"@"+oldPiece+"@"+"N");
								this.turn += 1;
								
								if(this.echec(j) == true){
									
									gestionUndo("1");
								}
								else { 
									System.out.println(this.pl.T[l][m].toString()+"2");
									gestionUndo("1");
								    return false;
								 }
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * En cas d'echec le roi tente de bouger avant de faire de l'aleatoire dans l'IA.
	 * 
	 * @param j L'un des deux joueurs
	 * 
	 * @return true si le Roi a reussis a s'echaper
	 */
	public boolean kingEscape(Joueur j) {
		int i,k,kx,ky,x, y;
		String oldPiece = "N";
		kx = j.kposx;
		ky = j.kposy;
		for(i=0;i<3;++i) {
			for(k=0;k<3;++k) {
				x= kx+i-1;
				y = ky+k-1;
				if(this.pl.T[kx][ky].verifDeplacement(kx, ky, x, y, this.pl)) {
					if(this.pl.T[x][y].isAlive){
						oldPiece = this.pl.T[x][y].getName();
					}
					if(	this.pl.deplacerPiece(kx,ky,x,y,this.ia)){
						
						this.historique.add(x+""+y+""+kx+""+ky+"@"+oldPiece+"@"+"N");
						j.kposx = x;
						j.kposy = y;
					
						if(echec(j) == false) {
						
							j.kposx = kx;
							j.kposy = ky;
							return true;
						}
						else {
							this.turn+=1;
							this.gestionUndo("1");
							System.out.println("J1 toujours en echec autre tentative");
							j.kposx = kx;
							j.kposy = ky;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Selectionne aleatoirement une piece que le joueur peux deplacer et lui fait realiser un deplacement aleatoire.
	 * 
	 * @param j	L'un des deux joueurs
	 * 
	 * @return true si le deplacement de la piece est possible, false sinon
	 */
	public boolean gestionTourIA(Joueur j){
		
		int depx = (int)(Math.random() * (8-0));
		int depy = (int)(Math.random() * (8-0));
		
		int desx = (int)(Math.random() * (8-0));
		int desy = (int)(Math.random() * (8-0));
		
		// DEPART
		// Test si pièce sur la case et si pièce du bon joueur
		while(this.pl.T[depx][depy].isAlive == false || this.pl.T[depx][depy].couleur != j.couleur){
				depx = (int)(Math.random() * (8-0));									
				depy = (int)(Math.random() * (8-0));	
				/*System.out.println(depx +" "+depy);*/
		}
		
		// DESTINATION
		// Test si arrive sur une pièce du même joueur
		
		while(this.pl.T[desx][desy].couleur == j.couleur){
				desx = (int)(Math.random() * (8-0));									
				desy = (int)(Math.random() * (8-0));	
				/*System.out.println(desx +" "+desy);*/
		}
		String oldPiece = "N";
		if(this.pl.T[desx][desy].isAlive){
			oldPiece = this.pl.T[desx][desy].getName();
		}
		if(this.j1.echec == 2 && this.echec(this.j1) && this.turn %2 == 0) {
			if(this.kingEscape(this.j1)) {
				return true;
			}
		}
		if(this.j1.echec == 2 && this.echec(this.j1) && this.turn %2 == 0) {
			if(this.kingEscape(this.j1)) {
				return true;
			}
		}
		if(this.pl.deplacerPiece(depx, depy, desx, desy, this.ia) == true) {
			
			
			this.historique.add(desx+""+desy+""+depx+""+depy+"@"+oldPiece+"@"+"N");
			
			if(this.j1.echec == 2 && this.echec(this.j1) && this.turn %2 == 0) {
				
				this.turn += 1;
				this.gestionUndo("1");
				System.out.println("J1 toujours en echec autre tentative");
				return false;
				
			}
			if(this.j2.echec == 2 && this.echec(this.j2) && this.turn %2 == 1) {
				
				this.turn += 1;
				this.gestionUndo("1");
				System.out.println("J2 toujours en echec autre tentative");
				return false;
				
			}
			if(this.echec(this.j1)) {
				System.out.println("J1 est en echec");
				if(this.echecEtMat(this.j1)) {
					System.out.println("Echec et mat, J2 a gagné !");
					System.exit(1);
				}
				else this.j1.echec = 2;
			}
			else {
				this.j1.echec = 0;
			}
			
			if(this.echec(this.j2)) {
				System.out.println("J2 est en echec");
				if(this.echecEtMat(this.j2)) {
					System.out.println("Echec et mat, J1 a gagné !");
					System.exit(1);
				}
				else this.j2.echec = 2;
			}
			else {
				this.j2.echec = 0;
			}
				this.turn += 1;
				return true;
			}
		
		
		return false;
	}
	
	/**
	 * Utilise historique qui est une liste de chaine de caracteres.
	 * La partie est sauvegardé dans historique a chaque action.
	 * Le undo permet de revenir en arriere sur ces mouvements.
	 * 
	 * @param s Chaine de caractere
	 */
	public void gestionUndo(String s) {
		int number = 0;
		int mult = 1;
		for(int i = s.length()-1; i >= 0; i--) {
			if (s.charAt(i) > '9' && s.charAt(i) < '0') {
				number = -1;
				break;
			}
			number += Character.getNumericValue(s.charAt(i))*mult;
			mult *= 10;
		}
	
		
		if((number > -1) && (number <= this.historique.size())) {
			for(int i = 0; i < number; i++) {
				
				String str = this.historique.get(this.historique.size()-1);
				if(str.length() == 4) {
					
					this.pl.deplacerPieceUndo(str.charAt(0)-'0', str.charAt(1)-'0', str.charAt(2)-'0', str.charAt(3)-'0',"","");
				}
				else if(str.length() > 4) {
					
					String strArr[] = str.split("@");
					this.pl.deplacerPieceUndo(strArr[0].charAt(0)-'0', strArr[0].charAt(1)-'0', strArr[0].charAt(2)-'0', strArr[0].charAt(3)-'0',strArr[1],strArr[2]);
				}
				
				this.historique.remove(this.historique.size()-1);
			}
		}
		else
			System.out.println("vous avez depasser la taille de l'historique ou rentré une chaîne qui ne représente pas un nombre");
		
		this.turn -= number;
	}
}
