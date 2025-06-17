package org.informatorio.services.registrarPartidos;

import org.informatorio.Storing.PartidosStoring;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.Partido;

public interface RegistrarPartidosService {
    public Partido crearPartidoConGanador(Equipo equipo1, Equipo equipo2, Equipo ganador);
    public Partido crearPartidoEmpate(Equipo equipo1, Equipo equipo2);
    public void asignarMinutosAJugadores(Equipo equipo);
    public void registrarPartido(PartidosStoring partidos);

}
