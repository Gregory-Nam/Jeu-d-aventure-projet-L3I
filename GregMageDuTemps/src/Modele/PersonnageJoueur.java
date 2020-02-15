package Modele;

import java.io.File;

import application.Jeu;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class PersonnageJoueur extends Personnage{
	
	private final double largeurPersonnage;
	private BooleanProperty aBienReponduperiodeCourante;
	public PersonnageJoueur(File ... sprites) {
		super(sprites);
		initPersonnage(sprites);
		
		this.aBienReponduperiodeCourante = new SimpleBooleanProperty(false);
		largeurPersonnage = spriteCourant.getImage().getWidth();
		super.xMin = spriteCourant.getX();
		super.xMax = getXMin() + spriteCourant.getImage().getWidth();
	}
	
	@Override
	public void seDirigerAGauche()  {
		double nouvellePosition = spriteCourant.getX() - 10;
		spriteCourant.setX(nouvellePosition);
		this.changerSprite(Deplacements.GAUCHE);
		super.xMin = nouvellePosition;
		super.xMax = nouvellePosition + largeurPersonnage;
	}
	
	@Override
	public void seDirigerADroite() {
		double nouvellePosition = spriteCourant.getX() + 10;
		spriteCourant.setX(nouvellePosition);
		this.changerSprite(Deplacements.DROITE);
		super.xMin = nouvellePosition;
		super.xMax = nouvellePosition + largeurPersonnage;

	}
	
	@Override
	public void initPersonnage(File[] sprites) {
		super.initPersonnage(sprites);
		spriteCourant = new ImageView(spritesPersonnageHM.get(Deplacements.DROITE).getImage());	
	}
	
	public void changerSprite(Deplacements d) {
		if(spriteCourant.getImage().equals(spritesPersonnageHM.get(d).getImage())) return;
		spriteCourant.setImage(spritesPersonnageHM.get(d).getImage());
		
		
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
	
	public boolean aBienRepondu() {
		return aBienReponduperiodeCourante.get();
	}

	public void liaisonDialogueAvecPNJ(PersonnageNonJoueur pnj){
		this.aBienReponduperiodeCourante.bind(pnj.getEtatReponseAttendu());
	}
}
