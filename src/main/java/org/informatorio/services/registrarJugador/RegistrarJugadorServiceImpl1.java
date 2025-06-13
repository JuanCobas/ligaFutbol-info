package org.informatorio.services.registrarJugador;

import org.informatorio.Storing.JugadoresStoring;
import org.informatorio.Utils.InputUtils;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;

import java.util.Scanner;

public class RegistrarJugadorServiceImpl1 implements RegistrarJugadorService {

    String MENU_INICIO = "Va a registrar un jugador";
    String MENU_NOMBRE = "Por favor ingrese el nombre completo del jugador";
    String MENU_EDAD = "Por favor ingrese la edad del jugador";
    String MENU_TIPO_JUGADOR = "Ingrese el tipo de jugador a crear, indicando el numero";
    String MENU_TIPO_JUGADOR_LISTA = "1 - Titular \n2 - Suplente";
    String MENU_JUGADOR_SUPLENTE = "Ingrese la cantidad de partidos que el jugador entro desde el banco";

    public RegistrarJugadorServiceImpl1(){

    }

    @Override
    public Jugador crearJugador(JugadoresStoring jugadoresStoring) {
        Scanner scanner = new Scanner(System.in);
        String nombreCompleto = "";
        int edad = 0;
        int opcionTipo = 0;
        boolean flag = Boolean.TRUE;
        Jugador jugador;
        while(flag){
            System.out.println(MENU_INICIO);
            System.out.println(MENU_NOMBRE);
            nombreCompleto = scanner.nextLine();

            System.out.println(MENU_EDAD);
            edad = (int)InputUtils.leerEnteroPositivo();

            System.out.println(MENU_TIPO_JUGADOR);
            System.out.println(MENU_TIPO_JUGADOR_LISTA);
            do{opcionTipo = (int) InputUtils.leerEnteroPositivo();}
                    while (opcionTipo > 2 || opcionTipo < 0);
            flag = Boolean.FALSE;
        }

        if(opcionTipo == 1){
            jugador = new JugadorTitular(nombreCompleto, edad);
        } else {
            int partidosDesdeBanco;
            System.out.println(MENU_JUGADOR_SUPLENTE);
            partidosDesdeBanco = (int) InputUtils.leerEnteroPositivo();
            jugador = new JugadorSuplente(nombreCompleto,edad,partidosDesdeBanco);
        }

        jugadoresStoring.getListaJugadores().add(jugador);
        ///scanner.close();
        return jugador;
    }
}
