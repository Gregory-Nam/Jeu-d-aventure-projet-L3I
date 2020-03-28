package enumerations;

import javafx.scene.input.KeyCode;

/**
 * Enumération des déplacements.
 * 
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE.
 *
 */
public enum Deplacements {

	HAUT(KeyCode.UP), DROITE(KeyCode.RIGHT), BAS(KeyCode.DOWN), GAUCHE(KeyCode.LEFT);

	/**
	 * La clee du code
	 */
	final private KeyCode kc;

	/**
	 * Le constructeur permet: L'initialisation de la clee
	 * 
	 * @param kc La clee du code
	 */
	private Deplacements(KeyCode kc) {
		this.kc = kc;
	}

}
