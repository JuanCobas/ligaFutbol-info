package org.informatorio.services.reports.reporteEquipo;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;

import java.util.List;
import java.util.Map;

public interface reporteEquipo {

    public Map<Jugador,Float> promedioGolesJugadores(Equipo equipo);
    public void mostrarPromedioGolesJugadores(Equipo equipo);
    public List<Jugador> jugadoresNoAnotaronGoles(Equipo equipo);
    public void mostrarJugadoresNoAnotaronGoles(Equipo equipo);
    public JugadorTitular jugadorTitularConMasMinutos(Equipo equipo);
    public JugadorSuplente jugadorSuplenteMasUtilizado(Equipo equipo);
    public void mostrarJugadorSuplenteMasUtilizado(JugadorSuplente jugador);
    public Equipo seleccionarEquipo();
    public void mostrarReporteEquipo(Equipo equipo);
}

