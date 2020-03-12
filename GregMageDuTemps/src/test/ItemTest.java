package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import elements.Item;
import enumerations.Materiaux;

class ItemTest {

	Item it;
	
	@BeforeEach
	void setUp() throws Exception {
		it = new Item(new File("Images/items/aiguille_bronze_transparence.png"),
			       new File("Images/items/aiguille_bronze.png"),
			       Materiaux.BRONZE, 630, "Aiguille");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Disabled
	void testGetImageView() {
		//TODO
	}

	@Test
	void testInteragir() {
		fail("Not yet implemented");
	}


	@Test
	void testGetImageViewPourInventaire() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNom() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMateriaux() {
		fail("Not yet implemented");
	}

	@Test
	void testGetXCentre() {
		fail("Not yet implemented");
	}

}
