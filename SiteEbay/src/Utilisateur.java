
public abstract class Utilisateur {
	
	protected String login = "";
	protected String nom = "";
	protected String prenom = "";
	
	abstract Offre emettreOffre(Enchere en, long prixO);
	
	public boolean checkOffrePrice(Enchere en, long prixOffre)
	{
		  if(prixOffre < en.prixMinimum)
		  {
			  System.out.println("Vous ne pouvez pas emettre une offre avec un prix inf�rieur � celui de l'ench�re en question");
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
			  System.out.println("Vous ne pouvez pas emettre une offre sur votre propre ench�re");
			  return false;
		  }
		  else
		  {
			  return true;
		  }
	  }
}
