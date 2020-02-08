package Modele;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class PersonnageJoueur extends Personnage{
	
	private final double largeurPersonnage;
	public PersonnageJoueur(File ... sprites) {
		super(sprites);
		initPersonnage(sprites);
		largeurPersonnage = spriteCourant.getImage().getWidth();
		super.xMin = spriteCourant.getX();
		super.xMax = getXMin() + spriteCourant.getImage().getWidth();
	}
	
	@Override
	public void seDirigerAGauche()  {
		double nouvellePosition = spriteCourant.getX() - 10;
		spriteCourant.setX(nouvellePosition);
		super.xMin = nouvellePosition;
		super.xMax = nouvellePosition + largeurPersonnage;
	}
	
	@Override
	public void seDirigerADroite() {
		double nouvellePosition = spriteCourant.getX() + 10;
		spriteCourant.setX(nouvellePosition);
		super.xMin = nouvellePosition;
		super.xMax = nouvellePosition + largeurPersonnage;

	}
	
	public void initPersonnage(File sprites[]) {
		Deplacements[] deplacements = Deplacements.values();
		for(int i = 0; i < deplacements.length; ++i) {
			Image img = new Image(sprites[i].toURI().toString());
			ImageView iv = new ImageView();
			iv.setImage(img);
			spritesPersonnageHM.put(deplacements[i], iv);
		}
		spriteCourant = new ImageView(spritesPersonnageHM.get(Deplacements.DROITE).getImage());		
	}
	
	public void changerSprite(KeyCode code) {
		switch(code) {
			case RIGHT:
				spriteCourant.setImage(spritesPersonnageHM.get(Deplacements.DROITE).getImage());
				break;
			case LEFT:
				spriteCourant.setImage(spritesPersonnageHM.get(Deplacements.GAUCHE).getImage());
				break;
			case UP:
				//traitement pour les interactions a faire
				spriteCourant.setImage(spritesPersonnageHM.get(Deplacements.HAUT).getImage());
				break;
			case DOWN:
				//traitement pour les interactions a faire
				spriteCourant.setImage(spritesPersonnageHM.get(Deplacements.BAS).getImage());
				break;
		}		
		
	}
	
	public boolean rencontreMur(KeyCode code) {
		return (code == KeyCode.LEFT) ? (super.xMin <= 0) : (super.xMin > 1000 - (getXMax() - getXMin()));
	}
	
	public void replacerGauche() {
		super.xMin = 0;
		super.xMax = largeurPersonnage;
		spriteCourant.setX(super.xMin);
	}
	
	public void replacerDroite() {
		super.xMin = 1000 - largeurPersonnage;
		super.xMax = 1000;
		spriteCourant.setX(super.xMin);
	}
	@Override
 	public void interagir() {
		// TODO Auto-generated method stub
	}

	@Override
	public double getXMin() {
		return super.xMin;
	}

	@Override
	public double getXMax() {
		return super.xMax;
	}

	public double getXCentre() {
		return (super.xMin + super.xMax) / 2;
	}
}
