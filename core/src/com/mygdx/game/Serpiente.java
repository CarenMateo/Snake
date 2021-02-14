package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Serpiente {
    ///////////
    //Estado//
    //////////

    protected ArrayList<Pieza> cuerpo;
    protected int direccion;

    ///////////////////
    ///COMPORTAMIENTO//
    ///////////////////

    public Serpiente(int PosX,int PosY, int nancho){
        cuerpo= new ArrayList();
        cuerpo.add(new Pieza(PosX,PosY, nancho));
    }

    //pintate
    public void render(SpriteBatch miSB){
        for (int i=0; i< cuerpo.size();i++){
            cuerpo.get(i).render(miSB);
        }
    }
    public Serpiente(Serpiente antigua){
        Pieza nuevaCabeza;

        PosX= antigua.getPosX();
        PosY = antigua.getPosY();
        ancho = antigua.getAncho();

        nuevaCabeza = new Pieza(PosX,PosY,nancho);

        direccion = Pieza.ARR;

        miCuerpo = new ArrayLis();
        miCuerpo.add(nuevaCabeza);
    }
    //moverse
    public void  moverse(int direccion){
        switch (direccion){
            case Pieza.DER:
                //1.Crear una pieza exactamente en la misma posicion que la cabeza
                //2.mover esa pieza a la derecha(Solo esto dentro del switch, lo demas fuera)
                //3. Añadir esa pieza a mi conjunto
                //4.Eliminar la ultima posicion del array(acordarnos de hacer el dispose)
                break;
             case Pieza.IZQ:
                 break;
            case Pieza.ARR:
                break;
            case Pieza.ABJ:
                break;
        }

    }
    public void crecer(){
        //Lo mismo que moverse, esto es llamado en el controlador cada x frames
    }
    public void dispose(){
        for(int i=0; i<cuerpo.size();i++){
            cuerpo.get(i).dispose();
        }
    }


}
