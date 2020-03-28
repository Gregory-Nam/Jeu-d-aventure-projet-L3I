package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.NomPNJ;

class NomPNJTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testToString() {

		assertEquals("Zavier Mais", NomPNJ.ZAVIER_MAIS.toString());
		assertEquals("Slyne", NomPNJ.SLYNE.toString());
		assertEquals("Carpenter", NomPNJ.CARPENTER.toString());
		assertEquals("Abitbol", NomPNJ.ABITBOL.toString());

	}

}
