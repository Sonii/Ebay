package Systeme;

import Utilisateur.Utilisateur;
import Enchere.AlerteAbonnement;
import Enchere.Enchere;
import Enchere.Offre;

public class Alerte {

	public static void prixReserveAtteint(Offre offre, Enchere en) // A modifier Alertes
	{
		//if((en.getPrixReserve() <= offre.getPrixOffre()) && (en.getListeOffres().get(en.getListeOffres().size() - 2).getPrixOffre() < en.getPrixReserve()))
		if(en.getPrixReserve(en.getVendeur()) <= offre.getPrixOffre())
		{
			for(Offre o : en.getListeOffres())
			{
				for(AlerteAbonnement alerte : en.getListeAlertes())
				{
					if(o.getAcheteur().equals(alerte.getAcheteur()))
					{
						if(alerte.isPrixReserveAtteint() == true)
						{
							if(o.getPrixOffre() != offre.getPrixOffre())
								System.out.println("Alerte : prix de reserve atteint par "+ offre.getAcheteur().getLogin());
							else if((o.getPrixOffre() == offre.getPrixOffre()) && (o.getAcheteur().equals(offre.getAcheteur())))
								System.out.println("Alerte : Le prix de réserve a été atteint par votre offre");
							else
								System.out.println("Alerte : prix de reserve atteint par "+ o.getAcheteur().getLogin());
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
				for(AlerteAbonnement alerte : en.getListeAlertes())
				{
					if(o.getAcheteur().equals(alerte.getAcheteur()))
					{
						if(alerte.isOffreSuperieure() == true)
						{
							System.out.println("Alerte : " + offre.getAcheteur().getLogin() + " vient d'emettre une offre superieure à la votre");
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
			for(AlerteAbonnement alerte : en.getListeAlertes())
			{
				if(o.getAcheteur().equals(alerte.getAcheteur()))
				{
					if(alerte.isEnchereAnnulee() == true)
					{
						System.out.println("Alerte : l'enchere " + en.getObjet().getDescription() + " vient d'être annulée par son vendeur");
					}
				}
			}
		}
	}
	
	public static void enchereTerminee(Enchere en)
	{
		int i = 0;
		if (en.getListeOffres().isEmpty()) {
			System.out.println("Alerte Vendeur : " + en.getVendeur().getLogin()+" votre enchere " + en.getObjet().getDescription()+ " s'est terminé mais aucun acheteur");
		}
		else {
			for(Offre o : en.getListeOffres())
			{
				while(i < en.getListeOffres().size() - 1)
				{
					System.out.println("Alerte : l'enchere " + en.getObjet().getDescription() + " vient d'être vendue à un autre acheteur");
					i++;
				}
				if(o.equals(en.getListeOffres().get(en.getListeOffres().size() - 1)))
					System.out.println("Félicitation : "+en.getListeOffres().get(en.getListeOffres().size()-1).getAcheteur().getLogin() +" (vous) avez eu l'enchere " + en.getObjet().getDescription());
				
			}
			System.out.println("Alerte Vendeur : " +en.getListeOffres().get(en.getListeOffres().size()-1).getAcheteur().getLogin() +" a remporter votre enchere" + en.getObjet().getDescription());
		
		}
	}
	
	public static void alerteVendeur(Utilisateur login, Offre offre)
	{
		System.out.println("Alerte Vendeur : Une nouvelle offre a été, emise sur votre enchere par " + offre.getAcheteur().getLogin());
	}
}
