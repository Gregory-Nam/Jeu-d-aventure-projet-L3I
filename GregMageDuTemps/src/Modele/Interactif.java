package Modele;

import javafx.util.Pair;

public abstract class Interactif {

	protected double xMin;
	protected double xMax;
	
	public abstract void interagir();
	public abstract double getXMin();
	public abstract double getXMax();
}
