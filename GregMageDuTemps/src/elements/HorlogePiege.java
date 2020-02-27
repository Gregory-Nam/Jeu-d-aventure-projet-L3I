package elements;

import java.io.File;

import application.Jeu;
import enumerations.Materiaux;

public class HorlogePiege extends Horloge{


	
	public HorlogePiege(File image, Materiaux materiaux, int periodeApresActivation, double position) {
		super(image, materiaux, 0, periodeApresActivation, position);
	}

	@Override
	public void interagir() {
		Jeu.getInstanceUnique().terminer("Il semble que cette horloge était un piège...", false );
	}

}
