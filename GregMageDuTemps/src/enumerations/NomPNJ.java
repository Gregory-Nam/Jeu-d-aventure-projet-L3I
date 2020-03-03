package enumerations;
/**
 *
 * @author Ahmadou Bamba MBAYE
 * Cette ennumération contient les personnages non joueur
 * qui proposent une enigme parmi les 5 énigmes:
 * <ul>
 *     <li>JACQUE</li>
 *     <li>NOMAUPIF</li>
 * </ul>
 *
 */
public enum NomPNJ {
	KLACE_HEUREOUVERRE("Klace Heureouverre"), SLYNE("Slyne"), CARPENTER("Carpenter"), ABITBOL("Abitbol"), ZAVIER_MAIS("Zavier Mais");
	/**
	*Le nom du personnage non joueur
	*/
	private final String nom;
	/**
	*Le constructeur initialisant le nom du personnage
	*/
	private NomPNJ(String s) {
		this.nom = s;
	}
	/**
	*Cette methode retourne le nom du personnage non joueur
	*@return le nom du personnage non joueur
	*/
	@Override
	public String toString() {
		return nom;
	}
	
}
