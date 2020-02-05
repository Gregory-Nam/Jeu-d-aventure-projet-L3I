package Modele;

import application.Jeu;
import javafx.scene.image.ImageView;


public class Porte extends Interactif{

	
	private Salle tab [];
	private double xMin;
	private double xMax;
	private ImageView spritePorte;
	private boolean estOuverte;
	
	
	public Porte(Salle salle1, Salle salle2, double x) {
		/* constructeur moche pour test */
		this.xMin = x;
		//this.xMax = xMin + spritePorte.getImage().getWidth();
		this.xMax = x + 20;
		tab = new Salle [2];
		tab [0] = salle1;
		tab [1] = salle2;
	}
	
	@Override
	public void interagir() {
		// Changement du sprite courant et changement de salle et appel de la methode static
		//petit test
		/* code moche pour test *****************************************************/
		System.out.println("interagir");
		Jeu.setSalleCourante(tab[1]);
	}
	
	public double getXMin() {
		return xMin;
	}
	
	public double getXMax() {
		return xMax;
	}
	
}
