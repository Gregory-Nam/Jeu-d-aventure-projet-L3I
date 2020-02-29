package enumerations;

public enum NomPNJ {
	KLACE_HEUREOUVERRE("Klace Heureouverre"), SLYNE("Slyne"), CARPENTER("Carpenter"), ABITBOL("Abitbol"), ZAVIER_MAIS("Zavier Mais");
	
	private final String nom;
	
	private NomPNJ(String s) {
		this.nom = s;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
}
