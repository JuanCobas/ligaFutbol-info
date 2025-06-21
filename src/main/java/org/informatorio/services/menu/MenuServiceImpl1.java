package org.informatorio.services.menu;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.Storing.PartidosStoring;
import org.informatorio.Utils.InputUtils;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.services.PersistenciaCSV.Jugador.JugadorCSVService;
import org.informatorio.services.asignarGolesJugador.asignarGolesAJugadorService;
import org.informatorio.services.estadisticas.estadisticasService;
import org.informatorio.services.incorporarJugadorEquipo.incorporarJugadorEquipoService;
import org.informatorio.services.lista.ListarService;
import org.informatorio.services.lista.ListarServiceImpl;
import org.informatorio.services.registrarEquipo.registrarEquipoService;
import org.informatorio.services.registrarJugador.RegistrarJugadorService;
import org.informatorio.services.registrarPartidos.RegistrarPartidosService;

import java.util.Scanner;

public class MenuServiceImpl1 implements MenuService {

    /// Implementacion de un MENU que permite mostrar opciones, retornar el
    /// entero seleccionado y usarlo para correr la opcion deseada.

    private final int OPCIONES = 13;
    private final  String MENU = "LIGA DE FUTBOL\n" +
            "1  - Registrar Jugador\n" +
            "2  - Crear Equipo\n" +
            "3  - Incorporar Jugadores a Equipos\n" +
            "4  - Registrar Partido\n" +
            "5  - Asignar Goles a Jugadores durante Partido\n" +
            "6  - Mostrar Listado de Jugadores y su Tipo\n" +
            "7  - Trasnferir Jugador a otro Equipo\n" +
            "8  - Mostrar Goleador de la Liga\n" +
            "9  - Mostrar Promedio de goles por Partido por cada Equipo\n" +
            "10 - Mostrar Ranking de Equipos por cantidad de goles anotados\n" +
            "11 - Mostrar Jugadores Suplentes que nunca hayan ingresado\n" +
            "12 - Mostrar el jugador titular con mayor cantidad de minutos jugados\n" +
            "13 - Exportar en un archivo .csv los jugadores de un equipo dado\n";

    private JugadoresStoring jugadoresAlmacenados;
    private EquiposStoring equiposAlmacenados;
    private PartidosStoring partidosAlmacenados;
    private RegistrarJugadorService registrarJugadorService;
    private ListarService<Jugador> listarJugadores;
    private ListarService<Equipo> listarEquipos;
    private registrarEquipoService registrarEquipoService;
    private RegistrarPartidosService registrarPartidosService;
    private incorporarJugadorEquipoService incorporarJugadorEquipoService;
    private asignarGolesAJugadorService asignarGolesAJugadorService;
    private estadisticasService estadisticasService;
    private JugadorCSVService jugadorCSVService;


    public MenuServiceImpl1(JugadoresStoring jugadoresAlmacenados,
                            EquiposStoring equiposAlmacenados,
                            PartidosStoring partidosAlmacenados,
                            RegistrarJugadorService registrarJugadorService,
                            registrarEquipoService registrarEquipoService,
                            RegistrarPartidosService registrarPartidosService,
                            incorporarJugadorEquipoService incorporarJugadorEquipoService,
                            ListarService<Equipo> listarEquipos,
                            asignarGolesAJugadorService asignarGolesAJugadorService,
                            estadisticasService estadisticasService,
                            JugadorCSVService jugadorCSVService) {

        this.jugadoresAlmacenados = jugadoresAlmacenados;
        this.equiposAlmacenados = equiposAlmacenados;
        this.partidosAlmacenados = partidosAlmacenados;
        this.registrarJugadorService = registrarJugadorService;
        listarJugadores = new ListarServiceImpl<>(jugadoresAlmacenados.getListaJugadores());
        this.registrarEquipoService = registrarEquipoService;
        this.registrarPartidosService = registrarPartidosService;
        this.incorporarJugadorEquipoService = incorporarJugadorEquipoService;
        this.listarEquipos = listarEquipos;
        this.asignarGolesAJugadorService = asignarGolesAJugadorService;
        this.estadisticasService = estadisticasService;
        this.jugadorCSVService = jugadorCSVService;
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

            case 2: {
                registrarEquipoService.crearEquipo(equiposAlmacenados);
                break;
            }

            case 3: {
                incorporarJugadorEquipoService.incorporarJugadorAEquipo();
                break;
            }

            case 4: {
                registrarPartidosService.registrarPartido(partidosAlmacenados);
                break;
            }

            case 5: {
                asignarGolesAJugadorService.asignarGolesAJugadorEnPartido();
                break;
            }

            case 6: {
                listarJugadores.listar();
                break;
            }
            case 7:{
                incorporarJugadorEquipoService.trasnferirJugadorAEquipo();
                break;
            }
            case 8:{
                estadisticasService.mostrarGoleadorDeLiga();
                break;
            }
            case 9:{
                estadisticasService.mostrarPromedioGolesPorPartidoPorEquipo();
                break;
            }
            case 10: {
                estadisticasService.mostrarEquiposPorCantidadGoles();
                break;
            }
            case 11:{
                estadisticasService.mostrarJugadoresSuplentesQueNuncaIngresaron();
                break;
            }
            case 12: {
                estadisticasService.mostrarJugadoresTitularsConMasMinutos();
                break;
            }
            case 13: {
                jugadorCSVService.PersistirJugadoresDeEquipo();
                break;
            }
        }
    }
}
