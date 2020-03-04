/*
 * Define los metodos para responder a eventos (acciones del usuario).
 */
package controller.matriz;

import model.matriz.Matriz;
import model.matriz.Nodo;



/**
 * Clase controllador
 * 
 * @author hp
 */
public class MatrizController {
    
    /**
     * De tipo string, es el nombre de la carpeta donde se 
     * encuentran los archivos para crear la matriz de adyacencia.
     */
    private static final String FOLDER = "_db";
    
    /**
     * Es el matriz de adyacencia representada como lista ligada.
     */
    private static Matriz matriz = new Matriz();
    
    /**
     * Invoca el constructor de la clase Matriz
     * para crear la nueva matriz a partir de un archivo con extensino .txt.
     * 
     * @param filename de tipo string, es el nombre del archivo .txt.
     */
    public static void init(String filename) {
        System.out.println("[INFO] : archivo seleccionado : " + filename);
        matriz = new Matriz(FOLDER, filename);
    }
    
    /**
     * Retorna el numero de datos distintos que existen en la matriz
     * para posteriormente mostrar ese numero al usuario
     * 
     * @return int numero de datos .
     */
    public static int numeroDeDatos() {
        int n = matriz.numeroDeVertices();
        System.out.println("[INFO] : # de datos en la DB : " + n);
        return n;
    }
    
    /**
     * Retorna el numero de llamadas realizadas por un numero
     * de telefono [number].
     * 
     * @param number numero del cual se quiere saber la cantidad de llamadas que ha realizado.
     * @return int numero de datos.
     */
    public static int numeroDeLLamadasRealizadasPor(long number) {
        int n = matriz.gradoDelVertice(number);
        System.out.println("[INFO] : # de llamadas realizadas por: " + number + " : " + n);
        return n;
    }
    
    /**
     * Retorna el camino de llamadas mas corto existente entre dos numeros.
     * 
     * @param a de tipo long, es el vertice desde el cual se inicia el recorrdio.
     * @param b de tipo long, es el vertice hasta el cual se llega el recorrdio.
     * @return de tipo string, es el camino más corto existente.
     */
    public static String caminoCorto(long a, long b) {
        String str = "";
        long[] aux = matriz.caminoCorto(a, b);
        if (aux != null) {
            for (int i = 0; i < aux.length; i++) {
                if (i == aux.length - 1)
                    str += "["+aux[i]+"]";
                else
                    str += "["+aux[i]+"]" + " --> ";
            }
        } else {
            str = "no es posible llegar de [" + a + "] a [" + b + "]";
        }
        System.out.println("[INFO] : " + str);
        return str;
    }
    
    /**
     * Retorna el camino de llamadas más corto y dirigido existente entre dos números.
     * 
     * @param a de tipo long, es el vertice desde el cual se inicia el recorrdio.
     * @param b de tipo long, es el vertice hasta el cual se llega el recorrdio.
     * @return de tipo string, es el camino más corto existente.
     */
    public static String caminoCortoDirigido(long a, long b) {
        String str = "";
        Nodo[] aux = matriz.caminoCortoDirigido(a, b);
        if (aux != null) {
            for (int i = 0; i < aux.length; i++) {
                if (i == aux.length - 1)
                    str += aux[i];
                else
                    str += aux[i] + " --> ";
            }
        } else {
            str = "no hay relación de llamada entre [" + a + "] y [" + b + "]";
        }
        System.out.println("[INFO] : " + str);
        return str;
    }
   
}
