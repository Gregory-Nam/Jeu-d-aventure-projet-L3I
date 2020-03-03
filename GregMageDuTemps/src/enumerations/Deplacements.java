package enumerations;
import javafx.scene.input.KeyCode;
/**
 *
 * @author Ahmadou Bamba MBAYE.
 *Cette ennumeration contient les elements suivants :
 *<ul>
	 *<li>HAUT quand on presse le bouton vers le haut</li>
	 *<li>DROITE quand on presse le bouton vers la droite </li>
	 *<li>BAS quand on presse le bouton vers le bas</li>
	 *<li>GAUCHE quand on presse le bouton vers la gauche </li>
 *</ul>
 */
public enum Deplacements {
	
	HAUT(KeyCode.UP), DROITE(KeyCode.RIGHT), BAS(KeyCode.DOWN), GAUCHE(KeyCode.LEFT);
	/**
	 * La clee du code
	 */
	final private KeyCode kc;
	/**
	 * Le constructeur permet: 
	 * L'initialisation de la clee
	 * @param kc La clee du code
	 */
	private Deplacements(KeyCode kc) {
		this.kc = kc;
	}
	
}
