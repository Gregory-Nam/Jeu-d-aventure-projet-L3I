package elements;

import java.io.File;

import enumerations.Deplacements;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;

/**
 * Implémentation des portes murales du jeu. </br>
 * Cette classe hérite de PorteExtremite.
 * 
 * @author Grégory NAM.
 * @author Hugo CHALIK.
 * @author Luca BEVILACQUA.
 * @author Ahmadou Bamba MBAYE.
 * @see PorteExtremite
 * @see Interactif
 */
public class PorteMurale extends PorteExtremite {

	/**
	 * Constante du fichier de l'image de la porte ouverte.
	 */
	private static final File FICHIER_PORTE_OUVERTE = new File("Images/Elements/Porte_Ouverte.png");

	/**
	 * Constante du fichier de l'image de la porte fermee.
	 */
	private static final File FICHIER_PORTE_FERME = new File("Images/Elements/Porte_Ferme.png");

	/**
	 * L'ImageView de la porte.
	 */
	private ImageView spritePorte;

	/**
	 * Boolean qui représente l'état de la porte.
	 */
	private boolean estOuverte;

	/**
	 * Constructeur de PorteMurale.
	 * 
	 * @param salle1   la premiére salle avec laquelle la porte fait le lien.
	 * @param salle2   la deuxiéme salle avec laquelle la porte fait le lien.
	 * @param position la position de la porte.
	 */
	public PorteMurale(Salle salle1, Salle salle2, double position) {
		super(salle1, salle2);
		initPorte();

		super.xMin = position;
		super.xMax = xMin + spritePorte.getImage().getWidth();
		spritePorte.setX(position);

		this.estOuverte = false;
	}

	/**
	 * Permet d'initialiser l'ImageView de la porte.
	 */
	public void initPorte() {
		spritePorte = new ImageView();
		Image imgPorte = new Image(FICHIER_PORTE_FERME.toURI().toString());
		spritePorte.setImage(imgPorte);
	}

	/**
	 * L'interaction avec une porte permet de changer changer l'ImageView de la
	 * porte pour l'ouvrir et de changer la salle courante.
	 */
	@Override
	public void interagir() {
		if (!estOuverte) {
			spritePorte.setImage(new Image(FICHIER_PORTE_OUVERTE.toURI().toString()));
			estOuverte = true;
		}
		super.interagir();
		PersonnageJoueur.getInstanceUnique().changerSprite(Deplacements.BAS);
	}

	@Override
	public double getXMin() {
		return super.xMin;
	}

	public double getXMax() {
		return super.xMax;
	}

	@Override
	public ImageView getImageView() {
		return spritePorte;
	}

}
