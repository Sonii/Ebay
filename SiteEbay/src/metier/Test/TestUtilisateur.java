package metier.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import metier.Enchere.Alerte;
import metier.Enchere.EtatEnchere;
import metier.Enchere.Offre;
import metier.Systeme.ListeEnchereSingleton;
import metier.Systeme.ListeUtilisateurSingleton;
import metier.Utilisateur.Utilisateur;

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
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		assertSame("Sonii",listUsers.getUtilisateur("Sonii").getLogin());
	}
	
	//verifier que VoirlisteUtiliserConnecter marche et creerUtilisateur

	@Test
	public void CreationUtilisateurLoginDejaUtilisé() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Sonii", "Hajar", "Malil");
		assertEquals(1,listUsers.getListeUtilisateur().size());
	}
	
	@Test
	public void VoirUtilisateurConnecté() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		assertEquals(2,listUsers.getListeUtilisateurConnecte().size());
	}
	
	@Test
	public void VoirUtilisateurApresDeconnection() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").seDeconnecte();
		 assertEquals(1,listUsers.getUtilisateur("Malilou").voirListeUtilisateurConnecte().size());
		 listUsers.getUtilisateur("Sonii").seConnecte();
		 assertEquals(2,listUsers.getUtilisateur("Malilou").voirListeUtilisateurConnecte().size());

	}
	
	@Test
	public void EnchereNonVisibleParAcheteur() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertEquals(0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
	
	
	@Test
	public void EnchereEtVisibleParLeVendeur() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Sonii")).size());
	}
	
	@Test
	public void PublierEnchereEtVisableParAcheteur() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
	
	@Test
	public void AcheteurFaitUneOffre() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Offre o = listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		assertNotNull(o);
	}
	
	@Test
	public void AcheteurFaitUneOffreSurEnchereNull() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Offre o = listUsers.getUtilisateur("Malilou").deposerOffre(null, 10);
		assertNull(o);
	}
	
	@Test
	public void AcheteurFaitUneOffrePrixInferieur() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Offre o = listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 0.99);
		assertNull(o);
	}
	
	@Test
	public void VendeurFaitUneOffre() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Offre o = listUsers.getUtilisateur("Sonii").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 10);
		assertNull(o);
	}
	
	@Test
	public void VendeurConfigurerAlerte() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Sonii").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		Alerte aa = listEnchere.getEnchereByDesc("Playstation").getAlerte(listUsers.getUtilisateur("Sonii"));
		assertNull(aa);
	}
	
	@Test
	public void AcheteurConfigurerAlerte() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		Alerte aa = listEnchere.getEnchereByDesc("Playstation").getAlerte(listUsers.getUtilisateur("Malilou"));
		assertNotNull(aa);
	}
	
	@Test
	public void VendeurAnnuleEnchere() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		EtatEnchere ee = listEnchere.getEnchereByDesc("Playstation").getEtatEnchere();
		assertEquals(ee,EtatEnchere.Annulée);
	}
	
	@Test
	public void VendeurAnnuleEncherePrixReserveAtteint() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 100);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		EtatEnchere ee = listEnchere.getEnchereByDesc("Playstation").getEtatEnchere();
		assertEquals(ee,EtatEnchere.Publiée);
	}
	
	@Test
	public void VendeurAnnuleEncherePrixReservePasAtteint() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 99);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		EtatEnchere ee = listEnchere.getEnchereByDesc("Playstation").getEtatEnchere();
		assertEquals(ee,EtatEnchere.Annulée);
	}
	
	@Test
	public void VisibiliteEnchereParAcheteurApresOffrePuisAnnulation() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 5);
		listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		assertEquals (1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
	
	@Test
	public void VisibiliteEnchereParAcheteurApresAnnulation() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").configurerAlertes(listEnchere.getEnchereByDesc("Playstation"), true, true, true);
		listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		assertEquals (0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
	
	
	
	@Test
	public void AcheteurAccederPrixDeReserve() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Malilou"));
		assertNull(prix);
	}
	
	@Test
	public void AcheteurAccederPrixDeReservePasAtteint() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 5);
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Malilou"));
		assertNull(prix);
	}
	
	@Test
	public void AcheteurAccederPrixDeReserveAtteint() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 100);
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Malilou"));
		assertNull(prix);
	}
	
	@Test
	public void AcheteurAccederPrixDeReserveAtteintParAutreAcheteur() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), (float) 100);
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("lol"));
		assertNull(prix);
	}
	
	@Test
	public void VendeurAccederPrixDeReserve() {
		Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		Float prix = listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Sonii"));
		assertNotNull(prix);
	}
	

}
