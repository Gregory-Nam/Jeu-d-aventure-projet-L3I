package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.NomSalle;

class NomSalleTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testToString() {

		assertEquals("Salle_depart", NomSalle.SALLE_DEPART.toString());
		assertEquals("Salle_1", NomSalle.SALLE_1.toString());
		assertEquals("Salle_2", NomSalle.SALLE_2.toString());
		assertEquals("Salle_3", NomSalle.SALLE_3.toString());
		assertEquals("Salle_bronze", NomSalle.SALLE_BRONZE.toString());
		assertEquals("Salle_argent", NomSalle.SALLE_ARGENT.toString());
		assertEquals("Salle_or", NomSalle.SALLE_OR.toString());
		assertEquals("Salle_piege", NomSalle.SALLE_PIEGE.toString());

	}

}
