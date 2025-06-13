package org.informatorio.services.incorporarJugadorEquipo;

import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;

public class incoporarJugadorEquipoServiceImpl1 implements incoporarJugadorEquipoService{

    @Override
    public void incoporarJugadorAEquipo(Equipo equipo, Jugador jugador) {
        equipo.getJugadores().add(jugador);
    }
}
