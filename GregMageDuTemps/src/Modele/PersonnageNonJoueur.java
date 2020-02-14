package Modele;

import javafx.scene.image.ImageView;
import java.io.File;
import java.util.HashMap;

import application.Jeu;
public class PersonnageNonJoueur extends Personnage {
	
	private NomPNJ nom;
	private HashMap<TypeDialogue, String> dialogues;
	public PersonnageNonJoueur(double x, File ... sprites) {
		super(sprites);
		this.nom = NomPNJ.JACQUE;
		dialogues = new HashMap<TypeDialogue, String>();
		initPersonnage(sprites);
		spriteCourant.setX(x);
		initDialogue();
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
		// TODO Auto-generated method stub
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
	
	public Item donnerItem() {
		/* TO-DO */
		return null;
	}
	
	
}
