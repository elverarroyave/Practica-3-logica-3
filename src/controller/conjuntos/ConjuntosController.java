/*
 * Define los metodos para responder a eventos (acciones del usuario).
 */
package controller.conjuntos;

import model.conjuntos.Conjuntos;

/**
 * Clase controllador
 * 
 * @author hp
 */
public class ConjuntosController {
    
    /**
     * De tipo string, es el nombre de la carpeta donde se 
     * encuentran los archivos para crear los conjuntos.
     */
    private static final String FOLDER = "_db";
    
    /**
     * Es el arbol que guarda los conjuntos.
     */
    private static Conjuntos conjuntos = new Conjuntos();
    
    /**
     * Invoca el constructor de la clase Conjuntos
     * para crear el nuevo arbol de conjuntos a partir de un archivo con extension .txt.
     * 
     * @param filename de tipo string, es el nombre del archivo .txt.
     */
    public static void init(String filename) {
        System.out.println("[INFO] : archivo seleccionado : " + filename);
        conjuntos = new Conjuntos(FOLDER, filename);
    }
    
    /**
     * Retorna el numero de conjuntos distintos que existen en el arbol
     * para posteriormente mostrar ese numero al usuario
     * 
     * Responde la pregunta: ¿Cuantos grupos hay?
     * @return int numero de conjuntos .
     */
    public static int numeroDeConjuntos() {
        int n = conjuntos.numeroDeConjuntos();
        System.out.println("[INFO] : # de grafos : " + n);
        return n;
    }
    
    /**
     * Retorna una cadena con los numeros de telefono que hacen parte del conjunto
     * del numero de telefono que entra como parametro.
     * 
     * Responde la pregunta: ¿A qué grupo pertenece un numero de teléfono A?
     * @param number
     * @return de tipo String, es una cadena con los nodos que hacen parte del conjunto.
     */
    public static String conjuntoDelTelefono(long number) {
        long[][] datos = conjuntos.idDelConjuntoDelTelefono(number);
        long[] data = datos[1];
        if (data == null || datos == null) {
            return "!";
        }
        String str = "[+] ID DEL CONJUNTO: " + datos[0][0] + "\n";
        for (int i = 0; i < data.length; i++) {
            if (number == data[i]) {
                str += "["+(i+1)+"] - [" + data[i] + "] <---------- it's here.\n";
            } else {
                str += "["+(i+1)+"] - [" + data[i] + "]\n";
            }
        }
        System.out.println(str);
        return str;
    }
    
    /**
     * Retorna una cadena con los numeros de telefono relacionados en un grupo con identificador
     * 
     * Responde la pregunta: ¿Números de teléfono relacionados en un grupo con identificador x (nombre o numero que le asignen al grupo)?
     * @param id
     * @return de tipo String, es una cadena con los numberos que hacen parte del conjunto.
     */
    public static String numeroDeDatosDelConjunto(int id) {
        long[] datos = conjuntos.numeroDeDatosDelConjunto(id);
        if (datos == null) {
            return "!";
        }
        String str = "[+] ID DEL CONJUNTO: " + id + "\n";
        for (int i = 0; i < datos.length; i++) {
            str += "["+(i+1)+"] - [" + datos[i] + "]\n";
        }
        System.out.println(str);
        return str;
    }
}
