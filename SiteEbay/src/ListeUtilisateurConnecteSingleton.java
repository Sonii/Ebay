import java.util.ArrayList;


public class ListeUtilisateurConnecteSingleton {

	private static ListeUtilisateurConnecteSingleton uniqueInstance;
	private ArrayList<Utilisateur> listeUtilisateur;
	
	private ListeUtilisateurConnecteSingleton()
	{
		this.listeUtilisateur = new ArrayList<Utilisateur>();
	}
	
	public static synchronized ListeUtilisateurConnecteSingleton getInstance()
    {
            if(uniqueInstance == null)
            {
                    uniqueInstance = new ListeUtilisateurConnecteSingleton();
            }
            return uniqueInstance;
    }
	
	public void ajouteUtilisateur(Utilisateur user)
    {
           this.listeUtilisateur.add(user);
    }
	
	public ArrayList<Utilisateur> VoirelisteUtilisateurConnecte()
	{
		return this.listeUtilisateur;
	}
	
	public void supprimeUtilisateur(Utilisateur user)
	{
		this.listeUtilisateur.remove(user);
	}
	
}
