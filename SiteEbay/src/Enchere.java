import java.util.*;


public class Enchere extends Objet{
	
	private Date dateEmission;
	private Date dateLimite;
	//private final Date hour = new Date(3600*1000);
	private final Date minute = new Date(60000);
	protected long prixMinimum = 0;
	protected long prixReserve = 0;
	protected Vendeur vendeur;
	protected String etatEnchere = "";
	
	public Enchere(long pMin, long pReserve, Vendeur v, String identifiant, String description, String etat) 
	{
		super(identifiant,description);
		this.dateEmission = new Date();
		//this.dateLimite = new Date(dateEmission.getTime() + 24*30*hour.getTime()); //Date emission + 30 jours
		this.dateLimite = new Date(dateEmission.getTime() + minute.getTime()); //Date emission + 1 minute
		this.vendeur = v;
		this.prixMinimum = pMin;
		this.prixReserve = pReserve;
		this.etatEnchere = etat;
	}
	
	public String getEtat()
	{
		return this.etatEnchere;
	}
	public void setEtat(String etat)
	{
		this.etatEnchere = etat;
	}
	public Date getDateEmission()
	{
		return this.dateEmission;
	}
	
	public Date getDateLimite()
	{
		return this.dateLimite;
	}
	
	/* Après une minute l'etat de l'offre passe à l'état terminé */
	public void DatelimiteEnchere()
	{
		Date now;
		System.out.println("Voici la date d'emission : "+this.dateEmission);
		System.out.println("Voici la date dans la quelle l'enchere est supposé s'arreter : "+this.dateLimite);
		while(true) 
		{
			now = new Date();
			if((now.compareTo(this.dateLimite) == 0) || (now.compareTo(this.dateLimite) > 0))
			{
				this.etatEnchere = "terminé";
				System.out.println("terminé");
				System.out.println("Voici la date où l'enchere s'arrete : "+now);
				return;
			}
			
		}
	}

}
