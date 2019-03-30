
public abstract class Tache {
	
	protected int r, priority, state, d, c, cRestante;
	protected String name;
	protected boolean isActive;
	
	public static final int PRET = 0;
	public static final int ACTIF = 1;
	public static final int ATTENTE = 2;
	public static final int ZOMBIE = 3;
	
	
	public Tache() {
		this.r = 0;
		this.c = 0;
		this.cRestante = 0;
		this.d = 0;
		this.priority = 0;
		this.state = 0;
		this.name = "";
	}
	
                    
	public Tache(int r, int c, int d, int priority, int state, String name) {
		this.r = r;
		this.c = c;
		this.cRestante = c;
		this.d = d;
		this.priority = priority;
		this.state = state;
		this.name = name;
	}
	
	public void effectuer(int time) {
		this.cRestante -= time;
		if(cRestante < 0)
			cRestante = 0;
	}


	public int getR() {
		return r;
	}


	public void setR(int r) {
		this.r = r;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public int getD() {
		return d;
	}


	public void setD(int d) {
		this.d = d;
	}


	public int getC() {
		return c;
	}


	public void setC(int c) {
		this.c = c;
	}
	
	public int getcRestante() {
		return cRestante;
	}


	public void setcRestante(int cRestante) {
		this.cRestante = cRestante;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
	
}