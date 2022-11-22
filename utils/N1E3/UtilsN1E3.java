package utils.N1E3;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/*
Modifica l’exercici anterior. Ara, en lloc de mostrar el resultat per la pantalla, guarda el resultat en 
un fitxer TXT.
*/

public class UtilsN1E3 {

	public static void main(String[] args) {
		String sCarpAct = System.getProperty("user.dir"); // Per defecte, si la carpeta no es passa com a paràmetre, considerem la carpeta actual on séstà executant el projecte
		if (args.length==1) {
			sCarpAct = args[0];
		}
		
		
		// Obrim el fitxer
		PrintWriter pw = null;
		try { 
			pw = new PrintWriter(new FileWriter("directoris.txt"));
		} catch (Exception ex) {
			System.out.println("Missatge error obrir fitxer: " + ex.getMessage());
			return;
		} 
		mostraCarpeta(sCarpAct, pw); // Escriu el llistat de directoris recursivament al fitxer directoris.txt
		
		// Tanquem el fitxer
		try {
			pw.close();
		} catch (Exception ex) {
			System.out.println("Missatge error tancament fitxer: " + ex.getMessage());
			return;
		}
		System.out.println("Llistat de la carpeta " + sCarpAct + " guardat al fitxer directoris.txt");
	}
	
	
	
	
	// mètode que mostra els fitxers que conté una carpeta, i les seves subcarpetes.
	static void mostraCarpeta(String sCarpAct, PrintWriter pw) {
		File carpeta = new File(sCarpAct);
		
		File[] lFitxers = carpeta.listFiles();
		
		if (lFitxers == null) {
			System.out.println(sCarpAct + " no es una carpeta valida.");
		} else if (lFitxers.length == 0) {
			System.out.println("No hi ha elements dins de la carpeta");
		}
		else {
			Arrays.sort(lFitxers); // Ordenem alfabèticament la llista de fitxers
			
			// guardem llistat de fitxers a directori.txt
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			for (int i=0; i< lFitxers.length; i++) {
		    	File fitxer = lFitxers[i];
		    	// Escriu al fitxer
		    	pw.println(String.format("%s (%s) - %s",
		        		sCarpAct + "\\" + fitxer.getName(),
		        		fitxer.isDirectory() ? "D" : "F",
		                sdf.format(fitxer.lastModified())
		                ));
		        
		        // Si es un directori cridem recursivament el mètode de mostrar els fitxers que conté
		        if (fitxer.isDirectory()) {
		        	mostraCarpeta(sCarpAct + "\\" + fitxer.getName(), pw);
		        }
		    }
		}
	}
	

}
