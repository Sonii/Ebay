import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Offre offre1 = new Offre(null, 10);
		Offre offre2 = new Offre(null, 11);
		Offre offre3 = new Offre(null, 13);
		
		ArrayList<Offre> offre = new ArrayList<Offre>();
		offre.add(offre3);
		offre.add(offre1);
		offre.add(offre2);
		offre.add(offre3);
		
		Collections.sort(offre, new OffreComparator());
		for(Offre off : offre)
		{
			System.out.println(off.getPrixOffre());
			
		}
		System.out.println(offre.get(3).getPrixOffre());

	}

}
