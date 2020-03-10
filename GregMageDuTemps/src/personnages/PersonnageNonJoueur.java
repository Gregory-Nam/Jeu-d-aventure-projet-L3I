package personnages;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilitaire.AnalyseFichierEnigmeUtil;

import java.io.File;
import java.util.HashMap;

import application.Jeu;
import elements.Item;
import enumerations.NomPNJ;
import enumerations.TypeDialogue;
/**
 * @author Ahmadou Bamba MBAYE.
 * La classe PersonnageNonJoueur herite de la classe Personnage  Elle gere les personnages non joueurs.
 * Cette classe contient comme parametre le nom du personnage non joueur, l'item a donner, un HashMap qui contient
 * et un boolean confirmant s'il a bien repondu
 */
public class PersonnageNonJoueur extends Personnage {
	/**
	 *Le nom du personnage non joueur
	 */
	private NomPNJ nom;
	/**
	 * Un hashMap qui contient le type de dialogue et un string
	 */
	private HashMap<TypeDialogue, String> dialogues;
	/**
	 * Un boolean qui retourne vrai si le personnage a bien repondu a la question
	 */
	private BooleanProperty aRecuUneBonneReponse;
	/**
	 * Le prite pour l'enigme de type ImageView
	 */
	private ImageView spritePourEnigme;
	
	/**
	 * Le constructeur de la classe PersonnageNonJoueur prend en paramètre un nom du personnage non joueur,
	 *un nombre x, un item i, un sprite pour l'énigme:
	 * <ul>
		 * <li>il récupère tout ce qui était dans le constructeur de la classe mère Personnage en affectant l'item
		 *en possession à i</li>
		 * <li>il crée un nouvelle image en vue</li>
		 * <li>le sprite pour enigme en creant une nouvelle image</li>
		 * <li>il crée un nouveau boolean SimpleBooleanProperty qui prend false en paramètre de sorti</li>
		 * <li>il initialise le nom du personnage non joueur</li>
		 * <li>il crée le dialogue dans un HashMap</li>
	 * </ul>
	 * @param x la position
	 * @param i un item quelconque
	 * @param spritePourEnigme Le sprite pour l'enigme
	 * @param sprites Les sprites
	 */
	public PersonnageNonJoueur(NomPNJ nom, double x, Item i, File spritePourEnigme, File ... sprites) {
		super();
		super.itemEnPossession = i;
		
		this.spritePourEnigme = new ImageView(new Image(spritePourEnigme.toURI().toString()));
		this.aRecuUneBonneReponse = new SimpleBooleanProperty(false);
		this.nom = nom;
		this.dialogues = new HashMap<TypeDialogue, String>();
		
		if(sprites.length > 1)
			super.initPersonnage(sprites);
		
		spriteCourant = new ImageView(new Image(sprites[0].toURI().toString()));	

		spriteCourant.setX(x);
		initDialogue();
	}

	/**
	 * La méthode abstraite seDirigerADroite() gére la cas ou le personnage se dirige vers la droite..
	 * Elle met a jour la position du spriteCourant
	 */
	@Override
	public void seDirigerADroite() {
		spriteCourant.setX(spriteCourant.getX() - 20);
		
	}
	/**
	 * La méthode abstraite seDirigerAGauche() gére la cas ou le personnage se dirige vers la gauche..
	 * Elle met a jour la position du spriteCourant
	 */
	@Override
	public void seDirigerAGauche() {
		spriteCourant.setX(spriteCourant.getX() + 20);
	}
	/**
	 * Cette methode permet d'affecter chaque dialogue a son Type de dialogue à l'aide l'enumeration TypeDialogue
	 */
	private void initDialogue() {
		dialogues.put(TypeDialogue.BONNE_REPONSE, AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.BONNE_REPONSE));
		dialogues.put(TypeDialogue.QUESTION,AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.QUESTION));
		dialogues.put(TypeDialogue.MAUVAISE_REPONSE, AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.MAUVAISE_REPONSE));
		dialogues.put(TypeDialogue.DEJA_REPONDU, AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.DEJA_REPONDU));
		dialogues.put(TypeDialogue.REPONSE, AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.REPONSE));
	}
	/**
	 * La methode getNom() recupere le nom du personnage non joueur
	 * @return Le nom du personnage non joueur
	 */
	public NomPNJ getNom() {
		return nom;
	}
	/**
	 * Cette methode gere l'interagissement en lancant les enigmes
	 */
	@Override
	public void interagir() {
		Jeu.getInstanceUnique().lancerEnigme(this);
	}
	/**
	 * La methode getXMin() recupere la valeur xMin
	 * @return La valeur de xMin
	 */
	@Override
	public double getXMin() {
		return spriteCourant.getX();
	}
	/**
	 * La methode getXMax() recupere la valeur xMax
	 * @return La valeur de xMax
	 */
	@Override
	public double getXMax() {
		return spriteCourant.getX() + spriteCourant.getImage().getWidth();
	}
	/**
	 * Cette methode permet de poser la question
	 * @return Elle retourne la <strong>QUESTION</strong>
	 */
	public String poseQuestion() {
		return dialogues.get(TypeDialogue.QUESTION);
	}
	/**
	 * Cette methode donne une MAUVAISE_REPONSE
	 * @return Elle retourne la <strong>MAUVAISE_REPONSE</strong>
	 */
	public String repondAUneMauvaiseReponse() {
		return dialogues.get(TypeDialogue.MAUVAISE_REPONSE);
	}
	/**
	 * Cette methode donne une BONNE_REPONSE
	 * @return Elle retourne la <strong>BONNE_REPONSE</strong>
	 */
	public String repondAUneBonneReponse() {
		return dialogues.get(TypeDialogue.BONNE_REPONSE);
	}
	/**
	 * Cette methode signale si une question est deja repondue
	 * @return Elle retourne un message precisant que la question est <strong>DEJA_REPONDU</strong>
	 */
	public String ditQueTuAsDejaRepondu() {
		return dialogues.get(TypeDialogue.DEJA_REPONDU);
	}
	/**
	 * Cette methode donne une reponse
	 * @return Elle retourne la <strong>REPONSE</strong>
	 */
	public String reponse() {
		return dialogues.get(TypeDialogue.REPONSE);
	}
	/**
	 * Cette methode est boolean qui recupere la reponse attendue
	 * @return Elle retourne la bonne reponse
	 */
	public BooleanProperty getEtatReponseAttendu() {
		return aRecuUneBonneReponse;
	}
	/**
	 * Cette methode donne l'item
	 * @return Elle retourne l'item a donner
	 */
	public Item donnerItem() {
		return itemEnPossession;
	}
	/**
	 * Cette methode recupere l'image pour l'enigme
	 * @return Elle un sprite pour l'enigme
	 */
	public ImageView getImagePourEnigme() {
		return spritePourEnigme;
	}
	/**
	 * Cette methode est un boolean qui retournant TRUE si la reponse recu est la bonne
	 */
	public void aRecuUneBonneReponse() {
		aRecuUneBonneReponse.setValue(true);
	}
}
