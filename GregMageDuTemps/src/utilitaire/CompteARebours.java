package utilitaire;

import java.util.Timer;
import java.util.TimerTask;

import application.Jeu;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author Ahmadou Bamba MBAYE.
 * Cette classe permet de faire le compte a rebours avec un chrono de type Timer, une tache, des secondes, des minutes
 * 
 *
 */
public class CompteARebours {
	/**
	 * L'attribut chrono de type Timer.
	 * 
	 */
	private Timer chrono;
	/**
	 * L'attribut tache de type TimerTask.
	 */
	private TimerTask tache;
	/**
	 * L'attribut secondesP de type IntegerProperty.
	 */
	private IntegerProperty secondesP;
	/**
	 * L'attribut minutesP de type IntegerProperty.
	 */
	private IntegerProperty minutesP;
	/**
	 * L'attribut minutesSP de type StringProperty.
	 */
	private StringProperty secondesSP;
	/**
	 * L'attribut minutesSP de type StringProperty.
	 */
	private StringProperty minutesSP;
	/**
	 * L'attribut tempsTotalSP de type StringProperty.
	 */
	private StringProperty tempsTotalSP;
	/**
	 * Le constructeur de la classe CompteARebours a deux parametres minites et secondes.
	 * Il initialise les integers et les string property.
	 * Il affecte les sencondes et les minutes a leurs valeurs.
	 * Il fait la liaison des STRINGPROPERY avec les MINUTESPROPERTY.
	 * Ce constructeur permet d'initialiser aussi le TIMER et le THREAD.
	 * @param minutes Les minutes de chrono
	 * @param secondes Les secondes du chrono
	 */
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
	/**
	 * Cette methode permet de recuperer le StringPropery.
	 * @return Elle retourne le temps total
	 */
	public StringProperty getTempsTotalEnStringProperty() {
		return tempsTotalSP;
	}
	/**
	 * Cette methode permet de lancer le chrono de facon automatique quand le jeu est lancé.
	 */
	public void lancer() {
		chrono.scheduleAtFixedRate(tache, 1000, 1000);
	}	
}
