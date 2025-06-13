package org.informatorio;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.Storing.PartidosStoring;
import org.informatorio.services.menu.MenuService;
import org.informatorio.services.menu.MenuServiceImpl1;
import org.informatorio.services.registrarJugador.RegistrarJugadorService;
import org.informatorio.services.registrarJugador.RegistrarJugadorServiceImpl1;

import java.util.Scanner;

public class AppRun {

    JugadoresStoring jugadoresStoring = new JugadoresStoring();
    EquiposStoring equiposStoring = new EquiposStoring();
    PartidosStoring partidosStoring = new PartidosStoring();
    RegistrarJugadorService registrarJugadorService = new RegistrarJugadorServiceImpl1();
    Scanner scanner = new Scanner(System.in);

    private MenuService menu = new MenuServiceImpl1(
            jugadoresStoring,
            equiposStoring,
            partidosStoring,
            registrarJugadorService);


    public void runApp(){
        while(true){
            int opcion;
            opcion = menu.seleccionarOpcion(scanner);
            menu.correrOpcion(opcion);
        }
    }
}
