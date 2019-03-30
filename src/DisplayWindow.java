import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class DisplayWindow extends JFrame {
	
	private DisplayPanel displayPanel;
	
	public static final int hauteurTache = 100;
	public static final int largeurQuantum= 5;
	
	public DisplayWindow(int hyperPeriode, int nbTaches){             
	    this.setTitle("Ma première fenêtre Java");
	    // La fenêtre n'a pas exactement la bonne taille à cause des bords, on l'agrandit artificellement
	    this.setSize(hyperPeriode*largeurQuantum +50, hauteurTache * nbTaches + 100);
	    this.setLocationRelativeTo(null);     
	    this.displayPanel = new DisplayPanel();
	    
	    this.setContentPane(displayPanel);               
	    this.setVisible(true);
	    
	}       
	
	public void ajouterReveil(String name, int time) {
		displayPanel.ajouterReveil(name, time);
		displayPanel.repaint();
	}
	
	public void ajouterQuantum(String name, int time, int quantum, int cTotal, int cRestant) {
		displayPanel.ajouterQuantum(name, time, quantum, cTotal, cRestant);
		displayPanel.repaint();
	}
	
}