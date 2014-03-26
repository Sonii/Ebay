package metier.Enchere;

import java.util.ArrayList;

public class JDBCEnchereDAO implements EnchereDAO {

	@Override
	public boolean sauvegarderBDD(ArrayList<Enchere> encheres) {
		System.out.println("Sauvegarde en cours...");
		return true;
	}

	@Override
	public ArrayList<Enchere> chargerBDD() {
		System.out.println("Chargement en cours...");
		return new ArrayList<Enchere>() ;
	}

}
