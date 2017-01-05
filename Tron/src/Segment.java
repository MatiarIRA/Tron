
public class Segment {
	private Point debut;//debut: x, y
	private Point fin; // fin: x,y
	
	public Segment(Point debut, Point fin) {
		this.debut = debut;
		this.fin = fin;
	}
	
	public Point getDebut() {
		return debut;
	}
	public void setDebut(Point debut) {
		this.debut=debut;
	}
	
	public Point getFin() {
		return fin;
	}
	public void setFin(Point fin) {
		this.fin = fin;
	}
}
