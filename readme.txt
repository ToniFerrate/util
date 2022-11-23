#Java Utils

Per executar cada aplicació (cada exercici) desde la línia de comandes colocar-se desde el prompt a l'arrel del directori de *.class que conté els packages (usualment directori bin creat per Eclipse) i executar:
java <nom del package>.<nom de la classe main> <paràmetres que corresponguin en cada cas>

Exemples:


##N1E1
Ex 1:
java utils.N1E1.UtilsN1E1
(Si no té paràmetres, llista el directori actual)

Ex 2:
java utils.N1E1.UtilsN1E1 "C:\Users\Toni Ferraté\Desktop\eclipse\utils"
(llista el directori passat com a paràmetre)



##N1E2

Ex 1:
java utils.N1E2.UtilsN1E2
(Si no té paràmetres, llista recursivament el directori actual)

Ex 2:
java utils.N1E2.UtilsN1E2 "C:\Users\Toni Ferraté\Desktop\eclipse\utils"
(llista recursivameent el directori passat com a paràmetre)



##N1E3

Ex 1:
java utils.N1E3.UtilsN1E3
(Si no té paràmetres, llista recursivament el directori actual i el guarda a un fitxer de nom directoris.txt 
ubicat a la carpeta actual)

Ex 2:
java utils.N1E3.UtilsN1E3 "C:\Users\Toni Ferraté\Desktop\eclipse\utils"
(llista recursivameent el directori passat com a paràmetre i el guarda a un fitxer de nom directoris.txt 
ubicat a la carpeta actual)



##N1E4
Té dues operacions:

-dir 
(llista el directori passat com a segon paràmetre i ho guarda al fitxer de nom directoris.txt ubicat al directori actual.
Si no es passa segón paràmetre llista el directori actual)

-read 
(llegeix el fitxer passat com a segon paràmetre i el mostra en pantalla. Si no es passa segón paràmetre 
llegeix el fitxer directoris.txt del directori actual)

Ex 1:
java utils.N1E4.UtilsN1E4 -dir "C:\Users\Toni Ferraté\Desktop\eclipse\utils"

Ex 2:
java utils.N1E4.UtilsN1E4 -read "C:\Users\Toni Ferraté\Desktop\eclipse\utils\fitxer.txt"



##N1E5
Té quatre operacions:

-dir 
(llista el directori passat com a segon paràmetre i ho guarda al fitxer de nom directoris.txt ubicat al directori actual.
Si no es passa segón paràmetre llista el directori actual)

-read 
(llegeix el fitxer passat com a segon paràmetre i el mostra en pantalla. Si no es passa segón paràmetre 
llegeix el fitxer directoris.txt del directori actual)

-ser
(Serialitza un Objecte HashMap al fitxer hashmap.ser ubicat a la carpeta actual)

-des
(Deserialitza un Objecte HashMap del fitxer hashmap ubicat a la carpeta actual)

Ex 1:
java utils.N1E5.UtilsN1E5 -dir "C:\Users\Toni Ferraté\Desktop\eclipse\utils"

Ex 2:
java utils.N1E5.UtilsN1E5 -read "C:\Users\Toni Ferraté\Desktop\eclipse\utils\fitxer.txt"

Ex 3:
java utils.N1E5.UtilsN1E5 -ser

Ex 4:
java utils.N1E5.UtilsN1E5 -des

