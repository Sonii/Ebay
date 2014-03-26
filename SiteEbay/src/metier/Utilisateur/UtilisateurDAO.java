package metier.Utilisateur;

import java.util.ArrayList;

public interface UtilisateurDAO {
	
	abstract boolean sauvegarderBDD(ArrayList<Utilisateur> utilisateurs);
	
	abstract ArrayList<Utilisateur> chargerBDD ();
}
