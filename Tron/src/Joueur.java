
import java.awt.Color;
import java.util.Random;


/**
 * La classe Joueur pour représenter un Joueur. 
 * @author M.H.ERFANIAN AZMOUDEH et Vivianne NGUYEN-DONG
 *
 */
public class Joueur {
	
	private Trace trace;
	private Color couleur;
	private String nom; // peut etre enleve
	protected boolean enVie;
	private char dirCurrent;
	static boolean dejaAlonge = false;
	
	public Joueur(Color couleur, Trace trace, String nom){
		this.couleur = couleur;
		this.trace = trace;
		this.setNom(nom);
		enVie = true;
		setDirCurrent(dirCurrent());
		
	}
	
	Trace getTrace(){
		return trace;
	}
	
	Color getColor(){
		return couleur;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	void tuer(){
		enVie = false;
	}
	
	boolean mort(){
		return !enVie;
	}
	
	void setDirCurrent(char dirCurrent) {
		if(dirCurrent=='N'){
			
		}
		
	}
	
	/**
	 * la methode pour retourner la direction current du joueur
	 * @return la direction current 
	 */
	char dirCurrent(){
		Point posAvantTete = null;
		if(trace.getSgmtsDuTrace().size()<1){
			return'-';
		}else{
			posAvantTete = trace.getSgmtsDuTrace().getLast().getDebut();
		}
		Point posTete = trace.tete();
		
		int dirX = posTete.x - posAvantTete.x;
		int dirY = posTete.y - posAvantTete.y;
		
		if(dirX<0){
			return 'O';
		}else if(dirX>0){
			return 'E';
		}else if(dirY<0){
			return 'N';
		}else{
			return 'S';
		}		
	}
	
	/**
	 * la methode pour choisir une direction initiale aleatoirement pour chaque joueur
	 * @return une direction
	 */
	static char dirInitialAleatoir(){
		int rand = new Random().nextInt(4);
		switch(rand){
			case 0: return 'E';
			case 1: return 'O';
			case 2: return 'N';
			case 3: return 'S';
		}
		return 'E';	//as default	 
	}
	
	/**
	 * la methode pour changer la direction current du joueur
	 * @param c
	 */
	void nouvelleDirection(char c){
		dirCurrent=c;
		dejaAlonge = true;
		getTrace().allonge(dirCurrent, this);
	}
}

