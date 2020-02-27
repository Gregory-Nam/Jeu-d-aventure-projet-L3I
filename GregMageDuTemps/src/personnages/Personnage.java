package personnages;


import java.util.HashMap;

import elements.Interactif;
import elements.Item;
import enumerations.Deplacements;
import javafx.scene.image.*;
import java.io.File;

public abstract class Personnage extends Interactif {
	protected HashMap<Deplacements, ImageView> spritesPersonnageHM;
	protected ImageView spriteCourant;
	Item itemEnPossession;
	
	public Personnage () {
		spritesPersonnageHM = new HashMap<Deplacements,ImageView>();
	}
	
	public abstract void seDirigerADroite();
	
	public abstract void seDirigerAGauche();
	
	
	public void initPersonnage(File sprites[]) {
		Deplacements[] deplacements = Deplacements.values();
		for(int i = 0; i < sprites.length; ++i) {
			Image img = new Image(sprites[i].toURI().toString());
			ImageView iv = new ImageView();
			iv.setImage(img);
			spritesPersonnageHM.put(deplacements[i], iv);
		}
	}
	
	public ImageView getImageView() {
		return spriteCourant;
	}



	
	
}
