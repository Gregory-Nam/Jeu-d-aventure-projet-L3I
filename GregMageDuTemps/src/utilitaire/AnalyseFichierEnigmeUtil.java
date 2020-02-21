package utilitaire;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enumerations.TypeDialogue;
public final class AnalyseFichierEnigmeUtil {

	
	/* CLASSE UTILITAIRE, ON VEUT PAS QU'ELLE SOIT INSTANCIEE */
	private AnalyseFichierEnigmeUtil() {}
	
	public static String initDialoguesJSON(String nomPNJ, TypeDialogue type)  {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("Enigmes/enigmes.json"));
			JSONObject json = (JSONObject) obj;
			json = (JSONObject) json.get(nomPNJ);
			return (String)json.get(type.toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
