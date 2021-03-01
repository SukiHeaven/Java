package projet.piece;

public abstract class Piece {
	
	enum Color{
		BLANC,NOIR;
	}
	
	/**
	 * Chaque nom est le type de la piece pour permettre l'affichage
	 */
	public String name;
	
	/**
	 * Un piece vivante prends la valeur isAlive = true
	 */
	public Boolean isAlive;
	
	/**
	 * Chaque piece a un type couleur qui prends la valeur BLANC ou NOIR.
	 */
	public Color couleur;
	
	public Piece() {
	
		this.isAlive = true;
	
	}
	public void promotion(int desx, int desy, Plateau p) {
		
	}
	public void promotionIA(int desx, int desy, Plateau p) {
		
	}
	
	public abstract boolean verifDeplacement(int depx, int depy, int desx, int desy, Plateau P);
	
	/**
	 * @return true si piece vivante ou false si morte
	 */
	public Boolean getEtat() {
		return this.isAlive;
	}
	
	/**
	 * @return La couleur de la piece
	 */
	public Color getColor() {
		return this.couleur;
	}
	
	/**
	 * @return Le nom de la piece
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Une chaine de caractere
	 * 
	 * @return Nom de la piece et sa couleur.
	 */
	public String toString() {
		String s = "";
		if(this.couleur == Color.BLANC) {
			s = this.name +" blanc";
		}
		if(this.couleur == Color.NOIR) {
			s = this.name +" noir";
		}
		return s;
	}
	
	
}
