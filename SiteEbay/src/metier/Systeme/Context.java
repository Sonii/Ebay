package metier.Systeme;

import metier.Enchere.JDBCEnchereDAO;

public class Context {

	private ListeEnchereSingleton singEncheres = ListeEnchereSingleton.getInstance();
	private JDBCEnchereDAO jdbc = new JDBCEnchereDAO();
	
	public boolean open() {
		singEncheres.setDao(jdbc);
		return singEncheres.chargerEncheres();
	}
	
	public boolean close() {
		
		singEncheres.setDao(jdbc);
		return singEncheres.sauvegarderEncheres();
	}
}
