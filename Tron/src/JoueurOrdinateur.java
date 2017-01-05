
import java.awt.Color;
/**
 * la classe JoueurOrdinateur heritant la classe Joueur permettant pour verfier si 
 * un joueur est humain ou ordinateur. cette classe comporte aussi la partie 
 * intelligence artificielle de la joueur ordinateur
 * @author M.H.ERFANIAN AZMOUDEH et Vivianne NGUYEN-DONG
 *
 */
public class JoueurOrdinateur extends Joueur{

	Arene arene;
	Trace trace;
	
	public JoueurOrdinateur(Color couleur, Trace trace, String nom){
		super(couleur,trace,nom);
	}

	/**
	 * une methode pour recuperer le point apres la tete curent de la trace de joueur
	 * @return point
	 */
	Point pointApresTete(){
		Point teteProchaine = null;
		if(dirCurrent()=='N'){//up			
			teteProchaine = new Point (getTrace().tete().x, getTrace().tete().y-1);
		}else if(dirCurrent()=='S'){//down
			teteProchaine = new Point (getTrace().tete().x, getTrace().tete().y+1);
		}else if(dirCurrent()=='O'){//right
			teteProchaine = new Point (getTrace().tete().x-1, getTrace().tete().y);
		}else if(dirCurrent()=='E'){//left
			teteProchaine = new Point (getTrace().tete().x+1, getTrace().tete().y);
		}
		//System.out.println("teteprochain ordi: x: "+teteProchaine.x+" y: "+ teteProchaine.y);
		return teteProchaine;
	}
	
	/**
	 * la methode pour changer la direction du joueur vers droit
	 */
	void droit(){
		if(dirCurrent()=='N'){//up
			nouvelleDirection('E');
		}else if(dirCurrent()=='S'){//down
			nouvelleDirection('O');
		}else if(dirCurrent()=='O'){//right
			nouvelleDirection('N');
		}else if(dirCurrent()=='E'){//left
			nouvelleDirection('S');
		}
	}
	
	/**
	 * la methode pour changer la direction du joueur vers gauche
	 */
	void gauche(){
		if(dirCurrent()=='N'){//up
			nouvelleDirection('O');
		}else if(dirCurrent()=='S'){//down
			nouvelleDirection('E');
		}else if(dirCurrent()=='O'){//left
			nouvelleDirection('S');
		}else if(dirCurrent()=='E'){//right
			nouvelleDirection('N');
		}
	}
	
	/**
	 * la methode pour verifier si une changement de la direction du joueur vers droit est permit ou pas
	 * @return boolean
	 */
	boolean droitPermit(){
		Point teteProchaine = null;
		int x = getTrace().tete().x;
		int y = getTrace().tete().y;
		if(dirCurrent()=='N'){//up
			//la prochaine direction est Est
			teteProchaine= new Point(x+1, y);
			if(!getTrace().contient(teteProchaine) && !Arene.reachedWall(teteProchaine)){			
				return true;	
			}
		}else if(dirCurrent()=='S'){//down
			//la prochaine direction est Ouest
			teteProchaine= new Point(x-1, y);
			if(!getTrace().contient(teteProchaine) && !Arene.reachedWall(teteProchaine)){
				return true;	
			}
		}else if(dirCurrent()=='O'){//left
			//la prochaine direction est Nord
			teteProchaine= new Point(x, y-1);
			if(!getTrace().contient(teteProchaine) && !Arene.reachedWall(teteProchaine)){
				return true;	
			}
		}else if(dirCurrent()=='E'){//right
			//la prochaine direction est Sud
			teteProchaine= new Point(x, y+1);
//			System.out.println("en ordi x: "+ teteProchaine.x + " y: "+ teteProchaine.y);
			if(!getTrace().contient(teteProchaine) && !Arene.reachedWall(teteProchaine)){
				return true;	
			}
		}
		return false;
	}
	
	/**
	 * la methode pour verifier si une changement de la direction du joueur vers gauche est permit ou pas
	 * @return boolean
	 */
	boolean gauchePermit(){
		Point teteProchaine = null;
		int x = getTrace().tete().x;
		int y = getTrace().tete().y;
		if(dirCurrent()=='N'){//up
			//la prochaine direction est Ouest
			teteProchaine= new Point(x-1, y);
			if(!getTrace().contient(teteProchaine) && !Arene.reachedWall(teteProchaine)){
				return true;	
			}
		}else if(dirCurrent()=='S'){//down
			//la prochaine direction est Est
			teteProchaine= new Point(x+1, y);
			if(!getTrace().contient(teteProchaine) && !Arene.reachedWall(teteProchaine)){
				return true;	
			}
		}else if(dirCurrent()=='O'){//left
			//la prochaine direction est Sud
			teteProchaine= new Point(x, y+1);
			if(!getTrace().contient(teteProchaine) && !Arene.reachedWall(teteProchaine)){
				return true;	
			}
		}else if(dirCurrent()=='E'){//right
			//la prochaine direction est Nord
			teteProchaine= new Point(x, y-1);
//			System.out.println("en ordi x: "+ teteProchaine.x + " y: "+ teteProchaine.y);
			if(!getTrace().contient(teteProchaine) && !Arene.reachedWall(teteProchaine)){
				return true;	
			}
		}
		return false;
	}
}

