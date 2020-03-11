package utilitaire;

import java.util.HashMap;

import com.sun.glass.ui.Robot;

import application.Jeu;
import controleurs.EnigmeControleur;
import elements.Item;
import elements.Salle;
import enumerations.Deplacements;
import enumerations.NomPNJ;
import enumerations.NomSalle;
import enumerations.Periode;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;

public class FinisseurDeJeu {
	private static final KeyEvent droite = new KeyEvent(KeyEvent.KEY_PRESSED, "d", "droite", KeyCode.RIGHT, false, false, false, false);
	private static final KeyEvent gauche = new KeyEvent(KeyEvent.KEY_PRESSED, "g", "gauche", KeyCode.LEFT, false, false, false, false);
	private static final KeyEvent interagir = new KeyEvent(KeyEvent.KEY_PRESSED, "i", "interagir", KeyCode.UP, false, false, false, false);
	private static final KeyEvent echap = new KeyEvent(KeyEvent.KEY_PRESSED, "q", "escape", KeyCode.ESCAPE, false, false, false, false);
	private static final ActionEvent valide = new ActionEvent();


	private static Scene sceneJeu;
	private static Scene sceneEnigme;
	private static HashMap<NomPNJ,PersonnageNonJoueur> pnjs;
	private static EnigmeControleur enigmeC;
	private static Jeu jeu;
	private static PersonnageJoueur greg;
	
	private static NomSalle salleAvantObjectif;
	private static KeyEvent directionHorloge;
	private static KeyEvent directionPorte;
	private static int xPorte;
	private static int xHorloge;
	
	
	private FinisseurDeJeu() {};
	
	public static void finirJeu(Scene sceneJeu, Scene sceneEnigme, Scene sceneInventaire, EnigmeControleur rootEnigme,
								HashMap<NomPNJ,PersonnageNonJoueur> pnjs, HashMap<NomSalle, Salle> salles)
	{
		FinisseurDeJeu.sceneJeu = sceneJeu;
		FinisseurDeJeu.sceneEnigme = sceneEnigme;
		enigmeC = rootEnigme;
		FinisseurDeJeu.pnjs = pnjs;
		jeu = Jeu.getInstanceUnique();
		greg = PersonnageJoueur.getInstanceUnique();
		salleEnigmeBronze();
		System.out.println("coucou hihi");
		
	}
	
	
	private static void salleEnigmeBronze() {
		salleAvantObjectif = NomSalle.SALLE_2;
		directionHorloge = droite;
		directionPorte = gauche;
		xPorte = 200;
		xHorloge = 900;
		PauseTransition transition = new PauseTransition(new Duration(0.5));
		transition.setOnFinished(event ->{
	    	if(jeu.getSalleCourante().getNomSalle() != NomSalle.SALLE_3) {
		    	KeyEvent.fireEvent(sceneJeu, droite);
		    	transition.play();
	    	}  	

	    });
		deplacerJusquauPNJ(transition, pnjs.get(NomPNJ.KLACE_HEUREOUVERRE), droite, "13");
	}
	
	private static void salleEnigmeArgent() {
		salleAvantObjectif = NomSalle.SALLE_1;
		directionHorloge = gauche;
		directionPorte = gauche;
		xPorte = 760;
		xHorloge = 105;
		PauseTransition transition = new PauseTransition(new Duration(0.5));
		transition.setOnFinished(event ->{
	    	if(jeu.getSalleCourante().getNomSalle() != NomSalle.SALLE_2) {
		    	KeyEvent.fireEvent(sceneJeu, droite);
		    	transition.play();
	    	}  	

	    });
		deplacerJusquauPNJ(transition, pnjs.get(NomPNJ.SLYNE), droite, "45");
	}
	
	private static void salleEnigmeOr1() {
		directionHorloge = droite;
		xHorloge = 865;
		PauseTransition transition = new PauseTransition(new Duration(1));
		transition.setOnFinished(event ->{
	    	if(jeu.getSalleCourante().getNomSalle() != NomSalle.SALLE_1) {
		    	KeyEvent.fireEvent(sceneJeu, droite);
		    	transition.play();
	    	}  	

	    });
		
		deplacerJusquauPNJ(transition, pnjs.get(NomPNJ.CARPENTER), droite, "11");
	}
	
