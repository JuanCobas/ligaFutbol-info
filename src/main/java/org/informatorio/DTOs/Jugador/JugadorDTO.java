package org.informatorio.DTOs.Jugador;

import com.opencsv.bean.CsvBindByName;

public record JugadorDTO(
        @CsvBindByName(column = "Nombre") String nombreCompleto,
        @CsvBindByName(column = "Edad")int edad,
        @CsvBindByName(column = "Goles")int cantidadGoles,
        @CsvBindByName(column = "Titular")String esTitular
) {

}
