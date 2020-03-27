package application;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import controleurs.EnigmeControleur;
import controleurs.FinControleur;
import controleurs.InventaireControleur;
import controleurs.MenuControleur;
import elements.Horloge;
import elements.HorlogePiege;
import elements.Interactif;
import elements.Item;
import elements.PorteExtremite;
import elements.PorteMurale;
import elements.Salle;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;
import utilitaire.CompteARebours;
import utilitaire.FinisseurDeJeu;
import enumerations.Deplacements;
import enumerations.Materiaux;
import enumerations.NomPNJ;
import enumerations.NomSalle;
import enumerations.Periode;

/**
 * Implémentation du Jeu.
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE.
 */
public class Jeu {
	
	/**
	 * Instance unique du Jeu.
	 */
	private static Jeu instanceUnique = new Jeu();
	
	/**
	 * La largeur maximale de la fenêtre.
	 */
	public static final int X_MAX_FENETRE = 1000;
	
	/*
	 * La largeur minimale de la fenêtre.
	 */
	public static final int X_MIN_FENETRE = 0;
	
	/**
	 * Le PersonnageJoueur.
	 */
	private PersonnageJoueur greg;
	
	/**
	 * La salle courante.
	 */
	private Salle salleCourante;
	
	/**
	 * Le stage principale.
	 */
	private static Stage primaryStage;
	
	/**
	 * La scene du jeu.
	 */
	private Scene scene;
	
	/**
	 * La scene de l'inventaire.
	 */
	private Scene sceneInventaire;
	
	/**
	 * La scène du menu.
	 */
	private Scene sceneMenu;
	
	/**
	 * La scène de l'énigme.
	 */
	private Scene sceneEnigme;
	
	/**
	 * La scène de la fin du jeu.
	 */
	private Scene sceneFinJeu;
	
	/**
	 * La racine du jeu.
	 */
	private Pane root;
	
	/**
	 * Controleur de l'énigme.
	 */
	private EnigmeControleur rootEnigme;
	
	/**
	 * Controleur de l'inventaire.
	 */
	private InventaireControleur rootInventaire;
	
	/**
	 * Controleur du menu.
	 */
	private MenuControleur rootMenu;
	
	/**
	 * Controleur de la fin.
	 */
	private FinControleur rootFin;
	
	/**
	 * Label des messages qui seront affichés.
	 */
	private Label message;
	
	/**
	 * HashMap de salles par nom de salle.
	 */
	private HashMap<NomSalle, Salle> salles;
	
	/**
	 * HashMap de PNJ par nom de PNJ.
	 */
	private HashMap<NomPNJ, PersonnageNonJoueur> pnj;
	
	/**
	 * Période du Jeu.
	 */
	private Periode periodeCourante;
	
	private Jeu() {}
	
	/**
	 * Permet de lancer le menu du jeu.
	 * @param stage le stage principale (la fenêtre)
	 */
	public void commencer(Stage stage) {
		primaryStage = stage;
		primaryStage.setResizable(false);
		initSceneMenu();
		afficheMenu();
		
		primaryStage.show();
	}
	
	/**
	 * Permet de lancer le jeu
	 * @throws IOException
	 */
	public void lancerJeu() throws IOException {
		CompteARebours c = new CompteARebours(10, 0);
		this.periodeCourante = Periode.PERIODE_1;
		
		
		salles = new HashMap<NomSalle, Salle>();
		
		initPersonnageJoueurScene();
		initialisationDesElements();
		initStage();	
		initEnigmeScene();
		initInventaireScene();
		initSceneDeFin();
		initObjetInteractif();

		ajoutEvenement();
		ajoutEvenementEnigme();
		
		
		c.lancer();
		primaryStage.titleProperty().bind(c.getTempsTotalEnStringProperty());
		rootInventaire.creerListener(greg.getInventaire());
		afficheMessage(periodeCourante.toString().replace('_', ' '),2);
		
	}
	
	/**
	 * Permet d'initialiser le PersonnageJoueur.
	 */
	private void initPersonnageJoueurScene() {
		greg = PersonnageJoueur.getInstanceUnique();
	}
	
	/**
	 * Permet d'initialiser le stage.
	 * @throws IOException IOException si le fichier FXML est introuvable.
	 */
	private void initStage() throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vues/LeJeu.fxml"));
		scene = new Scene(root);
		message = (Label)root.getChildren().get(0);
		root.getChildren().add(0, salleCourante.getImageView());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Permet d'initialiser la scène de l'énigme.
	 */
	private void initEnigmeScene() {
		rootEnigme = new EnigmeControleur();
		sceneEnigme = new Scene(rootEnigme);
		
	}
	
