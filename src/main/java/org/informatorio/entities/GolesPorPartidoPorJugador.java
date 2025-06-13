package org.informatorio.entities;

import org.informatorio.entities.Jugador.Jugador;

public class GolesPorPartidoPorJugador {

    private Jugador jugador;
    private int goles;
    private Partido partido;

    public GolesPorPartidoPorJugador(){

    }

    public GolesPorPartidoPorJugador(Jugador jugador, int goles, Partido partido){
        this.jugador = jugador;
        this.goles = goles;
        this.partido = partido;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
