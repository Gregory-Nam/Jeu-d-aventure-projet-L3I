package Modele;
import javafx.scene.input.KeyCode;
public enum Deplacement {
	
	HAUT(KeyCode.UP), DROITE(KeyCode.RIGHT), BAS(KeyCode.DOWN), GAUCHE(KeyCode.LEFT);
	
	final private KeyCode kc;
	
	private Deplacement(KeyCode kc) {
		this.kc = kc;
	}
	
	
}
