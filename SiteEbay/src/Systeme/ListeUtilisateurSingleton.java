package Systeme;
import java.util.ArrayList;

import Utilisateur.Utilisateur;


public class ListeUtilisateurSingleton {

	private static ListeUtilisateurSingleton uniqueInstance;
	private ArrayList<Utilisateur> listeUtilisateur;
	
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
	public ArrayList<Utilisateur> voirListeUtilisateurConnecte()
	{
		//filtrer que les connecter...
		return this.listeUtilisateur;
	}
	
	public void supprimerUtilisateur(Utilisateur user)
	{
		this.listeUtilisateur.remove(user);
	}
	
}
