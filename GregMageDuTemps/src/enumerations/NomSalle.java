package enumerations;

/**
 * Enumeration des noms des salles.
 * 
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE.
 *
 */
public enum NomSalle {
	SALLE_DEPART("Salle_depart"), SALLE_1("Salle_1"), SALLE_2("Salle_2"), SALLE_3("Salle_3"),
	SALLE_PIEGE("Salle_piege"), SALLE_OR("Salle_or"), SALLE_BRONZE("Salle_bronze"), SALLE_ARGENT("Salle_argent");

	/**
	 * Le nom de la salle
	 */
	private final String nom;

	/**
	 * Constructeur privée de l'énumeration NomSalle
	 */
	private NomSalle(String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie le nom courant de la salle.
	 * @return le nom courant de la salle
	 */
	@Override
	public String toString() {
		return this.nom;
	}

}
