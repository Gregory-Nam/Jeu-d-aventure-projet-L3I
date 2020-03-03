package elements;

import java.io.File;
import application.Jeu;
import enumerations.Materiaux;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.PersonnageJoueur;
/**
 *
 * @author Ahmadou Bamba MBAYE.
 *La classe Item represente les items (Bronze, Argent et Or), les items sont definis par leur materiaux, leur nom,
 *leur position et leur image.
 */
public class Item extends Interactif{
/**
	 *Les materiaux de type Materiaux
	 */
	private Materiaux materiaux;
	/**
	 *Le nom d'un item
	 */
	private String nom;
	/**
	 *L'image de l'item
	 */
	private ImageView imageViewItem;
	private ImageView imageViewItemPourInventaire;
	/**
	 * Le constructeur de la classe Item cree un item en precisant sa position.
	 * Il initialise et met a jour la position
	 * @param f Le fichier
	 * @param position La position
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
	/**
	 * Cette methode permet de recuperer l'image en vue.
	 * @return Elle retourne l'image qui est en vue.
	 */
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
