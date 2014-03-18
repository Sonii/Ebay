package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import Enchere.AlerteAbonnement;
import Enchere.EtatEnchere;
import Enchere.Offre;
import Systeme.ListeEnchereSingleton;
import Systeme.ListeUtilisateurSingleton;
import Utilisateur.Utilisateur;

public class TestUtilisateur {

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
	}
	
	@Test
	public void CreationUtilisateurLogin() {
		 new Utilisateur ("Sonii","Hajar", "MALIL");

		assertSame("Sonii",listUsers.getUtilisateur("Sonii").getLogin());
	}

	@Test
	public void CreationUtilisateurLoginDejaUtilisé() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Sonii","Hajar", "MALIL");
		assertEquals(1,listUsers.getListeUtilisateur().size());
	}
	
	@Test
	public void EnchereNonVisibleParAcheteur() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertEquals(0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
	
	
	@Test
	public void EnchereEtVisibleParLeVendeur() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Sonii")).size());
	}
	
	@Test
	public void PublierEnchereEtVisableParAcheteur() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
	
	@Test
	public void AcheteurFaitUneOffre() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Offre o = listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		assertNotNull(o);
	}
	
	@Test
	public void AcheteurFaitUneOffreSurEnchereNull() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Offre o = listUsers.getUtilisateur("Malilou").deposerOffre(null, 10);
		assertNull(o);
	}
	
	@Test
	public void AcheteurFaitUneOffrePrixInferieur() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Offre o = listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 0.99);
		assertNull(o);
	}
	
	@Test
	public void VendeurFaitUneOffre() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Offre o = listUsers.getUtilisateur("Sonii").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 10);
		assertNull(o);
	}
	
	@Test
	public void VendeurConfigurerAlerte() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Sonii").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		AlerteAbonnement aa = listEnchere.getEnchereByDesc("Playstation").getAlerte(listUsers.getUtilisateur("Sonii"));
		assertNull(aa);
	}
	
	@Test
	public void AcheteurConfigurerAlerte() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		AlerteAbonnement aa = listEnchere.getEnchereByDesc("Playstation").getAlerte(listUsers.getUtilisateur("Malilou"));
		assertNotNull(aa);
	}
	
	@Test
	public void VendeurAnnuleEnchere() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		EtatEnchere ee = listEnchere.getEnchereByDesc("Playstation").getEtatEnchere();
		assertEquals(ee,EtatEnchere.Annulée);
	}
	
	@Test
	public void VendeurAnnuleEncherePrixReserveAtteint() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 100);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		EtatEnchere ee = listEnchere.getEnchereByDesc("Playstation").getEtatEnchere();
		assertEquals(ee,EtatEnchere.Publiée);
	}
	
	@Test
	public void VendeurAnnuleEncherePrixReservePasAtteint() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 99);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		EtatEnchere ee = listEnchere.getEnchereByDesc("Playstation").getEtatEnchere();
		assertEquals(ee,EtatEnchere.Annulée);
	}
	
	@Test
	public void VisibiliteEnchereParAcheteurApresOffrePuisAnnulation() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 5);
		listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		assertEquals (1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
	
	@Test
	public void VisibiliteEnchereParAcheteurApresAnnulation() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		assertEquals (0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
	
	
	
	@Test
	public void AcheteurAccederPrixDeReserve() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Malilou"));
		assertNull(prix);
	}
	
	@Test
	public void AcheteurAccederPrixDeReservePasAtteint() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 5);
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Malilou"));
		assertNull(prix);
	}
	
	@Test
	public void AcheteurAccederPrixDeReserveAtteint() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 100);
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Malilou"));
		assertNull(prix);
	}
	
	@Test
	public void AcheteurAccederPrixDeReserveAtteintParAutreAcheteur() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 new Utilisateur ("lol","ahh", "MARRANT");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 100);
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("lol"));
		assertNull(prix);
	}
	
	@Test
	public void VendeurAccederPrixDeReserve() {
		new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Sonii"));
		assertNotNull(prix);
	}
	

}
