import java.util.*;


public class Vendeur extends Utilisateur{
	
	public Vendeur(String log, String nom, String pre)
	{
		this.login = log;
		this.nom = nom;
		this.prenom = pre;
		this.encheres = new ArrayList<Enchere>();
		this.offres = new ArrayList<Offre>();
		this.type = TypeUtilisateur.Vendeur;
	}
	
	protected Enchere creeEnchere(long pMin, long pReserve, String identifiant, String description, String etat)
	{
		this.encheres.add(new Enchere(pMin, pReserve, this, identifiant, description, etat));
		new Enchere(pMin, pReserve, this, identifiant, description, etat).DatelimiteEnchere();
		return new Enchere(pMin, pReserve, this, identifiant, description, etat);	
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
	/*public Enchere creationEnchere(long pMin, long pReserve, String identifiant, String description, String etat)
	{
		new Enchere(pMin, pReserve, this, identifiant, description, etat).DatelimiteEnchere();
		return new Enchere(pMin, pReserve, this, identifiant, description, etat);
	}
	public boolean verificationEncherePropriete(Enchere en)
	{
		  if(this.login.equals(en.vendeur.login))
		  {
			  System.out.println("Vous ne pouvez pas emettre une offre sur votre propre enchère");
			  return false;
		  }
		  else
		  {
			  return true;
		  }
	}
	public Offre emettreOffre(Enchere en, long prixO) // a revoir
	{
		if(verficationPrixOffre(en, prixO) && verificationEncherePropriete(en))
		return new Offre(this, en, prixO);
		else
		return null;
	}*/
}
