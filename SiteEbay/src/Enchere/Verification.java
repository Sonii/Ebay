package Enchere;

import java.util.Date;
import java.util.TimerTask;

import Systeme.HorlogeSingleton;

public class Verification extends TimerTask{
	
	private Enchere enchere;
	private Date dateFin;
	
	public Verification(Enchere enchere, Date dateExpiration) {
		this.enchere=enchere;
		this.dateFin = dateExpiration;
	}

	@Override
	public void run() {
		Date date = HorlogeSingleton.getInstance().getTemps();
		if (this.dateFin.equals(date)) { // a verifier si la methode marche bien ..
			//alors changer le statuts de l'enchere etc..
			System.out.println("C'est LA FIN !!!!");
			this.cancel();
		}
		else {
			System.out.println("pas la fin");
		}
		
	}

	
}
