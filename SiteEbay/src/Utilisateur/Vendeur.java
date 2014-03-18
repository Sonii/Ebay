package Utilisateur;
import Enchere.Enchere;


public interface Vendeur {
	
	public Enchere creerEnchere(String description, String identifiant, float prixMin, float prixReserve, int nbrHeure);
	public void publierEnchere(Enchere en);
	public void annulerEnchere(Enchere en);
	
}
