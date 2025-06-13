package org.informatorio.entities.Jugador;

public class JugadorSuplente extends Jugador{


    private int partidosDesdeBanco;

    public JugadorSuplente(){

    }

    public JugadorSuplente(String nombreCompleto, int edad, int partidosDesdeBanco){
        super(nombreCompleto,edad);
        this.partidosDesdeBanco = partidosDesdeBanco;

    }


    public int getPartidosDesdeBanco() {
        return partidosDesdeBanco;
    }

    public void setPartidosDesdeBanco(int partidosDesdeBanco) {
        this.partidosDesdeBanco = partidosDesdeBanco;
    }

    @Override
    public String toString() {
        return super.toString() + " - Suplente";
    }
}
