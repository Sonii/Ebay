package metier.Test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import metier.Enchere.EtatEnchere;
import metier.Enchere.Objet;
import metier.Systeme.ListeEnchereSingleton;
import metier.Systeme.ListeUtilisateurSingleton;
import metier.Utilisateur.Utilisateur;

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
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		Objet o = listEnchere.getEnchereByDesc("Playstation").getObjet();
		assertNotNull(o);
	}
	
	@Test
	public void ObjectPossedeDescription () {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertNotNull(listEnchere.getEnchereByDesc("Playstation").getObjet().getDescription());
	}
	
	@Test
	public void ObjectPossedeDateDeFin() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertNotNull(listEnchere.getEnchereByDesc("Playstation").getDateCreation());
	}
	
	@Test
	public void ObjectPossedePrixDeReserve() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		assertNotNull(listEnchere.getEnchereByDesc("Playstation").getPrixReserve(listUsers.getUtilisateur("Sonii")));
	}
	
	@Test
	public void EnchereDateLimiteAtteinte() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
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
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		assertEquals(0,listEnchere.getEnchereByDesc("Playstation").getListeOffres().size());
	
	}
	
	@Test
	public void EncherirApresPublication() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("Malilou").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 assertEquals(1,listEnchere.getEnchereByDesc("Playstation").getListeOffres().size());
	
	}

	@Test
	public void EnchereEtatCreeVuParLesAcheteurs() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 assertEquals(0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	
	}
	
	@Test
	public void EnchereEtatCreeParAutreVendeur() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Sonii")).size());
	
	}
	
	@Test
	public void EnchereEtatPublieeVendeur() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Sonii")).size());
	
	}
	
	@Test
	public void EnchereEtatPublieeAcheteur() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	
	}
	
	@Test
	public void EnchereEtatAnnuleVendeur() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Sonii")).size());
	
	}
	
	@Test
	public void EnchereEtatAnnuleAcheteurPasOffre() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(0,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("Malilou")).size());
	
	}
	@Test
	public void EnchereEtatAnnuleAcheteurOffre() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
		 listUsers.getUtilisateur("Sonii").creerEnchere("Playstation", "13ADDS64K", 1, 100, 1);
		 listUsers.getUtilisateur("Sonii").publierEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 listUsers.getUtilisateur("lol").deposerOffre(listEnchere.getEnchereByDesc("Playstation"), 10);
		 listUsers.getUtilisateur("Sonii").annulerEnchere(listEnchere.getEnchereByDesc("Playstation"));
		 assertEquals(1,listEnchere.getlisteEnchereVisible(listUsers.getUtilisateur("lol")).size());
	}
	
	@Test
	public void EnchereEtatTermineAcheteurOffre() {
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
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
		 Utilisateur.creerUtilisateur("Sonii", "Thomas", "Remond");
		 Utilisateur.creerUtilisateur("Malilou", "Hajar", "Malil");
		 Utilisateur.creerUtilisateur("lol", "Haaa", "Marrant");
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
