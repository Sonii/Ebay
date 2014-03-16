import java.util.*;


public class Enchere {

	private Objet objet;
	private ArrayList<Offre> offres;
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
		this.prixMinimum = prixMin;
		this.prixReserve = prixReserve;
		this.utilisateur = user;
		this.etatEnchere = EtatEnchere.Crée;
		this.dateCreation = new Date();
		this.dateExpiration = new Date(dateCreation.getTime() + new Date(2000).getTime());
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
	
	public void prixReserveAtteint(Offre offre) // A modifier Alertes
	{
		if((this.prixReserve <= offre.getPrixOffre()) && (this.offres.get(offres.size() - 2).getPrixOffre() < offre.getPrixOffre()))
		{
			for(Offre o : offres)
			{
				if(o.getPrixOffre() < offre.getPrixOffre())
					System.out.println("Allerte prix de reserve atteint"+ o.getUtilisateur().getLogin());
				else
					System.out.println("Le prix d'offre a été atteint par votre offre");
			}
		}
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
			this.ajouteOffre(new Offre(utilisateur, prixO)); // On ajoute l'offre au tableau
			Collections.sort(offres, new OffreComparator()); // On tri le tableau selon l'ordre ascendant des pris d'offres
			this.OffreSupérieure(new Offre(utilisateur, prixO)); // On regarde si cette offre a le plus grand prix d'offre
			this.prixReserveAtteint(new Offre(utilisateur, prixO)); // On regarde si le prix de reserve à été atteint par cette offre
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
