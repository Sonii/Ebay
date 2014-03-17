package Utilisateur;
import java.util.*;

import Enchere.Alerte;
import Enchere.Enchere;
import Enchere.EtatEnchere;
import Enchere.Offre;
import Enchere.OffreComparator;
import Systeme.ListeEnchereSingleton;
import Systeme.ListeUtilisateurConnecteSingleton;
import Systeme.ModeConnexion;


public class Utilisateur implements Acheteur, Vendeur{

	private String login = "";
	private String prenom = "";
	private String nom = "";
	private ModeConnexion modeConnexion;

	public Utilisateur(String login, String prenom, String nom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		//this.enchere = new ArrayList<Enchere>();
		//this.encheresVisibles = new ArrayList<Enchere>();
	}
	
	public Offre CreeOffre(Enchere en, float prixO)
	{
		if(en.getEtatEnchere() == EtatEnchere.Publiée)
		{
			if((!(en.equals(this.getLogin())) && (prixO >= en.getPrixMinimum())))
			{
				Offre offre = new Offre(this, prixO);
				System.out.println("Votre offre a bien été crée");
				if(this.VerfierAlerteUtilisateur(en) == 0)
				{
					en.getListeAlertes().add(new Alerte(this, true,true,true));
				}
				Alerte.AlerteVendeur(this, offre); // A chaque fois qu'une offre est émise le vendeur est prévenu
				en.getListeOffres().add(offre); // On ajoute l'offre au tableau
				Collections.sort(en.getListeOffres(), new OffreComparator()); // On tri le tableau selon l'ordre ascendant des pris d'offres
				Alerte.OffreSupérieure(offre, en); // On regarde si cette offre a le plus grand prix d'offre
				Alerte.prixReserveAtteint(offre, en); // On regarde si le prix de reserve à été atteint par cette offre
				return offre;
			}
			else if(prixO < en.getPrixMinimum())
			{
				System.out.println("Vous ne pouvez pas emettre une offre à un prix inférieur au prix de l'enchere en question");
				return null;
			}
			else if(en.getUtilisateur().equals(this))
			{
				System.out.println("Vous ne pouvez pas faire d'offres sur votre propre Enchere");
				return null;
			}
			else
			{
				System.out.println("Une erreur s'est produite votre offre n'as pas été crée");
				return null;
			}
		}
		else
		{
			System.out.println("Cette offre a été annulée par son vendeur ou terminée");
			return null;
		}
	}
	
	/* Configuration D'ALERTES */	
/* Un Achteur peut configurer n'importe que alerte qui n'est pas la sienne biensur même s'il n'a pas emis des offres sur l'enchere en question*/

	public void ConfigurationAlertePrixReserve(Enchere en, boolean prixRes)
	{
		int i = 0;
		if(!en.getUtilisateur().equals(this))
		{
			for(Alerte alerte : en.getListeAlertes())
			{
				if(alerte.getUser().equals(this))
				{
					alerte.setPrixReserveAtteint(prixRes);
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				en.getListeAlertes().add(new Alerte(this, prixRes, true, true));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
	
	public void ConfigurationAlerteOffreSup(Enchere en, boolean prixRes)
	{
		int i = 0;
		
		if(!en.getUtilisateur().equals(this))
		{
			for(Alerte alerte : en.getListeAlertes())
			{
				if(alerte.getUser().equals(this))
				{
					alerte.setOffreSuperieure(prixRes);
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				en.getListeAlertes().add(new Alerte(this, true, prixRes, true));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
	
	public void ConfigurationAlerteEnchereAnnulee(Enchere en, boolean prixRes)
	{
		int i = 0;
		if(!en.getUtilisateur().equals(this))
		{
			for(Alerte alerte : en.getListeAlertes())
			{
				if(alerte.getUser().equals(this))
				{
					alerte.setEnchereAnnulee(prixRes);
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				en.getListeAlertes().add(new Alerte(this, true, true, prixRes));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
		
	public int VerfierAlerteUtilisateur(Enchere en)
	{
		for(Alerte alerte : en.getListeAlertes())
		{
			if(alerte.getUser().equals(this))
				return 1;
		}
		return 0;
	}
	
/* Fin Configuration Alertes */
	
	
	protected String getNom()
	{
		return this.nom;
	}
	
	protected String getPrenom()
	{
		return this.prenom;
	}
	
	public String getLogin()
	{
		return this.login;
	}
	
	protected ModeConnexion getModeConnexion()
	{
		return this.modeConnexion;
	}
	
	protected void setModeConnexion(ModeConnexion mode)
	{
		this.modeConnexion = mode;
	}
	
	protected void seConnecte()
	{
		this.setModeConnexion(ModeConnexion.Connecté);
		ListeUtilisateurConnecteSingleton.getInstance().ajouteUtilisateur(this);
	}
	
	protected void seDeconnecte()
	{
		this.setModeConnexion(ModeConnexion.Deconnecté);
		ListeUtilisateurConnecteSingleton.getInstance().supprimeUtilisateur(this);
	}
	
	public Enchere CreeEnchere(String description, String identifiant, float prixMin, float prixReserve)
	{
			Enchere enchere = new Enchere(description, identifiant, prixMin, prixReserve, this);//TOTO passer un parametre de temps peut-etre ?
			ListeEnchereSingleton.getInstance().ajouteEnchere(enchere);
			return enchere;
	}
	
	public void PublieEnchere(Enchere en)
	{
		if((this.equals(en.getUtilisateur())) && (en != null))
		{
			en.setEtatEnchere(EtatEnchere.Publiée);
		}
		else
		{
			System.out.println("Vérifiez que l'enchere que vous voulez publiez existe");
		}
		
	}
	
	public void AnnuleEnchere(Enchere en)
	{
		if(this.equals(en.getUtilisateur()) && (en != null))
		{
			if(en.getListeOffres().get(en.getListeOffres().size() - 1).getPrixOffre() < en.getPrixReserve(en.getUtilisateur())) // On vérifie bien que le prix de reserve n'as pas été atteint
			{
				en.setEtatEnchere(EtatEnchere.Annulée);
				Alerte.EnchereAnnulee(en);
			}
			else
			{
				System.out.println("Vous ne pouvez pas annuler votre enchere, car son prix de reserve a été atteint");
			}
		}
		else
		{
			System.out.println("Vérifiez que l'enchere que vous voulez publiez existe bien");
		}
	}
	
	/*public ArrayList<Enchere> getListeEncheresVisibles()
	{
		return this.encheresVisibles;
	}*/
	
	
}
