package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.Inventaire;
import elements.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class InventaireTest {

	Inventaire inv;
	
	@BeforeEach
	void setUp() throws Exception {
		inv = new Inventaire();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInventaire() {
		
	}

	@Test
	void testAjouterItem() {
		fail("Not yet implemented");
	}

	@Test
	void testSupprimerItem() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItem() {
		fail("Not yet implemented");
	}

	@Test
	void testGetInventaire() {
		ObservableList<Item> ol = FXCollections.observableArrayList(new ArrayList<Item>());
		assertEquals(ol, inv.getInventaire());
	}

	@Test
	void testCreerListener() {
		fail("Not yet implemented");
	}

}
