package Modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Enigme {
	/* GESTION FICHIER */
	private FileReader fichierEnigme;
	private String ensembleDuTexte;
	private StringBuilder enigme;
	private StringBuilder dialogeApresBonneRep;
	private StringBuilder dialogeApresMauvaiseRep;
	private StringBuilder dialogeApresDejaRepondu;
	
	/* AFFICHAGE */
	
	public Enigme() throws Exception {
		try {
			fichierEnigme = new FileReader("Enigmes/fichierEnigmes.txt");
		}
		catch(FileNotFoundException exception) {
			exception.printStackTrace();
		}
		initString();
		rechercheDialogues("NOM PNJ 1"); 
		initDialogue(enigme);
		initDialogue(dialogeApresBonneRep);
		initDialogue(dialogeApresDejaRepondu);
		initDialogue(dialogeApresMauvaiseRep);
	}
	
	private void initString() {
		ensembleDuTexte = "";
		enigme = new StringBuilder("QUESTION");
		dialogeApresBonneRep = new StringBuilder("BONNE REPONSE");
		dialogeApresDejaRepondu = new StringBuilder("DEJA REPONDU");
		dialogeApresMauvaiseRep = new StringBuilder("MAUVAISE REPONSE");
	}

	private void initDialogue(StringBuilder str) {
		int indiceDebut = ensembleDuTexte.indexOf(str.toString()) + str.length() + 1;
		int indiceFin = ensembleDuTexte.indexOf("#", indiceDebut);
		str.replace(0, str.length(), ensembleDuTexte.substring(indiceDebut, indiceFin)); 
	}
	
	private void rechercheDialogues(String str) {
		BufferedReader buffer = new BufferedReader(fichierEnigme);
		String ligne = null;

		try {
			while ( (ligne = buffer.readLine()) != null)
			{    
				/* RECHERCHE DU NOM DE PNJ */
			    if(ensembleDuTexte.equals("") && ligne.equals(str)) {
			    	ensembleDuTexte += ligne + '\n';
			    }
			    /* RECHERCHE DU DELIMITEUR */
			    else if(ligne.equals("##"))
			    	break;
			    /* FAIT PARTIE DE CE QU'ON RECHERCHE */
			    else
			    	ensembleDuTexte += ligne + '\n';
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
