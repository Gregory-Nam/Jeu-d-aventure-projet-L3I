package elements;

import java.io.File;
import java.util.Arrays;

import application.Jeu;
import enumerations.Materiaux;
import enumerations.Periode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.Personnage;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;

/**
 * Implémentation des horloges du Jeu. </br>
 * Une horloge est un objet interactif composée d'un certain matériaux.
 * @author Grégory NAM.
 * @author Hugo CHALIK.
 * @author Luca BEVILACQUA.
 * @author Ahmadou Bamba MBAYE.
 * @see    Interactif
 * @see    Materiaux
 */
public class Horloge extends Interactif {
	
	/**
	 * La période après l'activation de l'horloge.
	 */
	private Periode periodeApresActivation;
	
	/*
	 * Tableau qui represente les items manquants de l'horloge. </br>
	 * Il doit être rempli pour que l'horloge puisse être activé.
	 */
	private Item[] itemPourActiver;
	
	/**
	 * Boolean qui vérifie si l'horloge a été activée.
	 */
	private boolean aEteActive;
	
	/**
	 * Matériaux de l'horloge.
	 */
	private Materiaux materiauxHorloge;
	
	/**
	 * Constructeur de l'horloge.
	 * @param image image de l'horloge.
	 * @param materiaux matériaux de l'horloge.
	 * @param nbItemManquant nombre d'items à inserer pour activer l'horloge.
	 * @param periodeApresActivation periode dans laquelle l'horloge permet d'aller.
	 * @param position position de l'horloge dans la salle.
	 */
	public Horloge(File image, Materiaux materiaux, int nbItemManquant, Periode periodeApresActivation, double position) {
		Image i = new Image(image.toURI().toString());
		
		super.vueImageInteractif = new ImageView(i);
		super.xMin = position;
		super.xMax = super.xMin + i.getWidth();
		
		vueImageInteractif.setX(position);
		
		this.materiauxHorloge = materiaux;
		this.aEteActive = false;
		this.itemPourActiver = new Item[nbItemManquant];
		this.periodeApresActivation = periodeApresActivation;
	}
	
	/**
	 * Interaction avec l'horloge. </br>
	 * Une interaction avec l'horloge est possible si celle-ci n'a pas déjà été activée;
	 * L'interaction avec l'horloge peu :
	 * <ul>
	 * 		<li> Provoquer la fin du jeu (mauvais item utilisé ou mauvaise période) </li>
	 * 		<li> Afficher un message aucun item ne lui a été présenté.
	 * </ul>
	 */
	@Override
	public void interagir() {
		/* L'HORLOGE A DEJA ETE ACTIVE */
		if(aEteActive) return;
		Item item = PersonnageJoueur.getInstanceUnique().getItemEnMain();
		/* AUCUN ITEM PRESENTE */
		if(item == null) {
			Jeu.getInstanceUnique().afficheMessage("Je devrais prendre un item...", 1);
			return;
		}
		/* MAUVAISE PERIODE */
		if(Jeu.getInstanceUnique().getPeriodeCourante() != periodeApresActivation.precente()) {
			Jeu.getInstanceUnique().terminer("Tu n'étais pas dans le bon espace temps pour activer cet horloge...", false);
			PersonnageJoueur.getInstanceUnique().replacerGauche();
			PersonnageJoueur.getInstanceUnique().enleverItemEnMain();			
			return;
		}
		/* MAUVAIS ITEM */
		if(insererItemManquant(item) < 0) return;
		PersonnageJoueur.getInstanceUnique().enleverItemEnMain();
		
		/* SI ON PEUT ACTIVE L'HORLOGE ON L'ACTIVE */
		if(!peutEtreActive()) {
			Jeu.getInstanceUnique().afficheMessage("Ce ne sera pas suffisant", 1);
			return;
		}
		aEteActive = true;
		
		Jeu.getInstanceUnique().changerDePeriode();
	}

	/**
	 * Renvoie si l'horloge peut être activée.
	 * @return vrai si l'horloge peut être activée, faux sinon.
	 */
	public boolean peutEtreActive() {
		return itemPourActiver[itemPourActiver.length - 1] != (null);
	}

	/**
	 * Permet d'inserer un item dans l'horloge. </br>
	 * L'item n'est pas insérée si ce n'est pas le bon et provoque la fin du jeu.
	 * @param item l'item a inserer.
	 * @return 1 si l'item a été inserer, sinon renvoie -1.
	 */
	private int insererItemManquant(Item item) {
		if(!item.getMateriaux().equals(this.materiauxHorloge)) {
			Jeu.getInstanceUnique().terminer("L'horloge a été cassé par cet item en " + item.getMateriaux().toString(), false);
			return -1;
		}
		
		for(int i = 0; i < itemPourActiver.length; ++i) {
			/* ITEM DEJA DANS L'HORLOGE */
			if(itemPourActiver[i] == item) return -1;
			/* ENDROIT A INSERER L'ITEM */
			if(itemPourActiver[i] == null) {
				itemPourActiver[i] = item;
				return 1;
			}
		}
		return -1;
	}
	
	@Override
	public double getXMin() {
		return super.xMin;
	}
	
	@Override
	public double getXMax() {
		return super.xMax;
	}
	
	@Override
	public ImageView getImageView() {
		return super.vueImageInteractif;
	}

	/**
	 * Renvoiele matériaux de l'horloge.
	 * @return le matériaux de l'horloge.
	 */
	public Materiaux getMateriaux() {
		return this.materiauxHorloge;
	}
}
