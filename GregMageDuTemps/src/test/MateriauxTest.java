package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.Materiaux;

class MateriauxTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertEquals("BRONZE", Materiaux.BRONZE.toString());
		assertEquals("ARGENT", Materiaux.ARGENT.toString());
		assertEquals("OR", Materiaux.OR.toString());
		assertEquals("PLAQUE_OR", Materiaux.PLAQUE_OR.toString());
	}

}
