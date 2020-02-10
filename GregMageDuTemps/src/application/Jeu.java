package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.RootPaneContainer;

import Modele.CompteARebours;
import Modele.Interactif;
import Modele.NomSalle;
import Modele.Personnage;
import Modele.PersonnageJoueur;
import Modele.Porte;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.*;
import Modele.Salle;

public class Jeu {
	
	private static PersonnageJoueur greg;
	private static Salle salleCourante;
	private Stage primaryStage;
	private Scene scene;
	private static Pane root;
	
	private HashMap<NomSalle, Salle> salles;
	
	public Jeu(Stage primaryStage) throws IOException {
		CompteARebours c = new CompteARebours(10, 2);
		
		this.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		
		salles = new HashMap<NomSalle, Salle>();
		
		initPersonnageJoueurScene();
		initSallesScene();
		initStage();
		initObjetInteractif();
		creationEvenementDeplacement();
		
		
		c.lancer();
		primaryStage.titleProperty().bind(c.getStringPropery());
	}
	
	private void initPersonnageJoueurScene() {
		File haut = new File("Images/Personnages/wizardNord_transparent.png");
		File droite = new File("Images/Personnages/wizardDroite_transparent.png");
		File bas = new File("Images/Personnages/wizardSud_transparent.png");
		File gauche = new File("Images/Personnages/wizardGauche_transparent.png");
		greg = new PersonnageJoueur(haut, droite, bas, gauche);
	}
	
	private void initSallesScene() {
		/* SALLES */
		Salle salleDepart = new Salle(new File("Images/Salles/Periode_1/Salle_depart.png"), NomSalle.SALLE_DEPART);
		Salle salle1 = new Salle(new File("Images/Salles/Periode_1/Salle_1.png"), NomSalle.SALLE_1);
		Salle salleArgent = new Salle(new File("Images/Salles/Periode_1/Salle_Argent.png"), NomSalle.SALLE_ARGENT);
		
		/* PORTES */
		Porte p1 = new Porte(salleDepart, salle1, 940, true);
		Porte p2 = new Porte(salle1, salleArgent,730, false);
		
		salleDepart.ajoutInteractif(p1);
		salleDepart.ajoutInteractif(greg);
		salle1.ajoutInteractif(p1);
		salle1.ajoutInteractif(p2);
		salleArgent.ajoutInteractif(p2);
		
		/* REMPLISSAGE DE LA HASHMAP */
		salles.put(salleDepart.getNomSalle(), salleDepart);
		salles.put(salle1.getNomSalle(), salle1);
		salles.put(salleArgent.getNomSalle(), salleArgent);
		
		salleCourante = salleDepart;
	}
	
	private void initStage() throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/Vue/UneFenetre.fxml"));
		
		scene = new Scene(root);
		
		root.getChildren().addAll(salleCourante.getImageView());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	private static void initObjetInteractif() {
		/* SUPPRESSION DE TOUTES LES IMAGEVIEW DES OBJETS INTERACTIFS */
		for(int i = 1; i < root.getChildren().size(); ++i) {
			root.getChildren().remove(i);
		}
		/* AJOUT DES OBJETS INTERACTIFS DE LA SALLE COURANTE */
		for(Interactif i : salleCourante.getInteractifs()) {
			/* CONDITION POUR LE CAS DES PORTES EXTREMITE QUI N'ONT PAS D'IMAGEVIEW */
			if(i.getImageView() == null) continue;
			root.getChildren().add(i.getImageView());
		}
	}
	
	private void creationEvenementDeplacement() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode kc = event.getCode();
				switch(kc) {
				
					/* DEPLACEMENT A DROITE */
					case RIGHT:
						/* DEPLACEMENT NORMAL SI CE N'EST PAS UNE EXTREMITE */
						if(greg.getXCentre() < 950) {
							greg.seDirigerADroite();
							break;
						}
						/* EXTREMITE ATTEINTE, SEUL OBJET POSSIBLE (NORMALEMENT) EST UNE PORTE */
						Interactif objetExtremiteDroite = salleCourante.interactifAPosition(greg.getXCentre());
						if (objetExtremiteDroite != null && objetExtremiteDroite != greg) {
							objetExtremiteDroite.interagir();
							greg.replacerGauche();
						}
						break;
						
					/* DEPLACEMENT A GAUCHE */
					case LEFT :
						/* DEPLACEMENT NORMAL SI CE N'EST PAS UNE EXTREMITE */
						if(greg.getXCentre() > 50) {
							greg.seDirigerAGauche();
							break;
						}
						/* EXTREMITE ATTEINTE, SEUL OBJET POSSIBLE (NORMALEMENT) EST UNE PORTE */
						Interactif objetExtremiteGauche = salleCourante.interactifAPosition(greg.getXCentre());
						if (objetExtremiteGauche != null && objetExtremiteGauche != greg) {
							objetExtremiteGauche.interagir();
							greg.replacerDroite();
						}
						break;
					/*DEPLACEMENT VERS LE HAUT POUR INTERAGIR AVEC UN OBJET INTERACTIF */
					case UP : 
						Interactif objetInteractif = salleCourante.interactifAPosition(greg.getXCentre());
						if(objetInteractif != null && objetInteractif != greg 
						   && greg.getXCentre() >= objetInteractif.getXMin() 
						   && greg.getXCentre() <= objetInteractif.getXMax()) {
							objetInteractif.interagir();
						}
						break;
					default:
						System.out.println("TEST INTERAGIR AVEC PORTE");
						System.out.println("X centre : " + greg.getXCentre());
				}
				/* CHANGEMENT DE L'IMAGE DU PERSONNAGE JOUEUR SELON LE DEPLACEMENT */
				greg.changerSprite(kc);
			}
		});
	}

	public static void setSalleCourante(Salle nouvelleSalle) {
		salleCourante.supprimerInteractif(greg);
		salleCourante = nouvelleSalle;
		salleCourante.ajoutInteractif(greg);
		/* REMPLACEMENT DE L'ANCIENNE IMAGE DE LA SALLE AVEC LA NOUVELLE */
		root.getChildren().remove(0);
		root.getChildren().set(0, salleCourante.getImageView());
		/* MISE A JOUR DES OBJETS INTERACTIFS */
		initObjetInteractif();
	}
	
	public static Salle getSalleCourante() {
		return salleCourante;
	}
}
