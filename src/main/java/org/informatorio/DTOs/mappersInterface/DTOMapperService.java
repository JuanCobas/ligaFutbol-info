package org.informatorio.DTOs.mappersInterface;

public interface DTOMapperService<T, DTO> {

    public DTO mapperToDTO(T objeto);

    public T mapperToObject(DTO objetoDTO);
}
