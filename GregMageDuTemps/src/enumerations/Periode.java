package enumerations;

/**
 * Enumération des périodes.
 * 
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE.
 *
 */
public enum Periode {
	PERIODE_1("Periode_1"), PERIODE_2("Periode_2"), PERIODE_3("Periode_3"), PERIODE_OBJECTIF("Periode_objectif");

	/**
	 * La période
	 */
	private final String periode;

	/**
	 * Toutes les périodes.
	 */
	private static final Periode[] periodes = values();

	/**
	 * Constructeur privé de l'énumération Periode.
	 * 
	 * @param s
	 */
	private Periode(String s) {
		this.periode = s;
	}

	/**
	 * renvoie la période précédente.
	 * 
	 * @return la période précédente.
	 */
	public Periode precedente() {
		return ordinal() != 0 ? periodes[ordinal() - 1] : periodes[0];
	}

	/**
	 * renvoie la période suivante.
	 * 
	 * @return la période suivante.
	 */
	public Periode suivante() {
		return ordinal() != periodes.length - 1 ? periodes[ordinal() + 1] : periodes[ordinal()];
	}

	/**
	 * renvoie la période.
	 */
	@Override
	public String toString() {
		return periode;
	}

}
