import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<TachePeriodique> tachesPeriodiques = new LinkedList<>();
		
		// Réveil, charge, deadline, priorité, état, nom, période
		tachesPeriodiques.add(new TachePeriodique(0, 10, 40, 1, Tache.PRET,"Tache 1",40));
		tachesPeriodiques.add(new TachePeriodique(0, 30, 60, 3, Tache.PRET,"Tache 2",60));
		tachesPeriodiques.add(new TachePeriodique(0, 20, 40, 2, Tache.PRET,"Tache 3",40));
		
		//Ordonnanceur ordonnanceur = new Ordonnanceur(tachesPeriodiques);
		OrdonnanceurGraphique ordonnanceur = new OrdonnanceurGraphique(tachesPeriodiques);
		
		ordonnanceur.ordoRR(10);
		//ordonnanceur.ordoSJF();
		//ordonnanceur.ordoFIFO();
		//ordonnanceur.ordoRM();
		//ordonnanceur.ordoDM();
		//ordonnanceur.ordoEDF();
		//ordonnanceur.ordoLLF();
				
		
		
		
	}

}
