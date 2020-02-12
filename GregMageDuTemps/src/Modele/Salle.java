package Modele;

import java.io.File;
import java.util.ArrayList;

import application.Jeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilitaire.Position;

public class Salle {
	private static final double EXTREMITE_GAUCHE = 50;
	private static final double EXTREMITE_DROITE = 950;
	
	private ImageView spriteSalle;
	private final NomSalle nomDeLaSalle;
	private ArrayList<Interactif> objetsDeLaSalle;
	//constructeur par recopie
	public Salle(Salle s) {
		objetsDeLaSalle = s.objetsDeLaSalle;
		this.spriteSalle = s.spriteSalle;
		this.nomDeLaSalle = s.nomDeLaSalle;
	}
	public Salle(File cheminImage, NomSalle nomDeLaSalle) {
		objetsDeLaSalle = new ArrayList<Interactif>();
		this.nomDeLaSalle = nomDeLaSalle;
		initSalle(cheminImage);
	}
	
	private void initSalle(File cheminImage) {
		spriteSalle = new ImageView();
		Image imgSalle = new Image(cheminImage.toURI().toString());
		spriteSalle.setImage(imgSalle);
	}
	
	public ImageView getImageView() {
		return spriteSalle;
	}
	
	public NomSalle getNomSalle() {
		return nomDeLaSalle;
	}
	
	public void ajoutInteractif(Interactif ... interactifs) {
		for(Interactif i : interactifs) {
			if(objetsDeLaSalle.contains(i)) continue;
			objetsDeLaSalle.add(i);
		}
	}
	
	public void supprimerInteractif(Interactif i) {
		if(objetsDeLaSalle.contains(i)) objetsDeLaSalle.remove(i);
	}
	
	// gerer le cas ou il n'y a pas d'objet interactif par une exception ???
	public Interactif interactifAPosition(double x) {
		for(Interactif i : objetsDeLaSalle) {
			if(i.getXMin() <= x && x <= i.getXMax()) {
				return i;
			}
		}
		return null;
	}
	
	public ArrayList<Interactif> getInteractifs() {
		return objetsDeLaSalle;
	}

	public static double getExtremiteDroite() {
		return EXTREMITE_DROITE;
	}
	
	public static double getExtremiteGauche() {
		return EXTREMITE_GAUCHE;
	}
}
