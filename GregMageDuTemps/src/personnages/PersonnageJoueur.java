package personnages;

import java.io.File;

import application.Jeu;
import elements.Inventaire;
import elements.Item;
import enumerations.Deplacements;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

/**
 * Implémentation de PersonnageJoueur (Singleton)
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE.
 */
public class PersonnageJoueur extends Personnage{
	
	/**
	 * Instance unique de PersonnageJoueur
	 */
	private static PersonnageJoueur greg = new PersonnageJoueur();
	
	/**
	 * Sprite du PersonnageJoueur de dos.
	 */
	private final static String HAUT = "Images/Personnages/wizardNord_transparent.png";
	
	/**
	 * Sprite du PersonnageJoueur vers la droite.
	 */
	private final static String DROITE = "Images/Personnages/wizardDroite_transparent.png";
	
	/**
	 * Sprite du PersonnageJoueur de face.
	 */
	private final static String BAS = "Images/Personnages/wizardSud_transparent.png";
	
	/**
	 * Sprite du PersonnageJoueur vers la gauche.
	 */
	private final static String GAUCHE = "Images/Personnages/wizardGauche_transparent.png";
	
	/**
	 * Vitese de deplacement en pixel.
	 */
	private final static int VELOCITE = 15;
	
	/**
	 * La largeur du sprite du PersonnageJoueur en pixel.
	 */
	private final double largeurPersonnage;
	
	/**
	 * L'inventaire du PersonnageJoueur.
	 */
	private Inventaire inventaire;
	
	/**
	 * Une propriete booleene qui permet de lier le PNJ avec le PJ.
	 */
	private BooleanProperty aBienReponduperiodeCourante;
	
	/**
	 * Le constructeur privé de PersonnageJoueur.
	 */
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
		this.inventaire = new Inventaire();
		
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
	/**
	 * Permet de mettre à jour l'ImageView courante selon le deplacement passé en paramètre.
	 * @param d Le deplacement.
	 */
	public void changerSprite(Deplacements d) {
		if(spriteCourant.getImage().equals(spritesPersonnageHM.get(d).getImage())) return;
		spriteCourant.setImage(spritesPersonnageHM.get(d).getImage());
	}
	
	/**
	 * Permet d'enlever l'item que le PersonnageJoueur à dans les mains.
	 * Cela l'enlève également de l'inventaire.
	 */
	public void enleverItemEnMain() {
		inventaire.supprimerItem(itemEnPossession);
		super.itemEnPossession = null;
	}
	
	/**
	 * Permet de replacer le PersonnageJoueur tout à gauche de la vue.
	 */
	public void replacerGauche() {
		super.xMin = 0;
		super.xMax = largeurPersonnage;
		spriteCourant.setX(super.xMin);
		changerSprite(Deplacements.DROITE);
	}
	/**
	 * Permet de replacer le PersonnageJoueur tout à droite de la vue.
	 */
	public void replacerDroite() {
		super.xMin = 1000 - largeurPersonnage;
		super.xMax = 1000;
		spriteCourant.setX(super.xMin);
	}
	
	/**
	 * Permet d'ajouter l'item passé en paramètre dans l'inventaire.
	 * @param i item à prendre.
	 */
	public void prendreItem(Item i) {
		this.inventaire.ajouterItem(i);
	}
	
	/**
	 * Permet de prendre un item présent dans l'inventaire en main.
	 * @param i item à prendre.
	 */
	public void prendreItemEnMain(Item i) {
		if(inventaire.getItem(i) == null) return;
		super.itemEnPossession = i;
	}
	
	@Override
 	public void interagir() {
		Jeu.getInstanceUnique().afficheMessage("Je crois qu'il n'y a rien...",0.5);
	}

	/**
	 * Renvoie l'item en possession du PersonnageJoueur.
	 * @return l'item en possession du PersonnageJoueur.
	 */
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
	
	/**
	 * Renvoie l'instance unique de PersonnageJoueur.
	 * @return l'instance unique de PersonnageJoueur.
	 */
	public static PersonnageJoueur getInstanceUnique() {
		return greg;
	}
	
	/**
	 * Renvoie l'inventaire
	 * @return l'inventaire
	 */
	public Inventaire getInventaire() {
		return inventaire;
	}
	
	/**
	 * Renvoie si le PersonnageJoueur a bien répondu
	 * @return vrai si le PersonnageJoueur a donnée une bonne réponse,
	 *         faux si le PersonnageJoueur a donnée une mauvaise réponse ou n'a pas répondu.
	 */
	public boolean aBienRepondu() {
		return aBienReponduperiodeCourante.get();
	}
	

	/**
	 * Permet de reinitialiser le PersonnageJoueur.
	 * Il est replacer a gauche et l'inventaire est recréée.
	 */
	public void reinitialiser() {
		this.replacerGauche();
		this.inventaire = new Inventaire();
	}
	
	/**
	 * Permet de faire la liaison du dialogue le PNJ passé en paramètre.
	 * @param pnj le pnj avec qui on veut faire la liaison.
	 */
	public void liaisonDialogueAvecPNJ(PersonnageNonJoueur pnj){
		this.aBienReponduperiodeCourante.bind(pnj.getEtatReponseAttendu());
	}
}
