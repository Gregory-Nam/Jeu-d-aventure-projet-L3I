package controleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Controleur de la vue Enigme
 * 
 * @author Gregory NAM
 *
 */
public class EnigmeControleur extends GridPane {
	/**
	 * TextField qui permet au joueur de répondre.
	 */
	@FXML
	private TextField champsDeTexte;

	/**
	 * ImageView du PNJ qui sera affiché.
	 */
	@FXML
	private ImageView imagePersonnage;
	/**
	 * Label qui correspond au texte du PNJ.
	 */
	@FXML
	private Label dialogue;

	/**
	 * Constructeur de EnigmeControleur.
	 */
	public EnigmeControleur() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Enigme.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Methode qui permet de définir une action lorsque l'on a appuyé sur entrée
	 * dans le champs textuel.
	 * 
	 * @param r runnable qui contient les actions é effectuer.
	 */
	public void mettreEnActionChampsTextuel(Runnable r) {
		champsDeTexte.setOnAction(e -> {
			r.run();
		});
	}

	/**
	 * Permet de modifier le texte du champs textuel.
	 * 
	 * @param dialogue
	 */
	public void changeDialogue(String dialogue) {
		this.dialogue.setText(dialogue);
	}

	/**
	 * Permet de modifier l'image qui correspond a celle du personnage.
	 * 
	 * @param imagePersonnage image que l'on souhaite appliquer.
	 */
	public void changeImage(Image imagePersonnage) {
		this.imagePersonnage.setImage(imagePersonnage);
	}

	/**
	 * Renvoie le texte du champs textuel.
	 * 
	 * @return le texte du champs textuel.
	 */
	public String getTexteDuChamps() {
		return champsDeTexte.getText();
	}

	/**
	 * Renvoie le champs textuel.
	 * 
	 * @return le champs textuel.
	 */
	public TextField getChamps() {
		return champsDeTexte;
	}

	/**
	 * Permet de vider le champs textuel.
	 */
	public void nettoyerChampsTexte() {
		champsDeTexte.clear();
	}

	/**
	 * Permet d'empecher au joueur d'écrire du texte.
	 */
	public void desactiverEntree() {
		champsDeTexte.setDisable(true);
		champsDeTexte.setPromptText("Appuyez sur echap pour quitter...");
	}

	/**
	 * Permet d'autoriser le joueur é écrire du texte.
	 */
	public void activerEntree() {
		champsDeTexte.setDisable(false);

	}

	/**
	 * Permet d'activer le focus sur le champs textuel. Cela signifie que le joueur
	 * n'a pas a cliquer sur la zone de texte avant d'écrire.
	 */
	public void activerFocus() {
		champsDeTexte.requestFocus();
	}

	/**
	 * NE PAS UTILISER CETTE METHODE HORS DU FINISSEUR DE JEU. <br>
	 * Permet de simuler une entrée utilisateur.
	 * 
	 * @param texte texte que l'on souhaite écrire
	 */
	public void mettreText(String texte) {
		champsDeTexte.setText(texte);
		champsDeTexte.fireEvent(new ActionEvent());
	}

}
