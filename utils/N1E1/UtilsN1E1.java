package utils.N1E1;

import java.io.File;
import java.util.Arrays;

//Crea una classe que llisti alfabèticament el contingut d'un directori rebut per paràmetre.

public class UtilsN1E1 {

	public static void main(String[] args) {
		String sCarpAct = System.getProperty("user.dir"); // Per defecte, si la carpeta no es passa com a paràmetre, considerem la carpeta actual on séstà executant el projecte
		if (args.length==1) {
			sCarpAct = args[0];
		}
		System.out.println("Contingut de la carpeta: " + sCarpAct);
		mostrarCarpeta(sCarpAct);
	}
	
	static void mostrarCarpeta(String sCarpAct){
		File carpeta = new File(sCarpAct);
		
		String[] llistat = carpeta.list();

		if (llistat == null) {
			System.out.println(sCarpAct + " no és una carpeta vàlida.");
			return;
		} else if (llistat.length == 0) {
			System.out.println("No hi ha elements dins de la carpeta: " +sCarpAct);
		    return;
		}
		else {
			Arrays.sort(llistat); // Ordenem alfabèticament la llista de fitxers
		    for (int i=0; i< llistat.length; i++) {
		        System.out.println(llistat[i]);
		    }
		}
	}

}
