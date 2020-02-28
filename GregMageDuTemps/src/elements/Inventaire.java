package elements;

import java.util.ArrayList;

import fenetrePersonnalisee.EnigmePane;
import fenetrePersonnalisee.InventairePane;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;

public class Inventaire {

	private static final int TAILLE_INVENTAIRE = 4;
	private ObservableList<Item> inventaireObserve;
	
	public Inventaire() {
		inventaireObserve = FXCollections.observableArrayList(new ArrayList<Item>());
	}
	
	public boolean ajouterItem(Item i) {
		if(!capaciteAtteinte() && !inventaireObserve.contains(i))
			return this.inventaireObserve.add(i);
		
		return false;
	}
	
	public void supprimerItem(Item i) {
		inventaireObserve.remove(i);
	}
	
	public Item getItem(Item i) {
		return inventaireObserve.get(inventaireObserve.indexOf(i));
	}
	
	public ObservableList<Item> getInventaire(){
		return inventaireObserve;
	}
	private boolean capaciteAtteinte() {
		return inventaireObserve.size() == TAILLE_INVENTAIRE;
	}
	
	public void creerListener(InventairePane panneauEcoute) {
		inventaireObserve.addListener((Change<? extends Item> changement) -> {
			while(changement.next()) {
				if(changement.wasAdded())
					panneauEcoute.ajouterItem(inventaireObserve.get(changement.getFrom()));
				else if(changement.wasRemoved())
					panneauEcoute.supprimerItem(inventaireObserve.get(changement.getFrom()));

			}
		});
		
	}
	
	
}
