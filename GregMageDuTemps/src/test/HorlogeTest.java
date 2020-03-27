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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class HorlogeTest {

	Horloge h;
	ImageView img;
	
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
	void testGetXMin() {
		assertEquals(889, h.getXMin());
	}

	@Test
	void testGetXMax() {
		assertEquals(949, h.getXMax());
	}
	
	@Test
	public void testGetImageView() {
		img = new ImageView();
		img.setImage(new Image(new File("Images/Horloges/Horloge_bronze_transparence.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), h.getImageView().getImage()));
		img.setImage(new Image(new File("Images/Horloges/Horloge_argent_transparence.png").toURI().toString()));
		assertFalse(AppDeTest.compareImages(img.getImage(), h.getImageView().getImage()));
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
