package elements;

import application.Jeu;
import javafx.scene.image.ImageView;

/**
 * Impl�mentation des portes Extremite du jeu. 
 * </br>
 * Cette classe h�rite de Interactif.
 * @author Gr�gory NAM.
 * @author Hugo CHALIK.
 * @author Luca BEVILACQUA.
 * @author Ahmadou Bamba MBAYE.
 * @see Interactif
 */
public class PorteExtremite extends Interactif {

	/**
	 * Un tableau contenant les salles liees par la porte.
	 */
	protected Salle[] sallesLieesParLaPorte;
	
	/**
	 * Constructeur de PorteExtremite.
	 * @param salle1 La premi�re salle avec laquelle la porte fait le lien.
	 * @param salle2 La deuxi�me salle avec laquelle la porte fait le lien.
	 */
	public PorteExtremite(Salle salle1, Salle salle2) {
		super.xMin = Jeu.X_MAX_FENETRE;
		super.xMax = xMin;
		
		sallesLieesParLaPorte = new Salle [2];
		
		sallesLieesParLaPorte [0] = salle1;
		sallesLieesParLaPorte [1] = salle2;
	}
	
	/**
	 * Renvoie null, une porte extremit� n'a pas d'ImageView.
	 * @return null
	 */
	@Override
	public ImageView getImageView() {
		return null;
	}

	/**
	 * L'interaction avec une porte extr�mit� permet de changer la salle courante.
	 */
	@Override
	public void interagir() {
		if(estFaceGauche()) 
			Jeu.getInstanceUnique().setSalleCourante(sallesLieesParLaPorte[1]);
		else
			Jeu.getInstanceUnique().setSalleCourante(sallesLieesParLaPorte[0]);
	}

	/**
	 * Une porte est faceGauche lorsqu'elle est � droite de la salle. </br>
	 * Une porte est a droite de la salle lorsque la salle courante
	 * est �gale a la premi�re salle avec laquelle la porte fait le lien.
	 * @return vrai si la porte est a droite, faux si la porte est a gauche.
	 */
	private boolean estFaceGauche() {
		return (Jeu.getInstanceUnique().getSalleCourante() == sallesLieesParLaPorte[0]);
	}
	
	@Override
	public double getXMin() {
		return estFaceGauche() ? Jeu.X_MAX_FENETRE : Jeu.X_MIN_FENETRE;
	}
	
	@Override
	public double getXMax() {
		return getXMin();
	}
}
