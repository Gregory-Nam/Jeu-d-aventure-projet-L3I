package elements;

import java.io.File;
import application.Jeu;
import enumerations.Materiaux;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;

public class Item extends Interactif{

	private Materiaux materiaux;
	private String nom;
	private ImageView imageViewItem;
	private ImageView imageViewItemPourInventaire;
	
	public Item(File image, File imagePourInventaire, Materiaux materiaux, int position, String nom) {
		Image i = new Image(image.toURI().toString());
		imageViewItem = new ImageView(i);
		
		super.xMin = position;
		super.xMax = super.xMin + i.getWidth();
		
		i = new Image(imagePourInventaire.toURI().toString());
		imageViewItemPourInventaire = new ImageView(i);

		this.materiaux = materiaux;
		this.nom = nom;

		
		
		imageViewItem.setX(xMin);
	}
	
	@Override
	public ImageView getImageView() {
		return imageViewItem;
	}
	
	public ImageView getImageViewPourInventaire() {
		return imageViewItemPourInventaire;
	}
	
	public String getNom() {
		return nom + " en " + this.materiaux.toString();
	}
	public Materiaux getMateriaux() {
		return this.materiaux;
	}

	@Override
	public void interagir() {
		System.out.println("lol");
		PersonnageJoueur.getInstanceUnique().mettreItemdansInventaire(this);
		Jeu.getInstanceUnique().getSalleCourante().supprimerInteractif(this);
		Jeu.getInstanceUnique().initObjetInteractif();
	}

	@Override
	public double getXMin() {
		return super.xMin;
	}

	@Override
	public double getXMax() {
		return super.xMax;
	}
	
	
	
}
