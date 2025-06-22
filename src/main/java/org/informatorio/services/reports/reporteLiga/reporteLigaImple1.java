package org.informatorio.services.reports.reporteLiga;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.Storing.PartidosStoring;
import org.informatorio.entities.Equipo;
import org.informatorio.entities.GolesPorPartidoPorJugador;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Partido;
import org.informatorio.services.estadisticas.estadisticasService;
import org.informatorio.services.estadisticas.estadisticasServiceImpl1;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class reporteLigaImple1 implements reporteLiga {

    final private JugadoresStoring jugadoresAlmacenados;
    final private PartidosStoring partidosAlmacenados;
    final private EquiposStoring equiposAlmacenados;
    final private estadisticasService estadisticasService;

    public reporteLigaImple1(JugadoresStoring jugadoresAlmacenados, PartidosStoring partidosAlmacenados, EquiposStoring equiposAlmacenados, estadisticasService estadisticasService) {
        this.jugadoresAlmacenados = jugadoresAlmacenados;
        this.partidosAlmacenados = partidosAlmacenados;
        this.equiposAlmacenados = equiposAlmacenados;
        this.estadisticasService = estadisticasService;
    }
    String MENU_REPORTE = "REPORTE DE LA LIGA";
    String MENU_GOLES_TOTALES = "Los goles totales de la liga fueron: ";
    String MENU_EQUIPO_MAS_GOLES = "El equipo con mas goles fue: ";
    String MENU_EQUIPO_CANTIDAD_GOLES = "Cantidad de goles: ";

    @Override
    public int golesTotales(){
        int golesTotales = 0;
        for(Jugador jugador : jugadoresAlmacenados.getListaJugadores() ){
             golesTotales += jugador.getCantidadGoles();
        }
        return golesTotales;
    }

    @Override
    public Map.Entry<Equipo, Integer> equipoConMasGoles(){

        Map<Equipo, Integer> equiposYGoles = new HashMap<>();

        List<Partido> listaPartidos = partidosAlmacenados.getListaPartidos();
        Equipo equipo;
        for(Partido partido : listaPartidos){
            for (GolesPorPartidoPorJugador golesPorPartidoPorJugador : partido.getGolesPorPartidoPorJugadors()){
                equipo = estadisticasService.buscarEquipoDeJugador(golesPorPartidoPorJugador.getJugador());
                equiposYGoles.merge(equipo, golesPorPartidoPorJugador.getGoles(), (a, b) -> a + b);
            }
        }


        return equiposYGoles.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
    }
    @Override
    public void mostrarGolesTotales(){
        System.out.println(MENU_GOLES_TOTALES + golesTotales());
    }

    @Override
    public void mostrarEquipoConMasGoles(){
        System.out.println(MENU_EQUIPO_MAS_GOLES + equipoConMasGoles().getKey().getNombre());
        System.out.println(MENU_EQUIPO_CANTIDAD_GOLES + equipoConMasGoles().getValue());
    }

    @Override
    public void mostrarReporteLiga(){
        System.out.println(MENU_REPORTE);
        mostrarGolesTotales();
        mostrarEquipoConMasGoles();
    }



}
