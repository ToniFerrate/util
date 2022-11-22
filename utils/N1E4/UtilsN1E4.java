package utils.N1E4;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

/*
Afegeix la funcionalitat de llegir qualsevol fitxer TXT i mostra el seu contingut per consola.
*/

public class UtilsN1E4 {

	public static void main(String[] args) {
		
		String sCarpAct = System.getProperty("user.dir"); // Per defecte, si la carpeta no es passa com a paràmetre, considerem la carpeta actual on s'està executant el projecte
		String sFile = "directoris.txt"; // Per defecte, si la ruta del fitxer no es passa com a paràmetre, considerem directoris.txt
		String operacio = "";
		if (args.length > 0) {
			operacio = args[0];
		}
		switch (operacio) {
			case "-dir":
				if(args.length==2) {
					sCarpAct = args[1]; // Si la carpeta a llistar es passa com a segon paràmetre
				}
				escriureDirectoris(sCarpAct);
				break;
			
			case "-read":
				if(args.length==2) {
					sFile = args[1]; // Si el fitxer a llegir es passa com a segon paràmetre
				}
				llegirFitxer(sFile);
				break;
				
			default:
				System.out.println("Operació incorrecta. Operacions disponibles: -dir -read");
		}
				
				
		
	}
	
	static void escriureDirectoris(String sCarpAct) {
		// Obrim el fitxer
		PrintWriter pw = null;
		try { 
			pw = new PrintWriter(new FileWriter("directoris.txt"));
		} catch (Exception ex) {
			System.out.println("Missatge error obrir fitxer: " + ex.getMessage());
			return;
		} 
		pw.println("Contingut de la carpeta " + sCarpAct +" :");
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
	
	static void llegirFitxer(String fitxer) {
		
		File fichero = new File(fitxer); // Fitxer del que volem llegir
		Scanner s = null;

		try {
			System.out.println("... Llegim el contingut del fitxer " + fitxer + " ...");
			s = new Scanner(fichero);

			// llegim linia a linia el fitxer
			while (s.hasNextLine()) {
				String linea = s.nextLine(); // Llegim la linia
				System.out.println(linea);
			}
		} catch (Exception ex) {
			System.out.println("Missatge: " + ex.getMessage());
		} finally {
			// Tanquem el fitxer tant si la lectura ha estat correcta com si no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Missatge 2: " + ex2.getMessage());
			}
		}
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
