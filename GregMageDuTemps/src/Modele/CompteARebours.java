package Modele;

import java.util.Timer;
import java.util.TimerTask;

import javafx.stage.Stage;

public class CompteARebours {
	private Timer chrono;
	private TimerTask tache;
	private int secondes;
	private int minutes;
	
	public CompteARebours(int minutes, int secondes) {
		this.minutes = minutes;
		this.secondes = secondes;
		chrono = new Timer();
		tache = new TimerTask() {
			public void run() {
				
				if(CompteARebours.this.secondes == 0) {
					--CompteARebours.this.minutes;
					CompteARebours.this.secondes = 60;
				}
				--CompteARebours.this.secondes;
				System.out.println(CompteARebours.this.toString());
			}
		};
		
	}
	
	
	public void lancer() {
		chrono.scheduleAtFixedRate(tache, 1000, 1000);
	}
	
	public String toString() {
		return this.minutes + " " + this.secondes;
	}
	
	
}
