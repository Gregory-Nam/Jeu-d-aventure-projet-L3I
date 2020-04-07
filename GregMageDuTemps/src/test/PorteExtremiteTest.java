package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import elements.PorteExtremite;
import elements.Salle;
import enumerations.NomSalle;

class PorteExtremiteTest {

	PorteExtremite p;
	Salle s, s1;

	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}

	@BeforeEach
	void setUp() throws Exception {
		s = new Salle(new File("Images/Salles/Periode_1/Salle_depart.png"), NomSalle.SALLE_DEPART);
		s1 = new Salle(new File("Images/Salles/Periode_1/Salle_1.png"), NomSalle.SALLE_1);

		p = new PorteExtremite(s, s1);

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Disabled
	void testInteragir() {
		fail("NullPointerException, ne fonctionne pas dans l'environnement de test");
	}

	@Test
	void testGetXMin() {
		assertEquals(0, p.getXMin());
		/*
		 * Jeu.getInstanceUnique().setSalleCourante(s1); assertEquals(1000,
		 * p.getXMin());
		 */

	}

	@Test
	void testGetXMax() {
		assertEquals(0, p.getXMax());
		/*
		 * Jeu.getInstanceUnique().setSalleCourante(s1); assertEquals(1000,
		 * p.getXMax());
		 */
	}

	@Test
	void testGetImageView() {
		assertNull(p.getImageView());
	}

}
