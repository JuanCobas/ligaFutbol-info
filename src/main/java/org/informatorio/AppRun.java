package org.informatorio;

import org.informatorio.DTOs.Jugador.JugadorDTO;
import org.informatorio.DTOs.mappers.Jugador.DTOMapperService;
import org.informatorio.DTOs.mappers.Jugador.JugadorDTOMapperServiceImpl1;
import org.informatorio.Storing.EquiposStoring;
import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.Storing.PartidosStoring;
import org.informatorio.dataGenerator.DataGenerator;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Partido;
import org.informatorio.services.PersistanceCSV.Jugador.JugadorCSVService;
import org.informatorio.services.PersistanceCSV.Jugador.JugadorCSVServiceImpl1;
import org.informatorio.services.PersistanceCSV.PersistenciaCSV;
import org.informatorio.services.PersistanceCSV.PersistenciaCSVImpl1;
import org.informatorio.services.asignarGolesJugador.asignarGolesAJugadorService;
import org.informatorio.services.asignarGolesJugador.asignarGolesAJugadorServiceImpl1;
import org.informatorio.services.estadisticas.estadisticasService;
import org.informatorio.services.estadisticas.estadisticasServiceImpl1;
import org.informatorio.services.incorporarJugadorEquipo.incorporarJugadorEquipoService;
import org.informatorio.services.incorporarJugadorEquipo.incorporarJugadorEquipoServiceImpl1;
import org.informatorio.services.lista.ListarService;
import org.informatorio.services.lista.ListarServiceImpl;
import org.informatorio.services.menu.MenuService;
import org.informatorio.services.menu.MenuServiceImpl1;
import org.informatorio.services.registrarEquipo.registrarEquipoService;
import org.informatorio.services.registrarEquipo.reigistrarEquipoServiceImple1;
import org.informatorio.services.registrarJugador.RegistrarJugadorService;
import org.informatorio.services.registrarJugador.RegistrarJugadorServiceImpl1;
import org.informatorio.services.registrarPartidos.RegistrarPartidoServiceImpl1;
import org.informatorio.services.registrarPartidos.RegistrarPartidosService;

import java.util.Scanner;

public class AppRun {

    Scanner scanner = new Scanner(System.in);
    JugadoresStoring jugadoresStoring = new JugadoresStoring();
    EquiposStoring equiposStoring = new EquiposStoring();
    PartidosStoring partidosStoring = new PartidosStoring();
    ListarService<Partido> listarPartidoService = new ListarServiceImpl<>(partidosStoring.getListaPartidos());
    ListarServiceImpl<Equipo> listarEquipoService = new ListarServiceImpl<>(equiposStoring.getListaEquipos());
    ListarService<Jugador> listarJugadorService = new ListarServiceImpl<>(jugadoresStoring.getListaJugadores());
    RegistrarJugadorService registrarJugadorService = new RegistrarJugadorServiceImpl1();
    registrarEquipoService registrarEquipoService = new reigistrarEquipoServiceImple1();
    RegistrarPartidosService registrarPartidosService = new RegistrarPartidoServiceImpl1(listarEquipoService);
    incorporarJugadorEquipoService incorporarJugadorEquipoService = new incorporarJugadorEquipoServiceImpl1(listarEquipoService,listarJugadorService);
    asignarGolesAJugadorService asignarGolesAJugadorService = new asignarGolesAJugadorServiceImpl1(listarPartidoService);
    estadisticasService estadisticasService = new estadisticasServiceImpl1(jugadoresStoring,partidosStoring,equiposStoring);
    DTOMapperService<Jugador, JugadorDTO> dtoMapperService = new JugadorDTOMapperServiceImpl1();
    PersistenciaCSV<Jugador> persistenciaCSVJugador = new PersistenciaCSVImpl1<>(listarJugadorService, dtoMapperService);
    JugadorCSVService jugadorCSVService = new JugadorCSVServiceImpl1(persistenciaCSVJugador,listarEquipoService);


    private MenuService menu = new MenuServiceImpl1(
            jugadoresStoring,
            equiposStoring,
            partidosStoring,
            registrarJugadorService,
            registrarEquipoService,
            registrarPartidosService,
            incorporarJugadorEquipoService,
            listarEquipoService,
            asignarGolesAJugadorService,
            estadisticasService,
            jugadorCSVService
            );


    public void runApp(){
        while(true){
            int opcion;
            opcion = menu.seleccionarOpcion(scanner);
            menu.correrOpcion(opcion);
        }
    }
}
