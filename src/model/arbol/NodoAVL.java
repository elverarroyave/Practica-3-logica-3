/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.arbol;


/**
 *
 * @author cadav
 * @param <T>
 */
public class NodoAVL<T extends Comparable> {
    
    protected final T dato;
    private NodoAVL<T> li;
    private NodoAVL<T> ld;
    private int fB;

    public NodoAVL(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public NodoAVL<T> getLi() {
        return li;
    }

    public void setLi(NodoAVL<T> li) {
        this.li = li;
    }

    public NodoAVL<T> getLd() {
        return ld;
    }

    public void setLd(NodoAVL<T> ld) {
        this.ld = ld;
    }

    public int getfB() {
        return fB;
    }

    public void setfB(int fB) {
        this.fB = fB;
    }
    
}