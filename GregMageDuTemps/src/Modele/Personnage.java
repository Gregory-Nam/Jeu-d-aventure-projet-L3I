package Modele;


import java.util.ArrayList;
import javafx.scene.image.*;
import java.io.File;

public abstract class Personnage {
	//ensemble des sprites pour le personnage
	private ArrayList<ImageView> spritesPersonnage;
	// la vue de l'image courante du personnage
	protected ImageView spriteCourant;
	
	public Personnage(File ... sprites) {
		spritesPersonnage = new ArrayList<ImageView>();
		for(File f : sprites) {
			Image img = new Image(f.toURI().toString());
			ImageView iv = new ImageView();
			iv.setImage(img);
			spritesPersonnage.add(iv);
			spriteCourant = iv;
		}
	}
	
	public void seDirigerADroite() {
		
	}
	
	public void seDirigerAGauche() {
		
	}
	
	public void seDirigerVersLeNord() {
		
	}
	
	public void seDirigerVersLeSud() {
		
	}
	
	public ImageView getSpriteCourant() {
		return spriteCourant;
	}
	
	
}
