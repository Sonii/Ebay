import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Utilisateur toto = new Utilisateur("toto", "Thomas", "Remond");
		Utilisateur hajar = new Utilisateur("Hajar", "Hajar", "Malil");
		Enchere en2 = toto.CreeEnchere("", "", 5, 8);
		Enchere en3 = toto.CreeEnchere("", "", 6, 8);
		toto.PublieEnchere(en2);
		Enchere en = toto.CreeEnchere("", "", 0, 8);
		//System.out.println(ListeEnchereSingleton.getInstance().listeEnchere.get(0).getPrixMinimum());
		Enchere en1 = hajar.CreeEnchere("", "", 3, 12);
		//System.out.println(ListeEnchereSingleton.getInstance().listeEnchere.get(0).getPrixMinimum());
		//System.out.println(ListeEnchereSingleton.getInstance().listeEnchere.get(1).getPrixMinimum());
		toto.PublieEnchere(en);
		/*System.out.println(ListeEnchereSingleton.getInstance().listeEnchere.get(0).getPrixMinimum());
		System.out.println(ListeEnchereSingleton.getInstance().listeEnchere.get(1).getPrixMinimum());*/
		hajar.CreeOffre(en, 11);
		hajar.CreeOffre(en, 40);
		/*System.out.println(ListeEnchereSingleton.getInstance().listeEnchere.get(0).getPrixMinimum());
		System.out.println(ListeEnchereSingleton.getInstance().listeEnchere.get(1).getPrixMinimum());*/
		/*System.out.println(en.getListeOffres().get(0).getUtilisateur().getLogin());
		for(Enchere enchere : ListeEnchereSingleton.getInstance().getlisteEnchereVisible(hajar))
		{
			System.out.println(enchere.getPrixMinimum());
		}
		System.out.println(ListeEnchereSingleton.getInstance().getlisteEnchereVisible(hajar).get(0).getPrixMinimum());
		System.out.println(ListeEnchereSingleton.getInstance().getlisteEnchereVisible(hajar).get(0).getPrixMinimum());
		//ListeEnchereSingleton.getInstance().getlisteEnchereVisible(hajar);//=>ton echere hajar et l'enchere de toto parce a offre
		//ListeEnchereSingleton.getInstance().getlisteEnchereVisible(toto);
		/*toto.PublieEnchere(en);
		hajar.PublieEnchere(en);*/
		hajar.CreeOffre(en, 5);
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		/*System.out.println(en.getListeAlertes().get(0).user.getLogin());*/
		en.ConfigurationAlerteEnchereAnnulee(hajar, false);
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		en.ConfigurationAlerteOffreSup(hajar, false);
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		en.ConfigurationAlertePrixReserve(hajar, false);
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		
		/*en.CreeOffre(hajar, 11);
		en.ConfigurationAlerteEnchereAnnulee(hajar, false);
		toto.AnnuleEnchere(en);
		en.CreeOffre(hajar, 11);
		System.out.println(en.getPrixReserve(toto));
		System.out.println(en.getPrixReserve(hajar));
		
		//en.ConfigurationAlertePrixReserve(hajar, false);
		//en.ConfigurationAlerteOffreSup(hajar, false);
		//en.CreeOffre(hajar, 15);
		//en.ConfigurationAlertePrixReserve(toto, false);
		
		
		en.CreeOffre(hajar, 11);
		en.ConfigurationAlerteOffreSup(hajar, false);
		System.out.println(en.getListeAlertes().get(0).user.getLogin());
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		//System.out.println(en.alertes.get(1).user.getLogin());
		en.ConfigurationAlerteEnchereAnnulee(hajar, false);
		System.out.println(en.getListeAlertes().get(0).user.getLogin());
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);

		
		en.ConfigurationAlertePrixReserve(hajar, false);
		System.out.println(en.getListeAlertes().get(0).user.getLogin());
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);

		
	/*	en.CreeOffre(hajar, 11);
		System.out.println(en.alertes.get(0).user.getLogin());
		System.out.println(en.alertes.get(0).prixReserveAtteint);
		System.out.println(en.alertes.get(0).offreSuperieure);
		System.out.println(en.alertes.get(0).enchereAnnulee);
		//System.out.println(en.alertes.get(1).enchereAnnulee);
		//System.out.println(en.alertes.get(1).prixReserveAtteint);
		/*en.CreeOffre(hajar, 11);
		System.out.println(en.alertes.get(0).user.getLogin());
		System.out.println(en.alertes.get(0).prixReserveAtteint);
		en.ConfigurationAlertePrixReserve(hajar, false);
		System.out.println(en.alertes.get(0).user.getLogin());
		System.out.println(en.alertes.get(0).prixReserveAtteint);
		en.CreeOffre(hajar, 14);*/
		
	}

}
