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
		
		afficherTaches(tachesP,time);
		
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
			
			afficherTaches(tachesP, time);
			time += quantum;
		}
		
		
	}

	public void ordoSJF() {
		LinkedList<TachePeriodique> tachesPTriées = new LinkedList<>(tachesP);
		
		// Tri par charge croissante
		boolean sorted = false;
		while(sorted == false) {
			sorted = true;
			for(int i = 0; i < tachesPTriées.size() - 1; i++) {
				if(tachesPTriées.get(i).getC() > tachesPTriées.get(i+1).getC()) {
					sorted = false;
					TachePeriodique t = tachesPTriées.get(i);
					tachesPTriées.set(i, tachesPTriées.get(i+1));
					tachesPTriées.set(i+1, t);
				}
			}
		}
		
		int time = 0;
		int quantum = 1;
		while(time <= hyperPeriode) {
			
			for(TachePeriodique t : tachesPTriées) {
				t.reveiller(time);
			}
			
			// Effectuer la première tâche possible dans la liste
			for(Tache t : tachesPTriées) {
				if(t.getcRestante() > 0) {
					t.effectuer(quantum);
					break;
				}
			}
			
			afficherTaches(tachesPTriées,time);
			time += quantum;
		}
		
	}
	
	public void ordoFIFO() {
		
		LinkedList<TachePeriodique> tachesPFIFO = new LinkedList<>(tachesP);
		
		int time = 0;
		int quantum = 1;
		while(time <= hyperPeriode) {
			
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
			
			afficherTaches(tachesPFIFO,time);
			time += quantum;
		}
	}
	
	public void afficherTaches(LinkedList<TachePeriodique> taches, int time) {
		for(TachePeriodique tache : taches) {
			System.out.println("T = "+time+" -> "+(time+1)+" | "+tache);
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
