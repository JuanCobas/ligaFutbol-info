package org.informatorio.services.reports.reporteLiga;

import org.informatorio.entities.Equipo;

import java.util.Map;

public interface reporteLiga {

    public int golesTotales();

    public Map.Entry<Equipo, Integer> equipoConMasGoles();

    public void mostrarGolesTotales();


    public void mostrarEquipoConMasGoles();

    public void mostrarReporteLiga();
}
