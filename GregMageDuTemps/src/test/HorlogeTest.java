package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.Horloge;
import enumerations.Materiaux;
import enumerations.Periode;

class HorlogeTest {

	Horloge h;
	
	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		h = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 889);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInteragir() {
		fail("Not yet implemented");
	}

	@Test
	void testGetXMin() {
		assertEquals(889, h.getXMin());
	}

	@Test
	void testGetXMax() {
		assertEquals(949, h.getXMax());
	}

	@Test
	void testPeutEtreActive() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMateriaux() {
		assertEquals(Materiaux.BRONZE, h.getMateriaux());
	}

	@Test
	void testGetXCentre() {
		assertEquals(919, h.getXCentre());
	}

}
