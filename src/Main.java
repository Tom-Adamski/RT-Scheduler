import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<TachePeriodique> tachesPeriodiques = new LinkedList<>();
		
		// Réveil, charge, deadline, priorité, état, nom, période
		tachesPeriodiques.add(new TachePeriodique(0, 10, 40, 1, Tache.PRET,"Tache 1",40));
		tachesPeriodiques.add(new TachePeriodique(0, 30, 120, 3, Tache.PRET,"Tache 2",120));
		tachesPeriodiques.add(new TachePeriodique(0, 20, 80, 2, Tache.PRET,"Tache 3",80));
		
		Ordonnanceur ordonnanceur = new Ordonnanceur(tachesPeriodiques);
		
		System.out.println("Hyperpériode : " + ordonnanceur.calculHyperPeriode(tachesPeriodiques));
		
		//ordonnanceur.ordoRR(10);
		//ordonnanceur.ordoSJF();
		ordonnanceur.ordoFIFO();

	}

}
