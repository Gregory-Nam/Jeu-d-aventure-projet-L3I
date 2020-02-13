package Modele;

import javafx.scene.image.ImageView;
import java.io.File;

import application.Jeu;
public class PersonnageNonJoueur extends Personnage {
	
	public PersonnageNonJoueur(double x, File ... sprites) {
		super(sprites);
		initPersonnage(sprites);
		spriteCourant.setX(x);
	}

	@Override
	public void initPersonnage(File[] sprites) {
		super.initPersonnage(sprites);
		spriteCourant = new ImageView(spritesPersonnageHM.get(Deplacements.HAUT).getImage());	
	}
	
	@Override
	public void seDirigerADroite() {
		spriteCourant.setX(spriteCourant.getX() - 20);
		
	}

	@Override
	public void seDirigerAGauche() {
		spriteCourant.setX(spriteCourant.getX() + 20);
	}

	@Override
	public void interagir() {
		Jeu.lancerEnigme(this);
	}

	@Override
	public double getXMin() {
		// TODO Auto-generated method stub
		return spriteCourant.getX();
	}

	@Override
	public double getXMax() {
		return spriteCourant.getX() + spriteCourant.getImage().getWidth();
	}
}
