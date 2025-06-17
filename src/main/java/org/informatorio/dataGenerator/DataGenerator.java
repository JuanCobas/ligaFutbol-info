package org.informatorio.dataGenerator;

import org.informatorio.entities.Equipo;
import org.informatorio.entities.GolesPorPartidoPorJugador;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;
import org.informatorio.entities.Partido;

import java.util.*;

public class DataGenerator {


    /// Intento de generador de datos... Salio por CHATGPT pero hay que readecuarlo para que actue sobre los servicios de Storing.
    /// SIN USO POR AHORA

    static public void generateData(){
        ArrayList<Equipo> equipos = new ArrayList<>();
        Random random = new Random();

        // Crear 6 equipos con 5 jugadores cada uno
        for (int i = 1; i <= 6; i++) {
            Equipo equipo = new Equipo("Equipo " + i);
            List<Jugador> jugadores = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                String nombre = "Jugador " + i + "-" + j;
                int edad = 18 + random.nextInt(15); // edad entre 18 y 32

                Jugador jugador;
                if (random.nextBoolean()) {
                    jugador = new JugadorTitular(nombre, edad);
                } else {
                    jugador = new JugadorSuplente(nombre, edad, random.nextInt(10));
                }

                jugadores.add(jugador);
            }
            equipo.setJugadores(jugadores);
            equipos.add(equipo);
        }

        // Mostrar los equipos creados (opcional)
        equipos.forEach(eq -> {
            System.out.println(eq.getNombre() + ":");
            eq.getJugadores().forEach(j -> System.out.println("  - " + j));
        });

        // Crear 3 partidos entre equipos
        List<Partido> partidos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            // Elegir 2 equipos distintos
            List<Equipo> copiaEquipos = new ArrayList<>(equipos);
            Collections.shuffle(copiaEquipos);
            Equipo equipo1 = copiaEquipos.get(0);
            Equipo equipo2 = copiaEquipos.get(1);

            List<Equipo> equiposDelPartido = Arrays.asList(equipo1, equipo2);
            Partido partido = new Partido(equiposDelPartido);

            // Elegir 3 jugadores aleatorios de esos equipos que hagan goles
            List<Jugador> jugadoresDeAmbos = new ArrayList<>();
            jugadoresDeAmbos.addAll(equipo1.getJugadores());
            jugadoresDeAmbos.addAll(equipo2.getJugadores());
            Collections.shuffle(jugadoresDeAmbos);

            for (int j = 0; j < 3; j++) {
                Jugador goleador = jugadoresDeAmbos.get(j);
                int goles = 1 + random.nextInt(3); // 1 a 3 goles
                goleador.setCantidadGoles(goles); // suma goles al total
                GolesPorPartidoPorJugador eventoGol = new GolesPorPartidoPorJugador(goleador, goles, partido);
                partido.getGolesPorPartidoPorJugadors().add(eventoGol);
            }

            partidos.add(partido);
        }

        // Mostrar resultados de partidos (opcional)
        for (Partido p : partidos) {
            System.out.println("\n" + p);
            for (GolesPorPartidoPorJugador evento : p.getGolesPorPartidoPorJugadors()) {
                System.out.println("  " + evento.getJugador() + " hizo " + evento.getGoles() + " gol(es)");
            }
        }
    }
}
