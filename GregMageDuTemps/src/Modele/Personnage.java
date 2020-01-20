package Modele;


import java.util.ArrayList;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;

import java.io.File;

public abstract class Personnage {
	//ensemble des sprites pour le personnage
	private ArrayList<ImageView> spritesPersonnage;
	// la vue de l'image courante du personnage
	protected ImageView spriteCourant;
	
	public Personnage(File ... sprites /* haut, droite, bas, gauche*/) {
		spritesPersonnage = new ArrayList<ImageView>();
		for(File f : sprites) {
			Image img = new Image(f.toURI().toString());
			ImageView iv = new ImageView();
			iv.setImage(img);
			spritesPersonnage.add(iv);
		}
		spriteCourant = new ImageView(spritesPersonnage.get(3).getImage());
	}
	
	public void seDirigerADroite() {
		spriteCourant.setX(spriteCourant.getX() + 10);
	}
	
	public void seDirigerAGauche() {
		spriteCourant.setX(spriteCourant.getX() - 10);
	}
	
	public void seDirigerVersLeNord() {
		
	}
	
	public void seDirigerVersLeSud() {
		
	}
	
	public ImageView getSpriteCourant() {
		return spriteCourant;
	}

	public void setSprite(KeyCode code) {
		if(code == KeyCode.RIGHT){
			spriteCourant.setImage(spritesPersonnage.get(3).getImage());
		}
		else if(code == KeyCode.LEFT) {
			spriteCourant.setImage(spritesPersonnage.get(1).getImage());
		}
	}
	
	
}
