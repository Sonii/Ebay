package metier.Enchere;
import java.util.*;

import metier.Systeme.HorlogeSingleton;
import metier.Utilisateur.Utilisateur;


public class Enchere {

	private Objet objet;
	private ArrayList<Offre> offres;
	private ArrayList<Alerte> alertes;
	private float prixMinimum = 0;
	private float prixReserve = 0;
	private Date dateCreation;
	private Date dateExpiration;
	private Utilisateur vendeur;
	private EtatEnchere etatEnchere;
	private Timer timer;
	
	public Enchere(String description, String identifiant, float prixMin, float prixReserve, Utilisateur vendeur, int heures)
	{
		this.objet = new Objet(description, identifiant);
		this.offres = new ArrayList<Offre>();
		this.alertes = new ArrayList<Alerte>();
		this.prixMinimum = prixMin;
		this.prixReserve = prixReserve;
		this.vendeur = vendeur;
		this.etatEnchere = EtatEnchere.Crée;
		this.dateCreation = HorlogeSingleton.getInstance().getTemps();
		this.dateExpiration = new Date(dateCreation.getTime() + new Date(1000*60*60*heures).getTime());
		this.timer=new Timer();  //At this line a new Thread will be created
        timer.schedule(new VerificationTache(this, dateExpiration), 0, 10);
	}
	

/* Getteres and Setters*/
	// TOTO faire la methode enchere terminer => lance l'alerte{
	//Alerte.EnchereTerminee(this.enchere);
    //}
	public ArrayList<Alerte> getListeAlertes()
	{
		return this.alertes;
	}
	
	public ArrayList<Offre> getListeOffres()
	{
		return this.offres;
	}
	
	public Float getPrixReserve(Utilisateur utilisateur)
	{
		if(this.vendeur.equals(utilisateur))
		{
			return this.prixReserve;
		}
		else
		{
			System.out.println("Le prix de reserve d'une enchere n'est visible que par son vendeur");
			return null;
		}
	}
	
	public Alerte getAlerte (Utilisateur acheteur){
		for (Alerte aa : this.getListeAlertes() ){
			if (aa.getAcheteur()==acheteur){
				return aa;
			}
		}
		return null;
	}
	
	public void setEtatEnchere(EtatEnchere etat)
	{
		this.etatEnchere = etat;
	}
	
	public Objet getObjet()
	{
		return this.objet;
	}
	
	public Utilisateur getVendeur()
	{
		return this.vendeur; 
	}
	
	public float getPrixMinimum()
	{
		return this.prixMinimum;
	}
	
	public Date getDateCreation()
	{
		return this.dateCreation;
	}
	
	public Date getDateExpiration()
	{
		return this.dateExpiration;
	}
	
	public EtatEnchere getEtatEnchere()
	{
		return this.etatEnchere;
	}
}