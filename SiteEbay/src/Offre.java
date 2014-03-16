
public class Offre {

	private Utilisateur utilisateur;
	private float prixOffre = 0;
	
	public Offre(Utilisateur user, float prixO)
	{
		this.utilisateur = user;
		this.prixOffre = prixO;
	}
	
	public Utilisateur getUtilisateur()
	{
		return this.utilisateur;
	}
	
	public float getPrixOffre()
	{
		return this.prixOffre;
	}
	
}
