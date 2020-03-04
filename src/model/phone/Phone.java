/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.phone;


/**
 *
 * @author hp
 */
public class Phone implements Comparable<Phone> {

    private long number;
    private long fecha;
    private int conjuntoID;
    
    public Phone(long number) {
        this.number = number;
    }
    
    public Phone(long number, long fecha) {
        this.number = number;
        this.fecha = fecha;
    }
    
    public Phone(long number, int conjunto) {
        this.conjuntoID = conjunto;
        this.number = number;
    }
    
    public Phone(long number, long fecha, int conjunto) {
        this.conjuntoID = conjunto;
        this.number = number;
        this.fecha = fecha;
    }

    public int getConjuntoID() {
        return conjuntoID;
    }

    public void setConjuntoID(int conjunto) {
        this.conjuntoID = conjunto;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
    
    @Override
    public String toString() {
        return "[" + number + "]";
    }

    @Override
    public int compareTo(Phone phone) {
        return number > phone.getNumber()
            ? 1
            : number < phone.getNumber()
                ? -1
                : 0;
    }
    
}
