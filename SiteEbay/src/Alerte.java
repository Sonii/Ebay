
public class Alerte {

	protected  boolean prixReserveAtteint;
	protected  boolean offreSuperieure;
	protected  boolean enchereAnnulee;
	protected  boolean enchereTerminee;
	protected Utilisateur user;
	
	public Alerte(Utilisateur user, boolean prixR, boolean offreS, boolean enchereA, boolean enchereT)
	{
		this.user = user;
		this.prixReserveAtteint = prixR;
		this.offreSuperieure = offreS;
		this.enchereAnnulee = enchereA;
		this.enchereTerminee = enchereT;
	}
	
	public static void prixReserveAtteint(Offre offre, Enchere en) // A modifier Alertes
	{
		//if((en.getPrixReserve() <= offre.getPrixOffre()) && (en.getListeOffres().get(en.getListeOffres().size() - 2).getPrixOffre() < en.getPrixReserve()))
		if(en.getPrixReserve() <= offre.getPrixOffre())
		{
			for(Offre o : en.getListeOffres())
			{
				for(Alerte alerte : en.getListeAlertes())
				{
					if(o.getUtilisateur().equals(alerte.user))
					{
						if(alerte.prixReserveAtteint == true)
						{
							if(o.getPrixOffre() < offre.getPrixOffre())
								System.out.println("Alerte prix de reserve atteint par "+ o.getUtilisateur().getLogin());
							else
								System.out.println("Le prix d'offre a été atteint par votre offre");
						}
					}
				}
			}
		}
	}
	
		
	
	
}
