package projet.piece;

import projet.piece.Piece.Color;

public class Plateau{
	
	/**
	 * Tableau de piece
	 */
	public Piece T[][];
	
	public Plateau() {
		this.T = new Piece[8][8];
	}
	
	/**
	 * Initialise les pieces de chaque joueurs sur le plateau
	 * Les cases vides prennent la valeur d'un pion mort
	 */
	public void initializePlateau() {
		int i,j;
		
		
		for(i = 0;i<8;++i) {
			this.T[i][1] = new Pion();
			this.T[i][6] = new Pion();
			switch(i) {
			case 0: 
				this.T[i][0] = new Tour();
				this.T[i][7] = new Tour();
				break;
			case 1: 
				this.T[i][0] = new Cavalier();
				this.T[i][7] = new Cavalier();
				break;
			case 2: 
				this.T[i][0] = new Fou();
				this.T[i][7] = new Fou();
				break;
			case 3: 
				this.T[i][0] = new Reine();
				this.T[i][7] = new Reine();
				break;
			case 4: 
				this.T[i][0] = new Roi();
				this.T[i][7] = new Roi();
				break;
			case 5: 
				this.T[i][0] = new Fou();
				this.T[i][7] = new Fou();
				break;
			case 6: 
				this.T[i][0] = new Cavalier();
				this.T[i][7] = new Cavalier();
				break;
			case 7: 
				this.T[i][0] = new Tour();
				this.T[i][7] = new Tour();
				break;
			default: break;
			}
		}
		for(i=0;i<8;++i) {
			for(j=0;j<2;++j) {
				this.T[i][j].couleur = Color.BLANC;
				this.T[i][7-j].couleur = Color.NOIR;
			}
		}
		for(i=0;i<8;++i) {
			for(j=0;j<8;++j) {
				if(this.T[i][j] == null) {
					this.T[i][j] = new Pion();
					this.T[i][j].isAlive = false;
				}
			}
		}
	}
	
	/**
	 * Affiche a l'ecran les pieces vivantes et leurs coordonnees.
	 */
	void affichePieces() {
		int i,j,x,y;
		for(j=0;j<8;++j) {
			for(i=0;i<8;++i) {
				if(this.T[i][j].isAlive) {
					x = i+'a';
					y = j+1;
					System.out.println(this.T[i][j].toString() + "  x = "+ (char)x + " y = " + y );
				}	
			}
		}
	}
	
	/**
	 * Affiche a l'ecran si il existe ou non une piece aux coordonnees rentrees et si oui sa position.
	 * 
	 * @param x	Coordonnee en abscisse d'une piece
	 * @param y	Coordonnee en ordonnee d'une piece
	 */
	void afficheOnePiece(int x, int y) {
		if(this.T[x][y].isAlive  == false) {
			System.out.println("il n'y a pas de pieces a l'emplacement "+ x +" "+ y);
		}
		else {
			System.out.println(this.T[x][y].toString()+" x = " + x +" y = "+ y);
		}
	}
	
