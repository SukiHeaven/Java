package projet.piece;

public class Roi extends Piece{
	public Roi() {
		
		super();
		this.name = "Roi";
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
		if(desx > 7 || desy > 7 || desx < 0 || desy < 0) {
			return false;
		}
		
		if(P.T[desx][desy].isAlive) {
			if(this.couleur == P.T[desx][desy].couleur) {
				return false;
			}
		}		
		if((desx == depx) && (desy == depy)) {
			return false;
		}
		
		else {
			
				if(((desx == depx+1) || (desx == depx-1) || (desx == depx))
					&& ((desy == depy+1) || (desy == depy-1) || (desy == depy))) {
						return true;
			}
		return false;
		}
	}
}
