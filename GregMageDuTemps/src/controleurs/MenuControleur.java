package controleurs;

import java.io.IOException;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Classe de MenuControleur, controleur de la vue Menu.
 * 
 * @author Gregory NAM.
 *
 */

public class MenuControleur extends Pane {
	/**
	 * Label qui correspond au bouton permettant de lancer une partie ou de
	 * reprendre la partie en cours.
	 */
	@FXML
	private Label btnJouer;

	/**
	 * Label qui correspond au bouton permettant d'afficher les commandes du Jeu.
	 */
	@FXML
	private Label btnCommandes;

	/**
	 * Label qui correspond au bouton permettant d'afficher l'histoire du Jeu.
	 */
	@FXML
	private Label btnHistoire;

	/**
	 * Label qui correspond au bouton permetant de quitter le Jeu.
	 */
	@FXML
	private Label btnQuitter;

	/**
	 * Group qui englobe l'ensemble des boutons du menu.
	 */
	@FXML
	private Group groupMenu;

	/**
	 * Label qui correspond au texte des commandes.
	 */
	@FXML
	private Label labelCommande;

	/**
	 * Label qui correspond au texte de l'histoire.
	 */
	@FXML
	private Label labelHistoire;

	/**
	 * Label qui correspond a un texte d'indication.
	 */
	@FXML
	private Label indication;
	/**
	 * Evenement qui permet de quitter.
	 */
	EventHandler<MouseEvent> cliqueQuitter;

	/**
	 * Evenement du clique sur le bouton Jouer.
	 */
	EventHandler<MouseEvent> cliqueJouer;

	/**
	 * Evenement du clique sur le bouton Histoire ou Commande.
	 */
	EventHandler<MouseEvent> cliqueHistoireCommande;

	/**
	 * Evenement du clique sur le bouton reprendre.
	 */
	EventHandler<MouseEvent> cliqueReprendre;

	/**
	 * Evenement de la touche pour revenir en arriére dans le menu.
	 */
	EventHandler<KeyEvent> retourArriere;

	/**
	 * Constructeur de MenuControleur.
	 */
	public MenuControleur() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Menu.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		initReprendre();
		initJouer();
		initQuitter();
		initHistoireCommande();
		initRetourArriereDansMenu();
		initSurvoler();
	}

	/**
	 * Permet d'initialiser l'evenement Quitter.
	 */
	private void initQuitter() {
		cliqueQuitter = c -> {
			System.exit(0);
			;
		};
		btnQuitter.addEventHandler(MouseEvent.MOUSE_CLICKED, cliqueQuitter);
	}

	/**
	 * Permet d'initialiser l'evenement Jouer.
	 */
	private void initJouer() {
		cliqueJouer = c -> {
			try {
				Jeu.getInstanceUnique().lancerJeu();
				btnJouer.setText("Reprendre");
				btnJouer.removeEventHandler(MouseEvent.MOUSE_CLICKED, cliqueJouer);
				btnJouer.addEventHandler(MouseEvent.MOUSE_CLICKED, cliqueReprendre);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		btnJouer.addEventHandler(MouseEvent.MOUSE_CLICKED, cliqueJouer);
	}

	/**
	 * Permet d'initialiser l'evenement Reprendre.
	 */
	private void initReprendre() {
		cliqueReprendre = c -> {
			System.out.println("bug");
			Jeu.getInstanceUnique().reprendre();
		};
	}

	/**
	 * Permet d'initialiser l'evenement Histoire / Commande.
	 */
	private void initHistoireCommande() {

		cliqueHistoireCommande = c -> {
			visibiliteEnfant(groupMenu);

			if (c.getSource().equals(btnHistoire))
				visibiliteEnfant(labelHistoire);
			else
				visibiliteEnfant(labelCommande);

			visibiliteEnfant(indication);
			this.getScene().addEventHandler(KeyEvent.KEY_PRESSED, retourArriere);
		};
		btnHistoire.addEventHandler(MouseEvent.MOUSE_CLICKED, cliqueHistoireCommande);
		btnCommandes.addEventHandler(MouseEvent.MOUSE_CLICKED, cliqueHistoireCommande);
	}

	/**
	 * Permet de modifier la visiblite du noeud passé en paramétre.
	 * 
	 * @param enfant noeud dont on souhaite modifier la visibilité.
	 */
	private void visibiliteEnfant(Node enfant) {
		enfant.setVisible(!enfant.isVisible());
		enfant.setDisable(!enfant.isDisabled());
	}

	/**
	 * Permet d'initialiser l'evenement pour revenir en arriére dans le menu.
	 */
	private void initRetourArriereDansMenu() {
		retourArriere = k -> {
			KeyCode kc = k.getCode();
			switch (kc) {
			case ESCAPE:
				if (labelHistoire.isVisible())
					visibiliteEnfant(labelHistoire);
				else
					visibiliteEnfant(labelCommande);
				visibiliteEnfant(groupMenu);
				visibiliteEnfant(indication);
				break;
			default:
				break;
			}
			this.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, retourArriere);
		};

	}

	/**
	 * Permet d'initialiser l'effet "hover" des boutons.
	 */
	private void initSurvoler() {
		EventHandler<MouseEvent> entrer = s -> {
			Node element = ((Node) s.getSource());
			element.setStyle(element.getStyle() + "-fx-opacity:0.5;");

		};

		EventHandler<MouseEvent> sortir = s -> {
			((Node) s.getSource()).setStyle("-fx-background-color:gray;");
		};

		groupMenu.getChildren().subList(1, groupMenu.getChildren().size()).forEach(n -> {
			n.setOnMouseEntered(entrer);
			n.setOnMouseExited(sortir);
		});

	}
}
