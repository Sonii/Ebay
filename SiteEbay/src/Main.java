import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Utilisateur toto = new Utilisateur("toto", "Thomas", "Remond");
		Utilisateur hajar = new Utilisateur("Hajar", "Hajar", "Malil");
		
		Enchere en = toto.CreeEnchere("", "", 0, 12);
		
		en.CreeOffre(hajar, 12);
		System.out.println(en.getListeAlertes().get(0).user.getLogin());
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		System.out.println(en.getListeAlertes().get(0).enchereTerminee);
		
		
		en.CreeOffre(hajar, 14);
		en.ConfigurationAlertePrixReserve(hajar, false);
		en.CreeOffre(hajar, 15);
		//en.ConfigurationAlertePrixReserve(toto, false);
		
		
		/*//en.CreeOffre(hajar, 11);
		en.ConfigurationAlerteOffreSup(hajar, false);
		System.out.println(en.getListeAlertes().get(0).user.getLogin());
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		System.out.println(en.getListeAlertes().get(0).enchereTerminee);
		//System.out.println(en.alertes.get(1).user.getLogin());
		en.ConfigurationAlerteEnchereAnnulee(hajar, false);
		System.out.println(en.getListeAlertes().get(0).user.getLogin());
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		System.out.println(en.getListeAlertes().get(0).enchereTerminee);
		
		en.ConfigurationAlerteEnchereTerminee(hajar, false);
		System.out.println(en.getListeAlertes().get(0).user.getLogin());
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		System.out.println(en.getListeAlertes().get(0).enchereTerminee);
		
		en.ConfigurationAlertePrixReserve(toto, false);
		System.out.println(en.getListeAlertes().get(0).user.getLogin());
		System.out.println(en.getListeAlertes().get(0).prixReserveAtteint);
		System.out.println(en.getListeAlertes().get(0).offreSuperieure);
		System.out.println(en.getListeAlertes().get(0).enchereAnnulee);
		System.out.println(en.getListeAlertes().get(0).enchereTerminee);
		
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
