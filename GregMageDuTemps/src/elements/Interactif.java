package elements;

import javafx.scene.image.ImageView;
import javafx.util.Pair;

/**
 * Impl�mentation des Interactifs.
 * @author Gr�gory NAM.
 * @author Hugo CHALIK.
 * @author Luca BEVILACQUA.
 * @author Ahmadou Bamba MBAYE.
 */
public abstract class Interactif {

	/**
	 * Position x minimal. </br>
	 * Soit le d�but de l'image de l'interactif sur la sc�ne.
	 */
	protected double xMin;

	/**
	 * Position x maximal. </br>
	 * Soit la fin de l'image de l'interactif sur la sc�ne.
	 */
	protected double xMax;
	
	/**
	 * ImageView de l'interactif.
	 */
	protected ImageView vueImageInteractif;
	
	/**
	 * Renvoie l'ImageView de cet interactif.
	 * @return l'ImageView de cet ineractif.
	 */
	public abstract ImageView getImageView();
	
	/**
	 * Interaction avec un interactif.
	 */
	public abstract void interagir();
	
	/**
	 * Renvoie la coordonn�e x minimale de l'ImageView sur la scene.
	 * @return la coordonn�e x minimale de l'ImageView sur la scene.
	 */
	public abstract double getXMin();
	
	/**
	 * Renvoie la coordonn�e x maximale de l'ImageView sur la scene.
	 * @return la coordonn�e x maximale de l'ImageView sur la scene.
	 */
	public abstract double getXMax();
	
	/**
	 * Renvoie la coordonn�e x centrale de l'ImageView sur la scene.
	 * @return la coordonn�e x minimale de l'ImageView sur la scene.
	 */
	public double getXCentre() {
		return (getXMin() + getXMax()) / 2;
	}
}
