package metier.Systeme;

import metier.Enchere.JDBCEnchereDAO;
import metier.Utilisateur.JDBCUtilisateurDAO;

public class Context {

	private ListeEnchereSingleton singEncheres = ListeEnchereSingleton.getInstance();
	private JDBCEnchereDAO jdbcE = new JDBCEnchereDAO();
	private ListeUtilisateurSingleton singUtilisateur = ListeUtilisateurSingleton.getInstance();
	private JDBCUtilisateurDAO jdbcU = new JDBCUtilisateurDAO();
	
	public boolean openEbay() {
		singEncheres.setDao(jdbcE);
		singUtilisateur.setDao(jdbcU);
		singEncheres.chargerEncheres();
		singUtilisateur.chargerUtilisateur();
		return true;
	}
	
	public boolean closeEbay() {
		
		singEncheres.setDao(jdbcE);
		singUtilisateur.setDao(jdbcU);
		singEncheres.sauvegarderEncheres();
		singUtilisateur.sauvegarderUtilisateur();
		return true;
	}
}
