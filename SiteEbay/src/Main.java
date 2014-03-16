import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Alerte alerte = new Alerte();
		UsineUtilisateur usine = new UsineUtilisateur();
		Utilisateur toto = usine.CreeUtilisateur(TypeUtilisateur.Vendeur, "toto", "", "", alerte);
		System.out.println(alerte.utilisateur.get(0).login); 
		//Enchere en = toto.creeEnchere(110, 0, "", "", "");
		//System.out.println(toto.encheres.get(0).prixMinimum);
		Utilisateur hajar = usine.CreeUtilisateur(TypeUtilisateur.Achteur, "hajar", "", "", alerte);
		//Enchere e = hajar.creeEnchere(10, 0, "", "", "");
		//Offre o = hajar.creeOffre(e, 0);
		Enchere enn = toto.creeEnchere(0, 0, "", "", "");
		Offre off = toto.creeOffre(enn, 10);
		//System.out.println(toto.offres.get(0).getPrixOffre());
		Offre offf = hajar.creeOffre(enn, 100);
		System.out.println(hajar.offres.get(0).prixOffre);
		
		/*Vendeur a = new Vendeur("toto", "", "");
		Enchere en = a.creationEnchere(0, 0, "", "", "");
		System.out.println(en.etatEnchere);
		//System.out.println(en.utilisateurLogin);*/
	}

}
