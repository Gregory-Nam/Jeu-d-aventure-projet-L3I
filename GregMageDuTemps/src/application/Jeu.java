package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.RootPaneContainer;

import Modele.Interactif;
import Modele.NomSalle;
import Modele.Personnage;
import Modele.PersonnageJoueur;
import Modele.Porte;
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
	private static Salle salleCourante;
	private Stage primaryStage;
	private Scene scene;
	private static Pane root;
	
	private HashMap<NomSalle, Salle> salles;
	
	public Jeu(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		
		salles = new HashMap<NomSalle, Salle>();
		
		initPersonnageJoueurScene();
		initSallesScene();
		initStage();
		initObjetInteractif();

		creationEvenementDeplacement();
	}
	
	private void initPersonnageJoueurScene() {
		File haut = new File("Images/Personnages/wizardNord_transparent.png");
		File droite = new File("Images/Personnages/wizardDroite_transparent.png");
		File bas = new File("Images/Personnages/wizardSud_transparent.png");
		File gauche = new File("Images/Personnages/wizardGauche_transparent.png");
		greg = new PersonnageJoueur(haut, droite, bas, gauche);
	}
	
	private void initSallesScene() {
		Salle salleDepart = new Salle(new File("Images/Salles/Periode_1/Salle_depart.png"), NomSalle.SALLE_DEPART);
		Salle salle1 = new Salle(new File("Images/Salles/Periode_1/Salle_1.png"), NomSalle.SALLE_1);
		Porte p1 = new Porte(salleDepart, salle1, 730, new File("Images/Elements/Porte_ferme_100x110_Transparence.png"), false);
		salle1.ajoutInteractif(p1);
		salles.put(salleDepart.getNomSalle(), salleDepart);
		salles.put(salle1.getNomSalle(), salle1);
		
		salleCourante = salle1;
	}
	
	private void initStage() throws IOException {
		//Panneau qui correspond a la vue "UneFenetre.fxml"
		root = FXMLLoader.load(getClass().getResource("/Vue/UneFenetre.fxml"));
		//On assoscie la scene le panneau cree precedemment
		scene = new Scene(root);
		root.getChildren().addAll(salleCourante.getImageView(),greg.getImageView());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	private void initObjetInteractif() {
		/* i commence a 1 car on a forcement la salle et le personnage deja present dans les enfants du root */
		/* j permet l'acces aux objets de la salle */
		for(int i = 1, j = 0; i < root.getChildren().size(); ++i, ++j) {
			root.getChildren().remove(i);
			root.getChildren().add(salleCourante.getInteractifs().get(j).getImageView());
		}
		/* on veut ajouter le personnage en dernier */
		root.getChildren().add(greg.getImageView());
	}
	
	
	private void creationEvenementDeplacement() {
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
						greg.replacerDroite();
						System.out.println("TEST INTERAGIR AVEC PORTE");
				}
				greg.changerSprite(kc);
			}
		});
	}

	public static void setSalleCourante(Salle nouvelleSalle) {
		salleCourante = nouvelleSalle;
		root.getChildren().remove(0);
		root.getChildren().add(0, salleCourante.getImageView());
	}
		
}
