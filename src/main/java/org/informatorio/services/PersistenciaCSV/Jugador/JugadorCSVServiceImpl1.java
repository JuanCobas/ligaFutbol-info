package org.informatorio.services.PersistenciaCSV.Jugador;

import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.services.PersistenciaCSV.PersistenciaCSV;
import org.informatorio.services.lista.ListarService;

import java.util.List;

public class JugadorCSVServiceImpl1 implements  JugadorCSVService{

    PersistenciaCSV<Jugador> persistenciaCSV;
    ListarService<Equipo> listarService;


    public JugadorCSVServiceImpl1(PersistenciaCSV<Jugador> persistenciaCSV, ListarService<Equipo> listarService) {
        this.persistenciaCSV = persistenciaCSV;
        this.listarService = listarService;

    }

    @Override
    public void PersistirJugadoresDeEquipo() {


        listarService.listar();
        Equipo equipo = listarService.seleccionarDeLista();
        if (equipo == null) {
            System.out.println("La lista de Equipos esta vacia");
            return;
        }
        List<Jugador> listaJugadores = equipo.getJugadores();
        if (listaJugadores.isEmpty()) {
            System.out.println("La lista de Jugadores esta vacia");
            return;
        }
        try{

            persistenciaCSV.guardarObjeto(listaJugadores);


        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }



    }




}
