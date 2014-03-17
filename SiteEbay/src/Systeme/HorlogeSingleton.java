package Systeme;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HorlogeSingleton {
	
		private static HorlogeSingleton uniqueInstance;
	
	  // Définition des attributs
		Calendar cal = Calendar.getInstance();
		Date dateInit = new Date();

		protected static final SimpleDateFormat dateHeureFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
	  int heures;
	  int minutes;
	  int jours;
	  int annee;
	 
	  // Définition des méthodes
	 
	  public void incrementeHeure()
	  {

	      minutes++;
	      if (minutes==60)
	      {
	        minutes=0;
	        heures++;
	        if (heures==24)
	        {
	          heures=0;
	          jours++;
	          if(jours == 365){
	        	  jours=0;
	        	  annee++;
	          	}
	          }
	        }
	      }
	 
	  public Date getTemps() 
	  {
			cal.set(Calendar.HOUR_OF_DAY,heures);
			cal.set(Calendar.MINUTE,minutes);
			cal.set(Calendar.DAY_OF_YEAR,jours);
			cal.set(Calendar.YEAR,annee);
	    return cal.getTime();
	  }
	 
	  
	  public static synchronized HorlogeSingleton getInstance()
	    {
	            if(uniqueInstance == null)
	            {
	                    uniqueInstance = new HorlogeSingleton();
	            }
	            return uniqueInstance;
	    }
	  
	  public HorlogeSingleton ()
	  {
			cal.setTime(dateInit);
			heures=cal.get(Calendar.HOUR_OF_DAY);
			minutes=cal.get(Calendar.MINUTE);
			jours=cal.get(Calendar.DAY_OF_YEAR);
			annee =cal.get(Calendar.YEAR);
			
		  //Dans le constructeur de Horloge : Créer un Timer qui execute incrementeHeure() (seconde += 60) toute les millisecondes
		  //1 seconde en temps reel => 60000 seconde = 1000 minute sur l'horloge soit environ 16heure et 40 minute
		  TimerTask task = new TimerTask() { 
				@Override
				public void run() 
				{
					incrementeHeure();
				}	
			};
			
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(task, 0, 10); // commence à 0
		}
	  
}
	
