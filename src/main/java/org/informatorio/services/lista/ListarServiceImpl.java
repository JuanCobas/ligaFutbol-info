package org.informatorio.services.lista;

import org.informatorio.Utils.InputUtils;

import java.util.List;
import java.util.Scanner;

public class ListarServiceImpl<T> implements ListarService<T> {

    private String MENU_LISTA = "Lista";
    private String MENU_LISTA_VACIA = "La lista se encuentra vacia";
    String MENU_SELECCIONAR = "Seleccione la opcion desedeada";
    String MENU_SELECCION = "Ha seleccionado: ";
    private final List<T> lista;
    private final Scanner scanner = new Scanner(System.in);

    public ListarServiceImpl(List<T> lista) {

        this.lista = lista;
    }

    /// Servicio para mostrar listas genericas y seleccionar elementos de la misma retornandolo.
    /// Los Objetos se listan y muestran segun lo que devuelva su metodo toString.

    @Override
    public void listar() {

        if(lista.isEmpty()){
            System.out.println(MENU_LISTA_VACIA);
            return;
        }
        System.out.println(MENU_LISTA);
        for (int i = 0; i < lista.size(); i++){
            System.out.println(i+1 + " - " + lista.get(i));
        }
    }

    @Override
    public T seleccionarDeLista() {
        int opcion;
        T seleccion;
        System.out.println(MENU_SELECCIONAR);
        if(lista.isEmpty()){
            System.out.println(MENU_LISTA_VACIA);
            return null;
        }
        do {
            opcion = (int) InputUtils.leerEnteroPositivo();
        }
        while (opcion < 1 || opcion > lista.size());

        seleccion = lista.get(opcion - 1);

        System.out.println(MENU_SELECCION + seleccion);

        return seleccion;
    }

    @Override
    public List<T> getLista() {
        return lista;
    }
}
