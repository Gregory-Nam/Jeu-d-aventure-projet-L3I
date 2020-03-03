package elements;

import java.io.File;

import application.Jeu;
import enumerations.Materiaux;
import enumerations.Periode;
/**
*La classe Horloge piege hérite de la classe Horloge. Une des pièces contiendra une horloge qui est censée permettre au joueur de retrouver sa dimension temporelle très simplement, sans objet à trouver et sans énigme à résoudre. Évidemment cela n’est pas aussi simple. S’il interagit avec cette dernière,
il détruira totalement l’espace-temps dans lequel il se situe et mourra
**/
public class HorlogePiege extends Horloge{


	
	public HorlogePiege(File image, Materiaux materiaux, Periode periodeApresActivation, double position) {
		super(image, materiaux, 0, periodeApresActivation, position);
	}

	@Override
	public void interagir() {
		Jeu.getInstanceUnique().terminer("Il semble que cette horloge était un piège...", false );
	}

}
