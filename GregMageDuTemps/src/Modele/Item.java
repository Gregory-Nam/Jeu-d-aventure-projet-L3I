package Modele;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {

	private Materiaux materiaux;
	private String nom;
	private int xPosition;
	private ImageView imageViewItem;
	
	public Item(File f, int position) {
		Image i = new Image(f.toURI().toString());
		imageViewItem = new ImageView(i);

		this.xPosition = position;
		imageViewItem.setX(xPosition);
	}
	
	public ImageView getImageView() {
		return imageViewItem;
	}
	public double getXCentre() {
		return xPosition + imageViewItem.getImage().getWidth() / 2;
	}
	
}
