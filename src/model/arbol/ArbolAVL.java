/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.arbol;

import java.util.Stack;

/**
 *
 * @author cadav
 */
public class ArbolAVL {

    NodoAVL root;
    NodoAVL ult;
    NodoAVL x;

    public ArbolAVL() {
    }

    public NodoAVL insertarDato(Comparable dato) {     
        NodoAVL n = new NodoAVL(dato);
        if (root == null) {
            root = n;
            return root;
        }
        NodoAVL x = root; // Registro que uso para buscar
        NodoAVL padreX = null;
        NodoAVL pivote = root; // Nodo que se puede desvalancear
        NodoAVL padrePivote = null;
        NodoAVL y;
        NodoAVL q;

        /**
         * Busqueda donde insertar también va dejando el rastro de donde
         * posiblemente se puede desbalancear
         *
         */
        while (x != null) {
            // Validar si el nodo x esta en riesgo de desbalanceo
            if (x.getfB() != 0) {
                pivote = x;
                padrePivote = padreX;
            }
            // 
            int comparacion = n.getDato().compareTo(x.getDato());
            if (comparacion == 0) {
                //es un dato existente
                return x;
            } else if (comparacion < 0) {
                // n es menor
                padreX = x;
                x = (NodoAVL) x.getLi();
            } else {
                //n es mayor
                padreX = x;
                x = (NodoAVL) x.getLd();
            }
        }

        /**
         * Insertar el dato
         */
        if (n.getDato().compareTo(padreX.getDato()) > 0) {
            padreX.setLd(n);
        } else if (n.getDato().compareTo(padreX.getDato()) < 0) {
            padreX.setLi(n);
        }
        

        /**
         * Calcular el nuevo factor de balance
         */
        if (n.getDato().compareTo(pivote.getDato()) > 0) {
            pivote.setfB(pivote.getfB() - 1);
            y = (NodoAVL) pivote.getLd();
        } else {
            pivote.setfB(pivote.getfB() + 1);
            y = (NodoAVL) pivote.getLi();
        }
        q = y;
        
        while (y != n) {
            if (n.getDato().compareTo(y.getDato()) > 0) {
                y.setfB(y.getfB() - 1);
                y = (NodoAVL) y.getLd();
            } else {
                y.setfB(y.getfB() + 1);
                y = (NodoAVL) y.getLi();
            }
        }

        /**
         * Rebalancear
         */
        if (!((pivote.getfB() == -2) || (pivote.getfB() == 2))) {
            return n;
        }

        if (pivote.getfB() == +2) {
            if (q.getfB() == +1) {
                rotacionDerecha(padrePivote, pivote, q);
            } else {
                dobleRotacionDerecha(padrePivote, pivote, q);
            }
        } else if (pivote.getfB() == -2) {
            if (q.getfB() == -1) {
                rotacionIzquierda(padrePivote, pivote, q);
            } else {
                dobleRotacionIzquierda(padrePivote, pivote, q);
            }
        }

        return n;
    }

    /**
     * 1. void unaRotacionALaDerecha(nodoAVL p, nodoAVL q) 2.
     * p.asignaLi(q.retornaLd()) 3. q.asignaLd(p) 4. p.asignaFb(0) 5.
     * q.asignaFb(0) 6. fin(unaRotacionALaDerecha)
     *
     * @param pivote
     * @param q
     */
    private void rotacionDerecha(NodoAVL padrePivote, NodoAVL p, NodoAVL q) {
        p.setLi(q.getLd());
        q.setLd(p);
        p.setfB(0);
        q.setfB(0);
        
        if (padrePivote == null) {
            root = q;
        } else {
            if (padrePivote.getLi() != null && padrePivote.getLi() == p) {
               padrePivote.setLi(q);
            } else {
               padrePivote.setLd(q);
            }   
        }
    }

