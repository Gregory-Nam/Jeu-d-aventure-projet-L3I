package elements;

import application.Jeu;
import javafx.scene.image.ImageView;
/**
 * @author Ahmadou Bamba MBAYE .
 * La classe PorteExtremite herite de la classe Interactif
 * ,elle represente les portes qui sont a l'extremite de la salle
 * avec un tableau qui contient les salles liees a la porte et un boolean
 * qui renvoie true si le PJ est face gauche
 */
public class PorteExtremite extends Interactif {
/**
	 *
	 * Un tableau contenant les salles liees par la porte
	 */
	protected Salle[] sallesLieesParLaPorte ;
	/**
	 *
	 * Un boolean qui retourne:
	 * <ul>
	 *     <li><strong>true</strong> s'il fait face a gauche</li>
	 *     <li><strong>false</strong> s'il ne fait pas face a gauche</li>
	 * </ul>
	 */
	protected boolean estFaceGauche;
	/**
	 * Le constructeur de la classe PorteExtremite cree deux salles nommees salle1 et salle2 et :
	 * <ul>
		 * <li>recupere le xMin de la classe mere(Interactif)</li>
		 * <li>recupere le xMax de la classe mere(Interactif) et le xMax a pour valeur la somme de xMin et 50</li>
		 * <li>le personnage fait face a gauche</li>
	 * </ul>
	 *
	 * @param salle1 La salle numero 1
	 * @param salle2 La salle numero 2
	 */
	public PorteExtremite(Salle salle1, Salle salle2) {
		super.xMin = Jeu.X_MAX_FENETRE;
		super.xMax = xMin;
		this.estFaceGauche = true;
		
		sallesLieesParLaPorte = new Salle [2];
		
		sallesLieesParLaPorte [0] = salle1;
		sallesLieesParLaPorte [1] = salle2;
	}
	/**
	 * Cette methode permet de recuperer l'image en vue.
	 * @return Elle retourne l'image qui est en vue.
	 */
	@Override
	public ImageView getImageView() {
		return null;
	}
/**
	 * La methode interagir() teste:
	 * <ul>
		 * <li>si le personnage est a face gauche: Dans ce cas, on met a jour la salle courante a la salle liee par la porte numero 1</li>
		 * 
		 * <li>sinon: Dans ce cas, on met a jour la salle courante a la salle liee par la porte numero 0</li>
	 * </ul>
	 */
	@Override
	public void interagir() {
		if(estFaceGauche()) 
			Jeu.getInstanceUnique().setSalleCourante(sallesLieesParLaPorte[1]);
		else
			Jeu.getInstanceUnique().setSalleCourante(sallesLieesParLaPorte[0]);
	}
/**
	 * Cette methode retourne vrai si il fait face a gauche
	 * @return La salle courante est egale a la salle liee par la porte d'indice 0
	 */
	private boolean estFaceGauche() {
		return (Jeu.getInstanceUnique().getSalleCourante() == sallesLieesParLaPorte[0]);
	}
	/**
	 * Cette methode recupere et renvoie:.
	 * <ul>
		 * <li>X_MAX_FENETRE s'il est en face gauche</li>
		 * <li>X_MIN_FENETRE sinon</li>
	 * </ul>
	 */
	@Override
	public double getXMin() {
		return estFaceGauche() ? Jeu.X_MAX_FENETRE : Jeu.X_MIN_FENETRE;
	}
/**
	 * Cette methode recupere la valeur maximale
	 * @return la somme de la valeur minimale et 50
	 */
	@Override
	public double getXMax() {
		return getXMin();
	}
	
	
	
	
	

	

}
