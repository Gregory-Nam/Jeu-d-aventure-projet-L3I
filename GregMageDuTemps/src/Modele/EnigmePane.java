package Modele;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class EnigmePane extends GridPane {
	private SplitPane panneauDivise;
	private AnchorPane panneauGauche;
	private AnchorPane panneauDroit;
	private TextField champsDeTexte;
	private ImageView imagePersonnage;
	private Label dialogue;
	
	public EnigmePane() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/Enigme.fxml"));
		loader.setRoot(this);
        try {
            loader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }	
        initEnfant();
        
		
	}
	
	private void initEnfant() {
		panneauDivise = (SplitPane)this.getChildren().get(0);
		panneauGauche = (AnchorPane) panneauDivise.getItems().get(0);
		panneauDroit = (AnchorPane) panneauDivise.getItems().get(1);
		champsDeTexte = (TextField) this.getChildren().get(1);
		dialogue = (Label)panneauDroit.getChildren().get(0);
		imagePersonnage = (ImageView) panneauGauche.getChildren().get(0);
		
	}
	
	public void changeDialogue(String dialogue) {
		/* fonctionnel */
		this.dialogue.setText(dialogue);
	}
	
	public void changeImage(Image imagePersonnage) {
		/* fonctinnel */
		this.imagePersonnage.setImage(imagePersonnage);
	}
	
	public String getChamps() {
		return champsDeTexte.getText();
	}
	
	public void nettoyerChampsTexte() {
		champsDeTexte.clear();
	}
	
	
}
