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
	
	protected ArrayList<Alerte> getListeAlertes()
	{
		return this.alertes;
	}
	
	protected ArrayList<Offre> getListeOffres()
	{
		return this.offres;
	}
	
	protected float getPrixReserve()
	{
		return this.prixReserve;
	}
	
	protected void setEtatEnchere(EtatEnchere etat)
	{
		this.etatEnchere = etat;
	}
	
	protected void ajouteOffre(Offre offre)
	{
			this.offres.add(offre);	
	}
	
	protected void ajouteAlerte(Alerte alerte)
	{
			this.alertes.add(alerte);	
	}
	
	
	public void OffreSupérieure(Offre offre) // A modifier Alertes
	{
			for(Offre o : offres)
			{
				if(o.getPrixOffre() < offre.getPrixOffre())
				System.out.println("Allerte Offre superieure par un autre utilisateur"+ o.getUtilisateur().getLogin());
			}
	}
	
	public Offre CreeOffre(Utilisateur utilisateur, float prixO)
	{
		if((!(this.utilisateur.equals(utilisateur.getLogin())) && (prixO >= this.getPrixMinimum())))
		{
			System.out.println("Votre offre a bien été crée");
			if(this.VerfierAlerteUtilisateur(utilisateur) == 0)
			{
				this.ajouteAlerte(new Alerte(utilisateur, true,true,true,true));
			}
			this.ajouteOffre(new Offre(utilisateur, prixO)); // On ajoute l'offre au tableau
			Collections.sort(offres, new OffreComparator()); // On tri le tableau selon l'ordre ascendant des pris d'offres
			//this.OffreSupérieure(new Offre(utilisateur, prixO)); // On regarde si cette offre a le plus grand prix d'offre
			Alerte.prixReserveAtteint(new Offre(utilisateur, prixO), this); // On regarde si le prix de reserve à été atteint par cette offre
			return new Offre(utilisateur, prixO);
		}
		else if(prixO < this.getPrixMinimum())
		{
			System.out.println("Vous ne pouvez pas emettre une offre à un prix inférieur au prix de l'enchere en question");
			return null;
		}
		else if(this.utilisateur.getLogin().equals(this.getUtilisateur().getLogin()))
		{
			System.out.println("Vous ne pouvez pas faire d'offres sur votre propre Enchere");
			return null;
		}
		else
		{
			System.out.println("Une erreur s'est produite votre offre n'as pas été crée");
			return null;
		}
	}
	
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
				this.alertes.add(new Alerte(user, prixRes, true, true, true));
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
				this.alertes.add(new Alerte(user, true, prixRes, true, true));
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
				this.alertes.add(new Alerte(user, true, true, prixRes, true));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
	
	public void ConfigurationAlerteEnchereTerminee(Utilisateur user, boolean prixRes)
	{
		int i = 0;
		if(!this.getUtilisateur().equals(user))
		{
			for(Alerte alerte : this.alertes)
			{
				if(alerte.user.equals(user))
				{
					alerte.enchereTerminee = prixRes;
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				this.alertes.add(new Alerte(user, true, true, true, prixRes));
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
