package elements;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
<<<<<<< HEAD
 * Implémentations de l'Inventaire.
 * Un inventaire sera utilisé par le PersonnageJoueur. </br>
 * On peut également penser qu'il pourrait être utilisé pour
 * un conteneur tel un coffre.
 * @author Grégory NAM.
=======
 * Implementations de l'Inventaire.
 * Un inventaire sera utilise par le PersonnageJoueur. 
 * </br>
 * On peut egalement penser qu'il pourrait etre utilise pour
 * un conteneur tel un coffre.
 * @author Gregory NAM.
>>>>>>> 79ada19cbb7222020d137509038cabd0e19c8007
 * @author Hugo CHALIK.
 */
public class Inventaire {
	
	/**
	 * int constant qui représente la capacité de l'inventaire.
	 */
	private static final int TAILLE_INVENTAIRE = 4;
	
	/**
	 * Liste observable des items présents dans l'inventaire.
	 */
	private ObservableList<Item> inventaireObserve;
	
	/**
	 * Constructeur de l'inventaire.
	 */
	public Inventaire() {
		inventaireObserve = FXCollections.observableArrayList(new ArrayList<Item>());
	}
	
	/**
	 * Permet d'ajouter un item dans l'inventaire s'il n'existe pas déjà.
	 * @param i item à ajouter.
	 * @return vrai si l'item a été ajouté, faux sinon.
	 */
	public boolean ajouterItem(Item i) {
		if(!capaciteAtteinte() && !inventaireObserve.contains(i)) {
			return this.inventaireObserve.add(i);
		}
		return false;
	}
	
	/**
	 * Permet de supprimer un item dans l'inventaire.
	 * @param i item à supprimer.
	 */
	public void supprimerItem(Item i) {
		inventaireObserve.remove(i);
	}
	
	/**
	 * Renvoie l'item passé en paramètre s'il existe sinon renvoie null.
	 * @param i item é récupérer.
	 * @return item en paramètre s'il existe dans l'inventaire sinon null.
	 */
	public Item getItem(Item i) {
		return inventaireObserve.get(inventaireObserve.indexOf(i));
	}
	
	/**
	 * renvoie l'ObservableList des items.
	 * @return l'ObservableList des items.
	 */
	public ObservableList<Item> getInventaire(){
		return inventaireObserve;
	}
	
	/**
	 * Renvoie vrai si la capacite est atteinte, faux sinon.
	 * @return vrai si la capacite est atteinte, faux sinon.
	 */
	private boolean capaciteAtteinte() {
		return inventaireObserve.size() == TAILLE_INVENTAIRE;
	}

}
