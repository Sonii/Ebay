package Test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import Enchere.EtatEnchere;
import Enchere.Objet;
import Systeme.ListeEnchereSingleton;
import Systeme.ListeUtilisateurSingleton;
import Utilisateur.Utilisateur;

public class TestEnchere {

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
	public void EncherePossedeUnObjet () {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		Objet o = listEnchere.getEnchereByDesc("Playstation").getObjet();
		assertNotNull(o);
	}
	
	@Test
	public void ObjectPossedeDescription () {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertNotNull(listEnchere.getEnchereByDesc("Playstation").getObjet().getDescription());
	}
	
	@Test
	public void ObjectPossedeDateDeFin() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertNotNull(listEnchere.getEnchereByDesc("Playstation").getDateCreation());
	}
	
	@Test
	public void ObjectPossedePrixDeReserve() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertNotNull(listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Sonii")));
	}
	
	@Test
	public void EnchereDateLimiteAtteinte() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		
		 assertEquals(EtatEnchere.Crée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());

		 //System.out.println(dateExp);
		 //System.out.println(HorlogeSingleton.getInstance().getTemps());
		 try {  
	         Thread.sleep(1000);  //le temps (1ms) que le system detecte que la date a été atteinte
	      }  
	      catch (InterruptedException e) {  
	      }  
		// System.out.println(HorlogeSingleton.getInstance().getTemps());
		 assertEquals(EtatEnchere.Terminée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
		
	}
	
	
	@Test
	public void EncherirAvantPublication() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		assertEquals(0,listEnchere.getEnchereByDesc("Playstation").getListeOffres().size());
	
	}
	
	@Test
	public void EncherirApresPublication() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 assertEquals(1,listEnchere.getEnchereByDesc("Playstation").getListeOffres().size());
	
	}

	@Test
	public void EnchereEtatCreeVuParLesAcheteurs() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 assertEquals(0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	
	}
	
	@Test
	public void EnchereEtatCreeParAutreVendeur() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Sonii")).size());
	
	}
	
	@Test
	public void EnchereEtatPublieeVendeur() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Sonii")).size());
	
	}
	
	@Test
	public void EnchereEtatPublieeAcheteur() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	
	}
	
	@Test
	public void EnchereEtatAnnuleVendeur() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Sonii")).size());
	
	}
	
	@Test
	public void EnchereEtatAnnuleAcheteurPasOffre() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 new Utilisateur ("lol","ahh", "MARRANT");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	
	}
	@Test
	public void EnchereEtatAnnuleAcheteurOffre() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 new Utilisateur ("lol","ahh", "MARRANT");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("lol")).size());
	}
	
	@Test
	public void EnchereEtatTermineAcheteurOffre() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 new Utilisateur ("lol","ahh", "MARRANT");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 10);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 assertEquals(EtatEnchere.Publiée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
		// System.out.println(HorlogeSingleton.getInstance().getTemps());
		 try {  
	         Thread.sleep(1000);  //le temps (1ms) que le system detecte que la date a été atteinte
	      }  
	      catch (InterruptedException e) {  
	      }  
		 assertEquals(EtatEnchere.Terminée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
		assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("lol")).size());
	}
	@Test
	public void EnchereEtatTermineAcheteurPasOffre() {
		 new Utilisateur ("Sonii","Thomas", "REMOND");
		 new Utilisateur ("Malilou","Hajar", "MALIL");
		 new Utilisateur ("lol","ahh", "MARRANT");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 10);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 assertEquals(EtatEnchere.Publiée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
		// System.out.println(HorlogeSingleton.getInstance().getTemps());
		 try {  
	         Thread.sleep(1000);  //le temps (1ms) que le system detecte que la date a été atteinte
	      }  
	      catch (InterruptedException e) {  
	      }  
		 assertEquals(EtatEnchere.Terminée,listEnchere.getEnchereByDesc("Playstation").getEtatEnchere());
		assertEquals(0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	}
}
