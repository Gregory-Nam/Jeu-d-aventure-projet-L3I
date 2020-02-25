package personnages;


import java.util.HashMap;

import elements.Interactif;
import elements.Item;
import enumerations.Deplacements;
import javafx.scene.image.*;
import java.io.File;

public abstract class Personnage extends Interactif {
	//ensemble des sprites pour le personnage
	protected HashMap<Deplacements, ImageView> spritesPersonnageHM;
	// la vue de l'image courante du personnage
	// faire une paire <imageview, Deplacement> ?
	protected ImageView spriteCourant;
	Item itemEnPossession;
	
	public Personnage( File ... sprites /* haut, droite, bas, gauche*/) {
		spritesPersonnageHM = new HashMap<Deplacements,ImageView>();
	}
	
	public abstract void seDirigerADroite();
	
	public abstract void seDirigerAGauche();
	
	public void initPersonnage(File sprites[]) {
		Deplacements[] deplacements = Deplacements.values();
		for(int i = 0; i < sprites.length; ++i) {
			Image img = new Image(sprites[i].toURI().toString());
			System.out.println(sprites[i].toURI().toString());
			ImageView iv = new ImageView();
			iv.setImage(img);
			spritesPersonnageHM.put(deplacements[i], iv);
		}
			
	}
	
	public ImageView getImageView() {
		return spriteCourant;
	}



	
	
}
