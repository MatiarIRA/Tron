
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * la classe TronControlPanel comporte les composant pour controler le jeu
 * @author M.H.ERFANIAN AZMOUDEH et Vivianne NGUYEN-DONG
 *
 */
@SuppressWarnings("serial")
public class TronControlPanel extends JPanel implements ActionListener {
	// des composantes montrant la couleur de chaque joueur, indiquant
	// pour chaque joueur s'il est humain ou ordinateur et s'il est
	// humain, décrivant ses touches de direction.
	private JLabel lblDirHum1, lblDirHum2, lblDirOrdi;
	private JLabel HUM_1, HUM_2, ORDI;
	private JLabel changerVitess;
	//private Trace trace;
	Arene arene;
	ArrayList<Joueur> joueurs;
	private boolean stopClock;
	int speed;

	/**
	 * le bouton permettant de démarrer ou mettre en pause la partie en cours.
	 */
	private JButton btnPauseJeu;

	String[] type_de_nouvelle_partie = {
			"2 joueurs humains",
			"1 humain contre 1 ordinateur",
			"2 humains et 1 ordinateur"
			};
	/**
	 * Le dropbox permettant de choisir le type de nouvelle partie (2 joueurs
	 * humains, 1 humain contre 1 ordinateur, 2 humains 1 ordinateur, ...)
	 */
	JComboBox<String> cmbTypeJeu;
	
	//correpondant le type de jeu choisi par le menu comboBox
	protected static int codeTypeJeu;
	/**
	 * un bouton permettant de débuter une nouvelle partie.
	 */
	private JButton btnNouvelle_partie;

	/**
	 * champ texte pourra permettre de modifier le nombre de millisecondes entre
	 * chaque pas d'avancement des traces.
	 */
	private JTextField txtfVitess_avancement;

	/**
	 * Le JLabel pourra être utilisé pour indiquer l'état de la partie en cours
	 * (pause, en cours, joueur 2 a gagné, ...).
	 */
	protected JLabel lblEtatPartie;

	private TronPanel tp;

	public TronControlPanel(TronPanel tp) {
		this.tp=tp;
		
		this.setFocusable(true);
		
		stopClock= false;

		GridLayout gl = new GridLayout(0,3);
		setLayout(gl);
		
		HUM_1 = new JLabel("", JLabel.CENTER);
		HUM_1.setOpaque(true);
		add(HUM_1);
		ORDI = new JLabel("", JLabel.CENTER);
		ORDI.setOpaque(true);
		ORDI.setBackground(Color.darkGray);
		add(ORDI);
		HUM_2 = new JLabel("", JLabel.CENTER);
		HUM_2.setOpaque(true);
		add(HUM_2);
		
		lblDirHum1 = new JLabel("", JLabel.CENTER);
		add(lblDirHum1);
		lblDirOrdi = new JLabel("", JLabel.CENTER);
		add(lblDirOrdi);
		lblDirHum2 = new JLabel("", JLabel.CENTER);
		add(lblDirHum2);
		
		btnPauseJeu = new JButton("Pauser/Jouer");
		btnPauseJeu.addActionListener(this);
		add(btnPauseJeu);
		
		cmbTypeJeu = new JComboBox<String>(type_de_nouvelle_partie);
		cmbTypeJeu.setSelectedIndex(0);
		cmbTypeJeu.addActionListener(this);
		add(cmbTypeJeu);
		
		btnNouvelle_partie = new JButton("Nouvelle Partie");
		btnNouvelle_partie.addActionListener(this);
		add(btnNouvelle_partie);
		
		changerVitess= new JLabel("Vitess d'avancement (msec): ", JLabel.RIGHT );
		add(changerVitess);
		
		txtfVitess_avancement = new JTextField("500",5);
		txtfVitess_avancement.addActionListener(this);
		add(txtfVitess_avancement);
		lblEtatPartie = new JLabel("",JLabel.CENTER);
		add(lblEtatPartie);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		arene = tp.arene;
		
		if(e.getSource()==txtfVitess_avancement){
			speed=Integer.parseInt(txtfVitess_avancement.getText());
		}else{
			speed=Integer.parseInt(txtfVitess_avancement.getText());
		}
		
		if(e.getSource()==btnPauseJeu){
			if(stopClock==false){
				tp.timer.stop();
				stopClock=true;
				lblEtatPartie.setText("Pause");
			}else{
				tp.timer.start();
				stopClock=false;
				lblEtatPartie.setText("En cours");
			}
		}
		
		if(e.getSource()==btnNouvelle_partie){
			TronPanel.counter = 0;
			TronPanel.joueurs.set(0, null);
			HUM_1.setText("");
			lblDirHum1.setText("");
			TronPanel.joueurs.set(1, null);
			HUM_2.setText("");
			lblDirHum2.setText("");
			TronPanel.joueurs.set(2, null);
			ORDI.setText("");
			lblDirOrdi.setText("");
			lblEtatPartie.setText("");
			speed=Integer.parseInt(txtfVitess_avancement.getText());
			tp.timer.start();
		}
		
		if(e.getSource()==cmbTypeJeu){
			@SuppressWarnings("unchecked")
			JComboBox<String> cmb = (JComboBox<String>)e.getSource();
			lblEtatPartie.setText("En cours");
			switch (cmb.getSelectedIndex()){
				case 0: 
				codeTypeJeu=0;
				HUM_1.setText("Humain 1");
				HUM_1.setForeground(Color.blue);
				lblDirHum1.setText("Directions: W,S,Z,A");
				
				HUM_2.setText("Humain 2");
				HUM_2.setForeground(Color.red);
				lblDirHum2.setText("Directions: I,K,M,J");
				
				ORDI.setText("");
				ORDI.setForeground(Color.gray);
				lblDirOrdi.setText("");

				break;
				case 1: 
				codeTypeJeu=1;
				HUM_1.setText("Humain 1");
				HUM_1.setForeground(Color.blue);
				lblDirHum1.setText("Directions: W,S,Z,A");
				
				HUM_2.setText("");
				HUM_2.setForeground(Color.gray);
				lblDirHum2.setText("");
				
				ORDI.setText("Ordinateur");
				ORDI.setForeground(Color.green);

				break;				
				case 2:
				codeTypeJeu=2;
				HUM_1.setText("Humain 1");
				HUM_1.setForeground(Color.blue);
				lblDirHum1.setText("Directions: W,S,Z,A");
				
				HUM_2.setText("Humain 2");
				HUM_2.setForeground(Color.red);
				lblDirHum2.setText("Directions: I,K,M,J");
				
				ORDI.setText("Ordinateur");
				ORDI.setForeground(Color.green);

				break;
			}
			//joueurs = TronPanel.joueurs;
			
			//creer des joueurs a partir ici selon le type de jeu choisi par le ComboBox
			tp.createJoueurs(codeTypeJeu);
			arene.requestFocusInWindow();
		}		
	}
}

