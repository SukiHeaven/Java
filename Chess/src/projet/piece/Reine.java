package projet.piece;

public class Reine extends Piece{
	
	public Reine() {
		super();
		this.name = "Reine";
	}
	
	/**
	 * Retourne si une piece est en travers de la trajectoire prise en entree (une diagonale).
	 * 
	 * @param depx	Coordonnee en abscisse de la case de depart
	 * @param depy	Coordonnee en ordonnee de la case de depart
	 * @param desx	Coordonnee en abscisse de la case de depart
	 * @param desx	Coordonnee en ordonnee de la case de depart
	 * @param P		Plateau de la partie
	 * 
	 * @return false si pas de pièce sur la trajectoire ou true
	 */
	public boolean surLaDiagonale(int depx, int desx, int depy,int desy, Plateau P){
		int i,j;
		
		// Diagonale haut droite (fonctionne)
		if((depx < desx) && (depy < desy)){
			
			i = depx+1;
			for( j = depy+1; j<desy;++j){	
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
				if(P.T[i][j].isAlive) {
					return false;
				}
				i = i-1;
			}
		}
		return true;
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
		
		if(desx > 7 || desy > 7 || desx < 0 || desy < 0)
			return false;
		
		if(depx == desx && depy == desy)
			return false;

		// Cas Deplacement en +
		if((depx == desx) || (depy == desy)){
			if(depx != desx) {
				if(this.surLaLigne(depx, desx, desy, P) ==  true) {
					if(P.T[desx][desy].isAlive && P.T[desx][desy].couleur == P.T[depx][depy].couleur)
						return false;
				}
				else return false;
			}
		
			if(depy != desy) {
				if(this.surLaColonne(depy, desy, desx, P) ==  true) {
					if(P.T[desx][desy].isAlive && P.T[desx][desy].couleur == P.T[depx][depy].couleur)
						return false;
				}
				else return false;
			}
			return true;
		}

		// Cas d�placement en diagonale
		
	
		if((depx-desx)!= (depy-desy) && (depx - desx) != (desy -depy)) {
			return false;
		}
		
		if(desx-depx == 1 || desx-depx == -1 || desy -depy == 1 || desy -depy == -1) {
			
			if(P.T[desx][desy].isAlive && P.T[desx][desy].couleur == P.T[depx][depy].couleur) {
				
				return false;
			}
		}
		if(this.surLaDiagonale(depx,desx,depy,desy,P)){
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
