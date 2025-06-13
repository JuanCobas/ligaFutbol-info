package org.informatorio.services.registrarJugador;

import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.entities.Jugador.Jugador;


public interface RegistrarJugadorService {

    public Jugador crearJugador(JugadoresStoring jugadoresStoring);

}
