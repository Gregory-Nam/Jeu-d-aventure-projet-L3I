package elements;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import application.Jeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PorteMurale extends PorteExtremite{

	private static final File FICHIER_PORTE_OUVERTE = new File("Images/Elements/Porte_Ouverte.png");
	private static final File FICHIER_PORTE_FERME = new File("Images/Elements/Porte_Ferme.png");

	private ImageView spritePorte;
	private boolean estOuverte;
	
	public PorteMurale(Salle salle1, Salle salle2, double position) {
		super(salle1, salle2);
		initPorte();
		
		super.xMin = position;
		super.xMax = xMin + spritePorte.getImage().getWidth();
		spritePorte.setX(position);
		
		
		this.estOuverte = false;
	}
	
		
	public void initPorte() {
		spritePorte = new ImageView();
		Image imgPorte = new Image(FICHIER_PORTE_FERME.toURI().toString());
		spritePorte.setImage(imgPorte);
	}
	
	@Override
	public void interagir() {
		if(!estOuverte) {
			spritePorte.setImage(new Image(FICHIER_PORTE_OUVERTE.toURI().toString()));
			estOuverte = true;
		}
		super.interagir();
	}
	
	@Override
	public double getXMin() {
		return super.xMin;
	}
	
	@Override
	public double getXMax() {
		return super.xMax;
	}

	@Override
	public ImageView getImageView() {
		return spritePorte;
	}

}