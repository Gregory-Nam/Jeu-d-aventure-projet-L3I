package Modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class AnalyseFichierEnigmeUtil {

	
	/* CLASSE UTILITAIRE, ON VEUT PAS QU'ELLE SOIT INSTANCIEE */
	private AnalyseFichierEnigmeUtil() {}
	

	public static String initDialogue(String chaine, TypeDialogue type) {
		int indiceDebut = chaine.indexOf(type.toString()) + type.toString().length() + 1;
		int indiceFin = chaine.indexOf("#", indiceDebut);
		return chaine.subSequence(indiceDebut, indiceFin).toString();
	}
	
	public static String rechercheDialogues(String str) {
		String ensembleDesDialogues = "";
		String ligne = null;
		
		FileReader fichierEnigme;
		BufferedReader buffer;
		
		try {
			fichierEnigme = new FileReader("Enigmes/fichierEnigmes.txt");
			buffer = new BufferedReader(fichierEnigme);
		}
		catch(FileNotFoundException exception) {
			exception.printStackTrace();
			return null;
		}

		try {
			while ( (ligne = buffer.readLine()) != null)
			{    
				/* RECHERCHE DU NOM DE PNJ */
			    if(ensembleDesDialogues.equals("") && ligne.equals(str)) {
			    	ensembleDesDialogues += ligne + '\n';
			    }
			    /* RECHERCHE DU DELIMITEUR */
			    else if(ligne.equals("##"))
			    	break;
			    /* FAIT PARTIE DE CE QU'ON RECHERCHE */
			    else
			    	ensembleDesDialogues += ligne + '\n';
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return ensembleDesDialogues;
		
	}
}
