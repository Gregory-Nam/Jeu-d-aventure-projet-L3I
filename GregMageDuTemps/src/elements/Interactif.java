package elements;

import javafx.scene.image.ImageView;
import javafx.util.Pair;

public abstract class Interactif {

	protected double xMin;
	protected double xMax;
	protected ImageView vueImageInteractif;
	
	public abstract ImageView getImageView();
	public abstract void interagir();
	public abstract double getXMin();
	public abstract double getXMax();
	public double getXCentre() {
		return (getXMin() + getXMax()) / 2;
	}
}
