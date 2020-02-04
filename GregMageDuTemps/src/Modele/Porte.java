package Modele;

import utilitaire.SalleBoolean;

public class Porte extends Interactif{

	
	private SalleBoolean tab [];
	
	
	public Porte(Salle salleBefore, Salle salleAfter) {
		tab = new SalleBoolean [1];
		tab [0] = new SalleBoolean(salleBefore, false);
		tab [1] = new SalleBoolean(salleAfter, false);
	}
	
	@Override
	public void interagir() {
		// TODO Auto-generated method stub
		
	}
	
}
