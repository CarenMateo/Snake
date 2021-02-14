package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Serpiente {
    ///////////
    //Estado//
    //////////
    protected int posX,posY,ancho;
    protected ArrayList<Pieza> miCuerpo;
    protected int direccion;

    ///////////////////
    ///COMPORTAMIENTO//
    ///////////////////

    public Serpiente(int posX,int posY, int ancho){
       Pieza nuevaCabeza;
       nuevaCabeza = new Pieza(posX,posY,ancho);

       direccion = Pieza.ARR;
       this.posX = posX;
       this.posY = posY;
       this.ancho = ancho;

       miCuerpo = new ArrayList();
       miCuerpo.add(nuevaCabeza);


    }
    public Serpiente(Serpiente antigua){
        Pieza nuevaCabeza;

        posX= antigua.getPosX();
        posY = antigua.getPosY();
        ancho = antigua.getAncho();

        nuevaCabeza = new Pieza(posX,posY,ancho);

        direccion = Pieza.ARR;

        miCuerpo = new ArrayList();
        miCuerpo.add(nuevaCabeza);
    }

    //moverse
    public void  moverse(){
        this.crecer();
    }

    public void crecer(){
       Pieza nuevaCabeza;
       Pieza cabezaAntigua = miCuerpo.get(0);
       nuevaCabeza = new Pieza(cabezaAntigua);
       nuevaCabeza.moverse(direccion);
       miCuerpo.add(0,nuevaCabeza);
    }

    public void pintarse (SpriteBatch miSB){
        for(Pieza unaPieza: miCuerpo) {
            unaPieza.render(miSB);
        }
    }

    public void dispose(){
        for(Pieza unaPieza: miCuerpo) {
            unaPieza.dispose();
        }
    }
    public int getPosX(){ return posX;}
    public int getPosY(){ return posY;}
    public int getAncho(){ return ancho;}

    //comportamiento hasMuerto
    public boolean hasMuerto() {
        boolean resultado;
        if(testCuerpo()) {
            resultado = true;
        } else if (testParedes()) {
            resultado = true;
        } else resultado = false;

        return resultado;
    }
    private boolean testCuerpo(){
        // +(0) 1 2 3 4 5 6
        //tengo que mirar si colisionan una a una las piezas del cuerpo con la cabeza
        Pieza cabezona = this.miCuerpo.get(0);

        if (miCuerpo.size()<4) return false;

        for(int i=4;i<miCuerpo.size();i++){
            if (miCuerpo.get(i).colisiona(cabezona)) {
                return true;
            }
        }
        return false;
    }
    private boolean testParedes(){

    }
}
