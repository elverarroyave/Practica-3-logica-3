/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.conjuntos;

import java.util.Stack;
import model.arbol.ArbolAVL;
import model.arbol.NodoAVL;
import static model.conjuntos.Conjuntos.index;
import model.phone.Phone;

/**
 *
 * @author hp
 */
public class Conjunto extends ArbolAVL implements Comparable<Conjunto> {
    
    private int id;
    public int length = 0;
    
    public Conjunto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public long[] datos() {
        length = length == 0 ? this.numeroDeDatos() : length;
        NodoAVL<Phone> aux;
        Stack<NodoAVL> pila = new Stack<>();
        pila.add(this.getRoot());
        long[] datos = new long[length];
        
        int cont = 0;
        while (!pila.isEmpty()) {
            aux = pila.pop();
            datos[cont] = aux.getDato().getNumber();
            cont++;
            
            if (aux.getLi() != null)
                pila.add(aux.getLi());

            if (aux.getLd() != null)
                pila.add(aux.getLd());
        }
        return datos;
    }
    
    public void addConjunto(Conjunto conjunto) {
        NodoAVL<Phone> aux;
        Stack<NodoAVL> pila = new Stack<>();
        pila.add(conjunto.getRoot());
        
        while (!pila.isEmpty()) {
            aux = pila.pop();
            
            super.insertarDato(aux.getDato());
            long number = aux.getDato().getNumber();
            index.getPhoneByNumber(number).setConjuntoID(this.id);
            
            if (aux.getLi() != null)
                pila.add(aux.getLi());

            if (aux.getLd() != null)
                pila.add(aux.getLd());
        }
    }
    
    public void cambiarId(int id) {
        NodoAVL<Phone> aux;
        Stack<NodoAVL> pila = new Stack<>();
        pila.add(this.getRoot());
        this.id = id;
        while (!pila.isEmpty()) {
            aux = pila.pop();
            
            long number = aux.getDato().getNumber();
            aux.getDato().setConjuntoID(id);
            index.getPhoneByNumber(number).setConjuntoID(id);
            
            if (aux.getLi() != null)
                pila.add(aux.getLi());

            if (aux.getLd() != null)
                pila.add(aux.getLd());
        }
    }

    @Override
    public int compareTo(Conjunto conjunto) {
         return id > conjunto.getId()
            ? 1
            : id < conjunto.getId()
                ? -1
                : 0;
    }
    
}
