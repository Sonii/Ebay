import java.util.*;


public abstract class Utilisateur {
	
	protected String login = "";
	protected String nom = "";
	protected String prenom = "";
	protected ArrayList<Offre> offres;
	protected TypeUtilisateur type;
	protected ArrayList<Enchere> encheres;
	
	
	protected abstract Enchere creeEnchere(long pMin, long pReserve, String identifiant, String description, String etat);
	protected abstract Offre creeOffre(Enchere en, long prixO);


	public boolean verficationPrixOffre(Enchere en, long prixOffre)
	{
		try
		{
			if(prixOffre > en.prixMinimum)
			{
				return true;
			}
			else
			{
				System.out.println("Vous ne pouvez pas emettre une offre avec un prix inférieur au prix minimum de l'enchère en question");
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Vérifiez bien que votre enchere existe");
			return false;
		}
	}
	
	
	
}
