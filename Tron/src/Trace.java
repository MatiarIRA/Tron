
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * classe segments pour représenter la segments d'un joueur (ou du contour de l'arène de jeu).
 * @author M.H.ERFANIAN AZMOUDEH et Vivianne NGUYEN-DONG
 *
 */
public class Trace {
	/**
	 * segments contiendra une liste d'objets de type Segment
	 */
	private LinkedList<Segment> segments;
	@SuppressWarnings("unused")
	private Point tete;

	public Trace(Segment s0) {
		segments = new LinkedList<>();
		segments.add(s0);
		setTete(tete());
	}
	
	public LinkedList<Segment> getSgmtsDuTrace() {
		return segments;
	}

	public void setSgmtsDuTrace(LinkedList<Segment> segments) {
		this.segments = segments;
	}
	
	
	/**
	 * La méthode allonge  prenant en paramètre une direction (par exemple un char ou un autre type pour représenter les directions Nord, Sud, Est ou Ouest) et qui allongera la segments dans cette direction. Elle pourrait selon le cas, modifier l'extrémité du dernier segment pour le rallonger, ou ajouter un nouveau segment si il y a changement de direction par rapport au dernier.
	 * @param direction
	 * @s
	 */
	void allonge(char direction, Joueur j){

			Point teteProchaine = null;
			
			if(direction=='N'){//up			
				teteProchaine = new Point (tete().x, tete().y-1);
			}else if(direction=='S'){//down
				teteProchaine = new Point (tete().x, tete().y+1);
			}else if(direction=='O'){//right
				teteProchaine = new Point (tete().x-1, tete().y);
			}else if(direction=='E'){//left
				teteProchaine = new Point (tete().x+1, tete().y);
			}//else{j.mort(); return;}

			if(contient(teteProchaine)){
				j.tuer();
				System.out.println(TronPanel.counter);
				TronPanel.counter++;
			}
			//si les directions sont la meme??!!
			segments.addLast(new Segment(tete(), teteProchaine));
//		}
	}
	
	void setTete(Point tete){
		this.tete = tete;
	}
	
	/**
	 * La méthode Point tete() qui retournera le point correspondant à la tête de chaque joueur.
	 * @return point
	 */
	Point tete(){
		return ((Segment) segments.getLast()).getFin();		
	}
	
	/**
	 * la methode pour recuperer le point avant tete de chaque joueur
	 * @return point
	 */
	Point pointAvantTete(){
		return ((Segment)segments.getLast()).getDebut();
	}
		
	/**
	 * méthode boolean contient(Point p) pour vérifier si la segments (reliant tous les segments), passe par ce point.
	 * @param p
	 * @return
	 */
	boolean contient(Point p){
		if(segments.isEmpty())
	        return false;
		
	      ListIterator<Segment> it = segments.listIterator();
	      Point pd, pf; // pd: pointDebut et pd: pointFin
	      pd = it.next().getDebut();
	      while(it.hasNext()){
	        pf = it.next().getFin();
	        if(pd.x==pf.x && p.x==pd.x){
	          if(pd.y<=pf.y && p.y>=pd.y && p.y<=pf.y){
	        	  //System.out.println("True 1");
	        	  return true;
	          }
	          if(pd.y>=pf.y && p.y>=pf.y && p.y<=pd.y){
	        	  //System.out.println("True 2");
	        	  return true;
	          }
	        }
	        else if(pd.y==pf.y && p.y==pd.y){
	          if(pd.x<=pf.x && p.x>=pd.x && p.x<=pf.x){
	        	  //System.out.println("True 3");
	        	  return true;
	          }
	          if(pd.x>=pf.x && p.x>=pf.x && p.x<=pd.x){
	        	 //System.out.println("True 4");
	        	  return true;
	          }
	        }
	        pd = pf;
	      }
	      return false;            
	}
	
	
}

