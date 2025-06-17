package org.informatorio.DTOs.mappers.Jugador;

import org.informatorio.DTOs.Jugador.JugadorDTO;
import org.informatorio.entities.Jugador.Jugador;
import org.informatorio.entities.Jugador.JugadorSuplente;
import org.informatorio.entities.Jugador.JugadorTitular;

public class JugadorDTOMapperServiceImpl1 implements DTOMapperService<Jugador,JugadorDTO> {

    @Override
    public JugadorDTO mapperToDTO(Jugador jugador) {
        boolean titular;
        if(jugador instanceof JugadorTitular){
            titular = Boolean.TRUE;
        }
        else {
            titular = Boolean.FALSE;
        }

        JugadorDTO jugadorDTO = new JugadorDTO(jugador.getNombreCompleto(), jugador.getEdad(),
                jugador.getCantidadGoles(), titular);

        return jugadorDTO;
    }

    @Override
    public Jugador mapperToObject(JugadorDTO jugadorDTO) {
        String titular;
        Jugador jugador;
        if (jugadorDTO.esTitular()){
            jugador = new JugadorTitular(jugadorDTO.nombreCompleto(),jugadorDTO.edad());
        }
        else{
            jugador = new JugadorSuplente(jugadorDTO.nombreCompleto(),jugadorDTO.edad(),0);
        }
        return jugador;
    }
}
