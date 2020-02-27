package elements;

import java.io.File;

import application.Jeu;
import enumerations.Materiaux;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;

public class Item extends Interactif{

	private Materiaux materiaux;
	private String nom;
	private ImageView imageViewItem;
	
	public Item(File f, Materiaux materiaux, int position, String nom) {
		Image i = new Image(f.toURI().toString());
		imageViewItem = new ImageView(i);

		this.materiaux = materiaux;
		this.nom = nom;

		super.xMin = position;
		super.xMax = super.xMin + i.getWidth();
		
		imageViewItem.setX(xMin);
	}
	
	@Override
	public ImageView getImageView() {
		return imageViewItem;
	}
	
	public String getNom() {
		return nom + " de " + this.materiaux.toString();
	}
	public Materiaux getMateriaux() {
		return this.materiaux;
	}

	@Override
	public void interagir() {
		PersonnageJoueur.getInstanceUnique().prendreItem(this);
		Jeu.getInstanceUnique().getSalleCourante().supprimerInteractif(this);
		Jeu.getInstanceUnique().initObjetInteractif();
	}

	@Override
	public double getXMin() {
		return super.xMin;
	}

	@Override
	public double getXMax() {
		return super.xMax;
	}
	
	
	
}
