package Utilisateur;
import java.util.*;

import Enchere.AlerteAbonnement;
import Enchere.Enchere;
import Enchere.EtatEnchere;
import Enchere.Offre;
import Enchere.OffreComparator;
import Systeme.Alerte;
import Systeme.ListeEnchereSingleton;
import Systeme.ListeUtilisateurSingleton;


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
		ListeUtilisateurSingleton.getInstance().ajouteUtilisateur(this);
		
	}

	
	public Offre deposerOffre(Enchere en, float prixO)
	{
		if (en == null){
			System.out.println("Cette enchère n'existe pas");
			return null ;
		}
		
		if(en.getEtatEnchere() == EtatEnchere.Publiée)
		{
			if((!(en.getVendeur().equals(this)) && (prixO >= en.getPrixMinimum())))
			{
				Offre offre = new Offre(this, prixO);
				if(this.verfierAlerteUtilisateur(en) == 0)
				{
					en.getListeAlertes().add(new AlerteAbonnement(this, true,true,true));
				}
				Alerte.alerteVendeur(this, offre); // A chaque fois qu'une offre est émise le vendeur est prévenu
				en.getListeOffres().add(offre); // On ajoute l'offre au tableau
				Collections.sort(en.getListeOffres(), new OffreComparator()); // On tri le tableau selon l'ordre ascendant des pris d'offres
				Alerte.offreSuperieure(offre, en); // On regarde si cette offre a le plus grand prix d'offre
				Alerte.prixReserveAtteint(offre, en); // On regarde si le prix de reserve à été atteint par cette offre
				return offre;
			}
			else if(prixO < en.getPrixMinimum())
			{
				System.out.println("Vous ne pouvez pas emettre une offre à un prix inférieur au prix de l'enchere en question");
				return null;
			}
			else if(en.getVendeur().equals(this))
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
			System.out.println("Cette offre a été annulée par son vendeur ou terminée ou n'est pas ecnore publier");
			return null;
		}
	}
	
	/* Configuration D'ALERTES */	
/* Un Achteur peut configurer n'importe que alerte qui n'est pas la sienne biensur même s'il n'a pas emis des offres sur l'enchere en question*/


	private void configurerAlertePrixReserve(Enchere en, boolean prixRes)
	{
		int i = 0;
		if(!en.getVendeur().equals(this))
		{
			for(AlerteAbonnement alerte : en.getListeAlertes())
			{
				if(alerte.getAcheteur().equals(this))
				{
					alerte.setPrixReserveAtteint(prixRes);
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				en.getListeAlertes().add(new AlerteAbonnement(this, prixRes, true, true));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
	
	private void configurerAlerteOffreSup(Enchere en, boolean prixRes)
	{
		int i = 0;
		
		if(!en.getVendeur().equals(this))
		{
			for(AlerteAbonnement alerte : en.getListeAlertes())
			{
				if(alerte.getAcheteur().equals(this))
				{
					alerte.setOffreSuperieure(prixRes);
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				en.getListeAlertes().add(new AlerteAbonnement(this, true, prixRes, true));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
	
	private void configurerAlerteEnchereAnnulee(Enchere en, boolean annulation)
	{
		int i = 0;
		if(!en.getVendeur().equals(this))
		{
			for(AlerteAbonnement alerte : en.getListeAlertes())
			{
				if(alerte.getAcheteur().equals(this))
				{
					alerte.setEnchereAnnulee(annulation);
					i = 1;
					return;
				}
			}
			if(i == 0)
			{
				en.getListeAlertes().add(new AlerteAbonnement(this, true, true, annulation));
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas configurer des alertes sur votre propre enchere");
		}
	}
		
	private int verfierAlerteUtilisateur(Enchere en)
	{
		for(AlerteAbonnement alerte : en.getListeAlertes())
		{
			if(alerte.getAcheteur().equals(getLogin()))
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

	}
	
	protected void seDeconnecte()
	{
		this.setModeConnexion(ModeConnexion.Deconnecté);

	}

	
	public Enchere creerEnchere(String description, String identifiant, float prixMin, float prixReserve, int nbrHeure)
	{

			Enchere enchere = new Enchere(description, identifiant, prixMin, prixReserve, this, nbrHeure);
			ListeEnchereSingleton.getInstance().ajouteEnchere(enchere);
			return enchere;
	}

	
	public void publierEnchere(Enchere en)
	{
		if( (en != null) && (this.equals(en.getVendeur())))
		{
			en.setEtatEnchere(EtatEnchere.Publiée);
		}
		else
		{
			System.out.println("Vérifiez que l'enchere que vous voulez publiez existe");
		}
		
	}
	
	public void annulerEnchere(Enchere en)
	{
		if( (en != null) && this.equals(en.getVendeur()) )
		{	
			if( en.getListeOffres().isEmpty() || (en.getListeOffres().get(en.getListeOffres().size() - 1).getPrixOffre() < en.getPrixReserve(en.getVendeur()))) // On vérifie bien que le prix de reserve n'as pas été atteint
			{
				en.setEtatEnchere(EtatEnchere.Annulée);
				Alerte.enchereAnnulee(en);
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

	


	public void configurerAlertes(Enchere en, boolean prixRes, boolean annulation, boolean offreSup) {
		configurerAlertePrixReserve(en, prixRes);
		configurerAlerteOffreSup(en, offreSup);
		configurerAlerteEnchereAnnulee(en, annulation);
		
	}


}
