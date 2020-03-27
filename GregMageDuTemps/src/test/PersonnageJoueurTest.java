package test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.Inventaire;
import elements.Item;
import enumerations.Deplacements;
import enumerations.Materiaux;
import enumerations.NomPNJ;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;

class PersonnageJoueurTest {

	ImageView img;
	PersonnageNonJoueur pnj;
	Item itemTest;
	
	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		PersonnageJoueur.getInstanceUnique().reinitialiser();
		img = new ImageView();
		itemTest = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
				   new File("Images/items/aiguille_bronze.png"), Materiaux.BRONZE, 634, "Aiguille");
		pnj =   new PersonnageNonJoueur(NomPNJ.SLYNE, 599, 
				new Item(new File("Images/items/aiguille_argent_transparence.png"), 
				new File("Images/items/aiguille_argent.png"),Materiaux.ARGENT, 669, "Aiguille"),
				new File("Images/PNJ/Slyne_face.png"),
				new File("Images/PNJ/Slyne_face_transparence.png"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetXMin() {
		assertEquals(0, PersonnageJoueur.getInstanceUnique().getXMin());
	}

	@Test
	void testGetXMax() {
		assertEquals(56, PersonnageJoueur.getInstanceUnique().getXMax());
	}

	@Test
	void testGetXCentre() {
		assertEquals(28, PersonnageJoueur.getInstanceUnique().getXCentre());
	}

	@Test
	void testSeDirigerADroite() {
		ImageView img = new ImageView();
		img.setImage(new Image(new File("Images/Personnages/wizardDroite_transparent.png").toURI().toString()));
		PersonnageJoueur.getInstanceUnique().seDirigerADroite();
		assertTrue(AppDeTest.compareImages(img.getImage(), PersonnageJoueur.getInstanceUnique().getImageView().getImage()));
		assertEquals(15, PersonnageJoueur.getInstanceUnique().getXMin());
		assertEquals(71, PersonnageJoueur.getInstanceUnique().getXMax());
	}

	@Test
	void testSeDirigerAGauche() {
		ImageView img = new ImageView();
		img.setImage(new Image(new File("Images/Personnages/wizardGauche_transparent.png").toURI().toString()));
		PersonnageJoueur.getInstanceUnique().seDirigerADroite();
		PersonnageJoueur.getInstanceUnique().seDirigerADroite();
		PersonnageJoueur.getInstanceUnique().seDirigerAGauche();
		assertTrue(AppDeTest.compareImages(img.getImage(), PersonnageJoueur.getInstanceUnique().getImageView().getImage()));
		assertEquals(15, PersonnageJoueur.getInstanceUnique().getXMin());
		assertEquals(71, PersonnageJoueur.getInstanceUnique().getXMax());
	}

	@Test
	void testInitPersonnage() {	
		img.setImage(new Image(new File("Images/Personnages/wizardDroite_transparent.png").toURI().toString()));
		PersonnageJoueur.getInstanceUnique().changerSprite(Deplacements.DROITE);
		assertTrue(AppDeTest.compareImages(img.getImage(), PersonnageJoueur.getInstanceUnique().getImageView().getImage()));
	}

	@Test
	void testChangerSprite() {
		
		img.setImage(new Image(new File("Images/Personnages/wizardGauche_transparent.png").toURI().toString()));
		PersonnageJoueur.getInstanceUnique().changerSprite(Deplacements.GAUCHE);
		assertTrue(AppDeTest.compareImages(img.getImage(), PersonnageJoueur.getInstanceUnique().getImageView().getImage()));
	
		img.setImage(new Image(new File("Images/Personnages/wizardDroite_transparent.png").toURI().toString()));
		PersonnageJoueur.getInstanceUnique().changerSprite(Deplacements.DROITE);
		assertTrue(AppDeTest.compareImages(img.getImage(), PersonnageJoueur.getInstanceUnique().getImageView().getImage()));
		
		img.setImage(new Image(new File("Images/Personnages/wizardSud_transparent.png").toURI().toString()));
		PersonnageJoueur.getInstanceUnique().changerSprite(Deplacements.BAS);
		assertTrue(AppDeTest.compareImages(img.getImage(), PersonnageJoueur.getInstanceUnique().getImageView().getImage()));
		
		img.setImage(new Image(new File("Images/Personnages/wizardNord_transparent.png").toURI().toString()));
		PersonnageJoueur.getInstanceUnique().changerSprite(Deplacements.HAUT);
		assertTrue(AppDeTest.compareImages(img.getImage(), PersonnageJoueur.getInstanceUnique().getImageView().getImage()));
	}

	@Test
	void testEnleverItemEnMain() {
		PersonnageJoueur.getInstanceUnique().enleverItemEnMain();
		assertNull(PersonnageJoueur.getInstanceUnique().getItemEnMain());
	}

	@Test
	void testReplacerGauche() {
		assertEquals(0, PersonnageJoueur.getInstanceUnique().getXMin());
		assertEquals(56, PersonnageJoueur.getInstanceUnique().getXMax());
		assertEquals(0,PersonnageJoueur.getInstanceUnique().getImageView().getX());
		img.setImage(new Image(new File("Images/Personnages/wizardDroite_transparent.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), PersonnageJoueur.getInstanceUnique().getImageView().getImage()));
	}

	@Test
	void testReplacerDroite() {
		PersonnageJoueur.getInstanceUnique().replacerDroite();
		assertEquals(944, PersonnageJoueur.getInstanceUnique().getXMin());
		assertEquals(1000, PersonnageJoueur.getInstanceUnique().getXMax());
		assertEquals(944,PersonnageJoueur.getInstanceUnique().getImageView().getX());
	}

	@Test
	void testPrendreItem() {	
		PersonnageJoueur.getInstanceUnique().prendreItem(itemTest);
		assertTrue(PersonnageJoueur.getInstanceUnique().getInventaire().getInventaire().contains(itemTest));
	}

	@Test
	void testPrendreItemEnMain() {
		
		PersonnageJoueur.getInstanceUnique().prendreItem(itemTest);
		PersonnageJoueur.getInstanceUnique().prendreItemEnMain(itemTest);
		assertEquals(itemTest, PersonnageJoueur.getInstanceUnique().getItemEnMain());
	}

	@Test
	void testGetItemEnMain() {
		PersonnageJoueur.getInstanceUnique().prendreItem(itemTest);
		PersonnageJoueur.getInstanceUnique().prendreItemEnMain(itemTest);
		assertEquals(itemTest, PersonnageJoueur.getInstanceUnique().getItemEnMain());
	}

	@Test
	void testGetInventaire() {
        assertTrue(PersonnageJoueur.getInstanceUnique().getInventaire().getClass() == Inventaire.class);
	}

	@Test
	void testABienRepondu() {
		PersonnageJoueur.getInstanceUnique().liaisonDialogueAvecPNJ(pnj);
		assertFalse(PersonnageJoueur.getInstanceUnique().aBienRepondu());
		
		pnj.aRecuUneBonneReponse();
		PersonnageJoueur.getInstanceUnique().liaisonDialogueAvecPNJ(pnj);
		assertTrue(PersonnageJoueur.getInstanceUnique().aBienRepondu());
	}

	@Test
	void testLiaisonDialogueAvecPNJ() {
		PersonnageJoueur.getInstanceUnique().liaisonDialogueAvecPNJ(pnj);
		assertFalse(PersonnageJoueur.getInstanceUnique().aBienRepondu());
		
		pnj.aRecuUneBonneReponse();
		PersonnageJoueur.getInstanceUnique().liaisonDialogueAvecPNJ(pnj);
		assertTrue(PersonnageJoueur.getInstanceUnique().aBienRepondu());
	}

}
