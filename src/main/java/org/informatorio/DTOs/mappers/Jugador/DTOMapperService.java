package org.informatorio.DTOs.mappers.Jugador;

public interface DTOMapperService<T, DTO> {

    public DTO mapperToDTO(T objeto);

    public T mapperToObject(DTO objetoDTO);
}
