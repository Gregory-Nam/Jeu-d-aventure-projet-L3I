package utilitaire;

import java.util.HashMap;

import application.Jeu;
import controleurs.EnigmeControleur;
import elements.Item;
import elements.Salle;
import enumerations.NomPNJ;
import enumerations.NomSalle;
import enumerations.Periode;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;

/**
 * Classe utilitaire pour finir le jeu.
 * 
 * @author Grégory NAM
 *
 */
public class FinisseurDeJeu {
	private static final KeyEvent droite = new KeyEvent(KeyEvent.KEY_PRESSED, "d", "droite", KeyCode.RIGHT, false,
			false, false, false);
	private static final KeyEvent gauche = new KeyEvent(KeyEvent.KEY_PRESSED, "g", "gauche", KeyCode.LEFT, false, false,
			false, false);
	private static final KeyEvent interagir = new KeyEvent(KeyEvent.KEY_PRESSED, "i", "interagir", KeyCode.UP, false,
			false, false, false);
	private static final KeyEvent echap = new KeyEvent(KeyEvent.KEY_PRESSED, "q", "escape", KeyCode.ESCAPE, false,
			false, false, false);
	private static Scene sceneJeu;
	private static Scene sceneEnigme;
	private static HashMap<NomPNJ, PersonnageNonJoueur> pnjs;
	private static EnigmeControleur enigmeC;
	private static Jeu jeu;
	private static PersonnageJoueur greg;

	private static NomSalle salleAvantObjectif;
	private static KeyEvent directionHorloge;
	private static KeyEvent directionPorte;
	private static int xPorte;
	private static int xHorloge;
	private static boolean itemMisOr1;
	private static boolean item2pris;

	private FinisseurDeJeu() {
	};

	/**
	 * Permet de finir le jeu.
	 * 
	 * @param sceneJeu        la scéne du jeu.
	 * @param sceneEnigme     la scéne de l'enigme.
	 * @param sceneInventaire la scéne de l'inventaire.
	 * @param rootEnigme      le controleur de l'énigme.
	 * @param pnjs            les pnjs.
	 * @param salles          les salles.
	 */
	public static void finirJeu(Scene sceneJeu, Scene sceneEnigme, Scene sceneInventaire, EnigmeControleur rootEnigme,
			HashMap<NomPNJ, PersonnageNonJoueur> pnjs, HashMap<NomSalle, Salle> salles) {
		FinisseurDeJeu.sceneJeu = sceneJeu;
		FinisseurDeJeu.sceneEnigme = sceneEnigme;
		enigmeC = rootEnigme;
		FinisseurDeJeu.pnjs = pnjs;
		jeu = Jeu.getInstanceUnique();
		greg = PersonnageJoueur.getInstanceUnique();
		PauseTransition transition = new PauseTransition(new Duration(20));
		itemMisOr1 = false;
		item2pris = false;
		salleAvantObjectif = NomSalle.SALLE_2;
		directionHorloge = droite;
		directionPorte = gauche;
		xPorte = 200;
		xHorloge = 900;
		finirEtape(transition, NomSalle.SALLE_3, droite, pnjs.get(NomPNJ.KLACE_HEUREOUVERRE), "13");

	}

	private static void finirEtape(PauseTransition t, NomSalle nom, KeyEvent deplacementDepart, PersonnageNonJoueur pnj,
			String reponse) {
		t.setOnFinished(event -> {
			if (jeu.getSalleCourante().getNomSalle() != nom) {
				KeyEvent.fireEvent(sceneJeu, deplacementDepart);
				t.play();
			}

		});
		if (!itemMisOr1)
			deplacerJusquauPNJ(t, pnj, deplacementDepart, reponse);
		else
			allerSalleMurale(t, NomSalle.SALLE_3, xPorte);
	}

