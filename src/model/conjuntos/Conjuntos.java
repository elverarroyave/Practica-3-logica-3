/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.conjuntos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

import model.arbol.ArbolAVL;
import model.arbol.NodoAVL;
import model.phone.Phone;

/**
 *
 * @author hp
 */
public class Conjuntos extends ArbolAVL {
    
    /**
     * Es el arbol de busqueda principal.
     */
    public static Index index = new Index();
    private Index helper = new Index();
    
    public Conjuntos() {
        
    }
    
    /**
     * Constructor del arbol: crea un arbol
     * a partir de un archivo con extension .txt.
     * 
     * @param folder de tipo string, es el nombre de la carpeta en donde se busca el archivo .txt.
     * @param fileName de tipo string, es el nombre del archivo .txt.
     */
    public Conjuntos(String folder, String fileName) {
        try {
            index = new Index();
            helper = new Index();
            load(folder, fileName);
            System.out.println("[INFO] : [CONJUNTOS] : it's done.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void load(String folder, String fileName) throws IOException {
        FileReader file       = new FileReader(folder + "\\" + fileName);
        BufferedReader buffer = new BufferedReader(file);
        String data[], str;
        int conjuntoID = 1;
       
        while((str = buffer.readLine())!=null) {
            data = str.split("\\|");
            long numberOfA = Long.parseLong(data[0].trim());
            long numberOfB = Long.parseLong(data[1].trim());
            Phone a = index.getPhoneByNumber(numberOfA);
            Phone b = index.getPhoneByNumber(numberOfB);
            
            if (a == null && b == null) {
                crearNuevoConjunto(conjuntoID, numberOfA, numberOfB);
                conjuntoID++;
            } else if (a == null) {
                int id = b.getConjuntoID();
                agregarNuevoDatoAlConjunto(id, numberOfA);
            } else if (b == null) {
                int id = a.getConjuntoID();
                agregarNuevoDatoAlConjunto(id, numberOfB);
            } else if (a.getConjuntoID() != b.getConjuntoID()) {
                int idA = a.getConjuntoID() < b.getConjuntoID() ? a.getConjuntoID() : b.getConjuntoID();
                int idB = a.getConjuntoID() < b.getConjuntoID() ? b.getConjuntoID() : a.getConjuntoID();
                
                Conjunto conjuntoA = helper.getConjuntobyId(idA);
                Conjunto conjuntoB = helper.getConjuntobyId(idB);
                conjuntoA.addConjunto(conjuntoB);
                helper.eliminarConjuntobyId(idB);
            } 
       }
       buffer.close();
       organizar();
    }
    
    public void organizar() {
        NodoAVL<Conjunto> aux;
        Stack<NodoAVL> pila = new Stack<>();
        pila.add(helper.getRoot());
        
        int cont = 1;
        while (!pila.isEmpty()) {
            aux = pila.pop();
            
            if (aux.getDato().getRoot() != null) {
                Conjunto conjunto = aux.getDato();
                conjunto.cambiarId(cont);
                super.insertarDato(conjunto);
                cont++;
            } else {
                Conjunto c = aux.getDato();
                c.setId(-1);
            }
            
            if (aux.getLi() != null) {
                pila.add(aux.getLi());
            }

            if (aux.getLd() != null) {
                pila.add(aux.getLd());
            }
        }
        helper = new Index();
    }
    
    public void crearNuevoConjunto(int id, long a, long b) {
        Conjunto conjunto = new Conjunto(id);
        Phone phoneA = new Phone(a, id);
        Phone phoneB = new Phone(b, id);
        conjunto.insertarDato(phoneA);
        conjunto.insertarDato(phoneB);
        index.insertarDato(phoneA);
        index.insertarDato(phoneB);
        helper.insertarDato(conjunto);
    }
    
    public void agregarNuevoDatoAlConjunto(int id, long number) {
        Conjunto conjunto = helper.getConjuntobyId(id);
        conjunto.insertarDato(new Phone(number, id));
        index.insertarDato(new Phone(number, id));
        helper.insertarDato(conjunto);
    }
    
    public Conjunto getConjuntobyId(int id) {
        return (Conjunto) this.buscar(new Conjunto(id)).getDato();
    }
    
    public int numeroDeConjuntos() {
        NodoAVL<Conjunto> aux;
        Stack<NodoAVL> pila = new Stack<>();
        pila.add(this.getRoot());
        
        int cont = 0;
        while (!pila.isEmpty()) {
            aux = pila.pop();
            cont++;
            
            if (aux.getLi() != null) {
                pila.add(aux.getLi());
            }

            if (aux.getLd() != null) {
                pila.add(aux.getLd());
            }
        }
        return cont;
    }
    
    public long[] numeroDeDatosDelConjunto(int id) {
        Conjunto conjunto = this.getConjuntobyId(id);
        return conjunto.datos();
    }
    
    public long[][] idDelConjuntoDelTelefono(long number) {
        NodoAVL<Phone> nodo = index.buscar(new Phone(number));
        int id = nodo.getDato().getConjuntoID();
        long[] datos = this.getConjuntobyId(id).datos();
        long[][] res = new long[2][1];
        res[0][0] = id;
        res[1] = datos;
        
        return res;
    }
    
}
