
public class TachePeriodique extends Tache {
	
	int p;
	
	public TachePeriodique(int r, int c, int d, int priorite, int etat,
			String nom, int p) {
		super(r, c, d, priorite, etat, nom);
		this.p = p;
	}
	
	public TachePeriodique()
	{
		
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}
	
		
	

}