import java.util.*;

public class Main {

	public static void main(String[] args) {
		Vendeur a = new Vendeur("toto", "", "");
		Enchere en = a.creationEnchere(0, 0, "", "", "");
		System.out.println(en.etatEnchere);
		//System.out.println(en.utilisateurLogin);
	}

}
