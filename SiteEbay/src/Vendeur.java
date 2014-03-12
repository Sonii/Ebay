
public class Vendeur extends Utilisateur{
	
	public Vendeur(String log, String nom, String pre)
	{
		this.login = log;
		this.nom = nom;
		this.prenom = pre;
	}
	
	public Enchere creationEnchere(long pMin, long pReserve, String identifiant, String description, String etat)
	{
		new Enchere(pMin, pReserve, this.login, identifiant, description, etat).DatelimiteEnchere();
		return new Enchere(pMin, pReserve, this.login, identifiant, description, etat);
	}
	public void emettreOffre(){};
}
