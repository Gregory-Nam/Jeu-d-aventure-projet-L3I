package Modele;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilitaire.PairObjetPosition;

public class Salle {
	private ImageView spriteSalle;
	private final NomSalle nomDeLaSalle;
	private ArrayList<Interactif> objetsDeLaSalle;
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
	
	public ArrayList getPortesPositions() {
		ArrayList<PairObjetPosition> positionsDesPorte = new ArrayList<PairObjetPosition>();
		for(Interactif p : objetsDeLaSalle ) {
			if(!(p instanceof Porte)) continue;
			positionsDesPorte.add(new PairObjetPosition(p, ((Porte)p).getXMin(), ((Porte)p).getXMax()));
		}
		return positionsDesPorte;
	}
}
