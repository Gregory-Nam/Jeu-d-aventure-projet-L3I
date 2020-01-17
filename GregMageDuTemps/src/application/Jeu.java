package application;

import java.io.File;
import java.io.IOException;

import Modele.PersonnageJoueur;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Jeu {
	PersonnageJoueur greg;
	Stage primaryStage;
	Scene scene;
	AnchorPane root;
	
	public Jeu(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		initPersonnageJoueur();
		initStage();
		creationEvenementDeplacement();
		
		
		
	}
	
	public void initPersonnageJoueur() {
		File f = new File("Images/wizardDroite.png");
		greg = new PersonnageJoueur(f);
	}
	
	public void initStage() throws IOException {
		//Panneau qui correspond a la vue "UneFenetre.fxml"
		root = FXMLLoader.load(getClass().getResource("/Vue/UneFenetre.fxml"));
		//On assoscie � la scene le panneau cree precedemment
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		root.getChildren().add(greg.getSpriteCourant());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void creationEvenementDeplacement() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.D)
					greg.seDirigerADroite();
				else
					greg.seDirigerAGauche();
				
			}
			
		});
	}
}
