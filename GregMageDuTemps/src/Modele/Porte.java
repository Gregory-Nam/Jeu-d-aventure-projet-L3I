package Modele;

import java.io.File;

import application.Jeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Porte extends Interactif{

	private static final File FICHIER_PORTE_OUVERTE = new File("Images/Elements/Porte_Ouverte.png");
	private static final File FICHIER_PORTE_FERME = new File("Images/Elements/Porte_Ferme.png");
	
	private Salle sallesLieesParLaPorte [];
	private double xMin;
	private double xMax;
	private ImageView spritePorte;
	private boolean estOuverte;
	private boolean estPorteExtremite;
	
	
	public Porte(Salle salle1, Salle salle2, double x, boolean estPorteExtremite) {
		this.xMin = x;
		if(!estPorteExtremite) {
			initPorte();
			spritePorte.setX(x);
			this.xMax = xMin + spritePorte.getImage().getWidth();
		}
		else this.xMax = this.xMin + 60;
		sallesLieesParLaPorte = new Salle [2];
		sallesLieesParLaPorte [0] = salle1;
		sallesLieesParLaPorte [1] = salle2;
	}
	
	public Porte(Salle salle1, Salle salle2, double xSalle1, double xSalle2, boolean estPorteExtremite) {
		this(salle1, salle2, xSalle1, estPorteExtremite);
		
	}
		
	
	public void initPorte() {
		spritePorte = new ImageView();
		Image imgPorte = new Image(FICHIER_PORTE_FERME.toURI().toString());
		spritePorte.setImage(imgPorte);
	}
	
	@Override
	public void interagir() {
		/* code moche pour test *****************************************************/
		System.out.println("interagir");
		/* test sur l'adresse donc pas besoin de equals */
		if(Jeu.getSalleCourante() == sallesLieesParLaPorte[0])
			Jeu.setSalleCourante(sallesLieesParLaPorte[1]);
		else
			Jeu.setSalleCourante(sallesLieesParLaPorte[0]);
		
		if(this.xMin == 940) {
			this.xMin = 0;
			this.xMax = 50;
		}
		else if(this.xMin == 0){
			this.xMin = 940;
			this.xMax = 1000;
		}
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