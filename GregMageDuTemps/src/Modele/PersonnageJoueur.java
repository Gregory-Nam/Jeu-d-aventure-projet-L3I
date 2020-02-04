package Modele;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class PersonnageJoueur extends Personnage{
	
	public PersonnageJoueur(File ... sprites) {
		super(sprites);
		initPersonnage(sprites);
		largeurPersonnage = spriteCourant.getImage().getWidth();
		xPosition = spriteCourant.getX();
	}
	
	@Override
	public void seDirigerAGauche()  {
		double nouvellePosition = spriteCourant.getX() - 10;
		spriteCourant.setX(nouvellePosition);
		super.xPosition = nouvellePosition;
	}
	
	@Override
	public void seDirigerADroite() {
		double nouvellePosition = spriteCourant.getX() + 10;
		spriteCourant.setX(nouvellePosition);
		super.xPosition = nouvellePosition;
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
		System.out.println(spriteCourant.getX());
		
		
	}
	
	public void changerSprite(KeyCode code) {
		switch(code) {
			case RIGHT:
				spriteCourant.setImage(spritesPersonnageHM.get(Deplacement.DROITE).getImage());
				break;
			case LEFT:
				spriteCourant.setImage(spritesPersonnageHM.get(Deplacement.GAUCHE).getImage());
				break;
			case UP:
				//traitement pour les interactions a faire
				spriteCourant.setImage(spritesPersonnageHM.get(Deplacement.HAUT).getImage());
				break;
			case DOWN:
				//traitement pour les interactions a faire
				spriteCourant.setImage(spritesPersonnageHM.get(Deplacement.BAS).getImage());
				break;
		}		
		
	}
	
	public boolean rencontreMur(KeyCode code) {
		return (code == KeyCode.LEFT) ? (xPosition <= 0) : (xPosition > 1000 - largeurPersonnage);
	}

	@Override
	public void interagir() {
		// TODO Auto-generated method stub
		
	}
}
