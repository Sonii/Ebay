package Utilisateur;
import Enchere.Enchere;
import Enchere.Offre;


public interface Acheteur {

	public Offre CreeOffre(Enchere en, float prixO);
	public void ConfigurationAlertePrixReserve(Enchere en, boolean prixRes);
	public void ConfigurationAlerteOffreSup(Enchere en, boolean prixRes);
	public void ConfigurationAlerteEnchereAnnulee(Enchere en, boolean prixRes);
	
}
