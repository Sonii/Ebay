package Systeme;
import java.util.*;

import Utilisateur.Utilisateur;
import Enchere.Enchere;
import Enchere.EtatEnchere;
import Enchere.Offre;



public class ListeEnchereSingleton {

	private static ListeEnchereSingleton uniqueInstance;
	public ArrayList<Enchere> listeEnchere;

	
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
	
	public Enchere getEnchereByDesc (String Description) {
		for(Enchere enchere : this.listeEnchere)
		{
			if (enchere.getObjet().getDescription().equals(Description)){
				return enchere;
			}
		}
		return null;
	}
	
	public void deleteAllEnchere(){
		while ( listeEnchere.size() > 0){
			listeEnchere.remove(0);
		}
		return;
	}
	
	public ArrayList<Enchere> getlisteEnchereVisible(Utilisateur user)
	{
		ArrayList<Enchere> listeEnchereVisible = new ArrayList<Enchere>();
		for(Enchere enchere : this.listeEnchere)
		{
			if(enchere.getVendeur().equals(user))
			{
				listeEnchereVisible.add(enchere);
			}
			else if(enchere.getEtatEnchere() == EtatEnchere.Publiée)
			{
				listeEnchereVisible.add(enchere);
			}
			else 
			{
				for(Offre offre : enchere.getListeOffres())
				{
					if(offre.getAcheteur().equals(user))
					{
						listeEnchereVisible.add(enchere);
						break;
					}
				}
			}
		}
		return listeEnchereVisible;
	}
	
	public void supprimeEnchere(Enchere en)
	{
		this.listeEnchere.remove(en);
	}
	
}
