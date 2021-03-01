package projet.piece;

public class Fou extends Piece{
	
	public Fou() {
		super();
		this.name = "Fou";
	}
	
	/**
	 * Retourne si une piece est en travers de la trajectoire prise en entree (une diagonale).
	 * 
	 * @param depx	Coordonnée en abscisse de la case de départ
	 * @param depy	Coordonnée en ordonnee de la case de départ
	 * @param desx	Coordonnée en abscisse de la case de départ
	 * @param desy	Coordonnée en ordonnee de la case de départ
	 * @param P		Plateau de la partie
	 * 
	 * @return false si pas de piece sur la trajectoire ou true
	 */
	public boolean surLaDiagonale(int depx, int desx, int depy,int desy, Plateau P){
		int i,j;
		
		// Diagonale haut droite (fonctionne)
		if((depx < desx) && (depy < desy)){
			
			i = depx+1;
			for( j = depy+1; j<desy;++j){	
				if(i<=desx) {
					return false;
				}
				if(P.T[i][j].isAlive) {
						return false;
				}
				i = i+1;	
			}
		}

		// Diagonale bas droite
		else if((depx < desx) && (depy > desy)){
			i = depx+1;
			for(j = depy-1; j>desy; --j){
				if(i<=desx) {
					return false;
				}
				if(P.T[i][j].isAlive) {
					return false;
				}
				i = i+1;
			}
		}

		// Diagonale bas gauche
		else if((depx > desx) && (depy > desy)){
			i = depx-1;
			for(j = depy-1;  j>desy; --j){
				if(i>=desx) {
					return false;
				}
				if(P.T[i][j].isAlive) {
					return false;
				}
				i = i-1;
			}
		}

		// Diagonale haut gauche
		else if((depx > desx) && (depy < desy)){
			i = depx -1;
			for(j = depy+1; j<desy; ++j){
				if(i>=desx) {
					return false;
				}
				if(P.T[i][j].isAlive) {
					return false;
				}
				i = i-1;
			}
		}
		return true;
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
		
		if(depx == desx || depy == desy) {
			return false;		
		}
		
		if((depx-desx)!= (depy-desy) && (depx - desx) != (desy -depy)) {
			return false;
		}
		
		if(desx-depx == 1 || desx-depx == -1 || desy -depy == 1 || desy -depy == -1) {
			
			if(P.T[desx][desy].isAlive && P.T[desx][desy].couleur == P.T[depx][depy].couleur) {
				
				return false;
			}
		}
		else if(this.surLaDiagonale(depx,desx,depy,desy,P)){
			
			if(P.T[desx][desy].isAlive && P.T[desx][desy].couleur == P.T[depx][depy].couleur) {
				return false;
			}
		}
		else {
			return false;
		}
		return true;
	}

}
