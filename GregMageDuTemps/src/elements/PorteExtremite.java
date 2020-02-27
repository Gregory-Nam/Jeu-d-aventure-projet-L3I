package elements;

import application.Jeu;
import javafx.scene.image.ImageView;

public class PorteExtremite extends Interactif {

	protected Salle[] sallesLieesParLaPorte ;
	protected boolean estFaceGauche;
	
	public PorteExtremite(Salle salle1, Salle salle2) {
		super.xMin = Jeu.X_MAX_FENETRE;
		super.xMax = xMin;
		this.estFaceGauche = true;
		
		sallesLieesParLaPorte = new Salle [2];
		
		sallesLieesParLaPorte [0] = salle1;
		sallesLieesParLaPorte [1] = salle2;
	}
	
	@Override
	public ImageView getImageView() {
		return null;
	}

	@Override
	public void interagir() {
		if(estFaceGauche()) 
			Jeu.getInstanceUnique().setSalleCourante(sallesLieesParLaPorte[1]);
		else
			Jeu.getInstanceUnique().setSalleCourante(sallesLieesParLaPorte[0]);
	}

	private boolean estFaceGauche() {
		return (Jeu.getInstanceUnique().getSalleCourante() == sallesLieesParLaPorte[0]);
	}
	
	@Override
	public double getXMin() {
		return estFaceGauche() ? Jeu.X_MAX_FENETRE : Jeu.X_MIN_FENETRE;
	}

	@Override
	public double getXMax() {
		return getXMin();
	}
	
	
	
	
	

	

}
