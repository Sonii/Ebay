package metier.Utilisateur;
import metier.Enchere.Enchere;
import metier.Enchere.Offre;


public interface Acheteur {

	public Offre deposerOffre(Enchere en, float prixO);
	public void configurerAlertes (Enchere en, boolean prixRes, boolean annulation, boolean offreSup);
	
}
