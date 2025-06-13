package org.informatorio.services.menu;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.Storing.PartidosStoring;
import org.informatorio.Utils.InputUtils;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.services.lista.ListarService;
import org.informatorio.services.lista.ListarServiceImpl;
import org.informatorio.services.registrarJugador.RegistrarJugadorService;

import java.util.Scanner;

public class MenuServiceImpl1 implements MenuService {

    private final int OPCIONES = 6;
    private final  String MENU = "LIGA DE FUTBOL\n" +
            "1 - Registrar Jugador\n" +
            "2 - Crear Equipo\n" +
            "3 - Incorporar Jugadores a Equipos\n" +
            "4 - Registrar Partido\n" +
            "5 - Asignar Goles a Jugadores durante Partido\n" +
            "6 - Mostrar Listado de Jugadores y su Tipo\n";

    private JugadoresStoring jugadoresAlmacenados;
    private EquiposStoring equiposAlmacenados;
    private PartidosStoring partidosAlmacenados;
    private RegistrarJugadorService registrarJugadorService;
    private ListarService<Jugador> listarJugador;

    public MenuServiceImpl1(JugadoresStoring jugadoresAlmacenados,
                            EquiposStoring equiposAlmacenados,
                            PartidosStoring partidosAlmacenados,
                            RegistrarJugadorService registrarJugadorService) {
        this.jugadoresAlmacenados = jugadoresAlmacenados;
        this.equiposAlmacenados = equiposAlmacenados;
        this.partidosAlmacenados = partidosAlmacenados;
        this.registrarJugadorService = registrarJugadorService;
        listarJugador = new ListarServiceImpl<>(jugadoresAlmacenados.getListaJugadores());
    }

    @Override
    public int seleccionarOpcion(Scanner scanner) {
        int opcion;
        System.out.println(MENU);
        do {
            opcion = (int) InputUtils.leerEnteroPositivo();
        }while(opcion > OPCIONES );
        return opcion;
    }

    @Override
    public void correrOpcion(int opcion){

        switch (opcion){
            case 1: {
                registrarJugadorService.crearJugador(jugadoresAlmacenados);
                break;
            }

            case 2: {}

            case 3: {}

            case 4: {}

            case 5: {

            }

            case 6: {
                listarJugador.listar();
                break;
            }
        }
    }
}
