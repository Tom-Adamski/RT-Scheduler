import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class DisplayWindow extends JFrame {
	
	private DisplayPanel displayPanel;
	
	public static final int hauteurTache = 100;
	public static final int largeurQuantum= 1;
	
	public DisplayWindow(int hyperPeriode, int nbTaches){             
	    this.setTitle("Ma première fenêtre Java");
	    this.setSize(hyperPeriode, hauteurTache * nbTaches);
	    this.setLocationRelativeTo(null);     
	    this.displayPanel = new DisplayPanel();
	    
	    this.setContentPane(displayPanel);               
	    this.setVisible(true);
	    
	}       
	
	public void ajouterReveil(String name, int time) {
		displayPanel.ajouterReveil(name, time);
		displayPanel.repaint();
	}
	
	public void ajouterQuantum() {
		displayPanel.ajouterQuantum();
		displayPanel.repaint();
	}
	
}