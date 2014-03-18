package Enchere;

public class Objet {

	private String identifiant;
	private String description;
	
	public Objet(String description, String identifiant)
	{
		this.description = description;
		this.identifiant = identifiant;
	}
	
	public String getIdentifiant()
	{
		return this.identifiant;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	protected void setDescription(String description)
	{
		this.description = description;
	}
	
	protected void setIdentifiant(String identifiant)
	{
		this.identifiant = identifiant;
	}
	
}
