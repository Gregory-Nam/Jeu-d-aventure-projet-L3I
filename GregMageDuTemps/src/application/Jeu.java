package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import controleurs.EnigmeControleur;
import controleurs.FinControleur;
import controleurs.InventaireControleur;
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
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;
import utilitaire.CompteARebours;
import enumerations.Deplacements;
import enumerations.Materiaux;
import enumerations.NomPNJ;
import enumerations.NomSalle;
import enumerations.Periode;

public class Jeu {
	
	private static Jeu instanceUnique = new Jeu();
	
	public static final int X_MAX_FENETRE = 1000;
	public static final int X_MIN_FENETRE = 0;
	
	private PersonnageJoueur greg;
	private Salle salleCourante;
	private Stage primaryStage;
	private Scene scene;
	private Scene sceneInventaire;
	private Scene sceneEnigme;
	private Scene sceneFinJeu;
	
	private Pane root;
	private EnigmeControleur rootEnigme;
	private InventaireControleur rootInventaire;
	private FinControleur rootMort;
	private Label message;
	
	private HashMap<NomSalle, Salle> salles;
	private HashMap<NomPNJ, PersonnageNonJoueur> pnj;
	private Periode periodeCourante;
	
	private Jeu() {}
	
	public void lancerJeu(Stage stage) throws IOException {
		CompteARebours c = new CompteARebours(10, 0);
		this.periodeCourante = Periode.PERIODE_1;
		primaryStage = stage;
		primaryStage.setResizable(false);
		
		salles = new HashMap<NomSalle, Salle>();
		
		initPersonnageJoueurScene();
		creationDesObjetsInteractifs();
		initStage();	
		initEnigmeScene();
		initInventaireScene();
		initSceneDeFin();
		initObjetInteractif();

		ajoutEvenement();
		ajoutEvenementEnigme();
		
		
		c.lancer();
		primaryStage.titleProperty().bind(c.getTempsTotalEnStringProperty());
		greg.getInventaire().creerListener(rootInventaire);
		afficheMessage(periodeCourante.toString().replace('_', ' '),2);		
	}
	
	private void initPersonnageJoueurScene() {
		greg = PersonnageJoueur.getInstanceUnique();

	}
		
	private void initStage() throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vues/UneFenetre.fxml"));
		scene = new Scene(root);
		message = (Label)root.getChildren().get(0);
		root.getChildren().add(0, salleCourante.getImageView());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void initEnigmeScene() {
		rootEnigme = new EnigmeControleur();
		sceneEnigme = new Scene(rootEnigme);
		
	}
	
	private void initInventaireScene() {
		rootInventaire = new InventaireControleur();
		sceneInventaire = new Scene(rootInventaire);
	}

	private void initSceneDeFin() throws IOException {
		rootMort = new FinControleur();
		sceneFinJeu = new Scene(rootMort);
		
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
	
	private void initPnjItemPeriode1() {
		pnj = new HashMap<>();
		Item itemBronze = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
							       new File("Images/items/aiguille_bronze.png"),
							       Materiaux.BRONZE, 630, "Aiguille");
		Item itemArgent = new Item(new File("Images/items/aiguille_argent_transparence.png"),
								   new File("Images/items/aiguille_argent.png"),
								   Materiaux.ARGENT, 650, "Aiguille");
		
		Item itemOr1 = new Item(new File("Images/items/aiguille_or_transparence.png"),
				   new File("Images/items/aiguille_or.png"),
				   Materiaux.OR, 650, "Aiguille");
		
		
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
	
	private void initPnjItemPeriode3() {
		Item itemOr2 = new Item(new File("Images/items/Pendule_or_transparence.png"),
								new File("Images/items/Pendule_or.png"),
								Materiaux.OR, 650, "Pendule");
		
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
		
		/* HORLOGES */
		Horloge horlogeBronze = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 889);
		Horloge horlogeArgent = new Horloge(new File("Images/Horloges/Horloge_argent_transparence.png"), Materiaux.ARGENT, 1, Periode.PERIODE_3, 92);
		Horloge horlogeOr = new Horloge(new File("Images/Horloges/Horloge_or_transparence.png"), Materiaux.OR, 2, Periode.PERIODE_OBJECTIF, 853);
		HorlogePiege horlogePiege = new HorlogePiege(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.PLAQUE_OR, Periode.PERIODE_OBJECTIF, 86);

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

						if(objetInteractif != null) {
							objetInteractif.interagir();
							greg.changerSprite(Deplacements.BAS);
						}
						break;
					case I :
						voirInventaire();
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
			else if(rootEnigme.getChamps().equals(pnj.reponse())) {
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
	
	public void voirInventaire() {
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
					System.out.println(itemSelectionne);
					if( itemSelectionne != null) greg.prendreItemEnMain(itemSelectionne);
					break;
				default:
					break;
				}
			}
		});
	}
	
	public void terminer(String message, boolean aGagne) {
		rootMort.setRaisonDeLaFin(message, aGagne);
		primaryStage.setScene(sceneFinJeu);
		
	}
	
	
	public void changerDePeriode()  {
		PersonnageNonJoueur pnjMort;
		periodeCourante = periodeCourante.suivante();
		if(periodeCourante.equals(Periode.PERIODE_2)) {
			pnjMort = pnj.get(NomPNJ.KLACE_HEUREOUVERRE);
			pnj.remove(NomPNJ.KLACE_HEUREOUVERRE);
			salles.get(NomSalle.SALLE_3).supprimerInteractif(pnjMort);
		}
		else if(periodeCourante.equals(Periode.PERIODE_3)) {
			initPnjItemPeriode3();
			pnjMort = pnj.get(NomPNJ.SLYNE);
			pnj.remove(NomPNJ.SLYNE);
			salles.get(NomSalle.SALLE_2).supprimerInteractif(pnjMort);
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
	
	public Salle getSalleCourante() {
		return salleCourante;
	}
	
	public Periode getPeriodeCourante() {
		return periodeCourante;
	}
	
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

	public static Jeu getInstanceUnique() {
		return instanceUnique;
	}
}
