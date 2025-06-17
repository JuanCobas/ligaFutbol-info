package org.informatorio.services.lista;

import java.util.List;

public interface ListarService<T> {

    public void listar();
    public T seleccionarDeLista();
    List<T> getLista();
}
