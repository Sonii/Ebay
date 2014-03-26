package metier.Enchere;

import java.util.Date;
import java.util.TimerTask;

import metier.Systeme.Alerte;
import metier.Systeme.HorlogeSingleton;

public class VerificationTache extends TimerTask{
	
	private Enchere enchere;
	private Date dateFin;
	private Date date ;
	private static HorlogeSingleton hs = HorlogeSingleton.getInstance();
	
	public VerificationTache(Enchere enchere, Date dateExpiration) {
		this.enchere=enchere;
		this.dateFin = dateExpiration;
	}

	@Override
	public void run() {
		date=hs.getTemps();
		if (date.after(dateFin)) { // a verifier si la methode marche bien ..
			this.cancel();
			enchere.setEtatEnchere(EtatEnchere.Terminée);
			Alerte.enchereTerminee(enchere);
			
		}
	}

	
}
