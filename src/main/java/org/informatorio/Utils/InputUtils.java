package org.informatorio.Utils;

import java.util.Scanner;

public class InputUtils {

    /// Util para leer un entero positivo.

    public static long leerEnteroPositivo(){
        Scanner scanner = new Scanner(System.in);
        int entero = 0;
        boolean flag = Boolean.TRUE;
        while(flag){
            String input = scanner.nextLine();
            try {
                entero = Integer.parseInt(input);
                if (entero < 1) {
                    System.out.println("Debe ingresar un numero positivo");
                }
                else {
                    flag = Boolean.FALSE;
                }
            } catch (NumberFormatException e) {
                System.out.println("No ha ingresado un valor entero valido");
            }
        }
        return entero;

    }

    public static long leerEnteroNoNegativo(){
        Scanner scanner = new Scanner(System.in);
        int entero = 0;
        boolean flag = Boolean.TRUE;
        while(flag){
            String input = scanner.nextLine();
            try {
                entero = Integer.parseInt(input);
                if (entero < 0) {
                    System.out.println("Debe ingresar un numero no negativo");
                }
                else {
                    flag = Boolean.FALSE;
                }
            } catch (NumberFormatException e) {
                System.out.println("No ha ingresado un valor entero valido");
            }
        }
        return entero;

    }
}
