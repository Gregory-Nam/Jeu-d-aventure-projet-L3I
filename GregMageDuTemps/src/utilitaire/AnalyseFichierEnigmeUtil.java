package utilitaire;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enumerations.TypeDialogue;
/**
 * 
 *@author Ahmadou Bamba MBAYE
 *Cette classe permet d'analyser le fichier contenant les enigmes
 */
public final class AnalyseFichierEnigmeUtil {

	
	/* CLASSE UTILITAIRE, ON VEUT PAS QU'ELLE SOIT INSTANCIEE */
	/**
	 * CLASSE UTILITAIRE, ON VEUT PAS QU'ELLE SOIT INSTANCIEE
 	*/
	private AnalyseFichierEnigmeUtil() {}
	/**
	 * Cette methode contient deux parametres le nom du personnage non joueur et un type de dialogue.
	 * Elle crée une nouvelle variable de type JSONParse qui va contenant le JSONParse()
	 * @param nomPNJ
	 * Le nom du personnage non joueur
	 * @param type
	 * Le type de dialogue
	 * @return
	 * Elle retourne null
	 */
	public static String initDialoguesJSON(String nomPNJ, TypeDialogue type)  {
		JSONParser parser = new JSONParser();
		System.out.println(nomPNJ);
		try {
			Object obj = parser.parse(new FileReader("Enigmes/enigmes.json"));
			JSONObject json = (JSONObject) obj;
			json = (JSONObject) json.get(nomPNJ.toString());
			return (String)json.get(type.toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
