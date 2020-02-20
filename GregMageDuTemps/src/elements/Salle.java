package elements;

import java.io.File;
import java.util.ArrayList;

import application.Jeu;
import enumerations.NomSalle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Salle {
	
	private final NomSalle nomDeLaSalle;
	private ImageView spriteSalle;
	private ArrayList<Interactif> objetsDeLaSalle;
	private ArrayList<Item> itemsDeposeParPnj;
	
	/* CONSTRUCTEUR PAR RECOPIE */
	public Salle(Salle s) {
		objetsDeLaSalle = s.objetsDeLaSalle;
		this.spriteSalle = s.spriteSalle;
		this.nomDeLaSalle = s.nomDeLaSalle;
		this.itemsDeposeParPnj = s.itemsDeposeParPnj;
	}
	
	public Salle(File cheminImage, NomSalle nomDeLaSalle) {
		objetsDeLaSalle = new ArrayList<Interactif>();
		itemsDeposeParPnj = new ArrayList<Item>();
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
	
	public void ajoutItem(Item i) {
		itemsDeposeParPnj.add(i);
	}
	
	public ArrayList<Item> getItems() {
		return itemsDeposeParPnj;
	}
	
	public void supprimerInteractif(Interactif i) {
		if(objetsDeLaSalle.contains(i)) objetsDeLaSalle.remove(i);
	}
	
	public void supprimerItem(Item i) {
		if(itemsDeposeParPnj.contains(i)) itemsDeposeParPnj.remove(i);
	}
	
	// gerer le cas ou il n'y a pas d'objet interactif par une exception ???
	public Interactif interactifAPosition(double x) {
		for(Interactif i : objetsDeLaSalle) {
			System.out.println();
			if(i.getXMin() <= x && x <= i.getXMax()) {
				return i;
			}
		}
		return null;
	}
	
	public boolean aDesItems() {
		return itemsDeposeParPnj.size() == 0 ? false : true;
	}
	
	public ArrayList<Interactif> getInteractifs() {
		return new ArrayList<Interactif>(objetsDeLaSalle);
	}

	
}
