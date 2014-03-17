package Enchere;
import java.util.Comparator;

public class OffreComparator implements Comparator<Offre>{

	/*Comparaison de prix d'offres*/
	@Override
	public int compare(Offre offre1, Offre offre2) {
		return Float.compare(offre1.getPrixOffre(),offre2.getPrixOffre());
	}

	
}
