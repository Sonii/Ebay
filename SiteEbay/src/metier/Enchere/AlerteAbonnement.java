package metier.Enchere;

import metier.Utilisateur.Utilisateur;


public class AlerteAbonnement {

	private  boolean prixReserveAtteint;
	private  boolean offreSuperieure;
	private  boolean enchereAnnulee;
	private Utilisateur acheteur;
	
	public AlerteAbonnement(Utilisateur acheteur, boolean prixR, boolean offreS, boolean enchereA)
	{
		this.setAcheteur(acheteur);
		this.setPrixReserveAtteint(prixR);
		this.setOffreSuperieure(offreS);
		this.setEnchereAnnulee(enchereA);
	}
	
	
	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur2) {
		this.acheteur = acheteur2;
	}

	public boolean isOffreSuperieure() {
		return offreSuperieure;
	}

	public void setOffreSuperieure(boolean offreSuperieure) {
		this.offreSuperieure = offreSuperieure;
	}

	public boolean isEnchereAnnulee() {
		return enchereAnnulee;
	}

	public void setEnchereAnnulee(boolean enchereAnnulee) {
		this.enchereAnnulee = enchereAnnulee;
	}

	/**
	 * @return the prixReserveAtteint
	 */
	public boolean isPrixReserveAtteint() {
		return prixReserveAtteint;
	}

	/**
	 * @param prixReserveAtteint the prixReserveAtteint to set
	 */
	public void setPrixReserveAtteint(boolean prixReserveAtteint) {
		this.prixReserveAtteint = prixReserveAtteint;
	}
}
