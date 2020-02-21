package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import elements.Interactif;
import elements.Item;
import elements.PorteExtremite;
import elements.PorteMurale;
import elements.Salle;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;
import utilitaire.CompteARebours;
import enumerations.Deplacements;
import enumerations.NomSalle;
import fenetrePersonnalisee.EnigmePane;

public class Jeu {
	
	private static Jeu instanceUnique = new Jeu();
	
	public static final int X_MAX_FENETRE = 940;
	public static final int X_MIN_FENETRE = 0;
	
	private static PersonnageJoueur greg;
	private static Salle salleCourante;
	private	static Stage primaryStage;
	private static Scene scene;
	private static Scene sceneEnigme;
	private static Scene sceneFinJeu;
	
	private static Pane root;
	private static EnigmePane rootEnigme;
	private static HashMap<NomSalle, Salle> salles;
	
	private Jeu() {}
	
	public void lancerJeu(Stage stage) throws IOException {
		CompteARebours c = new CompteARebours(5, 5);
		
		primaryStage = stage;
		primaryStage.setResizable(false);
		
		salles = new HashMap<NomSalle, Salle>();
		
		initPersonnageJoueurScene();
		creationDesObjetsInteractifs();
		initStage();
		initEnigmeScene();
		initSceneDeFin();
		initObjetInteractif();

		ajoutEvenement();
		ajoutEvenementEnigme();
		
		
		c.lancer();
		primaryStage.titleProperty().bind(c.getTempsTotalEnStringProperty());
	}
	
	private void initPersonnageJoueurScene() {
		File haut = new File("Images/Personnages/wizardNord_transparent.png");
		File droite = new File("Images/Personnages/wizardDroite_transparent.png");
		File bas = new File("Images/Personnages/wizardSud_transparent.png");
		File gauche = new File("Images/Personnages/wizardGauche_transparent.png");
		greg = new PersonnageJoueur(haut, droite, bas, gauche);
	}
		
	private void initStage() throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vues/UneFenetre.fxml"));
		scene = new Scene(root);
		root.getChildren().addAll(salleCourante.getImageView());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void initEnigmeScene() {
		rootEnigme = new EnigmePane();
		sceneEnigme = new Scene(rootEnigme);	
	}

