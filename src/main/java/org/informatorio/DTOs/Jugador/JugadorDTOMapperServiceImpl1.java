package org.informatorio.DTOs.Jugador;

import org.informatorio.DTOs.mappersInterface.DTOMapperService;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;

public class JugadorDTOMapperServiceImpl1 implements DTOMapperService<Jugador,JugadorDTO> {

    @Override
    public JugadorDTO mapperToDTO(Jugador jugador) {
        String titular;
        if(jugador instanceof JugadorTitular){
            titular = "SI";
        }
        else {
            titular = "NO";
        }



        return new JugadorDTO(jugador.getNombreCompleto(), jugador.getEdad(), jugador.getCantidadGoles(), titular);
    }

    @Override
    public Jugador mapperToObject(JugadorDTO jugadorDTO) {
        String titular;
        Jugador jugador;
        if (jugadorDTO.esTitular().equals("SI")){
            jugador = new JugadorTitular(jugadorDTO.nombreCompleto(),jugadorDTO.edad());
        }
        else{
            jugador = new JugadorSuplente(jugadorDTO.nombreCompleto(),jugadorDTO.edad(),0);
        }
        return jugador;
    }
}
