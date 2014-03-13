
public abstract class Utilisateur {
	
	protected String login = "";
	protected String nom = "";
	protected String prenom = "";
	
	abstract Offre emettreOffre(Enchere en, long prixO);
	
	public boolean checkOffrePrice(Enchere en, long prixOffre)
	{
		  if(prixOffre < en.prixMinimum)
		  {
			  System.out.println("Vous ne pouvez pas emettre une offre avec un prix inférieur à celui de l'enchère en question");
			  return false;
		  }
		  else
		  {
			  return true;
		  }
	}
	public boolean checkUserIdentity(Enchere en)
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
}
