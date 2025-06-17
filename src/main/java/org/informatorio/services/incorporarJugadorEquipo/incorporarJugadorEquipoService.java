package org.informatorio.services.incorporarJugadorEquipo;

import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.services.lista.ListarService;

public interface incorporarJugadorEquipoService {

    public Equipo buscarEquipoDeJugador(Jugador jugador);

    public boolean verificarJugadorPerteneceAEquipo(Jugador jugador);

    public void agregarJugadorAEquipo(Equipo equipo, Jugador jugador);

    public void incorporarJugadorAEquipo();

    public void trasnferirJugadorAEquipo();
}
