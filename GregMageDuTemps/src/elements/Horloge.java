package elements;

import java.io.File;
import java.util.Arrays;

import application.Jeu;
import enumerations.Materiaux;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Horloge extends Interactif {
	private static final int PERIODE_APRES_ACTIVATION = 3;
	private Item[] itemPourActiver;
	private boolean aEteActive;
	private Materiaux materiauxHorloge;
	
	public Horloge(File image, Materiaux materiaux, int nbItemManquant, double position) {
		Image i = new Image(image.toURI().toString());
		super.vueImageInteractif = new ImageView(i);
		vueImageInteractif.setX(position);
		
		super.xMin = position;
		super.xMax = super.xMin + i.getWidth();
		this.materiauxHorloge = materiaux;
		this.aEteActive = false;
		this.itemPourActiver = new Item[nbItemManquant];
	}
	
	@Override
	public void interagir() {
		/* L'HORLOGE A DEJA ETE ACTIVE */
		if(aEteActive) return;
		System.out.println("horloge a pas ete active");
		Item item = Jeu.getInstanceUnique().greg.getItemEnMain();
		if(item == null) return;
		System.out.println("greg a un item");
		
		/* ON CHECK SI LE MATERIAUX DE L'ITEM CORRESPOND AU MATERIAUX DE L'HORLOGE */
		if(!item.getMateriaux().equals(this.materiauxHorloge)) Jeu.getInstanceUnique().terminer("Mauvais matériaux utilisé sur l'horloge");
		insererItemManquant(item);
		System.out.println("meme materiaux");
		System.out.println(peutEtreActive());
		
		/* SI ON PEUT ACTIVE L'HORLOGE ON L'ACTIVE */
		if(!peutEtreActive()) return;
		aEteActive = true;
		
		Jeu.getInstanceUnique().changerDePeriode(PERIODE_APRES_ACTIVATION);
		
		
	}

	public boolean peutEtreActive() {
		return itemPourActiver[itemPourActiver.length - 1] != (null);
	}

	private void insererItemManquant(Item item) {
		if(!item.getMateriaux().equals(this.materiauxHorloge)) {
			Jeu.getInstanceUnique().terminer("Mauvais materiaux utilise");
			return;
		}
		
		for(int i = 0; i < itemPourActiver.length; ++i) {
			/* ITEM DEJA DANS L'HORLOGE */
			if(itemPourActiver[i] == item) return;
			/* ENDROIT A INSERER L'ITEM */
			if(itemPourActiver[i] == null) {
				itemPourActiver[i] = item;
				return;
			}
		}	
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
