package elements;

import java.util.ArrayList;

public class Inventaire {

	private static final int TAILLE_INVENTAIRE = 3;
	private ArrayList<Item> inventaire;
	
	public Inventaire() {
		inventaire = new ArrayList<Item>(3);
	}
	
	public void ajoute(Item i) {
		if(capaciteAtteinte()) return;
		this.inventaire.add(i);
	}
	
	private boolean capaciteAtteinte() {
		if(inventaire.size() < TAILLE_INVENTAIRE)
			return false;
		else
			return true;
	}
	
	
}
