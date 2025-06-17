package org.informatorio.entities.Jugador;

public class Jugador {


    private String nombreCompleto;
    private int edad;
    private int cantidadGoles;

    public Jugador() {

    }

    public Jugador(String nombreCompleto, int edad){
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.cantidadGoles = 0;


    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCantidadGoles() {
        return cantidadGoles;
    }

    public void setCantidadGoles(int cantidadGoles) {
        this.cantidadGoles += cantidadGoles;
    }

    public void resetCantidadGoles(){
        this.cantidadGoles = 0;
    }

    @Override
    public String toString() {
        return this.nombreCompleto;
    }
}
