import java.util.*;


public class Achteur extends Utilisateur{
	
	public Achteur(String log, String nom, String pre)
	{
		this.login = log;
		this.nom = nom;
		this.prenom = pre;
		this.offres = new ArrayList<Offre>();
		this.type = TypeUtilisateur.Achteur;
	}
	
	protected Enchere creeEnchere(long pMin, long pReserve, String identifiant, String description, String etat)
	{ 
		System.out.println("Vous êtes un achteur vous ne pouvez pas créer d'enchere");
		return null;
	}
	
	
	protected Offre creeOffre(Enchere en, long prixO)
	{
		if(verficationPrixOffre(en, prixO))
		{
			this.offres.add(new Offre(this, en, prixO));
			return new Offre(this, en, prixO);	
		}
		else
		{
			return null;
		}
	}

}
