package enumerations;

public enum NomPNJ {
	KLACE("Klace"), NOM_PNJ_2("NOM_PNJ2"), NOM_PNJ_3("NOM_PNJ3"), NOM_PNJ_4("NOM_PNJ4"), NOM_PNJ_5("NOM_PNJ5");
	
	private final String nom;
	
	private NomPNJ(String s) {
		this.nom = s;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
}
