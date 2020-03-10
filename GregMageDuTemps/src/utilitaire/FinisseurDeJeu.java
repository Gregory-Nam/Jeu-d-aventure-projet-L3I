package utilitaire;

import application.Jeu;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FinisseurDeJeu {

	private FinisseurDeJeu() {};
	
	public static void finirJeu() {
		for(int i = 0; i < 10; ++i) {
			System.out.println("xd");
			//Jeu.getPrimaryStage().fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "d", "droite", KeyCode.RIGHT, false, false, false, false));
			KeyEvent.fireEvent(Jeu.getPrimaryStage().getScene(),new KeyEvent(KeyEvent.KEY_PRESSED, "d", "droite", KeyCode.RIGHT, false, false, false, false));
		}
		
	}
	
	
}
