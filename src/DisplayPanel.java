import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
 
public class DisplayPanel extends JPanel { 
	
	private ArrayList<Reveil> reveils;
	private ArrayList<Quantum> quantums;
	
	private ArrayList<String> nomsTaches;
	
	private static int hauteurTache = 100;
	private static int largeurQuantum = 1;
	
	
	public DisplayPanel() {
		super();
		this.hauteurTache = DisplayPanel.hauteurTache;
		this.largeurQuantum = DisplayPanel.largeurQuantum;
		this.reveils = new ArrayList<>();
		this.quantums = new ArrayList<>();
		this.nomsTaches = new ArrayList<>();
	}
	
	
	public static void setHauteurTache(int hauteurTache) {
		DisplayPanel.hauteurTache = hauteurTache;
	}

	public static void setLargeurQuantum(int largeurQuantum) {
		DisplayPanel.largeurQuantum = largeurQuantum;
	}

	public void paintComponent(Graphics g){
			
		for(Reveil r : reveils) {
			int j = chercherIndice(r.name);
			g.fillRect(r.time * largeurQuantum, j * hauteurTache, (r.time+1) * largeurQuantum, (j+1)*hauteurTache);
		}
	} 
  
	public void ajouterReveil(String name, int time) {
		reveils.add(new Reveil(name, time));
	}
	
	public void ajouterQuantum() {
		
	}
	
	private int chercherIndice(String s) {
		int i = 0;
				
		while(i < nomsTaches.size() - 1) {
			
			if(nomsTaches.get(i).equals(s)) {
				return i;
			}
			i++;
		}
		nomsTaches.add(i,s);
		return i;
		
	}
  
	class Reveil {
		String name;
		int time;
		
		public Reveil(String name, int time) {
			this.name = name;
			this.time = time;
		}
		
	}
	
	class Quantum {
		String name;
		int time;
		int duree;
	}
  
}