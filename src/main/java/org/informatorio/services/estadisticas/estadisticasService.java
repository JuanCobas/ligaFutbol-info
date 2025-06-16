package org.informatorio.services.estadisticas;

import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;

import java.util.List;
import java.util.Map;

public interface estadisticasService {

    public Jugador goleadorDeLiga();

    public Map<Equipo, Float> promedioGolesPorPartidoPorEquipo();

    public List<Equipo> rankingEquiposPorCantidadGoles();

    public List<JugadorSuplente> jugadoresSuplentesQueNuncaIngresaron();

    public JugadorTitular jugadorTitularConMasMinutos();

    public void mostrarJugadoresTitularsQueNuncaIngresaron();

    public void mostrarJugadoresSuplentesQueNuncaIngresaron();

    public void mostrarEquiposPorCantidadGoles();

}
