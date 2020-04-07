package controleurs;

import java.io.IOException;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Classe du controleur de la vue EcranDeFin.
 * 
 * @author Gregory NAM
 *
 */
public class FinControleur extends Pane {

	/**
	 * Label qui contient la raison de la fin du jeu.
	 */
	@FXML
	private Label raisonDeLaFin;

	/**
	 * Label qui contient l'état de la fin : mort ou victoire.
	 */
	@FXML
	private Label etatDeLaFin;

	/**
	 * Constructeur FinControleur.
	 */
	public FinControleur() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/EcranDeFin.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

		etatDeLaFin = (Label) this.getChildren().get(0);
		raisonDeLaFin = (Label) this.getChildren().get(1);
	}

	/**
	 * Permet de mettre la raison de la fin.
	 * 
	 * @param message message qui indique pourquoi le jeu s'arréte.
	 * @param aGagne  boolean qui permet d'afficher Bravo ou non.
	 */
	public void setRaisonDeLaFin(String message, boolean aGagne) {
		if (aGagne)
			etatDeLaFin.setText("Bravo !");
		raisonDeLaFin.setText(message);
	}
}
