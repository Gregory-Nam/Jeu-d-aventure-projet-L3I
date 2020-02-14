package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurPersonnageJoueur extends ImageView implements Initializable  {

	private Stage stage;
	
	@FXML
	private ImageView personnageJoueur;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void creationListener() {
		EventHandler<ActionEvent> deplacerDroite = event -> {
			
		};
	}

}
