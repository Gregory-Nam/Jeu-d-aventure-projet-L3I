package Modele;
import javafx.scene.input.KeyCode;
public enum Deplacement {
	
	HAUT(KeyCode.UP), BAS(KeyCode.DOWN), DROITE(KeyCode.RIGHT), GAUCHE(KeyCode.LEFT);
	
	final private KeyCode kc;
	
	private Deplacement(KeyCode kc) {
		this.kc = kc;
	}
	
	final public KeyCode direction() {
		return kc;
	}
}
