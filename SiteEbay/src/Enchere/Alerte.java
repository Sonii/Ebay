package Enchere;
import Utilisateur.Utilisateur;


public class Alerte {

	private  boolean prixReserveAtteint;
	private  boolean offreSuperieure;
	private  boolean enchereAnnulee;
	private Utilisateur user;
	
	public Alerte(Utilisateur user, boolean prixR, boolean offreS, boolean enchereA)
	{
		this.setUser(user);
		this.setPrixReserveAtteint(prixR);
		this.setOffreSuperieure(offreS);
		this.setEnchereAnnulee(enchereA);
	}
	
	public static void prixReserveAtteint(Offre offre, Enchere en) // A modifier Alertes
	{
		//if((en.getPrixReserve() <= offre.getPrixOffre()) && (en.getListeOffres().get(en.getListeOffres().size() - 2).getPrixOffre() < en.getPrixReserve()))
		if(en.getPrixReserve(en.getUtilisateur()) <= offre.getPrixOffre())
		{
			for(Offre o : en.getListeOffres())
			{
				for(Alerte alerte : en.getListeAlertes())
				{
					if(o.getUtilisateur().equals(alerte.getUser()))
					{
						if(alerte.isPrixReserveAtteint() == true)
						{
							if(o.getPrixOffre() != offre.getPrixOffre())
								System.out.println("Alerte : prix de reserve atteint par "+ o.getUtilisateur().getLogin());
							else if((o.getPrixOffre() == offre.getPrixOffre()) && (o.getUtilisateur().equals(offre.getUtilisateur())))
								System.out.println("Alerte : Le prix de réserve a été atteint par votre offre");
							else
								System.out.println("Alerte : prix de reserve atteint par "+ o.getUtilisateur().getLogin());
						}
					}
				}
			}
		}
	}
	
	public static void OffreSupérieure(Offre offre, Enchere en) // A modifier Alertes
	{
		for(Offre o : en.getListeOffres())
		{
			if(o.getPrixOffre() < offre.getPrixOffre())
			{
				for(Alerte alerte : en.getListeAlertes())
				{
					if(o.getUtilisateur().equals(alerte.getUser()))
					{
						if(alerte.isOffreSuperieure() == true)
						{
							System.out.println("Alerte : " + offre.getUtilisateur().getLogin() + " vient d'emettre une offre superieure à la votre");
						}
					}
				}
			}
		}
	}
	
	public static void EnchereAnnulee(Enchere en)
	{
		for(Offre o : en.getListeOffres())
		{
			for(Alerte alerte : en.getListeAlertes())
			{
				if(o.getUtilisateur().equals(alerte.getUser()))
				{
					if(alerte.isEnchereAnnulee() == true)
					{
						System.out.println("Alerte : l'enchere " + en.getObjet().getIdentifiant() + " vient d'être annulée par son vendeur");
					}
				}
			}
		}
	}
	
	public static void EnchereTerminee(Enchere en)
	{
		int i = 0;
		for(Offre o : en.getListeOffres())
		{
			while(i < en.getListeOffres().size() - 1)
			{
				System.out.println("Alerte : l'enchere " + en.getObjet().getIdentifiant() + " vient d'être vendue à un autre achteur");
				i++;
			}
			if(o.equals(en.getListeOffres().get(en.getListeOffres().size() - 1)))
				System.out.println("Félicitation : Vous avez eu l'enchere " + en.getObjet().getIdentifiant());
		}
	}
	
	public static void AlerteVendeur(Utilisateur user, Offre offre)
	{
		System.out.println("Alerte : Une nouvelle offre a été, emise sur votre enchere par " + offre.getUtilisateur().getLogin());
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
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
