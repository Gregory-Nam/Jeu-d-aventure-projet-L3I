package controleurs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class EnigmeControleur extends GridPane {
	@FXML
	private TextField champsDeTexte;
	@FXML
	private ImageView imagePersonnage;
	@FXML
	private Label dialogue;
	
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
	
	public void mettreEnActionChampsTextuel(Runnable r) {
		champsDeTexte.setOnAction(e -> {
			r.run();
		});
	}
	
	public void changeDialogue(String dialogue) {
		this.dialogue.setText(dialogue);
	}
	
	public void changeImage(Image imagePersonnage) {
		this.imagePersonnage.setImage(imagePersonnage);
	}
	
	public String getChamps() {
		return champsDeTexte.getText();
	}
	
	public void nettoyerChampsTexte() {
		champsDeTexte.clear();
		
	}
	
	public void desactiverEntree() {
		champsDeTexte.setDisable(true);
		champsDeTexte.setPromptText("Appuyez sur echap pour quitter...");
	}
	
	public void activerEntree() {
		champsDeTexte.setDisable(false);
		
	}
	
	public void activerFocus() {
		champsDeTexte.requestFocus();
	}
	
}
