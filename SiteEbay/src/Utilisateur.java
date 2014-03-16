import java.util.*;


public class Utilisateur {

	private String login = "";
	private String prenom = "";
	private String nom = "";
	private ModeConnexion modeConnexion;
	private ArrayList<Enchere> enchere;
	
	public Utilisateur(String login, String prenom, String nom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.enchere = new ArrayList<Enchere>();
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
	
	protected void setLogin(String login)
	{
		this.login = login;
	}
	
	protected void setNom(String nom)
	{
		this.nom = nom;
	}
	
	protected void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}
	
	protected void seConnecte()
	{
		this.setModeConnexion(ModeConnexion.Connecté);
	}
	
	protected void seDeconnecte()
	{
		this.setModeConnexion(ModeConnexion.Deconnecté);
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
		if((!this.enchere.contains(en)) && (en != null))
		{
			en.setEtatEnchere(EtatEnchere.Publiée);
			ListeEnchereSingleton.getInstance().ajouteEnchere(en);
		}
		else
		{
			System.out.println("Vérifiez que l'enchere que vous voulez publiez existe bien");
		}
		
	}
	
	public void AnnuleEnchere(Enchere en)
	{
		if((!this.enchere.contains(en)) && (en != null))
		{
			if(en.getListeOffres().get(en.getListeOffres().size() - 1).getPrixOffre() < en.getPrixReserve()) // On vérifie bien que le prix de reserve n'as pas été atteint
			{
				en.setEtatEnchere(EtatEnchere.Annulée);
				ListeEnchereSingleton.getInstance().supprimeEnchere(en);
			}
			else
			{
				System.out.println("Vous ne pouvez pas annuler votre enchere, car le prix de reserve a été atteint");
			}
		}
		else
		{
			System.out.println("Vérifiez que l'enchere que vous voulez publiez existe bien");
		}
	}
	
	
}
