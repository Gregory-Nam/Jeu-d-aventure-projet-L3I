package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import application.Jeu;
import elements.Inventaire;
import elements.Item;
import enumerations.Materiaux;
import javafx.scene.image.Image;
import personnages.PersonnageJoueur;

class ItemTest {

	Item it, it2;
	
	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		it = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
			       new File("Images/items/aiguille_bronze.png"),
			       Materiaux.BRONZE, 630, "Aiguille");
		it2 = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
			       new File("Images/items/aiguille_bronze.png"),
			       Materiaux.ARGENT, 625, "Aiguille");
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInteragir() {	
		it.interagir();
		Inventaire inv = PersonnageJoueur.getInstanceUnique().getInventaire();
		assertTrue(inv.getInventaire().contains(it));
		assertFalse(Jeu.getInstanceUnique().getSalleCourante().getInteractifs().contains(it));
	}

	@Test
	void testGetXMax() {
		assertEquals(650,  it.getXMax());
	}
	
	@Test
	void testGetXMin() {
		assertEquals(630, it.getXMin());
	}
	
	@Test
	void testGetNom() {
		assertEquals("Aiguille en BRONZE", it.getNom());
	}

	@Test
	void testGetMateriaux() {
		assertEquals(Materiaux.BRONZE, it.getMateriaux());
	}

	@Test
	void testGetXCentre() {
		assertEquals(640, it.getXCentre());
	}

}
