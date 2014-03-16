import java.util.*;


public class ListeEnchereSingleton {

	private static ListeEnchereSingleton uniqueInstance;
	private ArrayList<Enchere> listeEnchere;
	
	private ListeEnchereSingleton()
	{
		this.listeEnchere = new ArrayList<Enchere>();
	}
	
	public static synchronized ListeEnchereSingleton getInstance()
    {
            if(uniqueInstance == null)
            {
                    uniqueInstance = new ListeEnchereSingleton();
            }
            return uniqueInstance;
    }
	
	public void ajouteEnchere(Enchere en)
    {
           this.listeEnchere.add(en);
    }
	
	public ArrayList<Enchere> VoirelisteEnchere()
	{
		return this.listeEnchere;
	}
	
	public void supprimeEnchere(Enchere en)
	{
		this.listeEnchere.remove(en);
	}
	
}
