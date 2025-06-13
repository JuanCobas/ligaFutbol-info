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

    private ListarService<Jugador> listarJugadores;
    private final ListarService<Partido> listarPartidos;
    private ListarService<Equipo> listarEquipos;
    private final Scanner scanner;
    public asignarGolesAJugadorServiceImpl1(ListarService<Partido> listarPartidos, Scanner scanner) {
        this.listarPartidos = listarPartidos;
        this.scanner = scanner;
        this.listarEquipos = null;
        this.listarJugadores = null;


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
        listarEquipos = new ListarServiceImpl<Equipo>(partidoSeleccionado.getEquipos());
        listarEquipos.listar();
        equipoSeleccionado = listarEquipos.seleccionarDeLista();
        listarJugadores = new ListarServiceImpl<Jugador>(equipoSeleccionado.getJugadores());
        listarJugadores.listar();
        jugadorSeleccionado = listarJugadores.seleccionarDeLista();
        System.out.println(MENU_GOLES);
        goles = (int)InputUtils.leerEnteroPositivo();
        partidoSeleccionado.getGolesPorPartidoPorJugadors().
                add(new GolesPorPartidoPorJugador(jugadorSeleccionado, goles, partidoSeleccionado));
    }
}
