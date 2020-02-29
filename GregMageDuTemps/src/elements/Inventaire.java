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
		if(!capaciteAtteinte() && !inventaireObserve.contains(i)) {
			this.inventaireObserve.add(i);
			System.out.println(inventaireObserve);
		}
		
		return false;
	}
	
	public void supprimerItem(Item i) {
		System.out.println("supprime item");
		System.out.println(i);
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
				if(changement.wasAdded()) {
					panneauEcoute.ajouterItem(inventaireObserve.get(changement.getFrom()));
				}
				else if(changement.wasRemoved()) {
					panneauEcoute.supprimerItem(changement.getRemoved().get(0));
				}
				else if(changement.wasUpdated())
					System.out.println("update");

			}
		});
		
	}
	
	
}
