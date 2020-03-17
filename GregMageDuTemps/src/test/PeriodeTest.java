package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.Periode;

class PeriodeTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPrecedente() {
		assertEquals(Periode.PERIODE_3, Periode.PERIODE_OBJECTIF.precedente());
		assertEquals(Periode.PERIODE_2, Periode.PERIODE_3.precedente());
		assertEquals(Periode.PERIODE_1, Periode.PERIODE_2.precedente());
		assertEquals(Periode.PERIODE_1, Periode.PERIODE_1.precedente());
	}

	@Test
	void testSuivante() {
		assertEquals(Periode.PERIODE_2, Periode.PERIODE_1.suivante());
		assertEquals(Periode.PERIODE_3, Periode.PERIODE_2.suivante());
		assertEquals(Periode.PERIODE_OBJECTIF, Periode.PERIODE_3.suivante());
		assertEquals(Periode.PERIODE_OBJECTIF, Periode.PERIODE_OBJECTIF.suivante());
	}

	@Test
	void testToString() {
		assertEquals("Periode_1", Periode.PERIODE_1.toString());
		assertEquals("Periode_2", Periode.PERIODE_2.toString());
		assertEquals("Periode_3", Periode.PERIODE_3.toString());
		assertEquals("Periode_objectif", Periode.PERIODE_OBJECTIF.toString());
	}

}
