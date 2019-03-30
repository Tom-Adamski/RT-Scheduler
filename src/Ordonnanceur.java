import java.util.LinkedList;

public class Ordonnanceur {

	private LinkedList<TachePeriodique> tachesPeriodiques;
	
	private int hyperPeriode = 0;
	
	public Ordonnanceur(LinkedList<TachePeriodique> tachesPeriodiques) {
		this.tachesPeriodiques = tachesPeriodiques;
		
		this.hyperPeriode = calculHyperPeriode(tachesPeriodiques);
	}
	
	public void ordoRR(int quantum) {
		int i = 0;
		int time = 0;
		
		afficherTaches(time);
		
		while(time < hyperPeriode) {
	
			for(TachePeriodique t : tachesPeriodiques) {
				t.actualiser(time);
			}
			
			TachePeriodique t = tachesPeriodiques.pop();
			System.out.print("Effectuer " + t.getName());
			System.out.println();
			t.effectuer(quantum);
			tachesPeriodiques.add(t);
			
			afficherTaches(time);
			time += quantum;
		}
		
		
	}
	
	
	
	public void afficherTaches(int time) {
		for(TachePeriodique tache : tachesPeriodiques) {
			System.out.println("T = "+time+"| "+tache);
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
