package elements;

import javafx.scene.image.ImageView;
import javafx.util.Pair;
/**
 *
 * @author Ahmadou Bamba MBAYE.
 * La classe abstraite Interactif represente les objets interectifs leur largeur minimale, leur largeur maximale.Elle contient les
 * methodes abstraites utilisees par les classes horloge, PorteExtremite
 *
 *
 */
public abstract class Interactif {
/**
	 * Largeur minimale
	 */
	protected double xMin;
	/**
	 * Largeur maximale
	 */
	protected double xMax;
	
	protected ImageView vueImageInteractif;
	/**
	 * Cette methode abstraite permet de recuperer l'image en vue.
	 * @return Elle retourne l'image qui est en vue.
	 */
	public abstract ImageView getImageView();
	/**
	 * Cette methode abstraite permet d'interagir avec chaque horloge dans un ordre bien pr�cis
	 * afin de r�tablir le temps.
	 */
	public abstract void interagir();
	/**
	 * Cette methode abstraite permet de recup�rer la coordonn�e MINIMALE.
	 * @return Elle retourne la coordonn�e MINIMALE
	 */
	public abstract double getXMin();
	/**
	 * Cette m�thode abstraite permet de r�cup�rer la coordonn�e MAXIMALE.
	 * @return Elle retourne la coordonn�e MAXIMALE
	 */
	public abstract double getXMax();
	/**
	 * Cette m�thode  permet de r�cup�rer les coordonn�e centrale.
	 * @return Elle retourne la coordonn�e du centre
	 */
	public double getXCentre() {
		return (getXMin() + getXMax()) / 2;
	}
}
