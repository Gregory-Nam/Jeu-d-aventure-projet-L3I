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
 * @author Ahmadou Bamba MBAYE.
 * La classe PersonnageJoueur herite la classe Personnage. Elle gere les personnages joueurs.
 * Cette classe contient comme parametre la largeur du personnage et un boolean confirmant s'il a bien repondu
 */
public class PersonnageJoueur extends Personnage{
	
	private static PersonnageJoueur greg = new PersonnageJoueur();
	
	private final static String HAUT = "Images/Personnages/wizardNord_transparent.png";
	private final static String DROITE = "Images/Personnages/wizardDroite_transparent.png";
	private final static String BAS = "Images/Personnages/wizardSud_transparent.png";
	private final static String GAUCHE = "Images/Personnages/wizardGauche_transparent.png";
	private final static int VELOCITE = 15;
	/**
	 * La lergeur du personnage joueur
	 */
	private final double largeurPersonnage;
	private Inventaire inventaire;
	/**
	 * Boolean qui retourne vrai TRUE si le personnage a bien repondu
	 */
	private BooleanProperty aBienReponduperiodeCourante;
	/**
	 * Le constructeur sans paramètre de la classe PersonnageJoueur recupere le constructeur de la classe mère ensuite créer un tbaleau
	 *de sprites contenant 4 elèments : HAUT, DROITE, BAS et GAUCHE.
	 * Ce constructeur crée aussi un nouveau boolean SimpleBooleanProperty qui prend faux comme paramètre et un nouveau inventaire et initialise 
	 *la largeur du personnage.Il récupere la position minimale et maximale a l'aide de la classe mère Personnage
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
	
	/**
	 * La méthode seDirigerAGauche() gére la cas ou le personnage se dirige vers la gauche..
	 */
	@Override
	public void seDirigerAGauche()  {
		double nouvellePosition = spriteCourant.getX() - VELOCITE;
		spriteCourant.setX(nouvellePosition);
		this.changerSprite(Deplacements.GAUCHE);
		super.xMin = nouvellePosition;
		super.xMax = nouvellePosition + largeurPersonnage;
	}
	/**
	 * La méthode seDirigerADroite() gére la sas ou le personnage se dirige vers la droite..
	 */
	@Override
	public void seDirigerADroite() {
		double nouvellePosition = spriteCourant.getX() + VELOCITE;
		spriteCourant.setX(nouvellePosition);
		this.changerSprite(Deplacements.DROITE);
		super.xMin = nouvellePosition;
		super.xMax = nouvellePosition + largeurPersonnage;

	}
	/**
	 * Cette methode fait appel a la methode initPersonnage de la classe mere Personnage.
	 * Elle cree un spriteCourant avec une nouvelle image
	 */
	@Override
	public void initPersonnage(File[] sprites) {
		super.initPersonnage(sprites);
		spriteCourant = new ImageView(spritesPersonnageHM.get(Deplacements.DROITE).getImage());	
	}
	/**
	 * Cette methode qui prend un paramètre d de type déplacements permet de changer le sprite.
	 * Si l'image du sprite courant est egale a l'image du spritePersonnage.
	 * on met a jour l'image le sprite personnage
	 * @param d Le deplacement
	 */
	public void changerSprite(Deplacements d) {
		if(spriteCourant.getImage().equals(spritesPersonnageHM.get(d).getImage())) return;
		spriteCourant.setImage(spritesPersonnageHM.get(d).getImage());
	}
	
	public void enleverItemEnMain() {
		System.out.println("enlever item main");
		inventaire.supprimerItem(itemEnPossession);
		super.itemEnPossession = null;
	}
	/**
	 * La méthode replacerGauche() replace le personnage joueur:
	 * <ul>
	 *     <li>quand la xMin est null et le xMax est egal a la largeur du personnage joueur </li>
	 *     <li>et mit a joueur le xMin du spriteCourant</li>
	 * </ul>
	 */
	public void replacerGauche() {
		super.xMin = 0;
		super.xMax = largeurPersonnage;
		spriteCourant.setX(super.xMin);
	}
	/**
	 * La méthode replacerDroite() replace le personnage joueur:
	 * <ul>
	 *     <li>quand la xMax est egal a 1000 et le xMin est egal a la difference entre xMax et la largeur du personnage joueur </li>
	 *     <li>et mit a joueur le xMin du spriteCourant</li>
	 * </ul>
	 */
	public void replacerDroite() {
		super.xMin = 1000 - largeurPersonnage;
		super.xMax = 1000;
		spriteCourant.setX(super.xMin);
	}
	
	public void mettreItemdansInventaire(Item i) {
		this.inventaire.ajouterItem(i);
	}
	
	public void prendreItemEnMain(Item i) {
		super.itemEnPossession = inventaire.getItem(i);
	}
	
	@Override
 	public void interagir() {
		Jeu.getInstanceUnique().afficheMessage("Je crois qu'il n'y a rien...",0.5);
	}
/**
	 * La methode getItemEnMain() recupere l'item en main
	 * @return l'item en main
	 */
	public Item getItemEnMain() {
		return super.itemEnPossession;
	}
	/**
	 * La methode getXMin() recupere la valeur xMin
	 * @return La valeur de xMin
	 */
	@Override
	public double getXMin() {
		return super.xMin;
	}
/**
	 * La methode getXMax() recupere la valeur xMax
	 * @return La valeur de xMax
	 */
	@Override
	public double getXMax() {
		return super.xMax;
	}
/**
	 * La methode getXCentre() recupere la valeur du centre
	 * @return La valeur du centre (moyenne entre xMin et xMax)
	 */
	public double getXCentre() {
		return (super.xMin + super.xMax) / 2;
	}
	/**
	 * La methode getInstanceUnique() recupere l'instance unique du greg
	 * @return Le personnage joueur Greg
	 */
	public static PersonnageJoueur getInstanceUnique() {
		return greg;
	}
	/**
	 * La methode getInventaire() recupere l'inventaire
	 * @return Un inventaire
	 */
	public Inventaire getInventaire() {
		return inventaire;
	}
	/**
	 * Cette methode retourne un boolean
	 * @return
	 * Elle retourne <strong>TRUE</strong> si le personnage a bien repondu.
	 * Elle retourne <strong>FALSE</strong> si le personnage a mal repondu
	 */
	public boolean aBienRepondu() {
		return aBienReponduperiodeCourante.get();
	}
/**
	 * Cette methode fait la liaison du dialogue avec personnage non joueur
	 * @param pnj
	 * Le personnage non joueur
	 */
	public void liaisonDialogueAvecPNJ(PersonnageNonJoueur pnj){
		this.aBienReponduperiodeCourante.bind(pnj.getEtatReponseAttendu());
	}
}
