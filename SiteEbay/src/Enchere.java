import java.util.*;


public class Enchere {

	private Objet objet;
	private ArrayList<Offre> offres;
	private ArrayList<Alerte> alertes;
	private float prixMinimum = 0;
	private float prixReserve = 0;
	private Date dateCreation;
	private Date dateExpiration;
	private Utilisateur utilisateur;
	private EtatEnchere etatEnchere;
	
	public Enchere(String description, String identifiant, float prixMin, float prixReserve, Utilisateur user)
	{
		this.objet = new Objet(description, identifiant);
		this.offres = new ArrayList<Offre>();
		this.alertes = new ArrayList<Alerte>();
		this.prixMinimum = prixMin;
		this.prixReserve = prixReserve;
		this.utilisateur = user;
		this.etatEnchere = EtatEnchere.Crée;
		this.dateCreation = new Date();
		this.dateExpiration = new Date(dateCreation.getTime() + new Date(2000).getTime());
	}

	/* Création d'offre */
	
	
	
	/* Configuration D'ALERTES */	
/* Un Achteur peut configurer n'importe que alerte qui n'est pas la sienne biensur même s'il n'a pas emis des offres sur l'enchere en question*/

	public void ConfigurationAlertePrixReserve(Utilisateur user, boolean prixRes)
	{
		int i = 0;
		if(!this.getUtilisateur().equals(user))
		{
			for(Alerte alerte : this.alertes)
			{
				if(alerte.user.equals(user))
				{
					alerte.prixReserveAtteint = prixRes;
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				this.alertes.add(new Alerte(user, prixRes, true, true));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
	
	public void ConfigurationAlerteOffreSup(Utilisateur user, boolean prixRes)
	{
		int i = 0;
		
		if(!this.getUtilisateur().equals(user))
		{
			for(Alerte alerte : this.alertes)
			{
				if(alerte.user.equals(user))
				{
					alerte.offreSuperieure = prixRes;
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				this.alertes.add(new Alerte(user, true, prixRes, true));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
	
	public void ConfigurationAlerteEnchereAnnulee(Utilisateur user, boolean prixRes)
	{
		int i = 0;
		if(!this.getUtilisateur().equals(user))
		{
			for(Alerte alerte : this.alertes)
			{
				if(alerte.user.equals(user))
				{
					alerte.enchereAnnulee = prixRes;
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				this.alertes.add(new Alerte(user, true, true, prixRes));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
		
	public int VerfierAlerteUtilisateur(Utilisateur u)
	{
		for(Alerte alerte : this.alertes)
		{
			if(alerte.user.equals(u))
				return 1;
		}
		return 0;
	}
	
/* Fin Configuration Alertes */
	
/* Getteres and Setters*/
	
	protected ArrayList<Alerte> getListeAlertes()
	{
		return this.alertes;
	}
	
	protected ArrayList<Offre> getListeOffres()
	{
		return this.offres;
	}
	
	protected Float getPrixReserve(Utilisateur user)
	{
		if(this.utilisateur.equals(user))
		{
			return this.prixReserve;
		}
		else
		{
			System.out.println("Le prix de reserve d'une enchere n'est visible que par son vendeur");
			return null;
		}
	}
	
	protected void setEtatEnchere(EtatEnchere etat)
	{
		this.etatEnchere = etat;
	}
	
	public Objet getObjet()
	{
		return this.objet;
	}
	
	public Utilisateur getUtilisateur()
	{
		return this.utilisateur; 
	}
	
	public float getPrixMinimum()
	{
		return this.prixMinimum;
	}
	
	public Date getDateCreation()
	{
		return this.dateCreation;
	}
	
	public EtatEnchere getEtatEnchere()
	{
		return this.etatEnchere;
	}
}
