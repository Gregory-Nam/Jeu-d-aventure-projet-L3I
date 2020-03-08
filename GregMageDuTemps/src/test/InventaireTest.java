package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.Inventaire;
import elements.Item;
import enumerations.Materiaux;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class InventaireTest {

	Inventaire inv;
	Item testItem;
	
	@BeforeEach
	void setUp() throws Exception {
		inv = new Inventaire();
		testItem = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
			       new File("Images/items/aiguille_bronze.png"),
			       Materiaux.BRONZE, 630, "Aiguille");
		inv.ajouterItem(testItem);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInventaire() {
		fail("Not yet implemented");
	}

	@Test
	void testAjouterItem() {
		fail("Not yet implemented");
	}

	@Test
	void testSupprimerItem() {
		inv.supprimerItem(testItem);
		assertNull(inv.getItem(testItem));
	}

	@Test
	void testGetItem() {	
		assertEquals(testItem, inv.getItem(testItem));
	}

	@Test
	void testGetInventaire() {
		ObservableList<Item> ol = FXCollections.observableArrayList(new ArrayList<Item>());
		assertEquals(ol, inv.getInventaire());
	}
	
	@Test
	void testCapaciteAtteinte() {
		fail("Not yet implemented");
	}

	@Test
	void testCreerListener() {
		fail("Not yet implemented");
	}

}
