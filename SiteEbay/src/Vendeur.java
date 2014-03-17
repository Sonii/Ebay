
public interface Vendeur {
	
	public Enchere CreeEnchere(String description, String identifiant, float prixMin, float prixReserve);
	public void PublieEnchere(Enchere en);
	public void AnnuleEnchere(Enchere en);
	
}
