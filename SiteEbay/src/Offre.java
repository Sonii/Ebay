
public class Offre {
	
	 private Utilisateur user;
	 private Enchere enchere;
	 private long prixOffre = 0;
	 
	 public Offre(Utilisateur user, Enchere en, long prixO) 
	 {
		 this.user = user;
		 this.enchere = en;
		 this.prixOffre = prixO;
	 }
	  
}
