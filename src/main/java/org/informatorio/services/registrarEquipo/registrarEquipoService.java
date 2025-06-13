package org.informatorio.services.registrarEquipo;

import org.informatorio.Storing.EquiposStoring;
import org.informatorio.entities.Equipo;

import java.util.Scanner;

public interface registrarEquipoService {

    public Equipo crearJugador(Scanner scanner, EquiposStoring equiposStoring);

}
