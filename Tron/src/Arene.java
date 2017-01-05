
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * La classe Arene qui sera une sous-classe de JComponent et qui servira à définir votre propre composant graphique qui s'occupera de dessiner l'arène de jeu et les traces des joueurs
 * @author M.H.ERFANIAN AZMOUDEH et Vivianne NGUYEN-DONG
 *
 */
@SuppressWarnings("serial")
public class Arene extends JComponent{
	
	protected int largeur_grille;
	protected int hauteur_grille;
	
	private Trace enceinte;
	private static double echelleX;
	private static double echelleY;
	private final int MARGIN = 5;
	
	Graphics2D g2;
	int l_cell;
	//nRowColumn ca veut dire que le nombre des rengees egale le nombre des colomns
	static int nRowColumn=20;
	TronPanel tp;
	JLabel lblGagnant;
	

	public Arene(TronPanel tp){
		this.tp = tp;
		largeur_grille = 500;
		hauteur_grille = 500;
		this.setFocusable(true);
		enceinte = new Trace(new Segment(new Point(MARGIN,MARGIN), new Point(largeur_grille,MARGIN)));
		enceinte.getSgmtsDuTrace().add(new Segment(new Point(largeur_grille,hauteur_grille), new Point(MARGIN,largeur_grille)));
	}
		

	/**
	 * la redefiniton de la méthode paintComponent (définie dans JComponent) pour qu'elle dessine l'arène de jeu et son contenu (le mur d'enceinte et toutes les traces des joueurs) au complet à chaque fois qu'elle est appelée. Le code de cette méthode devra bien sûr se baser sur les dimensions de la grille de jeu virtuelle, et sur les  informations contenues dans la Trace de chaque joueur (couleur, et coordonnées dans la grille vitruelle). Et elle devra effectuer un calcul simple de changement de coordonnées (changement d'échelle) pour appeler les instructions de tracé adaptées aux dimensions réelles en pixels de votre composant graphique. 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Recover Graphics2D
		g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.yellow);
		echelleX= (double)(getWidth()-MARGIN*2)/largeur_grille;
		echelleY = (double)(getHeight()-MARGIN*2)/hauteur_grille;
		g2.drawRect(MARGIN,MARGIN,(int)(largeur_grille*echelleX), (int)(hauteur_grille*echelleY));		
		
		l_cell = largeur_grille/nRowColumn;
				
		g2.setColor(Color.LIGHT_GRAY);
		
		for(int j=1; j<nRowColumn; j++){
			for(int i=0; i<nRowColumn; i++){			
				Trace grilleHor= new Trace(new Segment(new Point(i,j), new Point(i+1,j)));
				drawGrille(grilleHor);
			}
		}

		for(int i=1; i<nRowColumn; i++){
			for(int j=0; j<nRowColumn; j++){			
				Trace grilleVer= new Trace(new Segment(new Point(i,j), new Point(i,j+1)));
				drawGrille(grilleVer);
			}
		}
		
		drawTraceDuJoueur();		
	}

	/**
	 * la methode reacheWall pour verifier si la tete d'une trace heurte le mur de bordur ou pas
	 * @param p
	 * @return
	 */
	static boolean reachedWall(Point p){
		if(p.x<=0 || p.x>=nRowColumn || p.y<=0 || p.y>=nRowColumn)
			return true;
		return false;
	}
	
	/**
	 * la methode pour dessiner le trace des joueurs
	 */
	void drawTraceDuJoueur(){
		Point p1, p2 =null;		
		for(Joueur j: TronPanel.joueurs){
			if(j!=null){
				for(int i=0; i<j.getTrace().getSgmtsDuTrace().size(); i++){
				p1 = j.getTrace().getSgmtsDuTrace().get(i).getDebut();
				p2 = j.getTrace().getSgmtsDuTrace().get(i).getFin();
				g2.setColor(j.getColor());
				int x1 = MARGIN + (int)(p1.x*l_cell*echelleX);
				int y1 = MARGIN + (int)(p1.y*l_cell*echelleY);
				int x2 = MARGIN + (int)(p2.x*l_cell*echelleX);
				int y2 = MARGIN + (int)(p2.y*l_cell*echelleY);
				g2.setStroke(new BasicStroke(5));
				g2.drawLine(x1, y1, x2, y2);
				g2.setColor(Color.CYAN);
				g2.drawLine(x2,y2,x2+1, y2+1);
				
				}
			}
		}	
	}
	
	/**
	 * la methode pour dessiner le grille de l'Arene
	 * @param grille
	 */
	void drawGrille(Trace grille){
		Point p1 = grille.tete();
		Point p2 = grille.pointAvantTete();
		int x1 = MARGIN + (int)(p1.x*l_cell*echelleX);
		int y1 = MARGIN + (int)(p1.y*l_cell*echelleY);
		int x2 = MARGIN + (int)(p2.x*l_cell*echelleX);
		int y2 = MARGIN + (int)(p2.y*l_cell*echelleY);
		g2.drawLine(x1, y1, x2, y2);
	}

}