	private static void deplacerJusquauPNJ(PauseTransition t, PersonnageNonJoueur pnj, KeyEvent deplacement, String reponse) {
		
		t.setOnFinished(event ->{
	    	if(jeu.getSalleCourante().interactifAPosition(greg.getXMin(), greg.getXMax()) != pnj ) {
		    	KeyEvent.fireEvent(sceneJeu, deplacement);
		    	t.play();
	    	}
	    	else {
	    		KeyEvent.fireEvent(sceneJeu, interagir);
	    		repondre(pnj,reponse);
	    		prendreItem(t, pnj.donnerItem());
    		}
	    });
		t.play();
	}
	
	private static void repondre(PersonnageNonJoueur pnj, String reponse) {
		enigmeC.activerFocus();
		enigmeC.mettreText(reponse);
		KeyEvent.fireEvent(sceneEnigme, echap);
	}
	
	private static void prendreItem(PauseTransition t, Item i) {
		t.setOnFinished(event ->{
	    	if(jeu.getSalleCourante().interactifAPosition(greg.getXMin(), greg.getXMax()) != i) {
		    	KeyEvent.fireEvent(sceneJeu, droite);
		    	t.play();
	    	}
	    	else {
	    		KeyEvent.fireEvent(sceneJeu, interagir);
	    		if(!jeu.getPeriodeCourante().equals(Periode.PERIODE_3))
	    			allerSalleMurale(t, salleAvantObjectif, xPorte);
	    		else {
	    			allerDansUneSalle(t, NomSalle.SALLE_OR, droite);
	    		}
	    			
    		}
	    });
		t.play();
	}
	
	private static void allerSalleMurale(PauseTransition t, NomSalle nomAvant, int xPorte) {
		t.setOnFinished(event ->{
	    	if(jeu.getSalleCourante().getNomSalle() != nomAvant) {
		    	KeyEvent.fireEvent(sceneJeu,directionPorte);
		    	t.play();
	    	}
	    	else if(  xPorte - 10 < greg.getXCentre() && greg.getXCentre() < xPorte + 10){
	    		KeyEvent.fireEvent(sceneJeu, interagir);
	    		allerHorloge(t, xHorloge);
    		}
	    	else {
	    		KeyEvent.fireEvent(sceneJeu, directionPorte);
	    		t.play();
	    	}
	    });
		
		t.play();
	}
	
	private static void allerHorloge(PauseTransition t, int xHorloge) {
		t.setOnFinished(event ->{
	        if(xHorloge - 10 < greg.getXCentre() && greg.getXCentre() < xHorloge + 10){
	        	greg.prendreItemEnMain(greg.getInventaire().getInventaire().get(0));
	    		KeyEvent.fireEvent(sceneJeu, interagir);
	    		if(jeu.getPeriodeCourante().equals(Periode.PERIODE_2)) {
	    			salleEnigmeArgent();
	    		}
	    		else if(jeu.getPeriodeCourante().equals(Periode.PERIODE_3))
	    		{
	    			salleEnigmeOr1();
	    		}
    		}
	    	else {
	    		KeyEvent.fireEvent(sceneJeu, directionHorloge);
	    		t.play();
	    	}
	    });
		
		t.play();
		
	}
	
	private static void allerDansUneSalle(PauseTransition t, NomSalle nom, KeyEvent direction) {
		t.setOnFinished(event ->{
	    	if(jeu.getSalleCourante().getNomSalle() != nom) {
	    		System.out.println("bloc");
		    	KeyEvent.fireEvent(sceneJeu, direction);
		    	t.play();
	    	}
	    	else if(jeu.getPeriodeCourante().equals(Periode.PERIODE_3)) {
	    		allerHorloge(t, xHorloge);
	    	}

	    });
		t.play();
	}
}
