package Modele;

import javafx.scene.image.ImageView;


public class Porte extends Interactif{

	
	private Salle tab [];
	private double xMin;
	private double xMax;
	private ImageView spritePorte;
	private boolean estOuverte;
	
	
	public Porte(Salle salle1, Salle salle2, double x) {
		this.xMin = x;
		this.xMax = xMin + spritePorte.getImage().getWidth();
		tab = new Salle [1];
		tab [0] = new Salle(salle1);
		tab [1] = new Salle(salle2);
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