	/**
	 * Deplace la piece dont les coordonnees sont prise en entree si le deplacement est possible.
	 * Affiche a l'ecran si la piece deplacee a mangee une piece ou non.
	 * Realise la promotion du pion si necessaire.
	 * 
	 * @param depx	Coordonnee en abscisse de la piece
	 * @param depy	Coordonnee en ordonnee de la piece
	 * @param desx	Coordonnee en abscisse de la piece
	 * @param desy	Coordonnee en ordonnee de la piece
	 * 
	 * @return true losque lap piece est deplace ou false si le deplacement est impossible
	 */
	public boolean deplacerPiece(int depx, int depy, int desx, int desy, int ia) {
		int x = desx +'a';
		int y = desy+1 ;
		
		
		if(this.T[depx][depy].verifDeplacement(depx, depy, desx, desy, this) && this.T[depx][depy].isAlive) {
			if(this.T[desx][desy].isAlive == true) {
				System.out.println("La piece "+this.T[desx][desy].toString() +" a ete manger par " + this.T[depx][depy].toString()+" qui est maintenant en "+(char)x+" "+y);
			}
			else {
			
				System.out.println(this.T[depx][depy].toString()+" deplacer en "+(char)x+" "+y);
			}

			this.T[desx][desy] = null;
			System.gc();
			if(this.T[depx][depy].getName().equals("Pion")){
				if(desy == 0 || desy == 7){
				if((this.T[depx][depy].couleur == Color.NOIR) && (desy == 0)) {
					if(ia > 0){
					this.T[depx][depy].promotionIA(desx, desy, this);
					}
					else {
						this.T[depx][depy].promotion(desx, desy, this);
					}
				}
				if((this.T[depx][depy].couleur == Color.BLANC) && (desy == 7)) {
					if(ia > 1){
						this.T[depx][depy].promotionIA(desx,desy,this);
					}
					else {
						this.T[depx][depy].promotion(desx,desy,this);
					}
				}
			}
			else {
				this.T[desx][desy] = new Pion();
			}
			this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Tour")) {
				this.T[desx][desy] = new Tour();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Fou")){
				this.T[desx][desy] = new Fou();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Cavalier")) {
				this.T[desx][desy] = new Cavalier();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Reine")) {
				this.T[desx][desy] = new Reine();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Roi")) {
				this.T[desx][desy] = new Roi();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			this.T[depx][depy].isAlive = false;
			return true;
		}
		System.out.println("La piece "+this.T[depx][depy].toString()+  " ne peux aller en "+ (char)x +" "+ y);
		return false;
	}
	
	/**
	 * Deplace la piece dont les coordonnees sont prise en entree si le deplacement est possible.
	 * Permet de tester les deplacements du Roi pour connaitre si il y a echec et mat.
	 * 
	 * @param depx	Coordonnee en abscisse de la piece
	 * @param depy	Coordonnee en ordonnee de la piece
	 * @param desx	Coordonnee en abscisse de la piece
	 * @param desy	Coordonnee en ordonnee de la piece
	 * 
	 * @return true losque la piece est deplace ou false si le deplacement est impossible
	 */
	public boolean deplacerPieceEchecEtMat(int depx, int depy, int desx, int desy) {
		if(this.T[depx][depy].verifDeplacement(depx, depy, desx, desy, this) && this.T[depx][depy].isAlive) {
			if(this.T[desx][desy].isAlive == true) {
				
			}
			this.T[desx][desy] = null;
			System.gc();
			if(this.T[depx][depy].getName().equals("Pion")){
				this.T[desx][desy] = new Pion();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Tour")) {
				this.T[desx][desy] = new Tour();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Fou")){
				this.T[desx][desy] = new Fou();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Cavalier")) {
				this.T[desx][desy] = new Cavalier();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Reine")) {
				this.T[desx][desy] = new Reine();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Roi")) {
				this.T[desx][desy] = new Roi();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			this.T[depx][depy].isAlive = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Deplace les pieces dans a leur ancienne position pour realiser l'undo.
	 * 
	 * @param depx	Coordonnee en abscisse de la piece
	 * @param depy	Coordonnee en ordonnee de la piece
	 * @param desx	Coordonnee en abscisse de la piece
	 * @param desy	Coordonnee en ordonnee de la piece
	 * @param name	Nom de la piece
	 * @param promot	Type de promotion si il y en a eu une
	 * 
	 * @return true true
	 */
	public boolean deplacerPieceUndo(int depx, int depy, int desx, int desy, String name, String promot) {
		
		if(promot.equals("P")) {
			this.T[desx][desy] = null;
			System.gc();
			this.T[desx][desy] = new Pion();
			this.T[desx][desy].couleur = this.T[depx][depy].couleur;
		}
		else {
			this.T[desx][desy] = null;
			System.gc();
			if(this.T[depx][depy].getName().equals("Pion")){
				this.T[desx][desy] = new Pion();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Tour")) {
				this.T[desx][desy] = new Tour();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Fou")){
				this.T[desx][desy] = new Fou();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Cavalier")) {
				this.T[desx][desy] = new Cavalier();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Reine")) {
				this.T[desx][desy] = new Reine();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
			if(this.T[depx][depy].getName().equals("Roi")) {
				this.T[desx][desy] = new Roi();
				this.T[desx][desy].couleur = this.T[depx][depy].couleur;
			}
		}
		if(name.equals("N")) {
			this.T[depx][depy].isAlive = false;
		}
		else {
			this.T[depx][depy] = null;
			System.gc();
			if(name.equals("Pion")){
				this.T[depx][depy] = new Pion();
			}
			if(name.equals("Tour")) {
				this.T[depx][depy] = new Tour();
			}
			if(name.equals("Fou")){
				this.T[depx][depy] = new Fou();
			}
			if(name.equals("Cavalier")) {
				this.T[depx][depy] = new Cavalier();
			}
			if(name.equals("Reine")) {
				this.T[depx][depy] = new Reine();
			}
			if(name.equals("Roi")) {
				this.T[depx][depy] = new Roi();
			}
			
			if(this.T[desx][desy].couleur == Color.BLANC) {
				this.T[depx][depy].couleur = Color.NOIR;
			}
			else if(this.T[desx][desy].couleur == Color.NOIR) {
				this.T[depx][depy].couleur = Color.BLANC;
			}
		}
		
		return true;
	}
}
