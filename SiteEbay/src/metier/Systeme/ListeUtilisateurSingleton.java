package metier.Systeme;
import java.util.ArrayList;

import metier.Utilisateur.JDBCUtilisateurDAO;
import metier.Utilisateur.ModeConnexion;
import metier.Utilisateur.Utilisateur;
import metier.Utilisateur.UtilisateurDAO;


public class ListeUtilisateurSingleton {

	private static ListeUtilisateurSingleton uniqueInstance;
	private ArrayList<Utilisateur> listeUtilisateur;
	private UtilisateurDAO dao;
	
	private ListeUtilisateurSingleton()
	{
		this.listeUtilisateur = new ArrayList<Utilisateur>();
	}
	
	
	public Utilisateur getUtilisateur(String login){
		for (Utilisateur u : this.listeUtilisateur){
			if (u.getLogin().equals(login))
				return u;
		}
		return null;
	}
	
	
	public static synchronized ListeUtilisateurSingleton getInstance()
    {
            if(uniqueInstance == null)
            {
                    uniqueInstance = new ListeUtilisateurSingleton();
            }
            return uniqueInstance;
    }
	
	public void ajouteUtilisateur(Utilisateur user)
    {
			if (this.getUtilisateur(user.getLogin())== null)
				this.listeUtilisateur.add(user);
			else 
				return;
    }
	
	
	public void deleteAllUtilisateur(){
		while ( listeUtilisateur.size() > 0){
			listeUtilisateur.remove(0);
		}
		return;
	}
	
	public ArrayList<Utilisateur> getListeUtilisateur () {
		
		return this.listeUtilisateur;
	}
	
	public boolean verifierExistanceLogin(String login)
	{
		int i = 0;
		for(Utilisateur util : this.listeUtilisateur)
		{
			if(util.getLogin().equals(login))
			{
				i = 1;
				System.out.println("Le mot de passe que vous avez entré existe déjà, essayez un nouveau mot de passe");
				break;
			}
		}
		if(i == 1)
			return false;
		else 
			return true;
	}
	
	public ArrayList<Utilisateur> getListeUtilisateurConnecte()
	{
		ArrayList<Utilisateur> listeUtilisateurConnectes = new ArrayList<Utilisateur>();
		for(Utilisateur util : this.listeUtilisateur)
		{
			if(util.getModeConnexion() == ModeConnexion.Connecté)
				listeUtilisateurConnectes.add(util);
		}
		return listeUtilisateurConnectes;
	}
	
	public void supprimerUtilisateur(Utilisateur user)
	{
		this.listeUtilisateur.remove(user);
	}

	public void setDao(JDBCUtilisateurDAO jdbcU) {
		this.dao = jdbcU;
	}

	public boolean sauvegarderUtilisateur (){
		return this.dao.sauvegarderBDD(this.listeUtilisateur);
	}

	public boolean chargerUtilisateur() {
		this.listeUtilisateur=this.dao.chargerBDD();
		return true;
	}
	
}
