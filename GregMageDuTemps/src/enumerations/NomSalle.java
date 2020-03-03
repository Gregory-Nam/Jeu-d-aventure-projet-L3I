package enumerations;
/**
 * @author Ahmadou Bamba MBAYE<br>
 * Cette ennumération contient la liste des salles dans cette ordre:
 *<ol>
 *     <li>Les Salles simples</li>
	 *     <ul>
	 *         <li>SALLE_DEPART: c'est la salle de depart</li>
	 *         <li>SALLE_1: la premiere salle </li>
	 *         <li>SALLE_2: la deuxieme salle</li>
	 *         <li>SALLE_3: la troisieme salle</li>
	 *     </ul>
 *     <li>Les salles contenants les items:</li>
	 *     <ul>
	 *         <li>SALLE_PIEGE: la salle contenant l'enigme piege</li>
	 *     	   <li>SALLE_BRONZE: la salle contenant l'item en bronze et l'enigme 1</li>
	 *         <li>SALLE_ARGENT: la salle contenant l'item en argent et l'enigme 2</li>
	 *         <li>SALLE_OR: la salle contenant l'item en or et l'enigme 3</li>
	 *     </ul>
 *
 *</ol>
 */
public enum NomSalle {
	SALLE_DEPART("Salle_depart"), SALLE_1("Salle_1"), SALLE_2("Salle_2"), SALLE_3("Salle_3"),
	SALLE_PIEGE("Salle_piege"), SALLE_OR("Salle_or"), SALLE_BRONZE("Salle_bronze"), SALLE_ARGENT("Salle_argent");
	/**
	 * Le nom de la salle
	 * @param nom
	 */
	private final String nom;
	/**
	 * Le nom de la salle
	 */
	private NomSalle(String nom) {
		this.nom = nom;
	}
	/**
	 * Elle retourne une chaine de caractere
	 * @return Le nom courant de la salle
	 */
	@Override
	public String toString() {
		return this.nom;
	}
	
}
