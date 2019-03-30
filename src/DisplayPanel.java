import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
 
public class DisplayPanel extends JPanel { 
	
	private ArrayList<Reveil> reveils;
	private ArrayList<Quantum> quantums;
	
	private ArrayList<String> nomsTaches;
	
	private static int hauteurTache = 100;
	private static int marge = 10;
	private static int largeurQuantum = 5;
	
	
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
		
		System.out.println("Drawing");
		g.setColor(Color.RED);
		for(Reveil r : reveils) {
			int j = chercherIndice(r.name);
			g.fillRect(r.time*largeurQuantum,
					j*(hauteurTache+marge), 
					largeurQuantum,
					hauteurTache);
		}
		
		g.setColor(Color.BLUE);
		for(Quantum q : quantums) {
			int j = chercherIndice(q.name);
			
			System.out.println( (j*(hauteurTache+marge) + hauteurTache * (float)q.cRestant/q.cTotal));
			
			g.drawLine(q.time*largeurQuantum,
					j*(hauteurTache+marge),
					(q.time+q.quantum)*largeurQuantum,
					(j+1)*(hauteurTache+marge));
		
		}
		
		
	} 
  
	public void ajouterReveil(String name, int time) {
		reveils.add(new Reveil(name, time));
	}
	
	public void ajouterQuantum(String name, int time, int quantum, int cTotal, int cRestant) {
		quantums.add(new Quantum(name, time, quantum, cTotal, cRestant));
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
		int quantum;
		int cTotal;
		int cRestant;
		
		public Quantum(String name, int time, int quantum, int cTotal, int cRestant) {
			this.name = name;
			this.time = time;
			this.quantum = quantum;
			this.cTotal = cTotal;
			this.cRestant = cRestant;
		}
		
	}
  
}