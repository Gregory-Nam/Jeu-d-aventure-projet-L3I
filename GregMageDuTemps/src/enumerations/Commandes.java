package enumerations;

import javafx.scene.input.KeyCode;

public enum Commandes {

	INTERAGIR(KeyCode.E);

	private final KeyCode kc;
	
	private Commandes (KeyCode kc) {
		this.kc = kc;
	}
}
