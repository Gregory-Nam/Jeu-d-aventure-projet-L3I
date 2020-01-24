package application;

import java.io.File;
import java.io.IOException;

import Modele.Personnage;
import Modele.PersonnageJoueur;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.*;

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
		File haut = new File("Images/wizardDroite.png");
		File droite = new File("Images/wizardDroite_transparent.png");
		File bas = new File("Images/wizardDroite.png");
		File gauche = new File("Images/wizardGauche_transparent.png");
		greg = new PersonnageJoueur(haut, droite, bas, gauche);
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
				KeyCode kc = event.getCode();
				System.out.println(kc);
				greg.setSprite(kc);
				if(kc == KeyCode.RIGHT) {
					greg.seDirigerADroite();
				}
				else if(kc == KeyCode.LEFT) {
					greg.seDirigerAGauche();
				}
			}
			
		});
	}
}
