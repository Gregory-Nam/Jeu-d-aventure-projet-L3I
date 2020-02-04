package Modele;


import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.*;
import javafx.scene.input.KeyCode;

import java.io.File;

public abstract class Personnage extends Interactif {
	//ensemble des sprites pour le personnage
	protected HashMap<Deplacement, ImageView> spritesPersonnageHM;
	// la vue de l'image courante du personnage
	// faire une paire <imageview, Deplacement> ?
	protected ImageView spriteCourant;
	protected double xPosition;
	protected double largeurPersonnage;
	
	public Personnage(File ... sprites /* haut, droite, bas, gauche*/) {
		spritesPersonnageHM = new HashMap<Deplacement,ImageView>();
	}
	
	public abstract void seDirigerADroite();
	
	public abstract void seDirigerAGauche();
	
	
	public ImageView getSpriteCourant() {
		return spriteCourant;
	}



	
	
}
