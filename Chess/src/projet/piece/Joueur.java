package projet.piece;
import projet.piece.Piece.Color;

public class Joueur {
	
	/**
	 * Couleur du joueur
	 */
	public Color couleur;
	
	/**
	 * Position en ordonnee du roi
	 */
	public int kposx;
	
	/**
	 * Position en abscisse du roi
	 */
	public	int kposy;
	
	/**
	 * Information sur la situation du joueur
	 */
	public int echec;
	
	public Joueur() {
		this.echec = 0;
		
	}
	
	/**
	 * Initialise la couleur du joueur en fonction de la chaine de caractere en entree.
	 * 
	 * @param s	Chaine de caractere
	 */
	public void setColor(String s) {
		
		if(s.equals("Blanc")){
			this.couleur = Color.BLANC;
		}
		if(s.equals("Noir")){
			this.couleur = Color.NOIR;
		}
		
	}
	
	/**
	 * Sauvegarde la position du roi en fonction de la couleur des piece du joueur.
	 */
	public void initKingPos() {
		if(this.couleur ==  Color.BLANC) {
			this.kposx = 4;
			this.kposy = 0;
		}
		else if(this.couleur ==  Color.NOIR) {
			this.kposx = 4;
			this.kposy = 7;
		}
	}
	
}
