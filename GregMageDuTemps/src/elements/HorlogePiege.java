package elements;

import java.io.File;

import application.Jeu;
import enumerations.Materiaux;

public class HorlogePiege extends Horloge{

	public HorlogePiege(File image, Materiaux materiaux, int nbItemManquant, double position) {
		super(image, materiaux, nbItemManquant,0, position);
	}
	
	@Override
	public void interagir() {
		Jeu.getInstanceUnique().terminer("Il semble que cette horloge était un piège...");
	}

}
