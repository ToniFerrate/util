package utils.N1E5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
Afegeix la funcionalitat de llegir qualsevol fitxer TXT i mostra el seu contingut per consola.
*/

public class UtilsN1E5 {

	public static void main(String[] args) {
		
		String sCarpAct = System.getProperty("user.dir"); // Per defecte, si la carpeta no es passa com a paràmetre, considerem la carpeta actual on séstà executant el projecte
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
				
			case "-ser":
				serialitzar();
				break;
				
			case "-des":
				deserialitzar();
				break;
				
			default:
				System.out.println("Operació incorrecta. Operacions disponibles: -dir -read -ser -des");
		}
	}
	
	static void serialitzar() {
		 HashMap<Integer, String> hmap = new HashMap<Integer, String>();
         //Adding elements to HashMap
         hmap.put(11, "Joan");
         hmap.put(2, "Josep");
         hmap.put(33, "Toni");
         hmap.put(9, "Maria");
         hmap.put(3, "Cristina");
         try
         {
        	 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hashmap.ser"));
             oos.writeObject(hmap);
             oos.close();
             System.out.printf("Serialized HashMap data is saved in hashmap.ser");
         } catch(IOException ioe){
             ioe.printStackTrace();
         }
    }
	
	static void deserialitzar() {
	      HashMap<Integer, String> map = null;
	      try {
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hashmap.ser"));
	         map = (HashMap<Integer, String>) ois.readObject();
	         ois.close();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Class not found");
	         c.printStackTrace();
	         return;
	      }
	      System.out.println("Deserialized HashMap..");
	      // Display content using Iterator
	      for (int k : map.keySet()) {
	    	  System.out.println("clau: "+ k + " & Valor: " + map.get(k));
	      }
	}
	
	
	
	static void escriureDirectoris(String sCarpAct) {
		// Obrim el fitxer
		PrintWriter pw = null;
		try { 
			pw = new PrintWriter(new FileWriter("directoris.txt"));
		} catch (Exception ex) {
			System.out.println("Missatge error obrir fitxer: " + ex.getMessage());
		} 
		pw.println("Contingut de la carpeta " + sCarpAct +" :");
		mostraCarpeta(sCarpAct, pw); // Escriu el llistat de directoris recursivament al fitxer directoris.txt
		
		// Tanquem el fitxer
		try {
			pw.close();
		} catch (Exception ex) {
			System.out.println("Missatge error tancament fitxer: " + ex.getMessage());
		}
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
