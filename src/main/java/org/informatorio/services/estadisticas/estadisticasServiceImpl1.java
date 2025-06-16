package org.informatorio.services.estadisticas;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.Storing.PartidosStoring;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class estadisticasServiceImpl1 implements estadisticasService{

    public JugadoresStoring jugadoresAlmacenados;
    public PartidosStoring partidosAlmacenados;
    public EquiposStoring equiposAlmacenados;

    public estadisticasServiceImpl1(JugadoresStoring jugadoresAlmacenados, PartidosStoring partidosAlmacenados, EquiposStoring equiposAlmacenados) {
        this.jugadoresAlmacenados = jugadoresAlmacenados;
        this.partidosAlmacenados = partidosAlmacenados;
        this.equiposAlmacenados = equiposAlmacenados;
    }


    @Override
    public Jugador goleadorDeLiga() {
        Jugador jugadorConMasGoles = new Jugador();
        for(Jugador jugador : jugadoresAlmacenados.getListaJugadores() ){
            if(jugador.getCantidadGoles() > jugadorConMasGoles.getCantidadGoles()){
                jugadorConMasGoles = jugador;
            }
        }
        return jugadorConMasGoles;
    }

    @Override
    public void promedioGolesPorPartidoPorEquipo() {

    }

    @Override
    public List<Equipo> rankingEquiposPorCantidadGoles() {
        List<Equipo> rankingEquiposPorCantidadGoles;
        rankingEquiposPorCantidadGoles =  equiposAlmacenados.getListaEquipos().stream()
                .sorted(Comparator.comparingInt(equipo -> equipo.getJugadores().stream()
                        .mapToInt(jugador -> jugador.getCantidadGoles()).sum())).collect(Collectors.toList());
        return rankingEquiposPorCantidadGoles;

    }

    @Override
    public void mostrarEquiposPorCantidadGoles(){
        List<Equipo> equiposRanking = rankingEquiposPorCantidadGoles();
        Integer cantidadGoles = 0;
        System.out.println("Ranking Equipos por Cantidad Goles");

        for(Equipo equipo : equiposRanking){
            for(Jugador jugador : equipo.getJugadores()){
                cantidadGoles += jugador.getCantidadGoles();
            }
            System.out.print("Equipo: " + equipo.getNombre() + " ");
            System.out.println("Cantidad Goles: " + cantidadGoles);
            cantidadGoles = 0;
        }
    }

    @Override
    public List<JugadorSuplente> jugadoresSuplentesQueNuncaIngresaron() {

        List<JugadorSuplente> listaJugadores = jugadoresAlmacenados.getListaJugadores().stream()
                .filter(jugador -> jugador instanceof JugadorSuplente)
                .map(jugador -> (JugadorSuplente) jugador ).collect(Collectors.toList());

        listaJugadores = listaJugadores.stream().filter(jugador -> jugador.getPartidosDesdeBanco() == 0)
                .collect(Collectors.toList());

        return listaJugadores;
    }

    @Override
    public void mostrarJugadoresSuplentesQueNuncaIngresaron(){

        List<JugadorSuplente> jugadorSuplentesNuncaIngresaron = jugadoresSuplentesQueNuncaIngresaron();

        for (JugadorSuplente jugador : jugadorSuplentesNuncaIngresaron) {
            System.out.println(jugador);
        }

    }

    @Override
    public JugadorTitular jugadorTitularConMasMinutos() {

        JugadorTitular jugadorTitular = jugadoresAlmacenados.getListaJugadores().stream()
                .filter(jugador -> jugador instanceof JugadorTitular).map(jugador -> (JugadorTitular) jugador)
                .max(Comparator.comparingInt(jugador -> jugador.getCantidadGoles())).orElse(null);
        return jugadorTitular;

    }

    @Override
    public void mostrarJugadoresTitularsQueNuncaIngresaron(){
        System.out.println(jugadorTitularConMasMinutos());
    }
}
