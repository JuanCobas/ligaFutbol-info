package org.informatorio.Storing;

import org.informatorio.entities.Equipo;

import java.util.ArrayList;
import java.util.List;

public class EquiposStoring {

    /// Por no tener persistencia almaceno aqui los Equipos creados
    private List<Equipo> listaEquipos = new ArrayList<Equipo>();

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }
}
