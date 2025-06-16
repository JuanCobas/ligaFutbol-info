package org.informatorio.services.asignarGolesJugador;

import org.informatorio.Utils.InputUtils;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.GolesPorPartidoPorJugador;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Partido;
import org.informatorio.services.lista.ListarService;
import org.informatorio.services.lista.ListarServiceImpl;

import java.util.Scanner;

public class asignarGolesAJugadorServiceImpl1 implements asignarGolesAJugadorService{

    private final String MENU = "VA A ASIGNAR GOLES A UN JUGADOR DE UN PARTIDO A ELEGIR";
    private final String MENU_GOLES = "Indique la cantidad de goles del jugador en dicho partido";
    private final String MENU_JUGADOR_HIZO_GOLES = "El jugador ya hizo goles en este partido";

    private ListarService<Jugador> listarJugadores;
    private ListarService<Partido> listarPartidos;
    private ListarService<Equipo> listarEquipos;

    public asignarGolesAJugadorServiceImpl1(ListarService<Partido> listarPartidos) {
        this.listarPartidos = listarPartidos;
        this.listarEquipos = null;
        this.listarJugadores = null;


    }

    @Override
    public boolean verificarJugadorHizoGoles(Partido partido, Jugador jugador) {
        return partido.getGolesPorPartidoPorJugadors().stream().anyMatch(goles -> goles.getJugador().equals(jugador));
    }

    @Override
    public void asignarGolesAJugadorEnPartido()
    {
        Partido partidoSeleccionado;
        Equipo equipoSeleccionado;
        Jugador jugadorSeleccionado;
        int goles;

        System.out.println(MENU);
        listarPartidos.listar();
        partidoSeleccionado = listarPartidos.seleccionarDeLista();
        if(partidoSeleccionado == null){
            return;
        }
        listarEquipos = new ListarServiceImpl<Equipo>(partidoSeleccionado.getEquipos());
        listarEquipos.listar();
        equipoSeleccionado = listarEquipos.seleccionarDeLista();
        if(equipoSeleccionado == null){
            return;
        }
        listarJugadores = new ListarServiceImpl<Jugador>(equipoSeleccionado.getJugadores());
        listarJugadores.listar();
        jugadorSeleccionado = listarJugadores.seleccionarDeLista();
        if(jugadorSeleccionado == null){
            return;
        }
        boolean jugadorYaHizoGoles = verificarJugadorHizoGoles(partidoSeleccionado, jugadorSeleccionado);
        if (jugadorYaHizoGoles) {
            System.out.println(MENU_JUGADOR_HIZO_GOLES);
            return;
        }
        System.out.println(MENU_GOLES);
        goles = (int)InputUtils.leerEnteroPositivo();
        partidoSeleccionado.getGolesPorPartidoPorJugadors().
                add(new GolesPorPartidoPorJugador(jugadorSeleccionado, goles, partidoSeleccionado));
    }
}
