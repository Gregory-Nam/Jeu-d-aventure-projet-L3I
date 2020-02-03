package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Modele.NomSalle;
import Modele.Personnage;
import Modele.PersonnageJoueur;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.Border;
import javafx.scene.image.*;
import Modele.Salle;

public class Jeu {
	private PersonnageJoueur greg;
	private Stage primaryStage;
	private Scene scene;
	private Pane root;
	
	private HashMap<NomSalle, Salle> salles;
	
	public Jeu(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		
		salles = new HashMap<NomSalle, Salle>();
		
		initPersonnageJoueurScene();
		initSallesScene();
		initStage();
		creationEvenementDeplacement();
	}
	
	public void initPersonnageJoueurScene() {
		File haut = new File("Images/Personnages/wizardNord_transparent.png");
		File droite = new File("Images/Personnages/wizardDroite_transparent.png");
		File bas = new File("Images/Personnages/wizardSud_transparent.png");
		File gauche = new File("Images/Personnages/wizardGauche_transparent.png");
		greg = new PersonnageJoueur(haut, droite, bas, gauche);
	}
	
	public void initSallesScene() {
		//initilisation de l'ensemble des salles dans la HashMap
		Salle salleDepart = new Salle(new File("Images/Salles/Periode_1/Salle_depart.png"), NomSalle.SALLE_DEPART);
		
		salles.put(salleDepart.getNomSalle(), salleDepart);
		
	}
	
	public void initStage() throws IOException {
		//Panneau qui correspond a la vue "UneFenetre.fxml"
		root = FXMLLoader.load(getClass().getResource("/Vue/UneFenetre.fxml"));
		//On assoscie la scene le panneau cree precedemment
		scene = new Scene(root);
		root.getChildren().addAll(salles.get(NomSalle.SALLE_DEPART).getSprite(),greg.getSpriteCourant());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void creationEvenementDeplacement() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode kc = event.getCode();
				if(greg.rencontreMur(kc)) return;
				switch(kc) {
					case RIGHT:
						greg.seDirigerADroite();
						break;
					case LEFT :
						greg.seDirigerAGauche();
						break;
					default:
						System.out.println("A TRAITER");
				}
				greg.changerSprite(kc);
			}
			
		});
	}
}
