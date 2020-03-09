package elements;

import java.io.File;

import application.Jeu;
import enumerations.Materiaux;
import enumerations.Periode;

/**
 * Impl�mentation de l'horloge pi�ge. </br>
 * Cette classe h�rite de Horloge.
 * @author Gr�gory NAM.
 * @author Hugo CHALIK.
 * @author Luca BEVILACQUA.
 * @author Ahmadou Bamba MBAYE.
 * @see    Interactif
 */
public class HorlogePiege extends Horloge{


	/**
	 * Constructeur d'Horloge pi�ge. </br>
	 * Le constructeur est le m�me que celui d'horloge,
	 * sauf qu'il ne prend pas en parametre le nombre d'item � inserer
	 * l'horloge pi�ge a tout ses items.
	 * @param image image de l'horloge.
	 * @param nbItemManquant nombre d'items � inserer pour activer l'horloge.
	 * @param periodeApresActivation periode dans laquelle l'horloge permet d'aller.
	 * @param position position de l'horloge dans la salle.
	 */
	public HorlogePiege(File image, Materiaux materiaux, Periode periodeApresActivation, double position) {
		super(image, materiaux, 0, periodeApresActivation, position);
	}

	/**
	 * Entraine la fin du jeu.
	 */
	@Override
	public void interagir() {
		Jeu.getInstanceUnique().terminer("Il semble que cette horloge �tait un pi�ge...", false );
	}

}
