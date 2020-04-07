package elements;

import java.io.File;

import application.Jeu;
import enumerations.Materiaux;
import enumerations.Periode;

/**
 * Implémentation de l'horloge piége. </br>
 * Cette classe hérite de Horloge.
 * 
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE
 * @see Interactif
 */
public class HorlogePiege extends Horloge {

	/**
	 * Constructeur d'Horloge piége. </br>
	 * Le constructeur est le méme que celui d'horloge, sauf qu'il ne prend pas en
	 * parametre le nombre d'item é inserer l'horloge piége a tout ses items.
	 * 
	 * @param image                  image de l'horloge.
	 * @param materiaux              materiaux de l'horloge.
	 * @param periodeApresActivation periode dans laquelle l'horloge permet d'aller.
	 * @param position               position de l'horloge dans la salle.
	 */
	public HorlogePiege(File image, Materiaux materiaux, Periode periodeApresActivation, double position) {
		super(image, materiaux, 0, periodeApresActivation, position);
	}

	/**
	 * Entraine la fin du jeu.
	 */
	@Override
	public void interagir() {
		Jeu.getInstanceUnique().terminer("Il semble que cette horloge était un piège...", false);
	}

}
