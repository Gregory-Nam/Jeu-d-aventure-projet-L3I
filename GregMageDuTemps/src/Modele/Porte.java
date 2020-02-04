package Modele;

import javafx.scene.image.ImageView;
import utilitaire.PaireSalleEtat;

public class Porte extends Interactif{

	
	private PaireSalleEtat tab [];
	private double xMin;
	private double xMax;
	private ImageView spritePorte;
	private boolean estOuverte;
	
	
	public Porte(Salle salle1, Salle salle2, double x) {
		this.xMin = x;
		this.xMax = xMin + spritePorte.getImage().getWidth();
		tab = new PaireSalleEtat [1];
		tab [0] = new PaireSalleEtat(salle1, false);
		tab [1] = new PaireSalleEtat(salle2, false);
	}
	
	@Override
	public void interagir() {
		// Changement du sprite courant et changement de salle
		
	}
	
	public double getXMin() {
		
		return xMin;
	}
	
	public double getXMax() {
		return xMax;
	}
	
}
