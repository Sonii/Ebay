package metier.Enchere;

import java.util.ArrayList;

public interface EnchereDAO {

	abstract boolean sauvegarderBDD(ArrayList<Enchere> encheres);
	
	abstract ArrayList<Enchere> chargerBDD ();
	
}
