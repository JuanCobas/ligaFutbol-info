package org.informatorio.services.asignarGolesJugador;

import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Partido;

public interface asignarGolesAJugadorService {

    public boolean verificarJugadorHizoGoles(Partido partido, Jugador jugador);
    public void asignarGolesAJugadorEnPartido();

}
