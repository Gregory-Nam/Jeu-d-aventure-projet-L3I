package personnages;

import java.io.File;


import elements.Item;
import enumerations.Deplacements;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

public class PersonnageJoueur extends Personnage{
	
	private static PersonnageJoueur greg = new PersonnageJoueur();
	
	private final static String HAUT = "Images/Personnages/wizardNord_transparent.png";
	private final static String DROITE = "Images/Personnages/wizardDroite_transparent.png";
	private final static String BAS = "Images/Personnages/wizardSud_transparent.png";
	private final static String GAUCHE = "Images/Personnages/wizardGauche_transparent.png";
	private final static int VELOCITE = 15;
	
	private final double largeurPersonnage;
	private BooleanProperty aBienReponduperiodeCourante;
	
	private PersonnageJoueur() {
		super();
		
		File[] sprites = new File[4];
		sprites[0] = new File(HAUT);
		sprites[1] = new File(DROITE);
		sprites[2] = new File(BAS);
		sprites[3] = new File(GAUCHE);
		initPersonnage(sprites);
		
		this.aBienReponduperiodeCourante = new SimpleBooleanProperty(false);
		this.largeurPersonnage = spriteCourant.getImage().getWidth();
		
		super.xMin = spriteCourant.getX();
		super.xMax = getXMin() + spriteCourant.getImage().getWidth();
	}
	
	
	@Override
	public void seDirigerAGauche()  {
		double nouvellePosition = spriteCourant.getX() - VELOCITE;
		spriteCourant.setX(nouvellePosition);
		this.changerSprite(Deplacements.GAUCHE);
		super.xMin = nouvellePosition;
		super.xMax = nouvellePosition + largeurPersonnage;
	}
	
	@Override
	public void seDirigerADroite() {
		double nouvellePosition = spriteCourant.getX() + VELOCITE;
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
	
	public void enleverItemEnMain() {
		super.itemEnPossession = null;
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
	
	public void prendreItem(Item i) {
		super.itemEnPossession = i;
	}
	
	@Override
 	public void interagir() {
		// TODO Auto-generated method stub
	}

	public Item getItemEnMain() {
		return super.itemEnPossession;
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
	
	public static PersonnageJoueur getInstanceUnique() {
		return greg;
	}
	
	public boolean aBienRepondu() {
		return aBienReponduperiodeCourante.get();
	}

	public void liaisonDialogueAvecPNJ(PersonnageNonJoueur pnj){
		this.aBienReponduperiodeCourante.bind(pnj.getEtatReponseAttendu());
	}
}
