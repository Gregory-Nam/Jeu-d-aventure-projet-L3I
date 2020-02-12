package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.RootPaneContainer;

import Modele.CompteARebours;
import Modele.Deplacements;
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
		Salle salle2 = new Salle(new File("Images/Salles/Periode_1/Salle_2.png"), NomSalle.SALLE_2);
		Salle salle3 = new Salle(new File("Images/Salles/Periode_1/Salle_3.png"), NomSalle.SALLE_3);
		Salle salleBronze = new Salle(new File("Images/Salles/Periode_1/Salle_bronze.png"), NomSalle.SALLE_BRONZE);
		Salle salleOr = new Salle(new File("Images/Salles/Periode_1/Salle_or.png"), NomSalle.SALLE_OR);
		Salle sallePiege = new Salle(new File("Images/Salles/Periode_1/Salle_piege.png"), NomSalle.SALLE_PIEGE);

		/* PORTES */
		Porte p1 = new Porte(salleDepart, salle1, 940, true);
		Porte p2 = new Porte(salle1, salleArgent,730, false);
		Porte p3 = new Porte(salle1, salle2, 940, true);
		Porte p4 = new Porte(salle2,salle3, 940, true);
		Porte p7 = new Porte(salle3, salleOr,940, true);
		Porte p6 = new Porte(salle2, salleBronze,182, false);
		Porte p5 = new Porte(salle3, sallePiege, 730, false);
		
		salleDepart.ajoutInteractif(p1,greg);
		salle1.ajoutInteractif(p1,p2,p3);
		salle2.ajoutInteractif(p3,p4,p6);
		salle3.ajoutInteractif(p4,p5,p7);
		salleArgent.ajoutInteractif(p2);
		salleOr.ajoutInteractif(p7);
		salleBronze.ajoutInteractif(p6);
		sallePiege.ajoutInteractif(p5);
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
						evenementsHorizontaux(Deplacements.DROITE);
						break;
						
					/* DEPLACEMENT A GAUCHE */
					case LEFT :
						evenementsHorizontaux(Deplacements.GAUCHE);
						break;
						
					/*DEPLACEMENT VERS LE HAUT POUR INTERAGIR AVEC UN OBJET INTERACTIF */
					case UP : 
						greg.changerSprite(Deplacements.HAUT);
						if(greg.getXCentre() <= Salle.getExtremiteGauche() || greg.getXCentre() >= Salle.getExtremiteDroite()) break;
						Interactif objetInteractif = salleCourante.interactifAPosition(greg.getXCentre());
						if(objetInteractif != null && objetInteractif != greg ) {
							objetInteractif.interagir();
							greg.changerSprite(Deplacements.BAS);
						}
						break;
						
					default:
						System.out.println("TEST INTERAGIR AVEC PORTE");
						System.out.println("X centre : " + greg.getXCentre());
				}
			}
		});
	}

	private void evenementsHorizontaux(Deplacements d) {
		/* CHOIX DE L'EXTREMITE SELON LE DEPLACEMENT DU PERSONNAGE */
		double extremite;
		boolean gauche;
		if( d == Deplacements.DROITE ) {
			extremite = Salle.getExtremiteDroite();
			gauche = false;
		}
		else if ( d == Deplacements.GAUCHE ) {
			extremite = Salle.getExtremiteGauche();
			gauche = true;
		}
		else return;
		
		/* DEPLACEMENT NORMAL SI CE N'EST PAS UNE EXTREMITE */
		if(!gauche && greg.getXCentre() < extremite ) {
			greg.seDirigerADroite();
			return;
		}
		else if (gauche && greg.getXCentre() > extremite) {
			greg.seDirigerAGauche();
			return;
		}
		
		/* EXTREMITE ATTEINTE, SEUL OBJET POSSIBLE (NORMALEMENT) EST UNE PORTE */
		Interactif objetExtremite = salleCourante.interactifAPosition(greg.getXCentre());
		if (objetExtremite != null && objetExtremite != greg) {
			objetExtremite.interagir();
			if(gauche) greg.replacerDroite();
			else greg.replacerGauche();
		}
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
		System.out.println(salleCourante.getNomSalle());
	}
	
	public static Salle getSalleCourante() {
		return salleCourante;
	}
}
