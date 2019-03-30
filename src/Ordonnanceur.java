import java.util.LinkedList;

public class Ordonnanceur {

	private LinkedList<TachePeriodique> tachesP;
	
	private int hyperPeriode = 0;
	
	public Ordonnanceur(LinkedList<TachePeriodique> tachesPeriodiques) {
		this.tachesP = tachesPeriodiques;
		
		this.hyperPeriode = calculHyperPeriode(tachesPeriodiques);
	}
	
	public void ordoRR(int quantum) {
		int i = 0;
		int time = 0;
		
		afficherTaches(tachesP,time,0);
		
		while(time < hyperPeriode) {
	
			for(TachePeriodique t : tachesP) {
				t.reveiller(time);
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
				System.out.print("Effectuer " + t.getName());
				t.effectuer(quantum);
				
			}
			else {
				System.out.print("Aucune tâche prête");
			}
			
			System.out.println();
			
			tachesP.add(t);
			
			afficherTaches(tachesP, time,quantum);
			time += quantum;
		}
		
		
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
			
			for(TachePeriodique t : tachesPSJF) {
				t.reveiller(time);
			}
			
			// Effectuer la première tâche possible dans la liste
			for(Tache t : tachesPSJF) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					break;
				}
			}
			
			afficherTaches(tachesPSJF,time,quantum);
			time += quantum;
		}
		
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
				}
			}
			
			for(Tache t : tachesPFIFO) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					break;
				}
			}
			
			afficherTaches(tachesPFIFO,time,quantum);
			time += quantum;
		}
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
				t.reveiller(time);
			}
			
			// Effectuer la première tâche possible dans la liste
			for(Tache t : tachesPRM) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					break;
				}
			}
			
			afficherTaches(tachesPRM,time,quantum);
			time += quantum;
		}
		
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
				t.reveiller(time);
			}
			
			// Effectuer la première tâche possible dans la liste
			for(Tache t : tachesPDM) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					break;
				}
			}
			
			afficherTaches(tachesPDM,time,quantum);
			time += quantum;
		}
		
	}

	public void ordoEDF() {
		
		LinkedList<TachePeriodique> tachesPEDF = new LinkedList<>(tachesP);
		
		int time = 0;
		int quantum = 1;
		while(time < hyperPeriode) {
			
			//Si une tâche se réveille on la déplace à la fin de la liste
			for(int i = 0; i < tachesPEDF.size(); i++) {
				tachesPEDF.get(i).reveiller(time);
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
			}
			
			
			afficherTaches(tachesPEDF,time,quantum);
			time += quantum;
		}
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
         for(int j = i+1; j<tachesPeriodiques.size(); j++) {
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
	
	
}
