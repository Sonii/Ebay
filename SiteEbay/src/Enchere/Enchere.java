package Enchere;
import java.util.*;

import Systeme.HorlogeSingleton;
import Utilisateur.Utilisateur;


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
	private Timer timer;
	
	public Enchere(String description, String identifiant, float prixMin, float prixReserve, Utilisateur user)
	{
		this.objet = new Objet(description, identifiant);
		this.offres = new ArrayList<Offre>();
		this.alertes = new ArrayList<Alerte>();
		this.prixMinimum = prixMin;
		this.prixReserve = prixReserve;
		this.utilisateur = user;
		this.etatEnchere = EtatEnchere.Crée;
		this.dateCreation = HorlogeSingleton.getInstance().getTemps();
		this.dateExpiration = new Date(dateCreation.getTime() + new Date(1000*60*60*2).getTime());
		this.timer=new Timer();  //At this line a new Thread will be created
        timer.schedule(new Verification(this, dateExpiration), 0, 1);
	}
	

/* Getteres and Setters*/
	
	public ArrayList<Alerte> getListeAlertes()
	{
		return this.alertes;
	}
	
	public ArrayList<Offre> getListeOffres()
	{
		return this.offres;
	}
	
	public Float getPrixReserve(Utilisateur user)
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
	
	public void setEtatEnchere(EtatEnchere etat)
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