    /**
     * 1. void dobleRotacionALaDerecha(nodoAVL p, nodoAVL q) 2. r =
     * q.retornaLd() 3. q.asignaLd(r.retornaLi()) 4. r.asignaLi(q) 5.
     * p.asignaLi(r.retornaLd() 6. r.asignaLd(p) 7. casos de r.retornaFb() 8. 0:
     * p.asignaFb(0) 9. q.asignaFb(0) 10. break 11. 1: p.asignaFb(–1) 12.
     * q.asignaFb(0) 13. break 14. –1: p.asignaFb(0) 15. q.asignaFb(1) 16.
     * fin(casos) 17. r.asignaFb(0) 18. q = r 19. fin(dobleRotacionALaDerecha)
     *
     * @param padrePivote
     * @param pivote
     * @param q
     */
    private void dobleRotacionDerecha(NodoAVL padrePivote, NodoAVL p, NodoAVL q) {
        NodoAVL r = (NodoAVL) q.getLd();
        
        q.setLd(r.getLi());
        r.setLi(q);
        
        p.setLi(r.getLd());
        r.setLd(p);
        
        if (padrePivote == null) {
            root = r;
        } else {
            if (padrePivote.getLi() != null && padrePivote.getLi() == p) {
                padrePivote.setLi(r);
            } else {
                padrePivote.setLd(r);
            }
        }
        
        switch (r.getfB()) {
            case 0:
                p.setfB(0);
                q.setfB(0);
                break;
            case 1:
                p.setfB(-1);
                q.setfB(0);
                break;
            case -1:
                p.setfB(0);
                q.setfB(1);
                break;
        }
        r.setfB(0);
    }

    /**
     * 1. void unaRotacionALaIzquierda(nodoAVL p, nodoAVL q) 2.
     * p.asignaLd(q.retornaLi()) 3. q.asignaLi(p) 4. p.asignaFb(0) 5.
     * q.asignaFb(0) 6. fin(unaRotacionALaIzquierda)
     *
     * @param padrePivote
     * @param pivote
     * @param q
     */
    private void rotacionIzquierda(NodoAVL padrePivote, NodoAVL p, NodoAVL q) {
        p.setLd(q.getLi());
        q.setLi(p);
        p.setfB(0);
        q.setfB(0);
        
        if (padrePivote == null) {
            root = q;
        } else {
            if (padrePivote.getLi() != null && padrePivote.getLi() == p) {
                padrePivote.setLi(q);
            } else {
                padrePivote.setLd(q);
            }
        }
    }

    /**
     * 
     * @param padrePivote
     * @param p
     * @param q
     */
    private void dobleRotacionIzquierda(NodoAVL padrePivote, NodoAVL p, NodoAVL q) {
        NodoAVL r = (NodoAVL) q.getLi();
        q.setLi(r.getLd());
        r.setLd(q);
        
        p.setLd(r.getLi());
        r.setLi(p);
        
        if (padrePivote == null) {
            root = r;
        } else {
            if (padrePivote.getLi() != null && padrePivote.getLi() == p) {
                padrePivote.setLi(r);
            } else {
                padrePivote.setLd(r);
            }
        }
        
        switch (r.getfB()) {
            case 0:
                p.setfB(0);
                q.setfB(0);
                break;
            case -1:
                p.setfB(1);
                q.setfB(0);
                break;
            case 1:
                p.setfB(0);
                q.setfB(-1);
                break;
        }
        r.setfB(0);
    }

    public NodoAVL buscar(Comparable dato) {
        NodoAVL aux = root;
        while (aux != null) {

            if (aux.getDato().compareTo(dato) == 0) {
                return aux;
            } else if (aux.getDato().compareTo(dato) < 0) {
                if (aux.getLd() != null) {
                    aux = (NodoAVL) aux.getLd();
                } else {
                    return null;
                }
            } else {
                if (aux.getLi() != null) {
                    aux = (NodoAVL) aux.getLi();
                } else {
                    return null;
                }
            }
        }
        return aux;
    }
    
    public void mostrarTodo() {
        NodoAVL aux;
        Stack<NodoAVL> pila = new Stack<>();
        pila.add(root);
        
        int cont = 1;
        while (!pila.isEmpty()) {
            aux = pila.pop();
            System.out.println("[" + cont++ + "] " + aux.getDato());
            
            if (aux.getLi() != null) {
                pila.add(aux.getLi());
            }

            if (aux.getLd() != null) {
                pila.add(aux.getLd());
            }
        }   
    }
    
    public int numeroDeDatos() {
        NodoAVL aux;
        Stack<NodoAVL> pila = new Stack<>();
        pila.add(root);
        
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
    
    public NodoAVL getRoot() {
        return root;
    }
    
    public void setRoot(NodoAVL nodo) {
        root = nodo;
    }
    
}
