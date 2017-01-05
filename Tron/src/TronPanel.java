

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * La classe TronPanel qui utilisera un LayoutManager de type BorderLayout et placera un Arene dans sa partie Center. Et un TronControlPanel dans une autre partie (ex. East) et qui implémentera la logique et le fonctionnement du jeu.
 * @author M.H.ERFANIAN AZMOUDEH et Vivianne NGUYEN-DONG
 *
 */
@SuppressWarnings("serial")
public class TronPanel extends JPanel implements ActionListener{
	TronPanel tp;
	TronControlPanel tcp;
	static Arene arene;
	Timer timer;
	Color couleur;
	String nom;
	Trace trace;
	private Joueur hum1;
	private Joueur hum2;
	private Joueur ordi;
	protected static ArrayList<Joueur> joueurs;
	private ArrayList<Point> pointAlreadyTaken;
	static int counter=0;
	int nrJoueur=0;
	
	public TronPanel(){		
		this.setLayout(new BorderLayout());
		timer = new Timer(500, this);
		timer.setInitialDelay(500);
		timer.start();
		
		//chaque joueur a sa prope place یu début à la fin du jeu
		joueurs = new ArrayList<>();
		joueurs.add(hum1);
		joueurs.add(hum2);
		joueurs.add(ordi);
		this.setFocusable(true);
	    tcp = new TronControlPanel(this);	    
	    arene = new Arene(this);
	   
	    //implementation de KeyListener pour gerer la direction de chaque joueur
	    arene.addKeyListener(new KeyListener(){
	    	@Override
            public void keyTyped(KeyEvent e) {
	    		char c = e.getKeyChar();
	    		//System.out.println("la touche appuye: "+c);
	    		Joueur hum1 = joueurs.get(0);
	    		Joueur hum2 = joueurs.get(1);
	    		//Joueurs Humain 1
	    		if(hum1!=null && hum1.enVie){
	    			if((hum1.dirCurrent()=='S' && (c == 'w' || c == 'W' || c == 'z' || c == 'Z'))||
		    		   (hum1.dirCurrent()=='N' && (c == 'z' || c == 'Z' || c == 'w' || c == 'W'))||
		    		   (hum1.dirCurrent()=='O' && (c == 's' || c == 'S' || c == 'a' || c == 'A'))||
		    		   (hum1.dirCurrent()=='E' && (c == 'a' || c == 'A' || c == 's' || c == 'S'))){
	    				//System.out.println("do nothing for hum1");
	    			}else{
			    		if(c == 'w' || c == 'W'){
			        		hum1.nouvelleDirection('N');	  
			      	  	}
			    		if(c == 's' || c == 'S'){
			    			hum1.nouvelleDirection('E');	  
			      	  	} 
			    		if(c == 'z' || c == 'Z'){
			    			hum1.nouvelleDirection('S');	  
			      	  	} 
			    		if(c == 'a' || c == 'A'){
			    			hum1.nouvelleDirection('O');	  
			      	  	}
	    			}
	    		}
	    
	    		if(hum2!=null && hum2.enVie){
	    			if((hum2.dirCurrent()=='S' && (c == 'i' || c == 'I' || c == 'm' || c == 'M'))||
		    		   (hum2.dirCurrent()=='N' && (c == 'm' || c == 'M' || c == 'i' || c == 'I'))||
		    		   (hum2.dirCurrent()=='O' && (c == 'k' || c == 'K' || c == 'j' || c == 'J'))||
		    		   (hum2.dirCurrent()=='E' && (c == 'j' || c == 'J' || c == 'k' || c == 'K'))){
	    			//System.out.println("do nothing for hum2");
	    			}else{
			    		//Joueurs Humain 2
			    		if(c == 'i' || c == 'I'){
			        		hum2.nouvelleDirection('N');	  
			      	  	}
			    		if(c == 'k' || c == 'K'){
			    			hum2.nouvelleDirection('E');	  
			      	  	} 
			    		if(c == 'm' || c == 'M'){
			    			hum2.nouvelleDirection('S');	  
			      	  	} 
			    		if(c == 'j' || c == 'J'){
			    			hum2.nouvelleDirection('O');	  
			      	  	}
	    			}
	    		}
            }

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    });
	    
	    arene.setSize(arene.largeur_grille, arene.hauteur_grille);
	    add(arene, BorderLayout.CENTER);
	    add(tcp, BorderLayout.SOUTH);	   
	}
	
