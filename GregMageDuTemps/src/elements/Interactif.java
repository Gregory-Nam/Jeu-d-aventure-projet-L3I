package elements;

import javafx.scene.image.ImageView;
import javafx.util.Pair;

/**
 * Implémentation des Interactifs.
 * 
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE
 */
public abstract class Interactif {

	/**
	 * Position x minimal. <br>
	 * Soit le début de l'image de l'interactif sur la scéne.
	 */
	protected double xMin;

	/**
	 * Position x maximal. <br>
	 * Soit la fin de l'image de l'interactif sur la scéne.
	 */
	protected double xMax;

	/**
	 * ImageView de l'interactif.
	 */
	protected ImageView vueImageInteractif;

	/**
	 * Renvoie l'ImageView de cet interactif.
	 * 
	 * @return l'ImageView de cet ineractif.
	 */
	public abstract ImageView getImageView();

	/**
	 * Interaction avec un interactif.
	 */
	public abstract void interagir();

	/**
	 * Renvoie la coordonnée x minimale de l'ImageView sur la scene.
	 * 
	 * @return la coordonnée x minimale de l'ImageView sur la scene.
	 */
	public abstract double getXMin();

	/**
	 * Renvoie la coordonnée x maximale de l'ImageView sur la scene.
	 * 
	 * @return la coordonnée x maximale de l'ImageView sur la scene.
	 */
	public abstract double getXMax();

	/**
	 * Renvoie la coordonnée x centrale de l'ImageView sur la scene.
	 * 
	 * @return la coordonnée x minimale de l'ImageView sur la scene.
	 */
	public double getXCentre() {
		return (getXMin() + getXMax()) / 2;
	}
}
