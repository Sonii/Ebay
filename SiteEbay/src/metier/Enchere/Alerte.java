package metier.Enchere;

import metier.Utilisateur.Utilisateur;


public class Alerte {

	private  boolean prixReserveAtteint;
	private  boolean offreSuperieure;
	private  boolean enchereAnnulee;
	private Utilisateur acheteur;
	
	public Alerte(Utilisateur acheteur, boolean prixR, boolean offreS, boolean enchereA)
	{
		this.setAcheteur(acheteur);
		this.setPrixReserveAtteint(prixR);
		this.setOffreSuperieure(offreS);
		this.setEnchereAnnulee(enchereA);
	}
	
	public static void prixReserveAtteint(Offre offre, Enchere en) // A modifier Alertes
	{
		//if((en.getPrixReserve() <= offre.getPrixOffre()) && (en.getListeOffres().get(en.getListeOffres().size() - 2).getPrixOffre() < en.getPrixReserve()))
		if(en.getPrixReserve(en.getVendeur()) <= offre.getPrixOffre())
		{
			for(Offre o : en.getListeOffres())
			{
				for(Alerte alerte : en.getListeAlertes())
				{
					if(o.getAcheteur().equals(alerte.getAcheteur()))
					{
						if(alerte.isPrixReserveAtteint() == true)
						{
							if(o.getPrixOffre() != offre.getPrixOffre())
								System.out.println("Alerte Enchérisseur "+alerte.getAcheteur().getLogin()+" : prix de reserve atteint par "+ offre.getAcheteur().getLogin());
							else if((o.getPrixOffre() == offre.getPrixOffre()) && (o.getAcheteur().equals(offre.getAcheteur())))
								System.out.println("Alerte Enchérisseur "+alerte.getAcheteur().getLogin()+" : Le prix de réserve a été atteint par votre offre");
							else
								System.out.println("Alerte Enchérisseur "+alerte.getAcheteur().getLogin()+" : prix de reserve atteint par "+ o.getAcheteur().getLogin());
						}
					}
				}
			}
		}
	}
	
	public static void offreSuperieure(Offre offre, Enchere en) // A modifier Alertes
	{
		for(Offre o : en.getListeOffres())
		{
			if(o.getPrixOffre() < offre.getPrixOffre())
			{
				for(Alerte alerte : en.getListeAlertes())
				{
					if(o.getAcheteur().equals(alerte.getAcheteur()))
					{
						if(alerte.isOffreSuperieure() == true)
						{
							System.out.println("Alerte Enchérisseur "+alerte.getAcheteur().getLogin()+" : " + offre.getAcheteur().getLogin() + " vient d'emettre une offre superieure à la votre");
						}
					}
				}
			}
		}
	}
	
	public static void enchereAnnulee(Enchere en)
	{
		for(Offre o : en.getListeOffres())
		{
			for(Alerte alerte : en.getListeAlertes())
			{
				if(o.getAcheteur().equals(alerte.getAcheteur()))
				{
					if(alerte.isEnchereAnnulee() == true)
					{
						System.out.println("Alerte Enchérisseur "+alerte.getAcheteur().getLogin()+" : l'enchere " + en.getObjet().getDescription() + " vient d'être annulée par son vendeur");
					}
				}
			}
		}
	}
	
	public static void enchereTerminee(Enchere en)
	{
		int i = 0;
		if (en.getListeOffres().isEmpty()) {
			System.out.println("Alerte Vendeur "+ en.getVendeur().getLogin()+" : votre enchere " + en.getObjet().getDescription()+ " s'est terminé mais aucun acheteur");
		}
		else {
			for(Offre o : en.getListeOffres())
			{
				while(i < en.getListeOffres().size() - 1)
				{
					System.out.println("Alerte Enchérisseur "+ o.getAcheteur().getLogin()+" : l'enchere " + en.getObjet().getDescription() + " vient d'être vendue à un autre acheteur");
					i++;
				}
				if(o.equals(en.getListeOffres().get(en.getListeOffres().size() - 1)))
					System.out.println("Alerte Enchérisseur "+en.getListeOffres().get(en.getListeOffres().size()-1).getAcheteur().getLogin() +" : vous avez eu l'enchere " + en.getObjet().getDescription());
				
			}
			System.out.println("Alerte Vendeur "+ en.getVendeur().getLogin()+" : " +en.getListeOffres().get(en.getListeOffres().size()-1).getAcheteur().getLogin() +" a remporter votre enchere" + en.getObjet().getDescription());
		
		}
	}
	
	public static void alerteVendeur(Utilisateur vendeur, Offre offre)
	{
		System.out.println("Alerte Vendeur "+ vendeur.getLogin()+" : Une nouvelle offre a été, emise sur votre enchere par " + offre.getAcheteur().getLogin());
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
