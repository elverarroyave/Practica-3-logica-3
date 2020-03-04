/*
 * Define la columan principal de vertices de
 * la matriz de adyacencia.
 */
package model.matriz;

import model.arbol.ArbolAVL;
import model.arbol.NodoAVL;
import model.phone.Phone;

/**
 * Clase vertice, es un arbol avl 
 * para realizar busquedas con mayor eficiencia.
 * 
 * @author hp
 */
public class Vertices extends ArbolAVL {
    
    /**
     * Agrega un nuevo vertice al arbol.
     * 
     * @param nodo de tipo comparable, es el dato que se desea guardar en el arbol.
     * @return el nodo una vez ha sido agregado. 
     */
    public Nodo add(Comparable nodo) {
        NodoAVL res = super.insertarDato(nodo);
        
        return res != null
            ? (Nodo) res.getDato()
            : null;
    }
    
    /**
     * Obtiene un nodo (vertice) del arbol por su numero.
     * 
     * @param number es el nodo que se quiere obtener del arbol.
     * @return el nodo una vez ha sido encontrado. 
     */
    public Nodo getNodoByNumberPhone(long number) {
        return (Nodo) super
            .buscar(
                new Nodo(
                    new Phone(number)
                )
            ).getDato();
    }
    
}
