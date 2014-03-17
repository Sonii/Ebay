import java.util.*;


public class Utilisateur {

	private String login = "";
	private String prenom = "";
	private String nom = "";
	private ModeConnexion modeConnexion;
	private ArrayList<Enchere> enchere;
	private ArrayList<Enchere> encheresVisibles;
	
	public Utilisateur(String login, String prenom, String nom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.enchere = new ArrayList<Enchere>();
		this.encheresVisibles = new ArrayList<Enchere>();
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
			enchere.add(new Enchere(description, identifiant, prixMin, prixReserve, this));
			return new Enchere(description, identifiant, prixMin, prixReserve, this);
	}
	
	protected ArrayList<Enchere> getMesEncheres()
	{
		return this.enchere;
	}
	
	public void PublieEnchere(Enchere en)
	{
		if((this.equals(en.getUtilisateur())) && (en != null))
		{
			en.setEtatEnchere(EtatEnchere.Publi�e);
			ListeEnchereSingleton.getInstance().ajouteEnchere(en);
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
				ListeEnchereSingleton.getInstance().supprimeEnchere(en);
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
	
	protected ArrayList<Enchere> getListeEncheresVisibles()
	{
		return this.encheresVisibles;
	}
	
	
}
