package metier.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import metier.Enchere.EtatEnchere;
import metier.Systeme.ListeEnchereSingleton;
import metier.Systeme.ListeUtilisateurSingleton;
import metier.Utilisateur.Utilisateur;

public class TestAlert {


	private static  ListeUtilisateurSingleton listUsers;
	private static  ListeEnchereSingleton listEnchere;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 listUsers = ListeUtilisateurSingleton.getInstance();
		 listEnchere = ListeEnchereSingleton.getInstance();
	}
	
	@After
	public void tearDown() throws Exception {
		listUsers.deleteAllUtilisateur();
		listEnchere.deleteAllEnchere();
		System.out.println("Fin du teste");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Debut du teste");
	}


	@Test
	public void TestAlerteEnchereTermineVendeur() {

		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 10);
		 try {  
	         Thread.sleep(1000);  //le temps (1ms) que le system detecte que la date a été atteinte
	      }  
	      catch (InterruptedException e) {  
	      }  
		 assertEquals(EtatEnchere.Terminée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
	}
	
	@Test
	public void TestAlerteEnchereEffectueVendeur() {

		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 10);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 try {  
	         Thread.sleep(1000);  //le temps (1ms) que le system detecte que la date a été atteinte
	      }  
	      catch (InterruptedException e) {  
	      }  
		 assertEquals(EtatEnchere.Terminée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
	}
	@Test
	public void TestAlerteAcheteurOffreSuperieur() {

		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 10);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 50);
		 try {  
	         Thread.sleep(1000);  //le temps (1ms) que le system detecte que la date a été atteinte
	      }  
	      catch (InterruptedException e) {  
	      }  
		 assertEquals(EtatEnchere.Terminée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
	}
	
	@Test
	public void TestAlerteAcheteurPrixReserveAtteint() {

		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 10);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 100);
		 try {  
	         Thread.sleep(1000);  //le temps (1ms) que le system detecte que la date a été atteinte
	      }  
	      catch (InterruptedException e) {  
	      }  
		 assertEquals(EtatEnchere.Terminée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
	}
	
	@Test
	public void TestAlerteAcheteurAnchereAnnulé() {

		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 10);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 90);
		 listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(EtatEnchere.Annulée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
	}
	
	@Test
	public void TestAlerteAcheteurAnchereAnnuléSansAbonnement() {
		 System.out.println("Pas d'alerte");
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 10);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"),false,false,false);
		 listUsers.getUtilisateur("lol").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"),false,false,false);
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 100);
		 listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(EtatEnchere.Annulée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());

	}
}