	/**
	 * la methode traceInitial met aleatoirement le segment initial de chaque joueur 
	 * quelque part dans l'arene 
	 * @return trace initial comportant un seul segment
	 */
	public Trace traceInitial(){ //contenant le premier segment
		pointAlreadyTaken = new ArrayList<>();
		//verification pour ne pas prendre la meme cordination pour deux joueur
		if(pointAlreadyTaken.size()==3){
			pointAlreadyTaken.clear();
		}
		Point p=null;
		int x1=0;
		int y1=0;
		do{
			x1 = new Random().nextInt((Arene.nRowColumn-4)-4)+4;
			y1 = new Random().nextInt((Arene.nRowColumn-4)-4)+4;
			
			p = new Point(x1,y1);
		}while(pointAlreadyTaken.contains(p));
		pointAlreadyTaken.add(p);
		
		char dir = Joueur.dirInitialAleatoir();
		int x2=0;
		int y2=0;
		switch(dir){
		case 'E': 
			x2=x1+1; y2=y1;
			break;
		case 'O': 
			x2=x1-1; y2=y1;
			break;
		case 'N': 
			x2=x1; y2=y1-1;
			break;
		case 'S': 
			x2=x1; y2=y1+1;
			break;
		}		
		Trace t= new Trace(new Segment(new Point(x1,y1), new Point(x2,y2)));
		//System.out.println("x: "+t.tete().x+" / y:"+ t.tete().y);
		return t;
	}
	
	/**
	 *creer des joueurs selon le type de jeu choisi par le ComboBox
	 * @param codeTypeJeu
	 */
	public void createJoueurs(int codeTypeJeu){
		timer.setDelay(tcp.speed);
		if(codeTypeJeu==0){
			hum1 = new JoueurHumain(Color.BLUE, traceInitial(), "Humain 1");
			joueurs.set(0, hum1);
			hum2 = new JoueurHumain(Color.RED, traceInitial(), "Humain 2");
			joueurs.set(1, hum2);
			joueurs.set(2, null);
			nrJoueur=2;
		}
		
		if(codeTypeJeu==1){
			hum1 = new JoueurHumain(Color.BLUE, traceInitial(), "Humain 1");
			joueurs.set(0, hum1);
			joueurs.set(1, null);
			ordi = new JoueurOrdinateur(Color.GREEN, traceInitial(), "Ordinateur");
			joueurs.set(2, ordi);
			nrJoueur=2;
		}
		if(codeTypeJeu==2){
			hum1 = new JoueurHumain(Color.BLUE, traceInitial(), "Humain 1");
			joueurs.set(0, hum1);
			hum2 = new JoueurHumain(Color.RED, traceInitial(), "Humain 2");
			joueurs.set(1, hum2);
			ordi = new JoueurOrdinateur(Color.GREEN, traceInitial(), "Ordinateur");
			joueurs.set(2, ordi);
			nrJoueur=3;
		}
	}

	/**
	 * la methode pour faire avancement pas a pas de chaque joueur
	 */
	void avancement() {
	
		for(Joueur j: joueurs){
			if(j!=null && j.enVie){
				if(j instanceof JoueurOrdinateur){
					if(Arene.reachedWall(((JoueurOrdinateur)j).pointApresTete())){
						//System.out.println("en tp x: "+((JoueurOrdinateur)j).pointApresTete().x+ " y: "+ ((JoueurOrdinateur)j).pointApresTete().y);
						if(((JoueurOrdinateur) j).droitPermit()){
							((JoueurOrdinateur) j).droit();
						}
						else if(((JoueurOrdinateur) j).gauchePermit()){
							((JoueurOrdinateur) j).gauche();
						}
						else {
							((JoueurOrdinateur) j).tuer();
							counter++;
						}
					}
				}
				if(!Joueur.dejaAlonge){
					j.getTrace().allonge(j.dirCurrent(), j);
				}else{
					Joueur.dejaAlonge = false;
				}
			
				if(Arene.reachedWall(j.getTrace().tete())){
					j.tuer();
					counter++;
					//continue;
				}
				
				for(Joueur p: joueurs){
					if(p!=null){
						if(p.equals(j)){
							continue;
						}
						if(j instanceof JoueurOrdinateur){
							if(p.getTrace().contient(((JoueurOrdinateur)j).pointApresTete()) ||
									j.getTrace().contient(((JoueurOrdinateur)j).pointApresTete())){
								if(((JoueurOrdinateur) j).droitPermit()){
									((JoueurOrdinateur) j).droit();
								}
								else if(((JoueurOrdinateur) j).gauchePermit()){
									((JoueurOrdinateur) j).gauche();
								}
								else {
									if(j.getTrace().contient(j.getTrace().tete())){
										j.tuer();
										counter++;
									}
								}
							}
						}
						else if(p.getTrace().contient(j.getTrace().tete())){
							j.tuer();
							counter++;
							//break;
						}
					}
				}	
			}
			
			if(nrJoueur-counter==1){
				ca: 
				for(Joueur player: joueurs){
					if(player==null)
						continue ca;
					if(player!=null){
						if(player.enVie){
							tcp.lblEtatPartie.setText(player.getNom()+" est gagne!");
							timer.stop();
						}
					}	
				}
			}
		}
	}
	
	/**
	 * la methode actionPerformed pour mettre a jour l'avancement de chaque joueur a chaque tick horologe.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		avancement();
		//requestFocusInWindow();
		arene.repaint();
		
	}
	
}

