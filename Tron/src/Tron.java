
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
/**
 * Le programme principal Tron.java qui pourra se contenter de créer un JFrame et d'y ajouter un TronPanel
 * @author M.H.ERFANIAN AZMOUDEH et Vivianne NGUYEN-DONG
 *
 */
public class Tron {
	public static void main(String[]args){
		TronPanel tp = new TronPanel();
		JFrame f = new JFrame("Tron");
		f.add(tp);
		
		f.setSize(600, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    
	    f.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
	            tp.arene.requestFocusInWindow();
	        }
	    });
		f.setVisible(true);				
	}
}

