package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import application.Jeu;
import elements.Horloge;
import elements.Interactif;
import elements.Salle;
import enumerations.Materiaux;
import enumerations.NomSalle;
import enumerations.Periode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;

class SalleTest {
	
	
	Salle s;
	ImageView img;
	File f;
	
	@BeforeAll
	static void setUpApp() throws Exception {
		AppDeTest.setUpClass();
	}

	@BeforeEach
	void setUp() {
		f = new File("Images/Salles/Periode_1/Salle_depart.png");
		s = new Salle(f, NomSalle.SALLE_1);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testGetImageView() {
		img = new ImageView();
		img.setImage(new Image(new File("Images/Salles/Periode_1/Salle_depart.png").toURI().toString()));
		assertTrue(AppDeTest.compareImages(img.getImage(), s.getImageView().getImage()));
		img.setImage(new Image(new File("Images/Salles/Periode_1/Salle_2.png").toURI().toString()));
		assertFalse(AppDeTest.compareImages(img.getImage(), s.getImageView().getImage()));
	}

	@Test
	void testGetNomSalle() {
		Assert.assertEquals(NomSalle.SALLE_1, s.getNomSalle());
		Assert.assertFalse(s.getNomSalle() == NomSalle.SALLE_2);
	}

	@Test
	void testAjoutInteractif() {
		Horloge h = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 889);
		s.ajoutInteractif(h);
		ArrayList<Interactif> ar = new ArrayList<Interactif>();
		ar.add(h);
		Assert.assertEquals(ar, s.getInteractifs());
	}

	@Test
	void testSupprimerInteractif() {
		Horloge h = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 889);
		assertFalse(s.supprimerInteractif(h));
		s.ajoutInteractif(h);
		assertTrue(s.supprimerInteractif(h));
		ArrayList<Interactif> ar = s.getInteractifs();
		assertFalse(ar.contains(h));
		
	}

	@Test
	void testInteractifAPosition() {
		Horloge h = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 20);
		s.ajoutInteractif(h);
		PersonnageJoueur.getInstanceUnique().seDirigerADroite(); //Greg se dirige deux fois � droite pour matcher avec la position de l'int�ractif
		PersonnageJoueur.getInstanceUnique().seDirigerADroite(); //la fonction devrait renvoyer l'interactif
		assertEquals(h, s.interactifAPosition(PersonnageJoueur.getInstanceUnique().getXMin(), PersonnageJoueur.getInstanceUnique().getXMax()));
		PersonnageJoueur.getInstanceUnique().seDirigerADroite(); //Greg se d�place encore deux fois � droite pour sortir de la zone de match entre l'interactif et lui m�me
		PersonnageJoueur.getInstanceUnique().seDirigerADroite(); //la fonction devrait renvoyer l'instance unique du personnage
		assertEquals(PersonnageJoueur.getInstanceUnique(), s.interactifAPosition(PersonnageJoueur.getInstanceUnique().getXMin(), PersonnageJoueur.getInstanceUnique().getXMax()));
	}

	@Test
	void testGetInteractifs() {
	    Horloge h = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 45);
	    s.ajoutInteractif(h);
	    ArrayList<Interactif> ar = new ArrayList<Interactif>();
	    ar.add(h);
	    assertEquals(ar, s.getInteractifs());
	}

}
