package elements;

import java.io.File;

import enumerations.Materiaux;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {

	private Materiaux materiaux;
	private String nom;
	private int xPosition;
	private ImageView imageViewItem;
	
	public Item(File f, Materiaux materiaux, int position, String nom) {
		Image i = new Image(f.toURI().toString());
		imageViewItem = new ImageView(i);

		this.materiaux = materiaux;
		this.nom = nom;
		this.xPosition = position;
		
		imageViewItem.setX(xPosition);
	}
	
	public ImageView getImageView() {
		return imageViewItem;
	}
	
	public double getXCentre() {
		return xPosition + imageViewItem.getImage().getWidth() / 2;
	}
	
	public String getNom() {
		return nom + " de " + this.materiaux.toString();
	}
	public Materiaux getMateriaux() {
		return this.materiaux;
	}
	
	
	
}