	private static void deplacerJusquauPNJ(PauseTransition t, PersonnageNonJoueur pnj, KeyEvent deplacement,
			String reponse) {

		t.setOnFinished(event -> {
			if (jeu.getSalleCourante().interactifAPosition(greg.getXMin(), greg.getXMax()) != pnj) {
				KeyEvent.fireEvent(sceneJeu, deplacement);
				t.play();
			} else {
				KeyEvent.fireEvent(sceneJeu, interagir);
				repondre(pnj, reponse);
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
		t.setOnFinished(event -> {
			if (jeu.getSalleCourante().interactifAPosition(greg.getXMin(), greg.getXMax()) != i) {
				KeyEvent.fireEvent(sceneJeu, droite);
				t.play();
			} else {
				KeyEvent.fireEvent(sceneJeu, interagir);
				if (!jeu.getPeriodeCourante().equals(Periode.PERIODE_3))
					allerSalleMurale(t, salleAvantObjectif, xPorte);
				else if (!itemMisOr1) {
					allerDansUneSalle(t, NomSalle.SALLE_OR, droite);
				} else if (itemMisOr1) {
					item2pris = true;
					allerSalleMurale(t, NomSalle.SALLE_PIEGE, xPorte);
				}
			}
		});
		t.play();
	}

	private static void allerSalleMurale(PauseTransition t, NomSalle nomAvant, int xPorte) {
		t.setOnFinished(event -> {
			if (jeu.getSalleCourante().getNomSalle() != nomAvant) {
				KeyEvent.fireEvent(sceneJeu, directionPorte);
				t.play();
			} else if (xPorte - 10 < greg.getXCentre() && greg.getXCentre() < xPorte + 10) {
				KeyEvent.fireEvent(sceneJeu, interagir);
				if (itemMisOr1 && !item2pris)
					deplacerJusquauPNJ(t, pnjs.get(NomPNJ.ABITBOL), gauche, "TEMPS");
				else if (item2pris)
					allerDansUneSalle(t, NomSalle.SALLE_OR, droite);
				else
					allerHorloge(t, xHorloge);
			} else {
				if (item2pris)
					directionPorte = droite;
				
				KeyEvent.fireEvent(sceneJeu, directionPorte);
				t.play();
			}
			return;
		});

		t.play();
	}

	private static void allerHorloge(PauseTransition t, int horlogeX) {
		t.setOnFinished(event -> {
			if (!(horlogeX - 10 < greg.getXCentre() && greg.getXCentre() < horlogeX + 10)) {
				KeyEvent.fireEvent(sceneJeu, directionHorloge);
				t.play();
			} else {
				greg.prendreItemEnMain(greg.getInventaire().getInventaire().get(0));
				/* avant l'interaction on est dans periode trois donc item deja mis */
				if (jeu.getPeriodeCourante().equals(Periode.PERIODE_3)) {
					itemMisOr1 = true;
				}
				KeyEvent.fireEvent(sceneJeu, interagir);
				if (jeu.getPeriodeCourante().equals(Periode.PERIODE_2)) {
					salleAvantObjectif = NomSalle.SALLE_1;
					directionHorloge = gauche;
					directionPorte = gauche;
					xPorte = 760;
					xHorloge = 105;
					finirEtape(t, NomSalle.SALLE_2, droite, pnjs.get(NomPNJ.SLYNE), "45");
				} else if (jeu.getPeriodeCourante().equals(Periode.PERIODE_3) && !itemMisOr1) {
					directionHorloge = droite;
					xHorloge = 865;
					finirEtape(t, NomSalle.SALLE_2, droite, pnjs.get(NomPNJ.CARPENTER), "11");
				} else if (!item2pris) {
					directionHorloge = droite;
					directionPorte = gauche;
					xPorte = 760;
					xHorloge = 865;
					finirEtape(t, NomSalle.SALLE_3, droite, null, null);
				}
				return;
			}
		});

		t.play();

	}

	private static void allerDansUneSalle(PauseTransition t, NomSalle nom, KeyEvent direction) {
		t.setOnFinished(event -> {
			if (jeu.getSalleCourante().getNomSalle() != nom) {
				KeyEvent.fireEvent(sceneJeu, direction);
				t.play();
			} else if (jeu.getPeriodeCourante().equals(Periode.PERIODE_3)) {
				allerHorloge(t, xHorloge);
			}
			return;
		});
		t.play();
	}
}
