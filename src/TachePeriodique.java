
public class TachePeriodique extends Tache {
	
	int p;
	
	public TachePeriodique(int r, int c, int d, int priorite, int etat,
			String nom, int p) {
		super(r, c, d, priorite, etat, nom);
		this.p = p;
	}
	
	
	public void actualiser(int time) {
		//System.out.println(name + " " + time + " " + d);
		if(time >= d) {
			System.out.println("Reset " + name);
			cRestante = c;
			d += d;
		}
	}
	
	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}
	
	
	public String toString() {
		return name+" | C = "+cRestante+" | D = "+d;
	}
	

}