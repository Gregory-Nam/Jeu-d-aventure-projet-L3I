package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.Deplacements;

class DeplacementsTest {

	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		assertEquals("HAUT", Deplacements.HAUT.toString());
		assertEquals("BAS", Deplacements.BAS.toString());
		assertEquals("DROITE", Deplacements.DROITE.toString());
		assertEquals("GAUCHE", Deplacements.GAUCHE.toString());
		
	}

}
