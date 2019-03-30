import java.util.LinkedList;

public class OrdonnanceurGraphique {

	private LinkedList<TachePeriodique> tachesP;
	
	private DisplayWindow displayWindow;
	
	private int hyperPeriode = 0;
	
	public OrdonnanceurGraphique(LinkedList<TachePeriodique> tachesPeriodiques) {
		this.tachesP = tachesPeriodiques;
		this.hyperPeriode = calculHyperPeriode(tachesPeriodiques);
		this.displayWindow = new DisplayWindow(hyperPeriode, tachesPeriodiques.size());
	}
	
	public void ordoRR(int quantum) {
		int i = 0;
		int time = 0;
		
		afficherTaches(tachesP,time,0);
		
		while(time <= hyperPeriode) {
	
			for(TachePeriodique t : tachesP) {
				if(t.reveiller(time))
					displayWindow.ajouterReveil(t.getName(), time);
			}
			
			TachePeriodique t = tachesP.pop();
			int popCount = 1;
			
			// Trouver une tâche à effectuer pendant un quantum
			while(t.getcRestante() == 0 && popCount < tachesP.size() ) {
				tachesP.add(t);
				t = tachesP.pop();
				popCount++;
			}
			
			if(t.getcRestante() != 0) {
				t.effectuer(quantum);
				displayWindow.ajouterQuantum(t.getName(),time,quantum,t.getC(),t.getcRestante());
			}
			
			tachesP.add(t);
			time += quantum;
		}
		
		displayWindow.revalidate();
		
	}

	public void ordoSJF() {
		LinkedList<TachePeriodique> tachesPSJF = new LinkedList<>(tachesP);
		
		// Tri par charge croissante
		boolean sorted = false;
		while(sorted == false) {
			sorted = true;
			for(int i = 0; i < tachesPSJF.size() - 1; i++) {
				if(tachesPSJF.get(i).getC() > tachesPSJF.get(i+1).getC()) {
					sorted = false;
					TachePeriodique t = tachesPSJF.get(i);
					tachesPSJF.set(i, tachesPSJF.get(i+1));
					tachesPSJF.set(i+1, t);
				}
			}
		}
		
		int time = 0;
		int quantum = 1;
		while(time < hyperPeriode) {
			System.out.println("Time : "+time+" Size is " + tachesPSJF.size());

			for(TachePeriodique t : tachesPSJF) {
				if(t.reveiller(time))
					displayWindow.ajouterReveil(t.getName(), time);
			}
			
			// Effectuer la première tâche possible dans la liste
			for(Tache t : tachesPSJF) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					displayWindow.ajouterQuantum(t.getName(), time, quantum, t.getC(), t.getcRestante());
					break;
				}
			}
			
