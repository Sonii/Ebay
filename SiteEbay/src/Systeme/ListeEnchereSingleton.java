package Systeme;
import java.util.*;

import Enchere.Enchere;
import Enchere.EtatEnchere;
import Enchere.Offre;
import Utilisateur.Utilisateur;


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
	
	public ArrayList<Enchere> getlisteEnchereVisible(Utilisateur user)
	{
		ArrayList<Enchere> listeEnchereVisible = new ArrayList<Enchere>();
		for(Enchere enchere : this.listeEnchere)
		{
			if(enchere.getUtilisateur().equals(user))
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
					if(offre.getUtilisateur().equals(user))
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
