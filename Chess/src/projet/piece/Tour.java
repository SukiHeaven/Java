package projet.piece;

public class Tour extends Piece {
	
	public Tour() {
		super();
		this.name = "Tour";
		}
	
	/**
	 * Retourne si une piece est en travers de la trajectoire prise en entree (une ligne).
	 * 
	 * @param depx	Coordonnee en abscisse de la case de depart
	 * @param desx	Coordonnee en abscisse de la case de depart
	 * @param P		Plateau de la partie
	 * 
	 * @return false si pas de pièce sur la trajectoire ou true
	 */
	public boolean surLaLigne(int depx, int desx,int y, Plateau P) {
		int i;
		if(depx < desx) {
			for(i = depx+1 ;i< desx ;++i) {
				if( P.T[i][y].isAlive ) {
					return false;
				}
			}
		}
		else if(depx > desx) {
			for(i = depx-1 ;i> desx ;--i) {
				if( P.T[i][y].isAlive ) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Retourne si une piece est en travers de la trajectoire prise en entree (une colonne).
	 * 
	 * @param depy	Coordonnee en ordonnee de la case de depart
	 * @param desy	Coordonnee en ordonnee de la case de depart
	 * @param P		Plateau de la partie
	 * 
	 * @return false si pas de pièce sur la trajectoire ou true
	 */
	public boolean surLaColonne(int depy, int desy,int x, Plateau P) {
		int i;
		if(depy < desy) {
			for(i = depy+1 ;i< desy ;++i) {
				if(P.T[x][i].isAlive) {
					return false;
				}
			}
		}
		
		else if(depy > desy) {
			for(i = depy-1 ;i > desy ;--i) {
				if(P.T[x][i].isAlive) {
					return false;
				}
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
		
		if(depx != desx && depy != desy) {
			return false;
		}
		if(depx == desx && depy == desy) {
			return false;
		}
		if(depx != desx) {
			if(this.surLaLigne(depx, desx, desy, P) ==  true) {
				if(P.T[desx][desy].isAlive && P.T[desx][desy].couleur == P.T[depx][depy].couleur) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		
		if(depy != desy) {
			if(this.surLaColonne(depy, desy, desx, P) ==  true) {
				if(P.T[desx][desy].isAlive && P.T[desx][desy].couleur == P.T[depx][depy].couleur) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return true;
	}
}
