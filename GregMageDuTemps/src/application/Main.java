package application;
	
import java.io.File;

import Modele.PersonnageJoueur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Panneau qui correspond a la vue "UneFenetre.fxml"
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Vue/UneFenetre.fxml"));
			//On assoscie à la scene le panneau cree precedemment
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// ****** a decomposer *****//
			File f = new File("Images/wizardDroite.png");
			PersonnageJoueur greg = new PersonnageJoueur(f);
			root.getChildren().add(greg.getSpriteCourant());
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
