package org.informatorio.Storing;

import org.informatorio.entities.Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;



public class JugadoresStoring {

    /// Por no tener persistencia almaceno aqui los jugadores creados
    private List<Jugador> listaJugadores = new ArrayList<Jugador>();

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }
}
