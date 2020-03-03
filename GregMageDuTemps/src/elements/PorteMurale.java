package elements;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import application.Jeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Ahmadou Bamba MBAYE .
 * La classe PorteMurale herite de la classe PorteExtremite
 * elle represente les porte murale la porte murale est definie par
 * un fichier ouvert, un fichier ferme, une image de vue et un boolean
 * renvoyant true si la porte est ouverte.
 * Cette utilide une methode qui initPorte() et une methode interagir()
 * qui gere l'interagissement des objets.
 */
public class PorteMurale extends PorteExtremite{
/**
	 * Cet attribut prive, static et final crée un nouveau File qui correspond a l'ouverture des portes
	 */
	private static final File FICHIER_PORTE_OUVERTE = new File("Images/Elements/Porte_Ouverte.png");
	/**
	 *
	 * Cet attribut prive, static et final crée un nouveau File qui correspond a la fermeture des portes
	 */
	private static final File FICHIER_PORTE_FERME = new File("Images/Elements/Porte_Ferme.png");
/**
	 *
	 * Le sprite de la porte
	 */
	private ImageView spritePorte;
	/**
 *
 * Un boolean qui retourne:
 * <ul>
	 *     <li><strong>false</strong> si la porte est ouverte</li>
	 *     <li><strong>true</strong> si la porte est fermee</li>
 * </ul>
 */
	private boolean estOuverte;
	/**
	 * Le constructeur de la classe PorteMurale:.
	 * initialise la porte a l'aide de la methode initPorte().
	 * affecte le xMin a la valeur de position.
	 * affecte le xMax la somme entre la valeur minimale et la largeur de l'image.
	 * met a jour le sprite de la porte a la position
	 * @param salle1
	 * La salle numero 1
	 * @param salle2
	 * La salle numero 2
	 * @param position
	 * La position quelconque de la porte
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
	 * La methode initPorte() cree une nouvelle image du sprite de la porte et met a jour l'image de la porte
	 */
	public void initPorte() {
		spritePorte = new ImageView();
		Image imgPorte = new Image(FICHIER_PORTE_FERME.toURI().toString());
		spritePorte.setImage(imgPorte);
	}
	/**
	 * La methode interagir() teste:
	 * <ul>
	 *     <li>si la porte est fermee: Dans ce cas on met a jour l'image en ouvrant le fichier FICHIER_PORTE_OUVERTE..</li>
	 *     
	 *     <li>si la porte n'est pas fermee: Dans ce cas, on fait appel a la methode interagir() de la classe mere PorteExtremite</li>
	 *     
	 * </ul>
	 */
	@Override
	public void interagir() {
		if(!estOuverte) {
			spritePorte.setImage(new Image(FICHIER_PORTE_OUVERTE.toURI().toString()));
			estOuverte = true;
		}
		super.interagir();
	}
	/**
	 * La methode getXMin() recupere la valeur xMin de la classe mere PorteExtremite
	 * @return La valeur de xMin
	 */
	@Override
	public double getXMin() {
		return super.xMin;
	}
	/**
	 * La methode getXMax() recupere la valeur xMax de la classe mere PorteExtremite
	 * @return La valeur de xMax
	 */
	@Override
	public double getXMax() {
		return super.xMax;
	}
/**
	 * La méthode getImageView() récupère l'image en vue
	 * @return Elle retourne le sprint courant
	 */
	@Override
	public ImageView getImageView() {
		return spritePorte;
	}

}
