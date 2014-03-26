package metier.Enchere;

import metier.Utilisateur.Utilisateur;


public class Offre {

	private Utilisateur acheteur;
	private float prixOffre = 0;
	
	public Offre(Utilisateur user, float prixO)
	{
		this.acheteur = user;
		this.prixOffre = prixO;
	}
	
	public Utilisateur getAcheteur()
	{
		return this.acheteur;
	}
	
	public float getPrixOffre()
	{
		return this.prixOffre;
	}
	
}
