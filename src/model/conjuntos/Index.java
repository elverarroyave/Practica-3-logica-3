/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.conjuntos;

import model.arbol.ArbolAVL;
import model.arbol.NodoAVL;
import model.phone.Phone;

/**
 *
 * @author hp
 */
public class Index extends ArbolAVL {
    
    public Phone getPhoneByNumber(long number) {
        NodoAVL nodo = super.buscar(new Phone(number));
        return nodo != null ? (Phone) nodo.getDato() : null;
    }
    
    public Conjunto getConjuntobyId(int id) {
        NodoAVL nodo = super.buscar(new Conjunto(id));
        return nodo != null ? (Conjunto) nodo.getDato() : null;
    }
    
    public void eliminarConjuntobyId(int id) {
        Conjunto conjunto = getConjuntobyId(id);
        conjunto.getRoot().setLd(null);
        conjunto.getRoot().setLi(null);
        conjunto.setRoot(null);
    }
    
}
