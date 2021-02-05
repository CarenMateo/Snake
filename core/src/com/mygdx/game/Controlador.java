package com.mygdx.game;

public class Controlador {

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONSTANTES
    private static  Controlador miControlador;

    //RESTO DEL ESTADO
    protected Serpiente snaky;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONTRUCTOR QUE INICIA LA SERPIENTE
    private Controlador(){

    }

    //RESTO DE COMPORTAMIENTOS
    private static Controlador getInstance( int posXinicial, int posYinicial, int ancho){
        if (Controlador.miControlador == null) {
            miControlador = new Controlador();
            miControlador.setSnaky(new Serpiente(posXinicial, posYinicial, ancho));
        }

        return Controlador.miControlador;
    }

    private void setSnaky(Serpiente nuevaSerpiente){
        snaky = nuevaSerpiente;
    }
}
