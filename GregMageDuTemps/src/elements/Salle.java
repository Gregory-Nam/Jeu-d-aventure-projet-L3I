package elements;

import java.io.File;
import java.util.ArrayList;

import application.Jeu;
import enumerations.NomSalle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;

public class Salle {
	
	private final NomSalle nomDeLaSalle;
	private ImageView spriteSalle;
	private ArrayList<Interactif> objetsDeLaSalle;
	
	/* CONSTRUCTEUR PAR RECOPIE */
	
	
	public Salle(File cheminImage, NomSalle nomDeLaSalle) {
		spriteSalle = new ImageView();
		objetsDeLaSalle = new ArrayList<Interactif>();
		this.nomDeLaSalle = nomDeLaSalle;
		initSalle(cheminImage);
	}
	
	public void initSalle(File cheminImage) {
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
			if(!objetsDeLaSalle.contains(i)) objetsDeLaSalle.add(i);
		}
		
	}
	
	public boolean supprimerInteractif(Interactif i) {
		return objetsDeLaSalle.remove(i);
	}
	
	// gerer le cas ou il n'y a pas d'objet interactif par une exception ???
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
	
	public ArrayList<Interactif> getInteractifs() {
		return new ArrayList<Interactif>(objetsDeLaSalle);
	}

	
}
