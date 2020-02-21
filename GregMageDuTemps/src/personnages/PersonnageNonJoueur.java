package personnages;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilitaire.AnalyseFichierEnigmeUtil;

import java.io.File;
import java.util.HashMap;

import application.Jeu;
import elements.Item;
import elements.Salle;
import enumerations.Deplacements;
import enumerations.NomPNJ;
import enumerations.TypeDialogue;
public class PersonnageNonJoueur extends Personnage {
	
	private NomPNJ nom;
	private Item itemADonner;
	private HashMap<TypeDialogue, String> dialogues;
	private BooleanProperty aRecuUneBonneReponse;
	private ImageView spritePourEnigme;
	
	
	public PersonnageNonJoueur(double x, Item i ,File spritePourEnigme, File ... sprites) {
		super(sprites);
		
		this.spritePourEnigme = new ImageView(new Image(spritePourEnigme.toURI().toString()));
		this.aRecuUneBonneReponse = new SimpleBooleanProperty(false);
		this.nom = NomPNJ.JACQUE;
		this.dialogues = new HashMap<TypeDialogue, String>();
		this.itemADonner = i;
		
		initPersonnage(sprites);
		spriteCourant.setX(x);
		initDialogue();
	}

	@Override
	public void initPersonnage(File[] sprites) {
		super.initPersonnage(sprites);
		
		spriteCourant = new ImageView(spritesPersonnageHM.get(Deplacements.BAS).getImage());	
	}
	
	@Override
	public void seDirigerADroite() {
		spriteCourant.setX(spriteCourant.getX() - 20);
		
	}

	@Override
	public void seDirigerAGauche() {
		spriteCourant.setX(spriteCourant.getX() + 20);
	}

	private void initDialogue() {
		String tousLesDialogues = AnalyseFichierEnigmeUtil.rechercheDialogues(nom.toString());
		dialogues.put(TypeDialogue.BONNE_REPONSE, AnalyseFichierEnigmeUtil.initDialogue(tousLesDialogues, TypeDialogue.BONNE_REPONSE));
		dialogues.put(TypeDialogue.QUESTION, AnalyseFichierEnigmeUtil.initDialogue(tousLesDialogues, TypeDialogue.QUESTION));
		dialogues.put(TypeDialogue.MAUVAISE_REPONSE, AnalyseFichierEnigmeUtil.initDialogue(tousLesDialogues, TypeDialogue.MAUVAISE_REPONSE));
		dialogues.put(TypeDialogue.DEJA_REPONDU, AnalyseFichierEnigmeUtil.initDialogue(tousLesDialogues, TypeDialogue.DEJA_REPONDU));
		dialogues.put(TypeDialogue.REPONSE, AnalyseFichierEnigmeUtil.initDialogue(tousLesDialogues, TypeDialogue.REPONSE));
	}
	
	@Override
	public void interagir() {
		Jeu.lancerEnigme(this);
	}

	@Override
	public double getXMin() {
		return spriteCourant.getX();
	}

	@Override
	public double getXMax() {
		return spriteCourant.getX() + spriteCourant.getImage().getWidth();
	}
	
	public String poseQuestion() {
		return dialogues.get(TypeDialogue.QUESTION);
	}
	
	public String repondAUneMauvaiseReponse() {
		return dialogues.get(TypeDialogue.MAUVAISE_REPONSE);
	}
	
	public String repondAUneBonneReponse() {
		return dialogues.get(TypeDialogue.BONNE_REPONSE);
	}
	
	public String ditQueTuAsDejaRepondu() {
		return dialogues.get(TypeDialogue.DEJA_REPONDU);
	}
	
	public String reponse() {
		return dialogues.get(TypeDialogue.REPONSE);
	}
	
	public BooleanProperty getEtatReponseAttendu() {
		return aRecuUneBonneReponse;
	}
	public Item donnerItem() {
		return itemADonner;
		
	}
	
	public ImageView getImagePourEnigme() {
		return spritePourEnigme;
	}
	public void aRecuUneBonneReponse() {
		aRecuUneBonneReponse.setValue(true);
	}
}