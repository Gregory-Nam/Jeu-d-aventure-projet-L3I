package personnages;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilitaire.AnalyseFichierEnigmeUtil;

import java.io.File;
import java.util.HashMap;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;

import application.Jeu;
import elements.Item;
import enumerations.NomPNJ;
import enumerations.TypeDialogue;

/**
 * Impl�mentation de PersonnageNonJoueur
 * @author Gr�gory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE.
 */
public class PersonnageNonJoueur extends Personnage {
	
	/**
	 * Le nom du PNJ
	 */
	private NomPNJ nom;
	
	/**
	 * HashMap qui contient le type de dialogue et le dialogue.
	 */
	private HashMap<TypeDialogue, String> dialogues;
	
	/**
	 * Une propri�t� bool�ene qui permet de lier le PNJ avec le PJ.
	 */
	private BooleanProperty aRecuUneBonneReponse;
	
	/**
	 * L'ImageView du PNJ pour la vue de l'enigme.
	 */
	private ImageView spritePourEnigme;
	
	/**
	 * Le constructeur de la classe PersonnageNonJoueur.
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

	@Override
	public void seDirigerADroite() {
		spriteCourant.setX(spriteCourant.getX() - 20);
	}
	
	@Override
	public void seDirigerAGauche() {
		spriteCourant.setX(spriteCourant.getX() + 20);
	}
	
	/**
	 * Permet d'initialiser la HashMap contenant les dialogues du PNJ.
	 */
	private void initDialogue() {
		dialogues.put(TypeDialogue.BONNE_REPONSE, AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.BONNE_REPONSE));
		dialogues.put(TypeDialogue.QUESTION,AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.QUESTION));
		dialogues.put(TypeDialogue.MAUVAISE_REPONSE, AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.MAUVAISE_REPONSE));
		dialogues.put(TypeDialogue.DEJA_REPONDU, AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.DEJA_REPONDU));
		dialogues.put(TypeDialogue.REPONSE, AnalyseFichierEnigmeUtil.initDialoguesJSON(nom.toString(), TypeDialogue.REPONSE));
	}
	
	/**
	 * Renvoie le nom du PNJ.
	 * @return le nom du PNJ.
	 */
	public NomPNJ getNom() {
		return nom;
	}
	
	@Override
	public void interagir() {
		Jeu.getInstanceUnique().lancerEnigme(this);
	}
	
	@Override
	public double getXMin() {
		return spriteCourant.getX();
	}
	
	@Override
	public double getXMax() {
		return spriteCourant.getX() + spriteCourant.getImage().getWidth();
	}
	
	/**
	 * Renvoie la question de l'enigme du PNJ.
	 * @return la question de l'enigme du PNJ.
	 */
	public String poseQuestion() {
		return dialogues.get(TypeDialogue.QUESTION);
	}
	
	/**
	 * Renvoie la r�ponse � une mauvaise r�ponse.
	 * @return la r�ponse � une mauvaise r�ponse.
	 */
	public String repondAUneMauvaiseReponse() {
		return dialogues.get(TypeDialogue.MAUVAISE_REPONSE);
	}
	
	/**
	 * Renvoie la r�ponse � une bonne r�ponse.
	 * @return la r�ponse � une bonne r�ponse.
	 */
	public String repondAUneBonneReponse() {
		return dialogues.get(TypeDialogue.BONNE_REPONSE);
	}
	
	/**
	 * Renvoie la r�ponse � une bonne r�ponse d�j� donn�e.
	 * @return la r�ponse � une bonne r�ponse d�j� donn�e.
	 */
	public String ditQueTuAsDejaRepondu() {
		return dialogues.get(TypeDialogue.DEJA_REPONDU);
	}
	
	/**
	 * Renvoie la r�ponse � l'�nigme.
	 * @return la r�ponse � l'�nigme.
	 */
	public String reponse() {
		return dialogues.get(TypeDialogue.REPONSE);
	}
	
	/**
	 * Renvoie une propri�t� bool�ene qui correspond � l'�tat de la r�ponse recu.
	 * @return une propri�t� bool�ene qui correspond � l'�tat de la r�ponse recu.
	 */
	public BooleanProperty getEtatReponseAttendu() {
		return aRecuUneBonneReponse;
	}
	
	/**
	 * Renvoie l'item en possession.
	 * @return l'item en possession.
	 */
	public Item donnerItem() {
		return itemEnPossession;
	}
	
	/**
	 * Renvoie l'ImageView du PNJ pour la vue de l'�nigme.
	 * @return l'ImageView du PNJ pour la vue de l'�nigme.
	 */
	public ImageView getImagePourEnigme() {
		return spritePourEnigme;
	}
	
	/**
	 * Permet de mettre la propri�t� bool�enne � vrai si
	 * le PNJ a recu une bonne r�ponse du PersonnageJoueur.
	 */
	public void aRecuUneBonneReponse() {
		aRecuUneBonneReponse.setValue(true);
	}
}
