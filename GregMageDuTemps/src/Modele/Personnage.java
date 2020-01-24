package Modele;


import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.*;
import javafx.scene.input.KeyCode;

import java.io.File;

public abstract class Personnage {
	//ensemble des sprites pour le personnage
	private ArrayList<ImageView> spritesPersonnage;
	protected HashMap<Deplacement, ImageView> spritesPersonnageHM;
	// la vue de l'image courante du personnage
	// faire une paire <imageview, Deplacement> ?
	protected ImageView spriteCourant;
	
	public Personnage(File ... sprites /* haut, droite, bas, gauche*/) {
		spritesPersonnage = new ArrayList<ImageView>();
		spritesPersonnageHM = new HashMap<Deplacement,ImageView>();
	}
	
	public abstract void seDirigerADroite();
	
	public abstract void seDirigerAGauche();
	
	
	public ImageView getSpriteCourant() {
		return spriteCourant;
	}


	public void setSprite(KeyCode code) {
		if(code == KeyCode.RIGHT){
			spriteCourant.setImage(spritesPersonnageHM.get(Deplacement.DROITE).getImage());
		}
		else if(code == KeyCode.LEFT) {
			spriteCourant.setImage(spritesPersonnageHM.get(Deplacement.GAUCHE).getImage());
		}
	}
	
	
}
