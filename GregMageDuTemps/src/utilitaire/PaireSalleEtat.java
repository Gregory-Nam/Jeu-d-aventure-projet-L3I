package utilitaire;

import Modele.Salle;

public class PaireSalleEtat {

	private Salle salle;
	private boolean estActive;
	
	
	public PaireSalleEtat(Salle salle, boolean estActive) {
		super();
		this.salle = salle;
		this.estActive = estActive;
	}

	public Salle getSalle() {
		return salle;
	}


	public boolean isActive() {
		return estActive;
	}
	
	
}
