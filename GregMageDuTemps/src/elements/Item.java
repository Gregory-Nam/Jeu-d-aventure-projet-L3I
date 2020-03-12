package elements;

import java.io.File;
import application.Jeu;
import enumerations.Materiaux;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;

/**
 * Impl�mentations des items du jeu.
 * @author Gregory NAM.
 * @author Hugo CHALIK.
 * @author Luca BEVILACQUA.
 * @author Ahmadou Bamba MBAYE.
 */
public class Item extends Interactif{
	/**
	 * Le mat�riaux de l'item.
	 */
	
	private Materiaux materiaux;
	
	/**
	 * Le nom de l'Item.
	 */
	
	private String nom;
	
	/**
	 * L'image de l'item.
	 */
	private ImageView imageViewItem;
	
	/*
	 * L'imageView de l'item pour l'inventaire.
	 */
	
	private ImageView imageViewItemPourInventaire;
	
	/**
	 * Constructeur d'Item.
	 * @param image Fichier de l'image de l'Item dans le jeu.
	 * @param imagePourInventaire Fichier de l'image pour l'inventaire. 
	 * @param materiaux Mat�riaux de l'Item.
	 * @param position Position de l'Item.
	 * @param nom Nom de l'Item.
	 */
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
	
	/**
	 * Renvoie l'ImageView de l'item pour l'inventaire.
	 * @return l'ImageView de l'item pour l'inventaire.
	 */
	public ImageView getImageViewPourInventaire() {
		return imageViewItemPourInventaire;
	}
	
	/**
	 * Renvoie le nom complet de l'Item (avec son mat�riaux).
	 * @return le nom complet de l'Item (avec son mat�riaux).
	 */
	public String getNom() {
		return nom + " en " + this.materiaux.toString();
	}
	
	/**
	 * Renvoie le materiaux de l'item.
	 * @return le materieux de l'item.
	 */
	public Materiaux getMateriaux() {
		return this.materiaux;
	}

	/**
	 * L'interaction avec un item permet de l'ajouter dans l'inventaire
	 * du PersonnageJoueur, de la supprimer de la salle et �galement
	 * de mettre � jour les objets interactifs sur la scene.
	 */
	@Override
	public void interagir() {
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
