/*
 * Define el nodo que tendrá la matriz de adyacencia.
 */
package model.matriz;

/**
 *
 * @author hp
 * @param <E>
 */
public class Nodo<E extends Comparable> implements Comparable<Nodo> {
    
    private E vertice;
    private Nodo liga;
    
    /**
     * Constructor Nodo: crea un nodo
     * a partir de un dato que implemente la clase Comparable.
     * 
     * @param vertice de tipo Comparable, es el vertice que se desea guardar.
     */
    public Nodo(E vertice) {
        this.vertice = vertice;
    }
    
    public E getVertice() {
       return vertice;
    }

    public void setVertice(E vertice) {
        this.vertice = vertice;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
    
    @Override
    public String toString() {
        return vertice.toString();
    }

    @Override
    public int compareTo(Nodo nodo) {
        return  vertice.compareTo(nodo.getVertice());
    }
    
}