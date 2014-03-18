package Utilisateur;
import Enchere.Enchere;
import Enchere.Offre;


public interface Acheteur {

	public Offre deposerOffre(Enchere en, float prixO);
	public void configurerAlertes (Enchere en, boolean prixRes, boolean annulation, boolean offreSup);
	
}
