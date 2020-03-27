package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.PorteMurale;
import elements.Salle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class PorteMuraleTest {

	PorteMurale p;
	Salle s, s1;
	ImageView img;
	
	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		p = new PorteMurale(s, s1, 730);
		img = new ImageView();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInteragir() {
		
		img.setImage(new Image(new File("Images/Elements/Porte_Ferme.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), p.getImageView().getImage()));
		try {
			p.interagir();
		} 
		catch (Exception e) {
			//Test du changement d'ImageView, le super.interagir de PorteExtremite ne fonctionnant pas dans l'environement de test
		}
		img.setImage(new Image(new File("Images/Elements/Porte_Ouverte.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), p.getImageView().getImage()));
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
	void testGetImageView() {
		img.setImage(new Image(new File("Images/Elements/Porte_Ferme.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), p.getImageView().getImage()));
		img.setImage(new Image(new File("Images/Elements/Porte_Ouverte.png").toURI().toString()));
		assertFalse(AppDeTest.compareImages(img.getImage(), p.getImageView().getImage()));
	}

	@Test
	void testInitPorte() {
		p.initPorte();
		img.setImage(new Image(new File("Images/Elements/Porte_Ferme.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), p.getImageView().getImage()));
	}


	@Test
	void testGetXCentre() {
		assertEquals(780, p.getXCentre());
	}

}
