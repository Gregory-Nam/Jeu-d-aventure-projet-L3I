package Modele;

import java.io.File;

import application.Jeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Porte extends Interactif{

	
	private Salle tab [];
	private double xMin;
	private double xMax;
	private ImageView spritePorte;
	private boolean estOuverte;
	private boolean estPorteExtremite;
	
	
	public Porte(Salle salle1, Salle salle2, double x, File imagePorte, boolean estPorteExtremite) {
		if(!estPorteExtremite) {
			initPorte(imagePorte);
			spritePorte.setX(x);
		}
		/* constructeur moche pour test */
		this.xMin = x;
		this.xMax = xMin + spritePorte.getImage().getWidth();
		tab = new Salle [2];
		tab [0] = salle1;
		tab [1] = salle2;
	}
	
	public void initPorte(File imagePorte) {
		spritePorte = new ImageView();
		Image imgPorte = new Image(imagePorte.toURI().toString());
		spritePorte.setImage(imgPorte);
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

	@Override
	public ImageView getImageView() {
		return spritePorte;
	}
	
}
