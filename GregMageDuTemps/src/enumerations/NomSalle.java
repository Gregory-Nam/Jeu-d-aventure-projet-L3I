package enumerations;

public enum NomSalle {
	SALLE_DEPART("Salle_depart"), SALLE_1("Salle_1"), SALLE_2("Salle_2"), SALLE_3("Salle_3"),
	SALLE_PIEGE("Salle_piege"), SALLE_OR("Salle_or"), SALLE_BRONZE("Salle_bronze"), SALLE_ARGENT("Salle_argent");
	
	private final String nom;
	
	private NomSalle(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
	
}
