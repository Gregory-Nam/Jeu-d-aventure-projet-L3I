package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.Inventaire;
import elements.Item;
import enumerations.Materiaux;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class InventaireTest {

	Inventaire inv;
	Item testItem1, testItem2, testItem3, testItem4, testItem5;

	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}

	@BeforeEach
	void setUp() throws Exception {
		inv = new Inventaire();
		testItem1 = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
				new File("Images/items/aiguille_bronze.png"), Materiaux.BRONZE, 630, "Aiguille");
		testItem2 = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
				new File("Images/items/aiguille_bronze.png"), Materiaux.ARGENT, 629, "Aiguille");
		testItem3 = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
				new File("Images/items/aiguille_bronze.png"), Materiaux.PLAQUE_OR, 628, "Aiguille");
		testItem4 = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
				new File("Images/items/aiguille_bronze.png"), Materiaux.OR, 627, "Aiguille");
		testItem5 = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
				new File("Images/items/aiguille_bronze.png"), Materiaux.BRONZE, 656, "Aiguille");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAjouterItem() {
		assertTrue(inv.ajouterItem(testItem1));

		assertFalse(inv.ajouterItem(testItem1));

		assertTrue(inv.ajouterItem(testItem2));
		assertTrue(inv.ajouterItem(testItem3));
		assertTrue(inv.ajouterItem(testItem4));

		assertFalse(inv.ajouterItem(testItem5));
	}

	@Test
	void testSupprimerItem() {
		inv.ajouterItem(testItem1);
		inv.supprimerItem(testItem1);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			inv.getItem(testItem1);
		});
	}

	@Test
	void testGetItem() {
		inv.ajouterItem(testItem1);
		assertEquals(testItem1, inv.getItem(testItem1));
	}

	@Test
	void testGetInventaire() {
		ObservableList<Item> ol = FXCollections.observableArrayList(new ArrayList<Item>());
		assertEquals(ol, inv.getInventaire());
	}

}
