package elements;

import java.io.File;
import java.util.ArrayList;

import enumerations.NomSalle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;
/**
 * Implémentation des salles du Jeu. </br>
 * Une salle est représentée par une ImageView et un nom, elle contient des interactifs.
 * @author Grégory NAM.
 * @author Hugo CHALIK.
 * @author Luca BEVILACQUA.
 * @author Ahmadou Bamba MBAYE.
 * @see    Interactif
 */
public class Salle {
	
	/**
	 * Le nom de la salle.
	 */
	private final NomSalle nomDeLaSalle;
	
	/**
	 * ImageView de la salle.
	 */
	private ImageView spriteSalle;
	
	/**
	 * Une liste contenant l'ensemble des objets interactifs étant presents dans la salle.
	 */
	private ArrayList<Interactif> objetsDeLaSalle;
	
	/**
	 * Constructeur de la classe Salle.
	 * @param cheminImage Fichier de l'image.
	 * @param nomDeLaSalle Le nom de la Salle.
	 */
	public Salle(File cheminImage, NomSalle nomDeLaSalle) {
		spriteSalle = new ImageView();
		objetsDeLaSalle = new ArrayList<Interactif>();
		this.nomDeLaSalle = nomDeLaSalle;
		initSalle(cheminImage);
	}
	
	/**
	 * Permet d'appliquer l'image passée en paramètre
	 * en tant qu'image de la salle.
	 * @param cheminImage Fichier de l'image qu'on souhaite utiliser
	 */
	public void initSalle(File cheminImage) {
		Image imgSalle = new Image(cheminImage.toURI().toString());
		spriteSalle.setImage(imgSalle);
	}
	
	/**
	 * Renvoie l'ImageView de la salle.
	 * @return l'imageView de la salle
	 */
	public ImageView getImageView() {
		return spriteSalle;
	}
	
	/**
	 * Renvoie le nom de la salle.
	 * @return le nom de la salle.
	 */
	public NomSalle getNomSalle() {
		return nomDeLaSalle;
	}
	
	/**
	 * Permet d'ajouter des objets interactifs dans la salle
	 * à condition qu'ils ne soient déjà pas présents.
	 * @param interactifs que l'on veut ajouter.
	 */
	public void ajoutInteractif(Interactif ... interactifs) {
		for(Interactif i : interactifs) {
			if(!objetsDeLaSalle.contains(i)) objetsDeLaSalle.add(i);
		}
		
	}
	
	/**
	 * Permet de supprimer un objet interactif de la salle.
	 * @param interactif que l'on souhaite supprimer.
	 * @return vrai si l'objet a été supprimé, sinon faux.
	 */
	public boolean supprimerInteractif(Interactif i) {
		return objetsDeLaSalle.remove(i);
	}
	
	/**
	 * Permet de renvoyer le premier interactif présent entre deux coordonnées.
	 * @param x1 la premiere coordonnée.
	 * @param x2 la deuxieme coordonée.
	 * @return le premier interactif trouvé entre les deux positions en paramètre.
	 */
	public Interactif interactifAPosition(double x1, double x2) {
		for(Interactif i : objetsDeLaSalle) {
			if(i.equals(PersonnageJoueur.getInstanceUnique())) continue;
			if(i.getXCentre() <= x2 && i.getXCentre() >= x1 ) {
				return i;
			}
		}
		return PersonnageJoueur.getInstanceUnique();
	}
	
	/**
	 * Renvoie une ArrayList des objets interactifs de la salle.
	 * @return une ArrayList des objets interactifs de la salle.
	 */
	public ArrayList<Interactif> getInteractifs() {
		return new ArrayList<Interactif>(objetsDeLaSalle);
	}

	
}