	private void initSceneDeFin() throws IOException {
		sceneFinJeu = new Scene(FXMLLoader.load(getClass().getResource("/vues/EcranDeFin.fxml")));
		sceneFinJeu.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode kc = event.getCode();
				switch(kc){
					case Q :
						System.exit(0);
						break;
					case R :
						try {
							lancerJeu(primaryStage);
						} catch (IOException e) {
							e.printStackTrace();
						}
					break;
					default: 
						break;
				}
				
			}
		});
	}
	
	private static void initObjetInteractif() {
		/* SUPPRESSION DE TOUTES LES IMAGEVIEW DES OBJETS INTERACTIFS */
		for(int i = 1; i < root.getChildren().size();)
			root.getChildren().remove(i);
		
		/* AJOUT DES OBJETS INTERACTIFS DE LA SALLE COURANTE */
		for(Interactif i : salleCourante.getInteractifs()) {
			/* CONDITION POUR LE CAS DES PORTES EXTREMITE QUI N'ONT PAS D'IMAGEVIEW */
			if(i.getImageView() == null) continue;
			root.getChildren().add(i.getImageView());
		}
		ajoutItemScene();
	}
	
	private void initPNJ() {
		File haut = new File("Images/PNJ/Klace_face_transparence.png");
		File droite = new File("Images/PNJ/Klace_droite_transparence.png");
		File bas = new File("Images/PNJ/Klace_face_transparence.png");
		File gauche = new File("Images/PNJ/Klace_gauche_transparence.png");
		File imagePourenigme = new File("Images/PNJ/Klace_face.png");
		Item itemBronze = new Item(new File("Images/items/aiguille_Bronze1.png"), 230);
		PersonnageNonJoueur pnj = new PersonnageNonJoueur(200, itemBronze, imagePourenigme, haut, droite, bas, gauche);
		salles.get(NomSalle.SALLE_1).ajoutInteractif(pnj);

	
	}
	
	private void creationDesObjetsInteractifs() {
		
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
		PorteExtremite p1 = new PorteExtremite(salleDepart, salle1);
		PorteMurale p2 = new PorteMurale(salle1, salleArgent,730);
		PorteExtremite p3 = new PorteExtremite(salle1, salle2);
		PorteExtremite p4 = new PorteExtremite(salle2,salle3);
		PorteExtremite p7 = new PorteExtremite(salle3, salleOr);
		PorteMurale p6 = new PorteMurale(salle2, salleBronze,182);
		PorteMurale p5 = new PorteMurale(salle3, sallePiege, 730);

		/* REMPLISSAGE DE LA HASHMAP */
		salles.put(salleDepart.getNomSalle(), salleDepart);
		salles.put(salle1.getNomSalle(), salle1);
		salles.put(salleArgent.getNomSalle(), salleArgent);
		salles.put(salleOr.getNomSalle(), salleOr);
		salles.put(salleBronze.getNomSalle(), salleBronze);
		salles.put(salle2.getNomSalle(), salle2);
		salles.put(salle3.getNomSalle(), salle3);
		salles.put(sallePiege.getNomSalle(), sallePiege);
	
		salleDepart.ajoutInteractif(p1, greg);
		salle1.ajoutInteractif(p1,p2,p3);
		salle2.ajoutInteractif(p3,p4,p6);
		salle3.ajoutInteractif(p4,p5,p7);
		salleArgent.ajoutInteractif(p2);
		salleOr.ajoutInteractif(p7);
		salleBronze.ajoutInteractif(p6);
		sallePiege.ajoutInteractif(p5); 

		initPNJ();

		salleCourante = salleDepart;
	}

	private void ajoutEvenement() {
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
						if(greg.getXCentre() <= X_MIN_FENETRE|| greg.getXCentre() >= X_MAX_FENETRE) break;
						Interactif objetInteractif = salleCourante.interactifAPosition(greg.getXCentre());

						if(objetInteractif != null && objetInteractif != greg ) {
							objetInteractif.interagir();
							greg.changerSprite(Deplacements.BAS);
						}
						break;
					case E :
						if(!salleCourante.aDesItems()) break;
						Item itemAPrendre = null;
						for(Item i : salleCourante.getItems())
						{
							if(greg.getXMin() < i.getXCentre() && i.getXCentre() < greg.getXMax()) {
								itemAPrendre = i;
								break;
							}
								
						}
						if(!itemAPrendre.equals(null)) {
							greg.prendreItem(itemAPrendre);
							supprimeItemScene(itemAPrendre);
							salleCourante.supprimerItem(itemAPrendre);
						}
						break;
					case Q :
						terminer();
						break;
					default :
						break;
				}
			}
		});
	}

	private void evenementsHorizontaux(Deplacements d) {
		/* CHOIX DE L'EXTREMITE SELON LE DEPLACEMENT DU PERSONNAGE */
		double extremite;
		boolean gauche;
		if( d == Deplacements.DROITE ) {
			extremite = X_MAX_FENETRE;
			gauche = false;
		}
		else if ( d == Deplacements.GAUCHE ) {
			extremite = X_MIN_FENETRE;
			gauche = true;
		}
		else return;
		
		/* DEPLACEMENT NORMAL SI CE N'EST PAS UNE EXTREMITE */
		if(!gauche && greg.getXMin() < extremite ) {
			greg.seDirigerADroite();
			return;
		}
		else if (gauche && greg.getXMin() > extremite) {
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
	
	@SuppressWarnings("incomplete-switch")
	private void ajoutEvenementEnigme() {
		sceneEnigme.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode kc = event.getCode();
				switch(kc) {
					case ESCAPE :
						primaryStage.setScene(scene);
						break;
				}
			}
		});
	}
		
	public static void lancerEnigme(PersonnageNonJoueur pnj) {
		primaryStage.setScene(sceneEnigme);
		greg.liaisonDialogueAvecPNJ(pnj);
		rootEnigme.changeImage(pnj.getImageView().getImage());

		/* INITIALISATION DU DIALOGUE DE DEPART */
		if(pnj.getEtatReponseAttendu().get())
			rootEnigme.changeDialogue(pnj.ditQueTuAsDejaRepondu());
		else
			rootEnigme.changeDialogue(pnj.poseQuestion());
		
		/* EVENEMENT SUR L'INPUT */
		rootEnigme.mettreEnActionChampsTextuel(() -> {
			rootEnigme.activerEntree();
			/* CAS OU LA BONNE REPONSE A DEJA ETE DONNEE */
			if(pnj.getEtatReponseAttendu().get()) 
				rootEnigme.changeDialogue(pnj.ditQueTuAsDejaRepondu());
			/* CAS OU LE JOUEUR DONNE LA BONNE REPONSE */
			else if(rootEnigme.getChamps().equals(pnj.reponse())) {
				pnj.aRecuUneBonneReponse();
				rootEnigme.changeDialogue(pnj.repondAUneBonneReponse());
				salleCourante.ajoutItem(pnj.donnerItem());
				ajoutItemScene();
				
			}
			/* CAS OU LE JOUEUR DONNE UNE MAUVAISE REPONSE */
			else {
				rootEnigme.changeDialogue(pnj.repondAUneMauvaiseReponse());
				rootEnigme.desactiverEntree();
				greg.replacerGauche();
				setSalleCourante(salles.get(NomSalle.SALLE_DEPART));
			}
			
			rootEnigme.nettoyerChampsTexte();
		});
		
	}
	
	private static void ajoutItemScene() {
		ArrayList<Item> items = salleCourante.getItems();
		for(int i = 0; i < items.size(); ++i) {
			root.getChildren().add(items.get(i).getImageView());
		}
	}
	
	private static void supprimeItemScene(Item i) {
			root.getChildren().remove(i.getImageView());
	}
	
	public static void terminer() {
		primaryStage.setScene(sceneFinJeu);
	}
	
	public static Salle getSalleCourante() {
		return salleCourante;
	}
	
	public static void setSalleCourante(Salle nouvelleSalle) {
		salleCourante.supprimerInteractif(greg);
		
		for(Item i : salleCourante.getItems())
			supprimeItemScene(i);
		
		salleCourante = nouvelleSalle;
		salleCourante.ajoutInteractif(greg);
		/* REMPLACEMENT DE L'ANCIENNE IMAGE DE LA SALLE AVEC LA NOUVELLE */
		root.getChildren().remove(0);
		root.getChildren().set(0, salleCourante.getImageView());
		/* MISE A JOUR DES OBJETS INTERACTIFS */
		initObjetInteractif();
	}

	public static Jeu getInstanceUnique() {
		return instanceUnique;
	}
}
