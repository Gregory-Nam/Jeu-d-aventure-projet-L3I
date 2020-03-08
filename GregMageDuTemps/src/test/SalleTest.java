package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elements.Horloge;
import elements.Interactif;
import elements.Item;
import elements.PorteMurale;
import elements.Salle;
import enumerations.Materiaux;
import enumerations.NomSalle;
import enumerations.Periode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;

class SalleTest {

	//Ces test sont broken à cause de l'interface graphique
	
	Salle s;
	
	
	@BeforeEach
	void setUp() throws Exception {
		s = new Salle(new File("Images/Salles/Periode_1/Salle_depart.png"), NomSalle.SALLE_1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInitSalle() {
		
	}

	@Test
	void testGetImageView() {
		ImageView img = new ImageView();
		img.setImage(new Image(new File("Images/Salles/Periode_1/Salle_depart.png").toURI().toString()));
		Assert.assertEquals(img, s.getImageView());
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
		fail("Not yet implemented");
	}

	@Test
	void testInteractifAPosition() {
		Horloge h = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 20);
		s.ajoutInteractif(h);
		PersonnageJoueur.getInstanceUnique().seDirigerADroite(); //Greg se dirige deux fois à droite pour matcher avec la position de l'intéractif
		PersonnageJoueur.getInstanceUnique().seDirigerADroite(); //la fonction devrait renvoyer l'interactif
		assertEquals(h, s.interactifAPosition(PersonnageJoueur.getInstanceUnique().getXMin(), PersonnageJoueur.getInstanceUnique().getXMax()));
		PersonnageJoueur.getInstanceUnique().seDirigerADroite(); //Greg se déplace encore deux fois à droite pour sortir de la zone de match entre l'interactif et lui même
		PersonnageJoueur.getInstanceUnique().seDirigerADroite(); //la fonction devrait renvoyer l'instance unique du personnage
		assertEquals(PersonnageJoueur.getInstanceUnique(), s.interactifAPosition(PersonnageJoueur.getInstanceUnique().getXMin(), PersonnageJoueur.getInstanceUnique().getXMax()));
	}

	@Test
	void testGetInteractifs() {
	    Horloge h = new Horloge(new File("Images/Horloges/Horloge_bronze_transparence.png"), Materiaux.BRONZE, 1, Periode.PERIODE_2, 45);
	}

}
