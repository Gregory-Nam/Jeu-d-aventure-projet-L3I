package elements;

import java.io.File;
import java.util.Arrays;

import application.Jeu;
import enumerations.Materiaux;
import enumerations.Periode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import personnages.Personnage;
import personnages.PersonnageJoueur;
import personnages.PersonnageNonJoueur;
/**
 *
 * @author Ahmadou Bamba MBAYE.
 *La classe Horloge herite la classe Interactif.
 *Elle représente l'horloge avec un nbObjetsPourActiver,
 *un boolean pour l'activer et la desactiver.L'horloge1 necessite un item Remontoir en bronze pour l'horloge et
 *un item de cle bronze. L'horloge2 necessite un item Remontoir en argent pour l'horloge et
 *un item de cle argent. L'horloge3 necessite un item en aiguille et remontoir en or pour l'horloge et
 *un item de cle en or
 */
public class Horloge extends Interactif {
	/**
	*La période après l'activation de l'horloge
	*/
	private Periode periodeApresActivation;
	private Item[] itemPourActiver;
	/**
	 * un boolean qui vérifie si l'horloge est activée
	 */
	
	private boolean aEteActive;
	private Materiaux materiauxHorloge;
	
	public Horloge(File image, Materiaux materiaux, int nbItemManquant, Periode periodeApresActivation, double position) {
		Image i = new Image(image.toURI().toString());
		
		super.vueImageInteractif = new ImageView(i);
		super.xMin = position;
		super.xMax = super.xMin + i.getWidth();
		
		vueImageInteractif.setX(position);
		
		this.materiauxHorloge = materiaux;
		this.aEteActive = false;
		this.itemPourActiver = new Item[nbItemManquant];
		this.periodeApresActivation = periodeApresActivation;
	}
	
	@Override
	public void interagir() {
		/* L'HORLOGE A DEJA ETE ACTIVE */
		if(aEteActive) return;
		Item item = PersonnageJoueur.getInstanceUnique().getItemEnMain();
		if(item == null) {
			Jeu.getInstanceUnique().afficheMessage("Je devrais prendre un item...", 1);
			return;
		}
		if(Jeu.getInstanceUnique().getPeriodeCourante() != periodeApresActivation.precente()) {
			Jeu.getInstanceUnique().terminer("Tu n'�tais pas dans le bon espace temps pour activer cet horloge...", false);
			PersonnageJoueur.getInstanceUnique().replacerGauche();
			PersonnageJoueur.getInstanceUnique().enleverItemEnMain();			
			return;
		}
		
		if(insererItemManquant(item) < 0) return;
		PersonnageJoueur.getInstanceUnique().enleverItemEnMain();
		
		/* SI ON PEUT ACTIVE L'HORLOGE ON L'ACTIVE */
		if(!peutEtreActive()) {
			Jeu.getInstanceUnique().afficheMessage("Ce ne sera pas suffisant", 1);
			return;
		}
		aEteActive = true;
		
		Jeu.getInstanceUnique().changerDePeriode();
	}

	public boolean peutEtreActive() {
		return itemPourActiver[itemPourActiver.length - 1] != (null);
	}

	private int insererItemManquant(Item item) {
		if(!item.getMateriaux().equals(this.materiauxHorloge)) {
			Jeu.getInstanceUnique().terminer("L'horloge a été cassé par cet item en " + item.getMateriaux().toString(), false);
			return -1;
		}
		
		for(int i = 0; i < itemPourActiver.length; ++i) {
			/* ITEM DEJA DANS L'HORLOGE */
			if(itemPourActiver[i] == item) return -1;
			/* ENDROIT A INSERER L'ITEM */
			if(itemPourActiver[i] == null) {
				itemPourActiver[i] = item;
				return 1;
			}
		}
		return -1;
	}
/**
	 * Cette methode recupere la valeur minimal.
	 * @return Elle retourne la valeur minimale
	 */
	@Override
	public double getXMin() {
		return super.xMin;
	}
/**
	 * Cette methode recupere la valeur maximale.
	 * @return Elle retourne la valeur maximale
	 */
	@Override
	public double getXMax() {
		return super.xMax;
	}
/**
	 * Cette methode recupere la vue de l'image.
	 * @return Elle retourne une image
	 */
	@Override
	public ImageView getImageView() {
		return super.vueImageInteractif;
	}

	public Materiaux getMateriaux() {
		return this.materiauxHorloge;
	}
}
