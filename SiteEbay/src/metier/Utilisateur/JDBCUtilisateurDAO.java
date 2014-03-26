package metier.Utilisateur;

import java.util.ArrayList;

import metier.Utilisateur.Utilisateur;;

public class JDBCUtilisateurDAO implements UtilisateurDAO {


	@Override
	public ArrayList<Utilisateur> chargerBDD() {
		System.out.println("Chargement en cours...");
		return new ArrayList<Utilisateur>() ;
	}

	@Override
	public boolean sauvegarderBDD(ArrayList<Utilisateur> utilisateurs) {
		System.out.println("Sauvegarde en cours...");
		return false;
	}



}
