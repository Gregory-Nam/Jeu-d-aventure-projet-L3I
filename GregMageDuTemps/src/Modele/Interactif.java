package Modele;

import javafx.scene.image.ImageView;
import javafx.util.Pair;

public abstract class Interactif {

	protected double xMin;
	protected double xMax;
	
	public abstract ImageView getImageView();
	public abstract void interagir();
	public abstract double getXMin();
	public abstract double getXMax();
}
