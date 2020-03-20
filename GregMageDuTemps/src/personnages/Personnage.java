package personnages;


import java.util.HashMap;

import elements.Interactif;
import elements.Item;
import enumerations.Deplacements;
import javafx.scene.image.*;
import java.io.File;

/**
 * Impl�mentation de Personnage.
 * @author Gr�gory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE.
 */
public abstract class Personnage extends Interactif {
	/**
	 * HashMap qui contient les ImageView du personnage selon son orientation.
	 */
	protected HashMap<Deplacements, ImageView> spritesPersonnageHM;
	
	/**
	 * L'ImageView courant du personnage.
	 */
	protected ImageView spriteCourant;
	
	/**
	 * L'item dont est en possession le Personnage.
	 */
	protected Item itemEnPossession;
	
	/**
	 * Le constructeur de la classe Personnage.
	 */
	protected Personnage () {
		spritesPersonnageHM = new HashMap<Deplacements,ImageView>();
	}
	
	/**
	 * Permet de d�placer le Personnage � droite.
	 */
	public abstract void seDirigerADroite();
	
	/**
	 * Permet de d�placer le Personnage � gauche.
	 */
	public abstract void seDirigerAGauche();
	
	/**
	 * Permet d'initialiser les sprites du personnage dans la HashMap.
	 * @param sprites tableau de fichiers des images.
	 */
	public void initPersonnage(File sprites[]) {
		Deplacements[] deplacements = Deplacements.values();
		for(int i = 0; i < sprites.length; ++i) {
			Image img = new Image(sprites[i].toURI().toString());
			ImageView iv = new ImageView();
			iv.setImage(img);
			spritesPersonnageHM.put(deplacements[i], iv);
		}
	}
	
	/**
	 * Renvoie l'ImageView courante du Personnage.
	 * @return l'ImageView courante du Personnage.
	 */
	public ImageView getImageView() {
		return spriteCourant;
	}



	
	
}
