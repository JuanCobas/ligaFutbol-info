package org.informatorio.services.estadisticas;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.Storing.PartidosStoring;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.GolesPorPartidoPorJugador;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;
import org.informatorio.entities.Partido;

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

    /// Servicio para obtener y mostrar por pantalla estadisticas especificas de jugadores, equipos, goles realizados.

    @Override
    public Equipo buscarEquipoDeJugador(Jugador jugador) {

        return equiposAlmacenados.getListaEquipos().stream().filter(equipo -> equipo.getJugadores().
                contains(jugador)).findFirst().orElse(null);

    }

    @Override
    public Jugador goleadorDeLiga() {
        Jugador jugadorConMasGoles = null;
        for(Jugador jugador : jugadoresAlmacenados.getListaJugadores() ){
            if(jugadorConMasGoles == null || jugador.getCantidadGoles() > jugadorConMasGoles.getCantidadGoles()){
                jugadorConMasGoles = jugador;
            }
        }
        return jugadorConMasGoles;
    }

    @Override
    public void mostrarGoleadorDeLiga(){
        Jugador jugadorConMasGoles = goleadorDeLiga();
        if(jugadorConMasGoles == null){
            System.out.println("No se encontro el goleador de liga");
        }
        else {
            System.out.println("Goleador de la Liga: " + jugadorConMasGoles.getNombreCompleto() + " - Goles:" + jugadorConMasGoles.getCantidadGoles());
        }
    }

    @Override
    public Map<Equipo, Float> promedioGolesPorPartidoPorEquipo() {
        Map<Equipo, Float> equiposYGoles = new HashMap<>();
        Map<Equipo, Integer> cantidadPartidos = new HashMap<>();
        Map<Equipo, Float> promedioPorEquipo = new HashMap<>();
        Float promedio = 0f;

        List<Partido> listaPartidos = partidosAlmacenados.getListaPartidos();
        Equipo equipo;
        for(Partido partido : listaPartidos){
            cantidadPartidos.merge(partido.getEquipos().get(0), 1, (a, b) -> a + b);
            cantidadPartidos.merge(partido.getEquipos().get(1), 1, (a, b) -> a + b);
            for (GolesPorPartidoPorJugador golesPorPartidoPorJugador : partido.getGolesPorPartidoPorJugadors()){
                equipo = buscarEquipoDeJugador(golesPorPartidoPorJugador.getJugador());
                equiposYGoles.merge(equipo, (float)golesPorPartidoPorJugador.getGoles(), (a, b) -> a + b);
            }
        }
        for (Map.Entry<Equipo, Float> entry : equiposYGoles.entrySet()){
            promedio = entry.getValue()/cantidadPartidos.get(entry.getKey());
            promedioPorEquipo.put(entry.getKey(), promedio);
        }

        return promedioPorEquipo;

    }

    @Override
    public void mostrarPromedioGolesPorPartidoPorEquipo(){

        Map<Equipo, Float> promedioPorEquipo = promedioGolesPorPartidoPorEquipo();

        for (Map.Entry<Equipo, Float> entry : promedioPorEquipo.entrySet()){
            System.out.print(entry.getKey() + " ");
            System.out.println(entry.getValue());
        }
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
                .max(Comparator.comparingInt(jugador -> (int)jugador.getMinutosJugados())).orElse(null);
        return jugadorTitular;

    }

    @Override
    public void mostrarJugadoresTitularsConMasMinutos(){
        JugadorTitular jugadorTitular = jugadorTitularConMasMinutos();
        if (jugadorTitular != null) {
            System.out.println("Jugador Titular con mas minutos jugados: " + jugadorTitularConMasMinutos().getNombreCompleto());
        }
        else{
            System.out.println("No hay jugadores con minutos todavia");
        }

    }
}
