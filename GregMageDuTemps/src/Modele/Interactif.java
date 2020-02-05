package Modele;

import javafx.util.Pair;

public abstract class Interactif {

	
	public abstract <T extends Interactif> T interagir();
	public abstract double getXMin();
	public abstract double getXMax();
}
