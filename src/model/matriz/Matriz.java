/*
 * Define los metodos para crear y manipular la matriz de adyacencia
 * represenatada como lista ligada .
 */
package model.matriz;

import model.phone.Phone;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



/**
 * Clase Matriz de adyacencia representada 
 * como lista ligada.
 * 
 * @author hp
 */
public class Matriz {
    
    /**
     * De tipo ArbolAVL, es la columna principal de
     * vertices de la matriz adyacente.
     */
    private Vertices vertices;
    
    /**
     * Constructor vacio de matriz.
     */
    public Matriz() {
    }
    
    /**
     * Constructor de matriz: crea una matriz adyacente
     * a partir de un archivo con extension .txt.
     * 
     * @param folder de tipo string, es el nombre de la carpeta en donde se busca el archivo .txt.
     * @param fileName de tipo string, es el nombre del archivo .txt.
     */
    public Matriz(String folder, String fileName) {
        try {
            this.vertices = new Vertices();
            load(folder, fileName);
            System.out.println("[INFO] : [MATRIZ] : it's done.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Crea una matriz adyacente a partir de un archivo con extension .txt.
     * 
     * @param folder de tipo string, es el nombre de la carpeta en donde se busca el archivo .txt.
     * @param fileName de tipo string, es el nombre del archivo .txt.
     * @throws java.io.FileNotFoundException
     */
    public void load(String folder, String fileName) throws FileNotFoundException, IOException {
       FileReader file       = new FileReader(folder + "\\" + fileName);
       BufferedReader buffer = new BufferedReader(file);
       String data[], str;
       
        while((str = buffer.readLine())!=null) {
            data = str.split("\\|");
            long numberOfA = Long.parseLong(data[0].trim());
            long numberOfB = Long.parseLong(data[1].trim());
            long fecha     = Long.parseLong(data[3].trim());
            Nodo<Phone> a = agregar(numberOfA);
            Nodo<Phone> b = agregar(numberOfB);
            
            if (!sonAdyacentes(a, b)) {
                agregarAdayacente(a, fecha, b);
            }
                
       }
       buffer.close();
    }
    
    /**
     * Agrega un nuevo vertice a la matriz de adyacencia.
     * 
     * @param number de tipo long, es nuevo vertice que se desea guardar.
     * @return de tipo Nodo, retorna el vertice agregado.
     */
    private Nodo agregar(long number) {
        return this.vertices.add(
            new Nodo(
                new Phone(
                    number,
                    0
                )
            )
        );
    }
    
    /**
     * Retorna True si el nodo (vertice) [a] tiene 
     * como adyacente un nodo (vertice) [b], retorna False si no es así.
     * 
     * @param a de tipo nodo, es el vertice principal.
     * @param b de tipo nodo, es el vertice del cual se quiere saber si pertenece a la liga adyacente de [a].
     * @return True o False.
     */
    private boolean sonAdyacentes(Nodo<Phone> a, Nodo<Phone> b) {
        while (a != null) {
            if (a.getVertice().getNumber() == b.getVertice().getNumber()) 
                return true;
            a = a.getLiga();
        }
        return false;
    }
    
    /**
     * Agrega el nodo [b] a la liga adyacente de [a].
     * 
     * @param a de tipo nodo, es el vertice principal.
     * @param fecha de tipo long, es la fecha en la cual el nodo [a] llama al nodo [b].
     * @param b de tipo nodo, es el vertice que se quiere agregar a la liga adyacente de [a].
     */
    private void agregarAdayacente(Nodo<Phone> a, long fecha, Nodo<Phone> b) {
        Nodo<Phone> aux = new Nodo(new Phone(
            b.getVertice().getNumber(),
            fecha
        ));
        aux.setLiga(a.getLiga());
        a.setLiga(aux);
    }
    
    /**
     * Retorna el numero de nodos (vertices) distintos que existen
     * en la matriz.
     * 
     * @return int.
     */
    public int numeroDeVertices() {
        return vertices.numeroDeDatos();
    }
    
    /**
     * Retorna el numero de nodos (vertices) adyacentes que posee el vertice [number]
     * en la matriz.
     * 
     * @param number de tipo long, es el vertice objetivo.
     * @return int.
     */
    public int gradoDelVertice(long number) {
        int cont = 0;
        Nodo run = vertices.getNodoByNumberPhone(number).getLiga();
        while (run != null) {
            cont++;
            run = run.getLiga();
        }
        return cont;
    }
    
    
    /**
     * Retorna la ruta más corta que existe entre [start] y [goal], si 
     * [start] y [goal] no tiene relacion alguna, entonces retorna null.
     * 
     * @param start de tipo long, es el vertice inicial, desde allí se empieza a trazar la ruta.
     * @param goal de tipo long, es el vertice objetivo, hasta allí se traza la ruta.
     * @return de tipo long[], es la ruta mas corta existe entre [start] y [goal].
     */
    public long[] caminoCorto(long start, long goal) {
        ArrayList<Long> explored = new ArrayList();
        Queue<long[]> queue = new LinkedList();
        queue.add(new long[]{start});
        
        while (!queue.isEmpty()) {
            long[] path = queue.poll();
            long node = path[path.length -1];
            
            if (!explored.contains(node)) {
                Nodo<Phone> neighbours = vertices.getNodoByNumberPhone(node);
                Nodo<Phone> neighbour = neighbours.getLiga();
                while (neighbour != null) {
                    if (!explored.contains(neighbour.getVertice().getNumber())) {
                        long[] new_path = new long[path.length + 1];
                        System.arraycopy(path, 0, new_path, 0, path.length);
                        new_path[path.length] = neighbour.getVertice().getNumber();
                        
                        queue.add(new_path);
                        if (neighbour.getVertice().getNumber() == goal) {
                            return new_path;
                        }
                    }
                    neighbour = neighbour.getLiga();
                }
                explored.add(node);
            }
        }
        return null;
    }
    
    /**
     * Retorna la ruta más corta, de vertices dirigidos, que existe entre [start] y [goal] , si 
     * [start] y [goal] no tiene relacion alguna, entonces retorna null.
     * 
     * @param start de tipo long, es el vertice inicial, desde allí se empieza a trazar la ruta.
     * @param goal de tipo long, es el vertice objetivo, hasta allí se traza la ruta.
     * @return de tipo long[], es la ruta mas corta existe entre [start] y [goal].
     */
    public Nodo[] caminoCortoDirigido(long start, long goal) {
        ArrayList<Nodo<Phone>> explored = new ArrayList();
        Queue<Nodo<Phone>[]> queue = new LinkedList();
        
        Nodo<Phone> temp = vertices.getNodoByNumberPhone(start);
        if (temp == null) return null;
        queue.add(new Nodo[]{temp});
        
        while (!queue.isEmpty()) {
            Nodo<Phone>[] path = queue.poll();
            Nodo<Phone> node = path[path.length -1];
            
            long ant_fecha = node.getVertice().getFecha();
            long act_fecha = 0;
            
            if (!explored.contains(node)) {
                Nodo<Phone> neighbours = vertices.getNodoByNumberPhone(node.getVertice().getNumber());
                Nodo<Phone> neighbour = neighbours.getLiga();
                
                while (neighbour != null) {
                    act_fecha = neighbour.getVertice().getFecha();
                    if (!explored.contains(neighbour) && act_fecha >= ant_fecha ) {
                        Nodo<Phone>[] new_path = new Nodo[path.length+1];
                        System.arraycopy(path, 0, new_path, 0, path.length);
                        new_path[path.length] = neighbour;
                        queue.add(new_path);
                        if (neighbour.getVertice().getNumber() == goal) {
                            return new_path;
                        }
                    }
                    
                    neighbour = neighbour.getLiga();
                }
                explored.add(node);
            }
        }
        return null;
    }
    
}
