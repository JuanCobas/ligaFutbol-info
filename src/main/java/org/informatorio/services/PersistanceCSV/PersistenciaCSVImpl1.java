package org.informatorio.services.PersistanceCSV;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.informatorio.DTOs.Jugador.JugadorDTO;
import org.informatorio.DTOs.mappers.Jugador.DTOMapperService;
import org.informatorio.services.lista.ListarService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PersistenciaCSVImpl1<T, DTO> implements PersistenciaCSV<T>{

    ListarService<T> listarObjetos;
    DTOMapperService<T, DTO> mapperService;
    String nombre;
    private final String MENU_SELECCIONAR_NOMBRE = "Seleccione el nombre del archivo CSV de salida";

    private final String MENU_CREACION_ARCHIVO_EXITO = String.
            format("El archivo ( %s ) fue creado con exito", nombre);
    private final String MENU_CREACION_ARCHIVO_FAIL = String.
            format("El archivo ( %s ) no pudo ser creado", nombre);

    public PersistenciaCSVImpl1(ListarService<T> listarObjetos, DTOMapperService<T,DTO> mapperService){
        this.listarObjetos = listarObjetos;
        this.mapperService = mapperService;
    }

    public void guardarObjeto(List<T> list) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Scanner scanner = new Scanner(System.in);
        nombre = "";

        System.out.println(MENU_SELECCIONAR_NOMBRE);
        nombre = scanner.nextLine();
        File file;
        FileWriter fileWriter;
        try {
            file = new File(nombre);
            if(file.createNewFile()){
                System.out.println(MENU_CREACION_ARCHIVO_EXITO);
            }
            else{
                System.out.println(MENU_CREACION_ARCHIVO_FAIL);
                return;
            }
            fileWriter = new FileWriter(file);

            StatefulBeanToCsv<DTO> writer = new StatefulBeanToCsvBuilder<DTO>(fileWriter).build();
            for (T element : list){
                DTO objectDTO = mapperService.mapperToDTO(element);
                writer.write(objectDTO);
            }

            fileWriter.close();

         }
        catch (IOException e){

        }
        finally {

        }



    }




}
