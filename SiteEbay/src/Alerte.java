
public class Alerte {

	protected  boolean prixReserveAtteint;
	protected  boolean offreSuperieure;
	protected  boolean enchereAnnulee;
	protected Utilisateur user;
	
	public Alerte(Utilisateur user, boolean prixR, boolean offreS, boolean enchereA)
	{
		this.user = user;
		this.prixReserveAtteint = prixR;
		this.offreSuperieure = offreS;
		this.enchereAnnulee = enchereA;
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
					if(o.getUtilisateur().equals(alerte.user))
					{
						if(alerte.prixReserveAtteint == true)
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
					if(o.getUtilisateur().equals(alerte.user))
					{
						if(alerte.offreSuperieure == true)
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
				if(o.getUtilisateur().equals(alerte.user))
				{
					if(alerte.enchereAnnulee == true)
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
}