	/**
	 * Permet d'initialiser la scène de l'inventaire.
	 */
	private void initInventaireScene() {
		rootInventaire = new InventaireControleur();
		sceneInventaire = new Scene(rootInventaire);
	}

	/**
	 * Permet d'initialiser la scène de fin.
	 * @throws IOException IOException si le fichier FXML n'existe pas.
	 */
	private void initSceneDeFin() throws IOException {
		rootFin = new FinControleur();
		sceneFinJeu = new Scene(rootFin);
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
							greg.reinitialiser();
							Jeu.getInstanceUnique().lancerJeu();
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
	
	/**
	 * Permet d'initialiser la scène du menu.
	 */
	private void initSceneMenu() {
		rootMenu = new MenuControleur();
		sceneMenu = new Scene(rootMenu);
	}
	
	/**
	 * Permet l'initialisation ou la mise à jour des Interactif
	 * dans la vue du jeu.
	 */
	public void initObjetInteractif() {
		/* SUPPRESSION DE TOUTES LES IMAGEVIEW DES OBJETS INTERACTIFS */
		for(int i = 2; i < root.getChildren().size();) {
			root.getChildren().remove(i);
		}
		
		/* AJOUT DES OBJETS INTERACTIFS DE LA SALLE COURANTE */
		for(Interactif i : salleCourante.getInteractifs()) {
			/* CONDITION POUR LE CAS DES PORTES EXTREMITE QUI N'ONT PAS D'IMAGEVIEW */
			if(i.getImageView() == null) continue;
			root.getChildren().add(i.getImageView());
		}
	}
	
	/**
	 * Permet d'initialiser les PNJ et les items sur la première periode.
	 */
	private void initPnjItemPeriode1() {
		pnj = new HashMap<>();
		Item itemBronze = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
							       new File("Images/items/aiguille_bronze.png"),
							       Materiaux.BRONZE, 634, "Aiguille");
		Item itemArgent = new Item(new File("Images/items/aiguille_argent_transparence.png"),
								   new File("Images/items/aiguille_argent.png"),
								   Materiaux.ARGENT, 669, "Aiguille");
		
		Item itemOr1 = new Item(new File("Images/items/aiguille_or_transparence.png"),
				   new File("Images/items/aiguille_or.png"),
				   Materiaux.OR, 641, "Aiguille");
		
		
		File bas = new File("Images/PNJ/Klace_face_transparence.png");
		File imagePourenigme = new File("Images/PNJ/Klace_face.png");
		PersonnageNonJoueur pnjBronze = new PersonnageNonJoueur(NomPNJ.KLACE_HEUREOUVERRE, 564, itemBronze, imagePourenigme, bas);
		
		bas = new File("Images/PNJ/Slyne_face_transparence.png");
		imagePourenigme = new File("Images/PNJ/Slyne_face.png");
		PersonnageNonJoueur pnjArgent = new PersonnageNonJoueur(NomPNJ.SLYNE, 599, itemArgent, imagePourenigme,bas);
		
		bas = new File("Images/PNJ/Carpenter_face_transparence.png");
		imagePourenigme = new File("Images/PNJ/Carpenter_face.png");
		
		PersonnageNonJoueur pnjOr1 = new PersonnageNonJoueur(NomPNJ.CARPENTER, 571, itemOr1, imagePourenigme, bas);
		
		salles.get(NomSalle.SALLE_2).ajoutInteractif(pnjArgent);
		salles.get(NomSalle.SALLE_3).ajoutInteractif(pnjBronze);
		salles.get(NomSalle.SALLE_1).ajoutInteractif(pnjOr1);
		pnj.put(pnjBronze.getNom(), pnjBronze);
		pnj.put(pnjArgent.getNom(), pnjArgent);
		pnj.put(pnjOr1.getNom(), pnjOr1);
	}
	
	/**
	 * Permet d'initialiser les PNJ et les items sur la troisième période.
	 */
	private void initPnjItemPeriode3() {
		Item itemOr2 = new Item(new File("Images/items/Pendule_or_transparence.png"),
								new File("Images/items/Pendule_or.png"),
								Materiaux.OR, 218, "Pendule");
		
		File bas = new File("Images/PNJ/Abitbol_face_transparence.png");
		File imagePourenigme = new File("Images/PNJ/Abitbol_face.png");
		PersonnageNonJoueur pnjOr2 = new PersonnageNonJoueur(NomPNJ.ABITBOL, 148, itemOr2, imagePourenigme, bas);
		
		bas = new File("Images/PNJ/Zavier_face_transparence.png");
		imagePourenigme = new File("Images/PNJ/Zavier_face.png");
		PersonnageNonJoueur pnjPiege = new PersonnageNonJoueur(NomPNJ.ZAVIER_MAIS, 484, null, imagePourenigme, bas);

		salles.get(NomSalle.SALLE_PIEGE).ajoutInteractif(pnjOr2,pnjPiege);
		pnj.put(pnjOr2.getNom(), pnjOr2);
		pnj.put(pnjPiege.getNom(), pnjPiege);
	}

