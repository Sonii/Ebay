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
		this.etatEnchere = EtatEnchere.Cr�e;
		this.dateCreation = new Date();
		this.dateExpiration = new Date(dateCreation.getTime() + new Date(2000).getTime());
	}

	/* Cr�ation d'offre */
	
	public Offre CreeOffre(Utilisateur utilisateur, float prixO)
	{
		if(this.etatEnchere == EtatEnchere.Publi�e)
		{
			if((!(this.utilisateur.equals(utilisateur.getLogin())) && (prixO >= this.getPrixMinimum())))
			{
				Offre offre = new Offre(utilisateur, prixO);
				System.out.println("Votre offre a bien �t� cr�e");
				if(this.VerfierAlerteUtilisateur(utilisateur) == 0)
				{
					this.alertes.add(new Alerte(utilisateur, true,true,true));
				}
				//utilisateur.getListeEncheresVisibles().add(this); // On ajoute l'enchere � la liste des enchere visibles de l'achteur 
				Alerte.AlerteVendeur(this.getUtilisateur(), offre); // A chaque fois qu'une offre est �mise le vendeur est pr�venu
				this.offres.add(offre); // On ajoute l'offre au tableau
				Collections.sort(offres, new OffreComparator()); // On tri le tableau selon l'ordre ascendant des pris d'offres
				Alerte.OffreSup�rieure(offre, this); // On regarde si cette offre a le plus grand prix d'offre
				Alerte.prixReserveAtteint(offre, this); // On regarde si le prix de reserve � �t� atteint par cette offre
				return offre;
			}
			else if(prixO < this.getPrixMinimum())
			{
				System.out.println("Vous ne pouvez pas emettre une offre � un prix inf�rieur au prix de l'enchere en question");
				return null;
			}
			else if(this.utilisateur.equals(utilisateur))
			{
				System.out.println("Vous ne pouvez pas faire d'offres sur votre propre Enchere");
				return null;
			}
			else
			{
				System.out.println("Une erreur s'est produite votre offre n'as pas �t� cr�e");
				return null;
			}
		}
		else
		{
			System.out.println("Cette offre a �t� annul�e par son vendeur ou termin�e");
			return null;
		}
	}
	
	/* Configuration D'ALERTES */	
/* Un Achteur peut configurer n'importe que alerte qui n'est pas la sienne biensur m�me s'il n'a pas emis des offres sur l'enchere en question*/

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
