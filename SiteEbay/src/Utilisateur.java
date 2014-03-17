import java.util.*;


public class Utilisateur {

	private String login = "";
	private String prenom = "";
	private String nom = "";
	private ModeConnexion modeConnexion;

	public Utilisateur(String login, String prenom, String nom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		//this.enchere = new ArrayList<Enchere>();
		//this.encheresVisibles = new ArrayList<Enchere>();
	}
	
	protected String getNom()
	{
		return this.nom;
	}
	
	protected String getPrenom()
	{
		return this.prenom;
	}
	
	protected String getLogin()
	{
		return this.login;
	}
	
	protected ModeConnexion getModeConnexion()
	{
		return this.modeConnexion;
	}
	
	protected void setModeConnexion(ModeConnexion mode)
	{
		this.modeConnexion = mode;
	}
	
	protected void seConnecte()
	{
		this.setModeConnexion(ModeConnexion.Connect�);
		ListeUtilisateurConnecteSingleton.getInstance().ajouteUtilisateur(this);
	}
	
	protected void seDeconnecte()
	{
		this.setModeConnexion(ModeConnexion.Deconnect�);
		ListeUtilisateurConnecteSingleton.getInstance().supprimeUtilisateur(this);
	}
	
	public Enchere CreeEnchere(String description, String identifiant, float prixMin, float prixReserve)
	{
			Enchere enchere = new Enchere(description, identifiant, prixMin, prixReserve, this);
			ListeEnchereSingleton.getInstance().ajouteEnchere(enchere);
			return enchere;
	}
	
	public void PublieEnchere(Enchere en)
	{
		if((this.equals(en.getUtilisateur())) && (en != null))
		{
			en.setEtatEnchere(EtatEnchere.Publi�e);
		}
		else
		{
			System.out.println("V�rifiez que l'enchere que vous voulez publiez existe");
		}
		
	}
	
	public void AnnuleEnchere(Enchere en)
	{
		if(this.equals(en.getUtilisateur()) && (en != null))
		{
			if(en.getListeOffres().get(en.getListeOffres().size() - 1).getPrixOffre() < en.getPrixReserve(en.getUtilisateur())) // On v�rifie bien que le prix de reserve n'as pas �t� atteint
			{
				en.setEtatEnchere(EtatEnchere.Annul�e);
				Alerte.EnchereAnnulee(en);
			}
			else
			{
				System.out.println("Vous ne pouvez pas annuler votre enchere, car son prix de reserve a �t� atteint");
			}
		}
		else
		{
			System.out.println("V�rifiez que l'enchere que vous voulez publiez existe bien");
		}
	}
	
	/*public ArrayList<Enchere> getListeEncheresVisibles()
	{
		return this.encheresVisibles;
	}*/
	
	
}
