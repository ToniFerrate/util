package utils.N1E2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/*
Afegeix a la classe de l’exercici anterior, la funcionalitat de llistar un arbre de directoris amb el
contingut de tots els seus nivells (recursivament) de manera que s'imprimeixin en pantalla en ordre 
alfabètic dins de cada nivell, indicant a més si és un directori (D) o un fitxer (F), i la seva última 
data de modificació.
*/

public class UtilsN1E2 {

	public static void main(String[] args) {
		String sCarpAct = System.getProperty("user.dir"); // Per defecte, si la carpeta no es passa com a paràmetre, considerem la carpeta actual on séstà executant el projecte
		if (args.length==1) {
			sCarpAct = args[0];
		}
		System.out.println("Contingut de la carpeta " + sCarpAct +" :");
		mostraCarpeta(sCarpAct);
	}
	
	
	
	
	// mètode que mostra els fitxers que conté una carpeta, i les seves subcarpetes.
	static void mostraCarpeta(String sCarpAct) {
		File carpeta = new File(sCarpAct);
		
		File[] lFitxers = carpeta.listFiles();
		
		if (lFitxers == null) {
			System.out.println(sCarpAct + " no es una carpeta valida.");
		} else if (lFitxers.length == 0) {
			System.out.println("No hi ha elements dins de la carpeta");
		}
		else {
			Arrays.sort(lFitxers); // Ordenem alfabèticament la llista de fitxers
			
			// Mostrem la llista de fitxers
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			for (int i=0; i< lFitxers.length; i++) {
		    	File fitxer = lFitxers[i];
		        System.out.println(String.format("%s (%s) - %s",
		        		sCarpAct + "\\" + fitxer.getName(),
		        		fitxer.isDirectory() ? "D" : "F",
		                sdf.format(fitxer.lastModified())
		                ));
		        
		        // Si es un directori cridem recursivament el mètode de mostrar els fitxers que conté
		        if (fitxer.isDirectory()) {
		        	mostraCarpeta(sCarpAct + "\\" + fitxer.getName());
		        }
		    }
		}
	}

}
