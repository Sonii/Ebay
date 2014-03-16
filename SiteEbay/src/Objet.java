
public class Objet {

	private String identifiant;
	private String description;
	
	public Objet(String identifiant, String description)
	{
		this.description = description;
		this.identifiant = identifiant;
	}
	
	protected String getIdentifiant()
	{
		return this.identifiant;
	}
	
	protected String getDescription()
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
