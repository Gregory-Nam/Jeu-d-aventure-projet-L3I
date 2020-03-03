package personnages;


import java.util.HashMap;

import elements.Interactif;
import elements.Item;
import enumerations.Deplacements;
import javafx.scene.image.*;
import java.io.File;
/**
 *
 * @author Ahmadou Bamba MBAYE.
 * Cette classe abstraite nommee personnage herite la classe Interactif.Elle gere les personnages joueur et les personnages non joueur
 * Elle gere le deplacement des personnages avec des methodes seDirigerADroite() quand le personnage se dirige vers la droite
 * et seDirigerAGauche() vers la gauche
 *
 *
 */
public abstract class Personnage extends Interactif {
	/**
	 * Le HashMap contient l'ensemble des des sprites pour le personnage..
	 *
	 */
	protected HashMap<Deplacements, ImageView> spritesPersonnageHM;
	/**
	 * La vue de l'image courante du personnage en faisant une paire (imageview, Deplacement)
	 */
	protected ImageView spriteCourant;
	/**
	*Litem étant en possession
	*/
	Item itemEnPossession;
	/**
	 * Le constructeur de la classe personnage cree un sprite personnage
	 * @param sprites Les sprites
	 */
	public Personnage () {
		spritesPersonnageHM = new HashMap<Deplacements,ImageView>();
	}
	/**
	 * La méthode abstraite seDirigerADroite() gére la sas ou le personnage se dirige vers la droite..
	 */
	public abstract void seDirigerADroite();
	/**
	 * La méthode abstraite seDirigerAGauche() gére la sas ou le personnage se dirige vers la gauche..
	 */
	public abstract void seDirigerAGauche();
	
	/**
	 * La methode initPersonnage() gere les sprites du personnage et les images associees.
	 * @param sprites Les sprites
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
	 * La methode abstraite getImageView() recupere l'image en vue
	 * @return Elle retourne le sprint courant
	 */
	public ImageView getImageView() {
		return spriteCourant;
	}



	
	
}
