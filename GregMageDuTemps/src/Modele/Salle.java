package Modele;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Salle {
	private ImageView spriteSalle;
	private final NomSalle nomDeLaSalle;
	
	//constructeur par recopie
	public Salle(Salle s) {
		this.spriteSalle = s.spriteSalle;
		this.nomDeLaSalle = s.nomDeLaSalle;
	}
	public Salle(File cheminImage, NomSalle nomDeLaSalle) {
		this.nomDeLaSalle = nomDeLaSalle;
		initSalle(cheminImage);
	}
	
	private void initSalle(File cheminImage) {
		spriteSalle = new ImageView();
		Image imgSalle = new Image(cheminImage.toURI().toString());
		spriteSalle.setImage(imgSalle);
	}
	
	public ImageView getSprite() {
		return spriteSalle;
	}
	
	public NomSalle getNomSalle() {
		return nomDeLaSalle;
	}
}
