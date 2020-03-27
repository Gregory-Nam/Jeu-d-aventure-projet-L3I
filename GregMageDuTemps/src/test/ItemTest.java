package test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import elements.Inventaire;
import elements.Item;
import enumerations.Materiaux;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;

class ItemTest {

	Item it, it2;
	ImageView img;
	
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
		img = new ImageView();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInteragir() {	
		//Test de la première partie de la méthode interagir car elle ne fonctionne pas complètement dans l'environnent de test
		PersonnageJoueur.getInstanceUnique().prendreItem(it); 
		Inventaire inv = PersonnageJoueur.getInstanceUnique().getInventaire();
		assertTrue(inv.getInventaire().contains(it));
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
	
	@Test
	void testGetImageView() {
		img.setImage(new Image(new File("Images/items/aiguille_bronze_transparence.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), it.getImageView().getImage()));
		img.setImage(new Image(new File("Images/items/aiguille_argent_transparence.png").toURI().toString()));
		assertFalse(AppDeTest.compareImages(img.getImage(), it.getImageView().getImage()));
	}
	
	@Test
	void testGetImageViewPourInventaire() {
		img.setImage(new Image(new File("Images/items/aiguille_bronze.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), it.getImageViewPourInventaire().getImage()));
		img.setImage(new Image(new File("Images/items/aiguille_argent.png").toURI().toString()));
		assertFalse(AppDeTest.compareImages(img.getImage(), it.getImageViewPourInventaire().getImage()));
	}
	

}
