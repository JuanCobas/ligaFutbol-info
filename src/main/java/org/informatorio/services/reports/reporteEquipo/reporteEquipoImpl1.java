package org.informatorio.services.reports.reporteEquipo;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.Storing.PartidosStoring;
import org.informatorio.Utils.InputUtils;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;
import org.informatorio.entities.Partido;
import org.informatorio.services.estadisticas.estadisticasService;

import java.util.*;

public class reporteEquipoImpl1 implements reporteEquipo {

    PartidosStoring partidosStoring;
    estadisticasService estadisticasService;
    EquiposStoring equiposStoring;
    String MENU_JUGADOR = "Jugador: ";
    String MENU_JUGADOR_GOLES_PROMEDIO = "Goles Promedios: ";
    String MENU_JUGADOR_SUPLENTE = "Jugador Suplenete mas utilizado: ";
    String MENU_SELECCIONAR_EQUIPO = "Ingrese el numero de equipo que desea seleccionar: ";




    public reporteEquipoImpl1(PartidosStoring partidosStoring, estadisticasService estadisticasService, EquiposStoring equiposStoring) {
        this.partidosStoring = partidosStoring;
        this.estadisticasService = estadisticasService;
        this.equiposStoring = equiposStoring;
    }

    @Override
    public Map<Jugador,Float> promedioGolesJugadores(Equipo equipo) {
        Map<Jugador,Float> promedioGolesJugadores = new HashMap<>();
        List<Partido> partidos = partidosStoring.getListaPartidos();
        int partidosTotales = 0;
        if (partidos.isEmpty()) {
            return null;
        }
        for (Partido partido : partidos) {
            if(partido.getEquipos().get(0).equals(equipo)) {
                partidosTotales++;
            }
            else if(partido.getEquipos().get(1).equals(equipo)) {
                partidosTotales++;

            }
            else {
                continue;
            }
        }
        for (Jugador jugador : equipo.getJugadores()) {
            promedioGolesJugadores.put(jugador, jugador.getCantidadGoles()/(float)partidosTotales);
        }

        return promedioGolesJugadores;

    }
    @Override
    public void mostrarPromedioGolesJugadores(Equipo equipo) {

        Map<Jugador,Float> promedioGolesJugadores = promedioGolesJugadores(equipo);
        System.out.println("Promedio Goles por jugador");
        if(promedioGolesJugadores.isEmpty()) {
            System.out.println("No hay jugadores en el equipo");
        }
        for(Map.Entry<Jugador,Float> jugador : promedioGolesJugadores.entrySet()) {
            System.out.println(MENU_JUGADOR + jugador.getKey().getNombreCompleto() + MENU_JUGADOR_GOLES_PROMEDIO + jugador.getValue());
        }
    }
    @Override
    public List<Jugador> jugadoresNoAnotaronGoles(Equipo equipo) {
        List<Jugador> jugadoresNoAnotaronGoles = new ArrayList<>();
        for(Jugador jugador : equipo.getJugadores()) {
            if(jugador.getCantidadGoles() > 0) {
                jugadoresNoAnotaronGoles.add(jugador);
            }
        }
        return jugadoresNoAnotaronGoles;
    }
    @Override
    public void mostrarJugadoresNoAnotaronGoles(Equipo equipo) {
        List<Jugador> jugadoresNoAnotaronGoles = jugadoresNoAnotaronGoles(equipo);
        System.out.println("Jugadores que no anotaron goles");
        if(jugadoresNoAnotaronGoles.isEmpty()) {
            System.out.println("No hay jugadores en el equipo o que no hayan anotado goles");
        }
        for(Jugador jugador : equipo.getJugadores()) {
            System.out.println(MENU_JUGADOR + jugador.getNombreCompleto());
        }
    }
    @Override
    public JugadorTitular jugadorTitularConMasMinutos(Equipo equipo) {

        return (JugadorTitular) equipo.getJugadores().stream().filter((jugador) -> jugador instanceof JugadorTitular)
                .max(Comparator.comparing(jugador -> ((JugadorTitular) jugador).getMinutosJugados())).orElse(null);
    }
    @Override
    public JugadorSuplente jugadorSuplenteMasUtilizado(Equipo equipo) {
        return (JugadorSuplente) equipo.getJugadores().stream().filter((jugador) -> jugador instanceof JugadorSuplente)
                .max(Comparator.comparing((jugador) -> ((JugadorSuplente) jugador).getPartidosDesdeBanco())).orElse(null);
    }
    @Override
    public void mostrarJugadorSuplenteMasUtilizado(JugadorSuplente jugador) {

        System.out.println(MENU_JUGADOR_SUPLENTE);
        System.out.println(MENU_JUGADOR + jugador.getNombreCompleto() + "Partidos ingreso desde banco: " + jugador.getPartidosDesdeBanco());
    }
    @Override
    public Equipo seleccionarEquipo() {
        System.out.println(MENU_SELECCIONAR_EQUIPO);
        List<Equipo> equipos = equiposStoring.getListaEquipos();
        long indice = 1;
        for(Equipo equipo1 : equipos) {
            System.out.println(indice + " - " + equipo1.getNombre());
            indice++;
        }
        indice = InputUtils.leerEnteroPositivo();

        return equipos.get((int)indice -1);

    }

    @Override
    public void mostrarReporteEquipo(Equipo equipo) {
        mostrarPromedioGolesJugadores(equipo);
        mostrarJugadoresNoAnotaronGoles(equipo);
        JugadorTitular jugadorTitular = jugadorTitularConMasMinutos(equipo);
        if(jugadorTitular != null) {
            estadisticasService.mostrarJugadoresTitularsConMasMinutos(jugadorTitularConMasMinutos(equipo));
        }
        JugadorSuplente jugadorSuplente = jugadorSuplenteMasUtilizado(equipo);
        if(jugadorSuplente != null) {
            mostrarJugadorSuplenteMasUtilizado(jugadorSuplente);
        }

    }


}
