package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.PorteMurale;
import elements.Salle;

class PorteMuraleTest {

	PorteMurale p;
	Salle s, s1;
	
	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		p = new PorteMurale(s, s1, 730);
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
		assertEquals(730, p.getXMin());
	}

	@Test
	void testGetXMax() {
		assertEquals(830, p.getXMax());
	}

	@Test
	void testPorteMurale() {
		fail("Not yet implemented");
	}

	@Test
	void testInitPorte() {
		fail("Not yet implemented");
	}

	@Test
	void testPorteExtremite() {
		fail("Not yet implemented");
	}

	@Test
	void testGetXCentre() {
		assertEquals(780, p.getXCentre());
	}

}
