package Modele;

import javafx.scene.image.ImageView;
import java.io.File;
public class PersonnageNonJoueur extends Personnage {
	
	public PersonnageNonJoueur(File ... sprites) {
		super(sprites);
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
	public PersonnageNonJoueur interagir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getXMin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getXMax() {
		// TODO Auto-generated method stub
		return 0;
	}
}
