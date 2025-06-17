package org.informatorio.Storing;

import org.informatorio.entities.Partido;

import java.util.ArrayList;
import java.util.List;

public class PartidosStoring {

    /// Por no tener persistencia almaceno aqui los Partidos creados

    private List<Partido> listaPartidos = new ArrayList<Partido>();

    public List<Partido> getListaPartidos() {
        return listaPartidos;
    }

}
