package utilitaire;

import Modele.Interactif;
import javafx.util.Pair;

public class PairObjetPosition {
	private Interactif i;
	private Pair <Double, Double> xPositions;
	
	PairObjetPosition(Interactif i, double xMin, double xMax) {
		xPositions = new Pair<Double,Double>(xMin, xMax);		
		this.i = i;
	}
	
	public Interactif getInteractif() {
		return i;
	}
	
	public Pair getXPosition() {
		return xPositions;
	}

}
