package elements;

import java.io.File;

import application.Jeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Porte extends Interactif{

	private static final File FICHIER_PORTE_OUVERTE = new File("Images/Elements/Porte_Ouverte.png");
	private static final File FICHIER_PORTE_FERME = new File("Images/Elements/Porte_Ferme.png");
	
	private Salle sallesLieesParLaPorte [];
	private ImageView spritePorte;
	private boolean estOuverte = false;
	private boolean estPorteExtremite;
	
	
	public Porte(Salle salle1, Salle salle2, double position, boolean estPorteExtremite) {
		this.estPorteExtremite = estPorteExtremite;
		super.xMin = position;
		if(!estPorteExtremite) {
			initPorte();
			spritePorte.setX(position);
			this.xMax = xMin + spritePorte.getImage().getWidth();
		}
		else super.xMax = this.xMin + 60;
		
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
		if(!estPorteExtremite && !estOuverte) {
			spritePorte.setImage(new Image(FICHIER_PORTE_OUVERTE.toURI().toString()));
			estOuverte = true;
		}
		/* TEST SUR L'ADRESSE (UNE SEULE INSTANCE EXISTE) */
		if(Jeu.getSalleCourante() == sallesLieesParLaPorte[0])
			Jeu.setSalleCourante(sallesLieesParLaPorte[1]);
		else
			Jeu.setSalleCourante(sallesLieesParLaPorte[0]);
		
		/* LES TESTS SUIVANT SONT A EFFECTUES SEULEMENT SI LA PORTE COURANTE */
		/* EST UNE PORTE EXTREMITE */
		if(!estPorteExtremite) return;
		
		if(this.xMin == Jeu.X_MAX_FENETRE) {
			this.xMin = Jeu.X_MIN_FENETRE;
			this.xMax = 50;
		}
		else if(this.xMin == Jeu.X_MIN_FENETRE) {
			this.xMin = Jeu.X_MAX_FENETRE;
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