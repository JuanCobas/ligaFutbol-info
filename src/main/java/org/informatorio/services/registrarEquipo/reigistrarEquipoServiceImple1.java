package org.informatorio.services.registrarEquipo;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.entities.Equipo;

import java.util.Scanner;

public class reigistrarEquipoServiceImple1 implements registrarEquipoService {


    /// Servicio para registrar Equipos nuevos y guardarlos en memoria.


    String MENU_INICIO = "Va a registrar un equipo";
    String MENU_NOMBRE = "Por favor ingrese el nombre completo del equipo";

    @Override
    public Equipo crearEquipo(EquiposStoring equiposStoring) {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        Equipo equipo;
        while(true){
            System.out.println(MENU_INICIO);
            System.out.println(MENU_NOMBRE);
            nombre = scanner.nextLine();
            equipo = new Equipo(nombre);
            equiposStoring.getListaEquipos().add(equipo);
            return equipo;
        }
    }
}