			time += quantum;
		}
		
		displayWindow.revalidate();
	}
	
	public void ordoFIFO() {
		
		LinkedList<TachePeriodique> tachesPFIFO = new LinkedList<>(tachesP);
		
		int time = 0;
		int quantum = 1;
		while(time < hyperPeriode) {
			
			//Si une tâche se réveille on la déplace à la fin de la liste
			for(int i = 0; i < tachesPFIFO.size(); i++) {
				if(tachesPFIFO.get(i).reveiller(time)) {
					TachePeriodique t = tachesPFIFO.remove(i);
					tachesPFIFO.add(t);
					displayWindow.ajouterReveil(t.getName(), time);
				}
			}
			
			for(Tache t : tachesPFIFO) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					displayWindow.ajouterQuantum(t.getName(), time, quantum, t.getC(), t.getcRestante());
					break;
				}
			}
			
			time += quantum;
		}
		displayWindow.revalidate();
	}
	
	public void ordoRM() {
		LinkedList<TachePeriodique> tachesPRM = new LinkedList<>(tachesP);
		
		// Tri par période croissante
		boolean sorted = false;
		while(sorted == false) {
			sorted = true;
			for(int i = 0; i < tachesPRM.size() - 1; i++) {
				if(tachesPRM.get(i).getP() > tachesPRM.get(i+1).getP()) {
					sorted = false;
					TachePeriodique t = tachesPRM.get(i);
					tachesPRM.set(i, tachesPRM.get(i+1));
					tachesPRM.set(i+1, t);
				}
			}
		}
		
		int time = 0;
		int quantum = 1;
		while(time < hyperPeriode) {
			
			for(TachePeriodique t : tachesPRM) {
				if(t.reveiller(time))
					displayWindow.ajouterReveil(t.getName(), time);
			}
			
			// Effectuer la première tâche possible dans la liste
			for(Tache t : tachesPRM) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					displayWindow.ajouterQuantum(t.getName(), time, quantum, t.getC(), t.getcRestante());
					break;
				}
			}
			time += quantum;
		}
		displayWindow.revalidate();
	}

	public void ordoDM() {
		LinkedList<TachePeriodique> tachesPDM = new LinkedList<>(tachesP);
		
		// Tri par deadline croissante
		boolean sorted = false;
		while(sorted == false) {
			sorted = true;
			for(int i = 0; i < tachesPDM.size() - 1; i++) {
				if(tachesPDM.get(i).getD() > tachesPDM.get(i+1).getD()) {
					sorted = false;
					TachePeriodique t = tachesPDM.get(i);
					tachesPDM.set(i, tachesPDM.get(i+1));
					tachesPDM.set(i+1, t);
				}
			}
		}
		
		int time = 0;
		int quantum = 1;
		while(time < hyperPeriode) {
			
			for(TachePeriodique t : tachesPDM) {
				if(t.reveiller(time))
					displayWindow.ajouterReveil(t.getName(), time);
			}
			
			// Effectuer la première tâche possible dans la liste
			for(Tache t : tachesPDM) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					displayWindow.ajouterQuantum(t.getName(), time, quantum, t.getC(), t.getcRestante());
					break;
				}
			}
			time += quantum;
		}
		displayWindow.revalidate();
	}

	public void ordoEDF() {
		
		LinkedList<TachePeriodique> tachesPEDF = new LinkedList<>(tachesP);
		
		int time = 0;
		int quantum = 1;
		while(time < hyperPeriode) {

			// Réveil tâches
			for(int i = 0; i < tachesPEDF.size(); i++) {
				if(tachesPEDF.get(i).reveiller(time))
					displayWindow.ajouterReveil(tachesPEDF.get(i).getName(), time);
			}
			
			//Trouver la tâche dont la deadline est la plus proche
			int shortestD = Integer.MAX_VALUE;
			int indiceTache = 0;
			
			for(int i = 0; i < tachesPEDF.size(); i++) {
				if(tachesPEDF.get(i).getD() < shortestD && tachesPEDF.get(i).getcRestante() != 0 ) {
					shortestD = tachesPEDF.get(i).getD();
					indiceTache = i;
				}
			}
			
			if(tachesPEDF.get(indiceTache).getcRestante() != 0) {
				tachesPEDF.get(indiceTache).effectuer(quantum);
				displayWindow.ajouterQuantum(tachesPEDF.get(indiceTache).getName(), time, quantum, 
						tachesPEDF.get(indiceTache).getC(), tachesPEDF.get(indiceTache).getcRestante());
			}
			
			time += quantum;
		}
		displayWindow.revalidate();
	}
	
	public void ordoLLF() {
		
		LinkedList<TachePeriodique> tachesPLLF = new LinkedList<>(tachesP);
		
		int time = 0;
		int quantum = 1;
		while(time < hyperPeriode) {
			
			// Réveil tâches
			for(int i = 0; i < tachesPLLF.size(); i++) {
				if(tachesPLLF.get(i).reveiller(time))
					displayWindow.ajouterReveil(tachesPLLF.get(i).getName(), time);
			}
			
			//Trouver la tâche dont la marge est la plus petite
			int shortestL = Integer.MAX_VALUE;
			int indiceTache = 0;
			
			for(int i = 0; i < tachesPLLF.size(); i++) {
				if((tachesPLLF.get(i).getD() -  tachesPLLF.get(i).getcRestante())< shortestL && tachesPLLF.get(i).getcRestante() != 0 ) {
					shortestL = tachesPLLF.get(i).getD() -  tachesPLLF.get(i).getcRestante();
					indiceTache = i;
				}
			}
			
			if(tachesPLLF.get(indiceTache).getcRestante() != 0) {
				tachesPLLF.get(indiceTache).effectuer(quantum);
				displayWindow.ajouterQuantum(tachesPLLF.get(indiceTache).getName(), time, quantum,
						tachesPLLF.get(indiceTache).getC(), tachesPLLF.get(indiceTache).getcRestante());
			}
			
			time += quantum;
		}
		displayWindow.revalidate();
	}
	

	
	
	
	public void afficherTaches(LinkedList<TachePeriodique> taches, int time, int quantum) {
		for(TachePeriodique tache : taches) {
			System.out.println("T = "+time+" -> "+(time+quantum)+" | "+tache);
		}
		System.out.println();
	}
	
	
	public int calculHyperPeriode(LinkedList<TachePeriodique> tachesPeriodiques) {
      int min, max, x, ppcm = 0;
      
      for(int i = 0; i<tachesPeriodiques.size(); i++) {
         for(int j = i+1; j<tachesPeriodiques.size()-1; j++) {
            if(tachesPeriodiques.get(i).getP() > tachesPeriodiques.get(j).getP()) {
               min = tachesPeriodiques.get(j).getP();
               max = tachesPeriodiques.get(i).getP();
            } else {
               min = tachesPeriodiques.get(i).getP();
               max = tachesPeriodiques.get(j).getP();
            }

            for(int k =0; k<tachesPeriodiques.size(); k++) {
               x = k * max;
               if(x % min == 0) {
            	  ppcm = x ;
               }
            }
         }
      }
      return ppcm;
   }
   
	public void setHyperPeriode(int hp) {
		this.hyperPeriode = hp;
	}
	
	public int getHyperPeriode() {
		return hyperPeriode;
	}
	
}