	/**
	 * Permet de créer certains éléments (portes, salles, horloges)
	 * et d'initialiser les HashMap.
	 */
	private void initialisationDesElements() {
		
		/* SALLES */
		Salle salleDepart = new Salle(new File("Images/salles/Periode_1/Salle_depart.png"), NomSalle.SALLE_DEPART);
		System.out.println(salleDepart.getImageView());
		Salle salle1 = new Salle(new File("Images/salles/Periode_1/Salle_1.png"), NomSalle.SALLE_1);
		Salle salleArgent = new Salle(new File("Images/salles/Periode_1/Salle_Argent.png"), NomSalle.SALLE_ARGENT);
		Salle salle2 = new Salle(new File("Images/salles/Periode_1/Salle_2.png"), NomSalle.SALLE_2);
		Salle salle3 = new Salle(new File("Images/salles/Periode_1/Salle_3.png"), NomSalle.SALLE_3);
		Salle salleBronze = new Salle(new File("Images/salles/Periode_1/Salle_bronze.png"), NomSalle.SALLE_BRONZE);
		Salle salleOr = new Salle(new File("Images/salles/Periode_1/Salle_or.png"), NomSalle.SALLE_OR);
		Salle sallePiege = new Salle(new File("Images/salles/Periode_1/Salle_piege.png"), NomSalle.SALLE_PIEGE);

		
		/* PORTES */
		PorteExtremite p1 = new PorteExtremite(salleDepart, salle1);
		PorteMurale p2 = new PorteMurale(salle1, salleArgent,730);
		PorteExtremite p3 = new PorteExtremite(salle1, salle2);
		PorteExtremite p4 = new PorteExtremite(salle2,salle3);
		PorteExtremite p7 = new PorteExtremite(salle3, salleOr);
		PorteMurale p6 = new PorteMurale(salle2, salleBronze,182);
		PorteMurale p5 = new PorteMurale(salle3, sallePiege, 730);
		
		/* HORLOGES */
		Horloge horlogeBronze = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 889);
		Horloge horlogeArgent = new Horloge(new File("Images/Horloges/Horloge_argent_transparence.png"), Materiaux.ARGENT, 1, Periode.PERIODE_3, 92);
		Horloge horlogeOr = new Horloge(new File("Images/Horloges/Horloge_or_transparence.png"), Materiaux.OR, 2, Periode.PERIODE_OBJECTIF, 853);
		HorlogePiege horlogePiege = new HorlogePiege(new File("Images/Horloges/Horloge_piege_transparence.png"), Materiaux.PLAQUE_OR, Periode.PERIODE_OBJECTIF, 86);

		/* REMPLISSAGE DE LA HASHMAP */
		salles.put(salleDepart.getNomSalle(), salleDepart);
		salles.put(salle1.getNomSalle(), salle1);
		salles.put(salleArgent.getNomSalle(), salleArgent);
		salles.put(salleOr.getNomSalle(), salleOr);
		salles.put(salleBronze.getNomSalle(), salleBronze);
		salles.put(salle2.getNomSalle(), salle2);
		salles.put(salle3.getNomSalle(), salle3);
		salles.put(sallePiege.getNomSalle(), sallePiege);
	
		/* AJOUT DES INTERACTIFS DANS LES SALLES */
		salleDepart.ajoutInteractif(p1, greg);
		salle1.ajoutInteractif(p1, p2,p3);
		salle2.ajoutInteractif(p3,p4,p6);
		salle3.ajoutInteractif(p4,p5,p7);
		salleArgent.ajoutInteractif(p2,horlogeArgent);
		salleOr.ajoutInteractif(p7, horlogeOr);
		salleBronze.ajoutInteractif(p6,horlogeBronze);
		sallePiege.ajoutInteractif(p5, horlogePiege); 

		/* INITIALISATION DES PNJ */	
		initPnjItemPeriode1();

