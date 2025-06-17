package org.informatorio.services.registrarPartidos;


import org.informatorio.Storing.PartidosStoring;
import org.informatorio.Utils.InputUtils;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorTitular;
import org.informatorio.entities.Partido;
import org.informatorio.services.lista.ListarService;

import java.util.ArrayList;
import java.util.Arrays;


public class RegistrarPartidoServiceImpl1 implements RegistrarPartidosService{


    /// Servicio para registrar Partidos nuevos entre Equipos y guardarlos en memoria.


    private final String MENU = "Va a registrar un Partido";
    private final String MENU_SELECCION_EQUIPO1 = "Seleccione el primer Equipo";
    private final String MENU_SELECCION_EQUIPO2 = "Seleccione el segundo Equipo";
    private final String MENU_SELECCION_EMPATE = "El partido fue un (1)Empate o tuvo (2)Ganador? ";
    private final String MENU_SELECCION_GANADOR = "Seleccione al ganador del partido";
    private ListarService<Equipo> listarEquipo;


    public RegistrarPartidoServiceImpl1(ListarService<Equipo> listarEquipo) {
        this.listarEquipo = listarEquipo;

    }

    @Override
    public Partido crearPartidoConGanador(Equipo equipo1, Equipo equipo2, Equipo ganador) {

        return new Partido(new ArrayList<Equipo>(Arrays.asList(equipo1,equipo2)), ganador);
    }

    @Override
    public Partido crearPartidoEmpate(Equipo equipo1, Equipo equipo2){

        return new Partido(new ArrayList<Equipo>(Arrays.asList(equipo1,equipo2)));
    }

    @Override
    public void asignarMinutosAJugadores(Equipo equipo){

        for(Jugador jugador : equipo.getJugadores()){
            if(jugador instanceof JugadorTitular){
                ((JugadorTitular) jugador).setMinutosJugados(90);
            }
        }

    }

    @Override
    public void registrarPartido(PartidosStoring partidos){
        Equipo equipo1;
        Equipo equipo2;
        Equipo ganador;
        boolean empate = Boolean.FALSE;
        int opcion;
        Partido partido;

        System.out.println(MENU);
        System.out.println(MENU_SELECCION_EQUIPO1);
        listarEquipo.listar();
        equipo1 = listarEquipo.seleccionarDeLista();
        if(equipo1 == null){
            return;
        }
        System.out.println(MENU_SELECCION_EQUIPO2);
        listarEquipo.listar();
        do{
            equipo2 = listarEquipo.seleccionarDeLista();
        }
        while(equipo1.equals(equipo2));
        if(equipo2 == null){
            return;
        }

        System.out.println(MENU_SELECCION_EMPATE);

        do{opcion = (int)InputUtils.leerEnteroPositivo();}
        while(opcion > 2);
        if(opcion == 1){
            empate = true;
        }
        if(!empate){
            System.out.println(MENU_SELECCION_GANADOR);
            listarEquipo.listar();
            ganador = listarEquipo.seleccionarDeLista();
            partido = crearPartidoConGanador(equipo1, equipo2, ganador);
        }
        else{
            partido = crearPartidoEmpate(equipo1, equipo2);
        }
        partidos.getListaPartidos().add(partido);
        asignarMinutosAJugadores(partido.getEquipos().get(0));
        asignarMinutosAJugadores(partido.getEquipos().get(1));

    }
}
