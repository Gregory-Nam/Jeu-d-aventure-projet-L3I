package elements;

import java.io.File;
import java.util.ArrayList;

import application.Jeu;
import enumerations.NomSalle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;
/**
 * @author Ahmadou Bamba MBAYE.
 * La classe Salle gere toutes les salles(Salle de Bronze,Salle d�argent,Salle d�or) du jeu contenant toutes des itemes.
 * Cette classe definit les salles avec
 * un nom, un sprite d'image, une liste contenant l'ensemble des objets
 * de la salle et une liste contenant les items deposes par le personnage non joueur
 *Cette classe contient deux constructeurs un d'entre eux est un constructeur par recopie
 */
public class Salle {
	/**
	 * Le nom de la salle
	 */
	private final NomSalle nomDeLaSalle;
	/**
	 * Le sprite de la salle
	 */
	private ImageView spriteSalle;
	/**
	 *
	 * Une liste contenant l'ensemble des objets interactifs etant presents dans la salle
	 */
	private ArrayList<Interactif> objetsDeLaSalle;
	
	/* CONSTRUCTEUR PAR RECOPIE */
	/**
	 * Ce constructeur de la classe Salle permet de:.
	 * <ul>
		 * <li>creer des objets interactif etant presents dans la salle</li>
		 * <li>initialiser l'item depose par le PNG</li>
		 * <li>initialiser une salle et son nom</li>
		 * <li>initialise la salle par la methode initSalle()</li>
	 * </ul>
	 * @param cheminImage
	 * Chemin menat vers l'image
	 * @param nomDeLaSalle
	 * Le nom de la salle
	 */
	
	
	public Salle(File cheminImage, NomSalle nomDeLaSalle) {
		spriteSalle = new ImageView();
		objetsDeLaSalle = new ArrayList<Interactif>();
		this.nomDeLaSalle = nomDeLaSalle;
		initSalle(cheminImage);
	}
	/**
	 * La methode initSalle() permet de:.
	 * <ul>
		 * <li>initialiser le sprite de la salle</li>
		 * <li>creer une nouvelle image</li>
		 * <li>mettre a jour l'image de la salle</li>
	 * </ul>
	 * @param cheminImage
	 * chemin menant vers l'image
	 */
	public void initSalle(File cheminImage) {
		Image imgSalle = new Image(cheminImage.toURI().toString());
		spriteSalle.setImage(imgSalle);
	}
	/**
	 * La methode getImageView() retourne le sprite de la salle
	 * @return
	 * Elle retourne le sprite de la salle
	 */
	public ImageView getImageView() {
		return spriteSalle;
	}
	/**
	 * La methode getNomSalle() retourne le nom de la salle
	 * @return
	 * Elle retourne le nom de la salle
	 */
	public NomSalle getNomSalle() {
		return nomDeLaSalle;
	}
	/**
	 * La methode ajoutInteractif() permet d'ajouter des objets inetrectis dans la salle
	 * @param interactifs
	 * Un objet interactif quelconque
	 */
	public void ajoutInteractif(Interactif ... interactifs) {
		for(Interactif i : interactifs) {
			if(!objetsDeLaSalle.contains(i)) objetsDeLaSalle.add(i);
		}
		
	}
	
	public boolean supprimerInteractif(Interactif i) {
		return objetsDeLaSalle.remove(i);
	}
	
	// gerer le cas ou il n'y a pas d'objet interactif par une exception ???
	/**
	 * Cette methode retourne un objet interactif si:
	 * <ul>
	 * <li>la valeur XMin est inferieure ou egale a la valeur de l'objet interactif courante et
	 * la valeur XMax est superieure ou egale a la valeur de l'objet interactif courante</li>
	 * <li></li>
	 * </ul>
	 * Elle retourne NULL si on entre pas dans la boucle.
	 * @param x
	 * Objet interactif quelconque
	 * @return Elle retourne un objet interactif
	 */
	public Interactif interactifAPosition(double x1, double x2) {
		for(Interactif i : objetsDeLaSalle) {
			if(i.equals(PersonnageJoueur.getInstanceUnique())) continue;
			System.out.println(i + " centre : " + i.getXMax());
			System.out.println(x1+ " " + x2);
			if(i.getXCentre() <= x2 && i.getXCentre() >= x1 ) {
				return i;
			}
		}
		return PersonnageJoueur.getInstanceUnique();
	}
	/**
	 * La methode getInteractifs() recupere et retourne les objets interactifs.
	 * @return
	 * Elle retourne une nouvelle liste des objets interactifs de la salle.
	 */
	public ArrayList<Interactif> getInteractifs() {
		return new ArrayList<Interactif>(objetsDeLaSalle);
	}

	
}
