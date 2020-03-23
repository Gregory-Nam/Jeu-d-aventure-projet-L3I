package utilitaire;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enumerations.TypeDialogue;

/**
 * Classe utilitaire pour l'analyse de fichier JSON.
 * @author Grégory NAM.
 *
 */
public final class AnalyseFichierEnigmeUtil {

	
	/* CLASSE UTILITAIRE, ON VEUT PAS QU'ELLE SOIT INSTANCIEE */
	private AnalyseFichierEnigmeUtil() {}
	
	/**
	 * Renvoie le dialogue pour le PNJ et le type de dialogue passé en paramètre.
	 * @param nomPNJ nom du PNJ dont on veut le dialogue
	 * @param type le type de dialogue que l'on souhaite récupérer.
	 * @return le dialogue pour le PNJ et le type de dialogue passé en paramètre.
	 */
	public static String initDialoguesJSON(String nomPNJ, TypeDialogue type)  {
		JSONParser parser = new JSONParser();
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
