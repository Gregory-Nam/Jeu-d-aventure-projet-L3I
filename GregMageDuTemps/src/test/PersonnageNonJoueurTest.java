package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.Item;
import enumerations.Materiaux;
import enumerations.NomPNJ;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageNonJoueur;

class PersonnageNonJoueurTest {

	PersonnageNonJoueur pnj1, pnj2, pnj3, pnj4, pnj5;
	Item itemTest;
	ImageView img;
	
	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		itemTest = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
				   new File("Images/items/aiguille_bronze.png"), Materiaux.BRONZE, 634, "Aiguille");
		
		pnj1 = new PersonnageNonJoueur(NomPNJ.KLACE_HEUREOUVERRE,564, itemTest,
			   new File("Images/PNJ/Klace_face.png"), 
			   new File("Images/PNJ/Klace_face_transparence.png"));
		
		pnj2 =  new PersonnageNonJoueur(NomPNJ.SLYNE, 599, 
				new Item(new File("Images/items/aiguille_argent_transparence.png"), 
				new File("Images/items/aiguille_argent.png"),Materiaux.ARGENT, 669, "Aiguille"),
				new File("Images/PNJ/Slyne_face.png"),
				new File("Images/PNJ/Slyne_face_transparence.png"));
		
		pnj3 =  new PersonnageNonJoueur(NomPNJ.CARPENTER, 571, 
				new Item(new File("Images/items/aiguille_or_transparence.png"),
				new File("Images/items/aiguille_or.png"), Materiaux.OR, 641, "Aiguille"), 
				new File("Images/PNJ/Carpenter_face.png"), 
				new File("Images/PNJ/Carpenter_face_transparence.png"));
		
		pnj4 =  new PersonnageNonJoueur(NomPNJ.ABITBOL, 148, 
				new Item(new File("Images/items/Pendule_or_transparence.png"),
				new File("Images/items/Pendule_or.png"), Materiaux.OR, 218, "Pendule"), 
				new File("Images/PNJ/Abitbol_face.png"), 
				new File("Images/PNJ/Abitbol_face_transparence.png"));
		
		pnj5 =  new PersonnageNonJoueur(NomPNJ.ZAVIER_MAIS, 484, null, 
				new File("Images/PNJ/Zavier_face.png"), 
				new File("Images/PNJ/Zavier_face_transparence.png"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetXMin() {
		assertEquals(564, pnj1.getXMin());
	}

	@Test
	void testGetXMax() {
		assertEquals(612, pnj1.getXMax());
	}

	@Test
	void testGetNom() {
		assertEquals(NomPNJ.KLACE_HEUREOUVERRE, pnj1.getNom());
	}

	@Test
	void testPoseQuestion() {
		assertEquals("Salut toi ! Combien y a-t-il de tableaux dans ce chateau ?", pnj1.poseQuestion());
		assertEquals("Coucou toi,\nlorsque ma soeur avait 5 ans, j'avais le double de son age. Aujourd'hui, elle en a 40.\nQuel est mon age ?", pnj2.poseQuestion());
		assertEquals("Sais-tu combien faut-il de temps (en secondes) pour qu'une horloge sonne midi, \nsi cette derniere sonne six heures en 5 secondes ?", pnj3.poseQuestion());
		assertEquals("J'espere que tu es malin...\nSans lui, rien ne peut se faire.\nIl passe vite ou lentement.\nSouvent quand on le perd, on ne le rattrape plus.\nQui suis-je ?", pnj4.poseQuestion());
		assertEquals("J'ai un petit probleme mathematique...\nConnais-tu le nombre qui est tel que si on le multiplie par deux, on lui ajoute sa moitie puis son quart et enfin 1, donnera 100 ?", pnj5.poseQuestion());
	}

	@Test
	void testRepondAUneMauvaiseReponse() {
		assertEquals("Oupps! Tu as mal repondu. Tu ne peux pas avoir l'item", pnj1.repondAUneMauvaiseReponse());
		assertEquals("Desole, tu as mal repondu. Tu n'auras pas l'item", pnj2.repondAUneMauvaiseReponse());
		assertEquals("Malheureusement, tu as mal repondu. Donc pas de recompense.", pnj3.repondAUneMauvaiseReponse());
		assertEquals("Oupps, c'est rate. L'item t'echappes.", pnj4.repondAUneMauvaiseReponse());
		assertEquals("La reponse est fausse. Tu aurais du aller a l'ecole..", pnj5.repondAUneMauvaiseReponse());
	}

	@Test
	void testRepondAUneBonneReponse() {
		assertEquals("Bravo ! Tu as tres bien repondu. Tu merites bien l'item pour activer l'horloge", pnj1.repondAUneBonneReponse());
		assertEquals("Felicitations, tu as bien repondu. Cet item est a toi", pnj2.repondAUneBonneReponse());
		assertEquals("Exact, tu as bien repondu. Voici ta recompense", pnj3.repondAUneBonneReponse());
		assertEquals("La reponse est correcte. Tu merites bien l'item", pnj4.repondAUneBonneReponse());
		assertEquals("Merci beaucoup !", pnj5.repondAUneBonneReponse());
	}

	@Test
	void testDitQueTuAsDejaRepondu() {
		assertEquals("Malheureusement, la bonne reponse a deja ete donnee.", pnj1.ditQueTuAsDejaRepondu());
		assertEquals("Mais tu as deja repondu !", pnj2.ditQueTuAsDejaRepondu());
		assertEquals("Desole, je t'ai deja donne l'item", pnj3.ditQueTuAsDejaRepondu());
		assertEquals("Pas besoin de me le repeter...", pnj4.ditQueTuAsDejaRepondu());
		assertEquals("Encore merci !", pnj5.ditQueTuAsDejaRepondu());
	}

	@Test
	void testReponse() {
		assertEquals("13", pnj1.reponse());
		assertEquals("45", pnj2.reponse());
		assertEquals("11", pnj3.reponse());
		assertEquals("temps", pnj4.reponse());
		assertEquals("36", pnj5.reponse());
		
	}

	@Test
	void testGetEtatReponseAttendu() {
		assertEquals(new SimpleBooleanProperty(false).toString(), pnj1.getEtatReponseAttendu().toString());
	}

	@Test
	void testDonnerItem() {
		assertEquals(itemTest, pnj1.donnerItem());
	}

	@Test
	void testGetImagePourEnigme() {
		img = new ImageView();
		img.setImage(new Image(new File("Images/PNJ/Klace_face.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), pnj1.getImagePourEnigme().getImage()));
		img.setImage(new Image(new File("Images/Salles/Periode_1/Salle_2.png").toURI().toString()));
		assertFalse(AppDeTest.compareImages(img.getImage(), pnj1.getImagePourEnigme().getImage()));
	}

	@Test
	void testARecuUneBonneReponse() {
		pnj1.aRecuUneBonneReponse();
		assertEquals(new SimpleBooleanProperty(true).toString(), pnj1.getEtatReponseAttendu().toString());
		assertEquals("Malheureusement, la bonne reponse a deja ete donnee.", pnj1.ditQueTuAsDejaRepondu());
	}

}
