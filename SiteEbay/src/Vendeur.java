
public class Vendeur extends Utilisateur{
	
	public Vendeur(String log, String nom, String pre)
	{
		this.login = log;
		this.nom = nom;
		this.prenom = pre;
	}
	
	public Enchere creationEnchere(long pMin, long pReserve, String identifiant, String description, String etat)
	{
		new Enchere(pMin, pReserve, this, identifiant, description, etat).DatelimiteEnchere();
		return new Enchere(pMin, pReserve, this, identifiant, description, etat);
	}
	public Offre emettreOffre(Enchere en, long prixO) // a revoir
	{
		if(checkOffrePrice(en, prixO) && checkUserIdentity(en))
		return new Offre(this, en, prixO);
		else
		return new Offre();
	}

}
