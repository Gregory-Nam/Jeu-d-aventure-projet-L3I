package elements;

import java.io.File;
import java.util.Arrays;

import application.Jeu;
import enumerations.Materiaux;
import enumerations.Periode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;

public class Horloge extends Interactif {
	private Periode periodeApresActivation;
	private Item[] itemPourActiver;
	private boolean aEteActive;
	private Materiaux materiauxHorloge;
	
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
	
	@Override
	public void interagir() {
		/* L'HORLOGE A DEJA ETE ACTIVE */
		if(aEteActive) return;
		Item item = PersonnageJoueur.getInstanceUnique().getItemEnMain();
		if(item == null) return;
		if(Jeu.getInstanceUnique().getPeriodeCourante() != periodeApresActivation.precente()) {
			Jeu.getInstanceUnique().terminer("Tu n'étais pas dans le bon espace temps pour activer cet horloge...", false);
			return;
		}
		
		if(insererItemManquant(item) < 0) return;
		PersonnageJoueur.getInstanceUnique().enleverItemEnMain();
		
		/* SI ON PEUT ACTIVE L'HORLOGE ON L'ACTIVE */
		if(!peutEtreActive()) return;
		aEteActive = true;
		
		Jeu.getInstanceUnique().changerDePeriode();
	}

	public boolean peutEtreActive() {
		return itemPourActiver[itemPourActiver.length - 1] != (null);
	}

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

	public Materiaux getMateriaux() {
		return this.materiauxHorloge;
	}
}
