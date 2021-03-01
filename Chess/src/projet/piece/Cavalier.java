package projet.piece;

public class Cavalier extends Piece {

	public Cavalier() {
		super();
		this.name = "Cavalier";
	}
	
	/**
	 * Retourne si le deplacement vers la case souhaitee est valide ou non en fonction du type de mouvement de la piece.
	 * 
	 * @param depx	Coordonnee en abscisse de la case de depart
	 * @param depy	Coordonnee en ordonnee de la case de depart
	 * @param desx	Coordonnee en abscisse de la case de depart
	 * @param desy	Coordonnee en ordonnee de la case de depart
	 * @param P		Plateau de la partie
	 * 
	 * @return true si deplacement valide sinon false
	 */
	public boolean verifDeplacement(int depx, int depy, int desx, int desy, Plateau P) {
		if(P.T[desx][desy].isAlive) {
			if(this.couleur == P.T[desx][desy].couleur) {
				return false;
			}
		}
		if((desx < 8) || (desy < 8) || (desx >= 0) || (desy >= 0)){
			if((desx == depx+1) || (desx == depx-1)){
				if((desy == depy+2) || (desy == depy-2)) {
					return true;
				}
				else {
					return false;
				}
			}
			
			if((desx == depx+2) || (desx == depx-2)){
				if((desy == depy+1) || (desy == depy-1)) {
					return true;
				}
				else {
					return false;
				}
			}
				
		}
		
		return false;
	}

}
