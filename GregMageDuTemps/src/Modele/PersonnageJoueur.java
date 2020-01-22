package Modele;

import java.io.File;

public class PersonnageJoueur extends Personnage{
	
	public PersonnageJoueur(File ... sprites) {
		super(sprites);
	}
	
	@Override
	public void seDirigerAGauche()  {
		spriteCourant.setX(spriteCourant.getX() - 10);
	}
	
	@Override
	public void seDirigerADroite() {
		spriteCourant.setX(spriteCourant.getX() + 10);
	}
	
	public void seDirigerVersLeNord() {
		
	}
	
	public void seDirigerVersLeSud() {
		
	}
	
}
