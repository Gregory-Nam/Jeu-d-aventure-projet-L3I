package enumerations;

/**
 * Enumération des noms des PNJ.
 * 
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @author Luca BEVILACQUA
 * @author Ahmadou Bamba MBAYE.
 *
 */

public enum NomPNJ {
	KLACE_HEUREOUVERRE("Klace Heureouverre"), SLYNE("Slyne"), CARPENTER("Carpenter"), ABITBOL("Abitbol"),
	ZAVIER_MAIS("Zavier Mais");

	/**
	 * Le nom du personnage non joueur.
	 */
	private final String nom;

	/**
	 * Le constructeur initialisant le nom du personnage.
	 * @param s nom du pnj.
	 */
	private NomPNJ(String s) {
		this.nom = s;
	}

	/**
	 * Cette méthode retourne le nom du personnage non joueur
	 * 
	 * @return le nom du personnage non joueur
	 */
	@Override
	public String toString() {
		return nom;
	}

}