		salleCourante = salleDepart;
	}
	
	/**
	 * Permet de gérer les evenements de la vue du Jeu.
	 */
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
						if(greg.getXMin() <= X_MIN_FENETRE || greg.getXMax() >= X_MAX_FENETRE) break;
						Interactif objetInteractif = salleCourante.interactifAPosition(greg.getXMin(), greg.getXMax());

						if(objetInteractif != null)
							objetInteractif.interagir();
						break;
					/* OUVERTURE DE L'INVENTAIRE */
					case I :
						afficheInventaire();
						break;
					/* MENU */
					case ESCAPE :
						primaryStage.setScene(sceneMenu);
						break;
					/* FINIR JEU */
					case P :
						try {
							FinisseurDeJeu.finirJeu(scene, sceneEnigme, sceneInventaire,rootEnigme, pnj, salles);
						}
						catch(Exception e) {
							System.exit(0);
						}
						break;
					default:
						break;
				}
			}
		});
	}

	/**
	 * Gestion des déplacements horizontaux du PersonnageJoueur.
	 * @param d déplacement du PersonnageJoueur.
	 */
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
		if(!gauche && greg.getXMax() < extremite ) {
			greg.seDirigerADroite();
			return;
		}
		else if (gauche && greg.getXMin() > extremite) {
			greg.seDirigerAGauche();
			return;
		}
		
		/* EXTREMITE ATTEINTE, SEUL OBJET POSSIBLE (NORMALEMENT) EST UNE PORTE */
		Interactif objetExtremite = salleCourante.interactifAPosition(greg.getXMin(),greg.getXMax());
		if (objetExtremite != null) {
			objetExtremite.interagir();
			if(objetExtremite.equals(greg)) return;
			if(gauche) greg.replacerDroite();
			else greg.replacerGauche();
		}
	}
	
	/**
	 * Permet la gestion des evenements sur la vue de l'énigme.
	 */
	private void ajoutEvenementEnigme() {
		sceneEnigme.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode kc = event.getCode();
				switch(kc) {
					case ESCAPE :
						primaryStage.setScene(scene);
						break;
					default:
						rootEnigme.activerFocus();
						break;
				}
			}
		});
	}
	

	/**
	 * Permet de lancer l'énigme du PNJ passé en paramètre.
	 * @param pnj pnj dont on souhaite lancer l'énigme.
	 */
	public void lancerEnigme(PersonnageNonJoueur pnj) {
		primaryStage.setScene(sceneEnigme);
		greg.liaisonDialogueAvecPNJ(pnj);
		rootEnigme.changeImage(pnj.getImageView().getImage());
		rootEnigme.activerEntree();

		/* INITIALISATION DU DIALOGUE DE DEPART */
		if(pnj.getEtatReponseAttendu().get()) {
			rootEnigme.changeDialogue(pnj.ditQueTuAsDejaRepondu());
			rootEnigme.desactiverEntree();
		}
		else
			rootEnigme.changeDialogue(pnj.poseQuestion());
		
		/* EVENEMENT SUR L'INPUT */
		rootEnigme.mettreEnActionChampsTextuel(() -> {
			/* CAS OU LA BONNE REPONSE A DEJA ETE DONNEE */
			if(pnj.getEtatReponseAttendu().get()) {
				rootEnigme.changeDialogue(pnj.ditQueTuAsDejaRepondu());
				rootEnigme.desactiverEntree();
			}
			/* CAS OU LE JOUEUR DONNE LA BONNE REPONSE */
			// expression reguliere : reponse du pnj précédé d'un espace ou rien et suivi d'un espace, d'un point, d'une virgule, d'un point d'exclamation ou rien
			else if(Pattern.compile("(^|[ ])"+ pnj.reponse() + "($|[.,! ])").matcher(rootEnigme.getTexteDuChamps().toLowerCase()).find()) {
				/* La condition */
				rootEnigme.desactiverEntree();
				pnj.aRecuUneBonneReponse();
				rootEnigme.changeDialogue(pnj.repondAUneBonneReponse());
				salleCourante.supprimerInteractif(greg);
				/* CAS PNJ PIEGE */
				if(pnj.donnerItem() == null) return;
				salleCourante.ajoutInteractif(pnj.donnerItem());
				salleCourante.ajoutInteractif(greg);
				initObjetInteractif();
				
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
	
	/**
	 * Permet d'afficher l'inventaire et de gérer les evenements sur la vue.
	 */
	public void afficheInventaire() {
		primaryStage.setScene(sceneInventaire);
		
		sceneInventaire.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode kc = event.getCode();
				switch(kc) {
				case I :
				case ESCAPE :
					primaryStage.setScene(scene);
					Item itemSelectionne = rootInventaire.getItemSelectionne();
					if( itemSelectionne != null) greg.prendreItemEnMain(itemSelectionne);
					break;
				default:
					break;
				}
			}
		});
	}
	
	/**
	 * Permet d'afficher le menu.
	 */
	public void afficheMenu() {
		primaryStage.setScene(sceneMenu);
	}
	
	/**
	 * Permet d'arreter le jeu.
	 * @param message message de fin.
	 * @param aGagne vrai si le joueur a gagné, faux sinon.
	 */
	public void terminer(String message, boolean aGagne) {
		rootFin.setRaisonDeLaFin(message, aGagne);
		primaryStage.setScene(sceneFinJeu);
		
	}
	
	/**
	 * Permet de reprendre la partie la où on était après avoir affiché le menu.
	 */
	public void reprendre() {
		primaryStage.setScene(scene);
	}
	
	/**
	 * Permet de changer de période.
	 */
	public void changerDePeriode()  {
		PersonnageNonJoueur pnjMort;
		periodeCourante = periodeCourante.suivante();
		ColorAdjust colorAdjust = new ColorAdjust();
		if(periodeCourante.equals(Periode.PERIODE_2)) {
			colorAdjust.setSaturation(-0.5);
			root.setEffect(colorAdjust);
			pnjMort = pnj.get(NomPNJ.KLACE_HEUREOUVERRE);
			pnj.remove(NomPNJ.KLACE_HEUREOUVERRE);
			salles.get(NomSalle.SALLE_3).supprimerInteractif(pnjMort);
		}
		else if(periodeCourante.equals(Periode.PERIODE_3)) {
			initPnjItemPeriode3();
			pnjMort = pnj.get(NomPNJ.SLYNE);
			pnj.remove(NomPNJ.SLYNE);
			salles.get(NomSalle.SALLE_2).supprimerInteractif(pnjMort);
			
			colorAdjust.setBrightness(-0.2);
			colorAdjust.setContrast(0.1);
			Effect sepia = new SepiaTone(0.5);
			colorAdjust.setInput(sepia);
			root.setEffect(colorAdjust);
		}
		else if(periodeCourante.equals(Periode.PERIODE_OBJECTIF)) {
			terminer("Tu as retrouvé ton espace temps !", true);
			return;
		}
		
		String dossierPeriode = periodeCourante.toString();
		salles.forEach((nomSalle,salle) -> {
				salle.initSalle(new File("Images/salles/"+ dossierPeriode + "/" + nomSalle.toString() + ".png"));
			}
		);
		setSalleCourante(salles.get(NomSalle.SALLE_DEPART));
		
		greg.replacerGauche();
		afficheMessage(dossierPeriode.replace('_', ' '), 2);
		
	}
	
	/**
	 * Renvoie la salle courante.
	 * @return la salle courante.
	 */
	public Salle getSalleCourante() {
		return salleCourante;
	}
	
	/**
	 * Renvoie la période courante.
	 * @return la période courante.
	 */
	public Periode getPeriodeCourante() {
		return periodeCourante;
	}
	
	/**
	 * Permet d'afficher un message sur la vue du jeu.
	 * @param message message à afficher
	 * @param seconde temps d'affichagee en seconde.
	 */
	public void afficheMessage(String message, double seconde) {
		this.message.setVisible(true);
		PauseTransition pause = new PauseTransition(
		        Duration.seconds(seconde)
		);
		pause.setOnFinished(
		        event -> this.message.setVisible(false)
		);
		pause.play();
		this.message.setText(message);

	}
	
	/**
	 * Permet de changer de salle courante.
	 * @param nouvelleSalle la nouvelle salle courante.
	 */
	public void setSalleCourante(Salle nouvelleSalle) {
		salleCourante.supprimerInteractif(greg);
	
		salleCourante = nouvelleSalle;
		salleCourante.ajoutInteractif(greg);
		/* REMPLACEMENT DE L'ANCIENNE IMAGE DE LA SALLE AVEC LA NOUVELLE */

		root.getChildren().remove(0);
		root.getChildren().add(0, salleCourante.getImageView());
		
		/* MISE A JOUR DES OBJETS INTERACTIFS */
		initObjetInteractif();
	}
	
	/**
	 * Renvoie l'instance unique du jeu.
	 * @return l'instance unique du jeu.
	 */
	public static Jeu getInstanceUnique() {
		return instanceUnique;
	}
	
	/**
	 * Renvoie le stage principale.
	 * @return le stage principale.
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
}