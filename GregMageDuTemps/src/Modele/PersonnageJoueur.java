package Modele;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PersonnageJoueur extends Personnage{
	
	public PersonnageJoueur(File ... sprites) {
		super(sprites);
		initPersonnage(sprites);
	}
	
	@Override
	public void seDirigerAGauche()  {
		spriteCourant.setX(spriteCourant.getX() - 10);
	}
	
	@Override
	public void seDirigerADroite() {
		spriteCourant.setX(spriteCourant.getX() + 10);
	}
	
	public void initPersonnage(File sprites[]) {
		Deplacement[] deplacements = Deplacement.values();
		for(int i = 0; i < deplacements.length; ++i) {
			Image img = new Image(sprites[i].toURI().toString());
			ImageView iv = new ImageView();
			iv.setImage(img);
			spritesPersonnageHM.put(deplacements[i], iv);
		}
		spriteCourant = new ImageView(spritesPersonnageHM.get(Deplacement.DROITE).getImage());
	}
	
	public void seDirigerVersLeNord() {
		
	}
	
	public void seDirigerVersLeSud() {
		
	}
	
}
