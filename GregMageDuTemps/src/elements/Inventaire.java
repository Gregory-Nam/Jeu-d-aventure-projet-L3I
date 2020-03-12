package elements;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Impl�mentations de l'Inventaire.
 * Un inventaire sera utilis� par le PersonnageJoueur. </br>
 * On peut �galement penser qu'il pourrait �tre utilis� pour
 * un conteneur tel un coffre.
 * @author Gr�gory NAM.
 * @author Hugo CHALIK.
 */
public class Inventaire {
	
	/**
	 * int constant qui repr�sente la capacit� de l'inventaire.
	 */
	private static final int TAILLE_INVENTAIRE = 4;
	
	/**
	 * Liste observable des items pr�sents dans l'inventaire.
	 */
	private ObservableList<Item> inventaireObserve;
	
	/**
	 * Constructeur de l'inventaire.
	 */
	public Inventaire() {
		inventaireObserve = FXCollections.observableArrayList(new ArrayList<Item>());
	}
	
	/**
	 * Permet d'ajouter un item dans l'inventaire s'il n'existe pas d�j�.
	 * @param i item � ajouter.
	 * @return vrai si l'item a �t� ajout�, faux sinon.
	 */
	public boolean ajouterItem(Item i) {
		if(!capaciteAtteinte() && !inventaireObserve.contains(i)) {
			return this.inventaireObserve.add(i);
		}
		return false;
	}
	
	/**
	 * Permet de supprimer un item dans l'inventaire.
	 * @param i item � supprimer.
	 */
	public void supprimerItem(Item i) {
		inventaireObserve.remove(i);
	}
	
	/**
	 * Renvoie l'item pass� en param�tre s'il existe sinon renvoie null.
	 * @param i item � r�cup�rer.
	 * @return item en param�tre s'il existe dans l'inventaire sinon null.
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
