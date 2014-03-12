import java.util.*;


public class Enchere extends Objet{
	
	private Date dateEmission;
	protected long prixMinimum = 0;
	protected long prixReserve = 0;
	protected String utilisateurLogin = "";
	protected String etatEnchere = "";
	private Date dateLimite;
	private final Date hour = new Date(3600*1000);
	
	public Enchere(long pMin, long pReserve, String userName, String identifiant, String description, String etat) 
	{
		super(identifiant,description);
		this.dateEmission = new Date();
		//this.dateLimite = new Date(dateEmission.getTime() + 24*30*hour.getTime()); //Date emission + 30 jours
		this.dateLimite = new Date();
		this.utilisateurLogin = userName;
		this.prixMinimum = pMin;
		this.prixReserve = pReserve;
		this.etatEnchere = etat;
		this.DatelimiteEnchere();
	}
	
	
	public void DatelimiteEnchere()
	{
		while(true) 
		{
			if(this.dateEmission.compareTo(this.dateLimite) == 0)
			{
				this.etatEnchere = "terminé";
				System.out.println("terminé");
				return;
			}
		}
	}

}
