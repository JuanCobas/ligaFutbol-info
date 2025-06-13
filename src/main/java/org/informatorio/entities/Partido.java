package org.informatorio.entities;

import java.util.List;

public class Partido {



    private List<Equipo> equipos;
    private Equipo ganador;
    private boolean empate;
    private List<GolesPorPartidoPorJugador> golesPorPartidoPorJugadors;

    public Partido(){

    }

    public Partido(List<Equipo> equipos,
                   List<GolesPorPartidoPorJugador> golesPorPartidoPorJugadors)
    {
        this.equipos = equipos;
        this.ganador = null;
        empate = true;
        this.golesPorPartidoPorJugadors = golesPorPartidoPorJugadors;
    }

    public Partido(List<Equipo> equipos,
                   Equipo ganador,
                   List<GolesPorPartidoPorJugador> golesPorPartidoPorJugadors)
    {
        this.equipos = equipos;
        this.ganador = ganador;
        empate = false;
        this.golesPorPartidoPorJugadors = golesPorPartidoPorJugadors;
    }



    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    public boolean isEmpate() {
        return empate;
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    public List<GolesPorPartidoPorJugador> getGolesPorPartidoPorJugadors() {
        return golesPorPartidoPorJugadors;
    }

}
