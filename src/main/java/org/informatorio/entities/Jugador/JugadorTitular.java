package org.informatorio.entities.Jugador;

public class JugadorTitular extends Jugador{

    private long minutosJugados;

    public JugadorTitular(){

    }
    public JugadorTitular(String nombreCompleto, int edad){
        super(nombreCompleto, edad);
        this.minutosJugados = 0;
    }

    public long getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(long minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    @Override
    public String toString() {
        return super.toString() + " - Titular";
    }
}
