package org.informatorio.services.PersistenciaCSV;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.List;

public interface PersistenciaCSV<T> {

    public void guardarObjeto(List<T> list) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;
}
