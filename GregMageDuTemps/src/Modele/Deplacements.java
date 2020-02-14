package Modele;
import javafx.scene.input.KeyCode;
public enum Deplacements {
	
	HAUT(KeyCode.UP), DROITE(KeyCode.RIGHT), BAS(KeyCode.DOWN), GAUCHE(KeyCode.LEFT);
	
	final private KeyCode kc;
	
	private Deplacements(KeyCode kc) {
		this.kc = kc;
	}
	
}
