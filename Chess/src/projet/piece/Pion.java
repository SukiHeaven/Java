package projet.piece;
import java.util.Scanner;
public class Pion extends Piece{
	
	public Pion() {
		super();
		this.name = "Pion";
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
		
		if(this.couleur == Color.BLANC ) {
			if(desy-depy <= 0) {
				return false;
			}

			if(desy-depy > 2 || desx-depx > 1 || desx-depx < -1) {
				return false;
			}
			if(desy-depy == 2 && depy != 1 ) {
				return false;
			}
			if(desx-depx == -1 || desx-depx == 1) {
				if(P.T[desx][desy].isAlive == false) {
					return false;
				}
				if(P.T[desx][desy].isAlive && this.couleur == P.T[desx][desy].couleur) {
					return false;
				}
			}
			if(desx-depx == 0 && P.T[desx][desy].isAlive) {
				return false;
			}
		}
		if(this.couleur == Color.NOIR) {
			if(desy-depy >= 0) {
				return false;
			}
			if(desy-depy < -2 || desx-depx < -1 || desx-depx > 1) {
				return false;
			}
			if(desy-depy == -2 && depy != 6 ) {
				return false;
			}
			if(desx-depx == 1 || desx-depx == -1) {
				if(P.T[desx][desy].isAlive && this.couleur == P.T[desx][desy].couleur) {
					return false;
				}
			}	
			if(desx-depx == 0 && P.T[desx][desy].isAlive) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Demande a l'utilisateur de choisir en quoi promouvoir son pion.
	 * 
	 * @param desx	Coordonnée en abscisse de la case de départ
	 * @param desy	Coordonnée en ordonnee de la case de départ
	 * @param P		Plateau de la partie
	 */
	public void promotion(int desx,int desy, Plateau P){
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Veuillez choisir en quoi promouvoir votre Pion : ");
		String s = sc2.nextLine();
		
		
		while( !s.equals("Tour") && !s.equals("Fou") && !s.equals("Reine") && !s.equals("Cavalier")){
			System.out.println("Veuillez choisir en quoi promouvoir votre Pion : ");
			s = sc2.nextLine();
		}
		
		if(s.equals("Tour"))
			P.T[desx][desy] = new Tour();
			
		else if(s.equals("Fou"))
			P.T[desx][desy] = new Fou();
		
		else if(s.equals("Cavalier"))
			P.T[desx][desy] = new Cavalier();
			
		else if(s.equals("Reine"))
			P.T[desx][desy] = new Reine();
	
		
	}
	
	/**
	 * Utilisee par l'IA qui pioche une valeur alesatoire et choisis en fonction de sa valeur en quoi promouvoir le pion.
	 * 
	 * @param desx	Coordonnée en abscisse de la case de départ
	 * @param desy	Coordonnée en ordonnee de la case de départ
	 * @param P		Plateau de la partie
	 */
	public void promotionIA(int desx, int desy, Plateau P){
		int a = (int)(Math.random()*(4-0));
		
		switch(a) {
			case 0 :
				P.T[desx][desy] = new Tour();
				System.out.println(this.getName()+ " est devenu une Tour");
				break;
			case 1 : 
				P.T[desx][desy] = new Fou();
				System.out.println(this.getName()+ " est devenu un Fou");
				break;
			case 2 :
				P.T[desx][desy] = new Cavalier();
				System.out.println(this.getName()+ " est devenu un Cavalier");
				break;
			case 3 :
				P.T[desx][desy] = new Reine();
				System.out.println(this.getName()+ " est devenu une Reine");
				break;
		}
	}
	
}
