package utilitaire;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enumerations.TypeDialogue;

/**
 * Classe utilitaire pour l'analyse de fichier JSON.
 * @author Gr�gory NAM.
 *
 */
public final class AnalyseFichierEnigmeUtil {

	
	/* CLASSE UTILITAIRE, ON VEUT PAS QU'ELLE SOIT INSTANCIEE */
	private AnalyseFichierEnigmeUtil() {}
	
	/**
	 * Renvoie le dialogue pour le PNJ et le type de dialogue pass� en param�tre.
	 * @param nomPNJ nom du PNJ dont on veut le dialogue
	 * @param type le type de dialogue que l'on souhaite r�cup�rer.
	 * @return le dialogue pour le PNJ et le type de dialogue pass� en param�tre.
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
