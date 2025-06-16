package org.informatorio.services.incorporarJugadorEquipo;

import org.informatorio.entities.Equipo;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.services.lista.ListarService;

public class incorporarJugadorEquipoServiceImpl1 implements incorporarJugadorEquipoService {

    private final String MENU_JUGADOR_TRANSFERIR = "Va a transferir un jugador a un equipo nuevo";
    private final String MENU_JUGADOR_A_EQUIPO = "Va a incorporar a un jugador a un equipo";
    private final String MENU_JUAGODOR = "Seleccione un jugador";
    private final String MENU_EQUIPO = "Seleccione un equipo";
    private final String MENU_EXITO = "Jugador agregado con exito";
    private ListarService<Equipo> listaEquipos;
    private ListarService<Jugador> listaJugadores;

    public incorporarJugadorEquipoServiceImpl1(ListarService<Equipo> listaEquipos, ListarService<Jugador> listaJugadores) {
        this.listaEquipos = listaEquipos;
        this.listaJugadores = listaJugadores;
    }

    @Override
    public Equipo buscarEquipoDeJugador(Jugador jugador) {

        return listaEquipos.getLista().stream().filter(equipo -> equipo.getJugadores().
                contains(jugador)).findFirst().orElse(null);

    }

    @Override
    public boolean verificarJugadorPerteneceAEquipo(Jugador jugador) {
        return listaEquipos.getLista().stream().anyMatch(equipo -> equipo.getJugadores().contains(jugador));
    }

    ;

    @Override
    public void agregarJugadorAEquipo(Equipo equipo, Jugador jugador) {
        boolean jugadorExisteEnAlgunEquipo = verificarJugadorPerteneceAEquipo(jugador);
        if (jugadorExisteEnAlgunEquipo) {
            System.out.println("El jugador ya existe en algun equipo");
        } else {
            equipo.getJugadores().add(jugador);
            System.out.println(MENU_EXITO);
        }

    }

    @Override
    public void incorporarJugadorAEquipo() {
        Jugador jugador;
        Equipo equipo;
        System.out.println(MENU_JUGADOR_A_EQUIPO);
        System.out.println(MENU_JUAGODOR);
        listaJugadores.listar();
        jugador = listaJugadores.seleccionarDeLista();
        if(jugador == null){
            return;
        }
        System.out.println(MENU_EQUIPO);
        listaEquipos.listar();
        equipo = listaEquipos.seleccionarDeLista();
        if(equipo == null){
            return;
        }
        agregarJugadorAEquipo(equipo, jugador);


    }

    @Override
    public void trasnferirJugadorAEquipo() {

        Jugador jugador;
        Equipo equipo;

        System.out.println(MENU_JUGADOR_TRANSFERIR);
        System.out.println(MENU_JUAGODOR);
        listaJugadores.listar();
        jugador = listaJugadores.seleccionarDeLista();
        if(jugador == null){
            return;
        }
        System.out.println(MENU_EQUIPO);
        listaEquipos.listar();
        equipo = listaEquipos.seleccionarDeLista();
        if(equipo == null){
            return;
        }
        Equipo equipoActual = buscarEquipoDeJugador(jugador);
        if (equipoActual != null) {
            equipoActual.getJugadores().remove(jugador);
            System.out.println("Jugador removido con exito");
        }
        else{
            System.out.println("El jugador no pertenece a ningun equipo");
        }
        agregarJugadorAEquipo(equipo, jugador);
        System.out.println(MENU_EXITO);

    }
}
