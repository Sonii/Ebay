
public class UsineUtilisateur {

	public Utilisateur CreeUtilisateur(TypeUtilisateur type, String login, String prenom, String nom, Alerte alerte)
	{
		Utilisateur utilisateur = null;
		switch(type)
		{
			case Vendeur:utilisateur = new Vendeur(login, nom, prenom); break;
			case Achteur:utilisateur = new Achteur(login, nom, prenom); break;
		}
		alerte.utilisateur.add(utilisateur);
		System.out.println("J'ai bien été crée"); // juste pour teste
		return utilisateur;
	}
	
}
