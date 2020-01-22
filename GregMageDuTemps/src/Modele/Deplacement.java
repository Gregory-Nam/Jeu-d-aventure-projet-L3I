package Modele;
import javafx.scene.input.KeyCode;
public enum Deplacement {
	
	HAUT(KeyCode.UP), DROITE(KeyCode.RIGHT), BAS(KeyCode.DOWN), GAUCHE(KeyCode.LEFT);
	
	final private KeyCode kc;
	final static private Deplacement[] valeurs = values();
	
	private Deplacement(KeyCode kc) {
		this.kc = kc;
	}
	
	final public KeyCode direction() {
		return kc;
	}
	
	final public Deplacement suivant() {
		int indice = this.ordinal();
		return (indice == valeurs.length - 1) ? valeurs[0] : valeurs[indice + 1];
	}
	
	final public Deplacement precedent() {
		int indice = this.ordinal();
		return (indice == 0) ? valeurs[valeurs.length - 1] : valeurs[indice - 1];
	}
}
