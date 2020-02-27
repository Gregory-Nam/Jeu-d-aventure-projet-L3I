package utilitaire;

import java.util.Timer;
import java.util.TimerTask;

import application.Jeu;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompteARebours {
	private Timer chrono;
	private TimerTask tache;
	private IntegerProperty secondesP;
	private IntegerProperty minutesP;
	
	private StringProperty secondesSP;
	private StringProperty minutesSP;
	
	private StringProperty tempsTotalSP;
	
	public CompteARebours(int minutes, int secondes) {
		/* INITIALISATION DES INTEGER ET STRING PROPERTY */
		secondesP = new SimpleIntegerProperty();
		minutesP = new SimpleIntegerProperty();
		secondesSP = new SimpleStringProperty();
		minutesSP = new SimpleStringProperty();
		tempsTotalSP = new SimpleStringProperty();
		
		/* AFFECTATION DE LEURS VALEURS */
		secondesP.set(secondes);
		minutesP.set(minutes);
		
		/* LIAISON DES STRINGPROPERY AVEC LES MINUTESPROPERTY */
		/* QUAND MINUTESP CHANGE, SECONDESP CHANGE, ETC.. */
		secondesSP.bind(minutesP.asString());
		minutesSP.bind(minutesP.asString());
		tempsTotalSP.bind(new SimpleStringProperty("Temps restant : ").concat(minutesSP.concat(" minutes ").concat(secondesP).concat(" secondes")));
		
		/* INITIALISATION DU TIMER ET DU THREAD */
		chrono = new Timer();
		tache = new TimerTask() {
			public void run() {
				
				/* CHANGEMENT DE LA MINUTE SI SECONDES = 0 */
				/* PLATFORM RUN LATER EST NECESSAIRE POUR METTRE A JOUR UN ELEMENT DU GUI DANS UN THREAD AUTRE */
				if(CompteARebours.this.secondesP.get() == 0) {
					if(CompteARebours.this.minutesP.get() == 0){
						this.cancel();
						Platform.runLater(() -> Jeu.getInstanceUnique().terminer("Temps fini",false));
						return;
					}
					Platform.runLater(() -> CompteARebours.this.minutesP.set(CompteARebours.this.minutesP.get() - 1));
					Platform.runLater(() -> CompteARebours.this.secondesP.set(60));
				}
				Platform.runLater(() ->CompteARebours.this.secondesP.set(CompteARebours.this.secondesP.get() - 1));
				
			}
		};
		
	}
	
	public StringProperty getTempsTotalEnStringProperty() {
		return tempsTotalSP;
	}
	
	public void lancer() {
		chrono.scheduleAtFixedRate(tache, 1000, 1000);
	}
	
	
	
}
