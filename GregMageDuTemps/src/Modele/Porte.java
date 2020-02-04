package Modele;

import javafx.scene.image.ImageView;
import utilitaire.PaireSalleEtat;

public class Porte extends Interactif{

	
	private PaireSalleEtat tab [];
	private double XMin;
	private double XMax;
	private ImageView spriteCourant;
	private boolean estOuverte;
	
	
	public Porte(Salle salleBefore, Salle salleAfter, double x) {
		
		this.XMin = x;
		tab = new PaireSalleEtat [1];
		tab [0] = new PaireSalleEtat(salleBefore, false);
		tab [1] = new PaireSalleEtat(salleAfter, false);
	}
	
	@Override
	public void interagir() {
		// Changement du sprite courant et changement de salle
		
	}
	
	public double getXMin() {
		
		return XMin;
	}
	
	public double getXMax() {
		return XMax;
	}
	
}
