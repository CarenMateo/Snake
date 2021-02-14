package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controlador {

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONSTANTES
    protected final static String IMAGEN_INICIAL = "imagenInicio.png";
    protected final static String IMAGEN_FINAL = "imagenFinal.png";
    protected final static int TIEMPO_CRECER = 239;
    protected final static int TIEMPO_MOVER = 59;

    private static  Controlador miControlador;

    //RESTO DEL ESTADO
    protected Serpiente serpi;

    //UN BATCH PARA DIBUJAR
    protected SpriteBatch batch;

    //IMAGENES SPLASH PARA INICIO Y FINAL
    protected  Texture imagenInicio;
    protected Texture imagenFinal;

    //El SIMULADOR DE TECLADO
    EstadoTeclado et;

    enum Videojuego{
        INICIO,
        JUGANDO,
        FINALIZADO
    }

    //ESTADO DEL CONTROLADOR
    protected  Videojuego controlVG;
    protected int controlTiempo;
    protected int anchoPantalla, altoPantalla;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONTRUCTOR QUE INICIA LA SERPIENTE
    private Controlador(){
        controlVG= Videojuego.INICIO;
        batch = new SpriteBatch();
        imagenInicio= new Texture(IMAGEN_INICIAL);
        imagenFinal = new Texture(IMAGEN_FINAL);
        anchoPantalla=Gdx.graphics.getWidth();
        altoPantalla=Gdx.graphics.getHeight();
        et = new EstadoTeclado(anchoPantalla,altoPantalla);
        controlTiempo = 0;
    }

    //RESTO DE COMPORTAMIENTOS
    private static Controlador getInstance( int posXinicial, int posYinicial, int ancho){
        if (Controlador.miControlador == null) {
            miControlador = new Controlador();
            miControlador.setSerpi(new Serpiente(posXinicial, posYinicial, ancho));
        }

        return Controlador.miControlador;
    }

    private void setSerpi(Serpiente nuevaSerpiente){
        serpi = nuevaSerpiente;
    }

    //COMPORTAMIENTO DE CONTROL Y DIBUJO DEL VIDEOJUEGO
    public void render(){
        switch (controlVG){
            case INICIO: this.pantallaInicio();
            break;
            case JUGANDO: this.controlaEstadoJugando();
            break;
            case FINALIZADO: this.finalPartida();
            break;
        }
    }

    //ELIMINACION DE RECURSOS
    public void dispose(){
        if (serpi!=null) serpi.dispose();
        batch.dispose();
        imgFinal.dispose();
        imgInicial.dispose();
    }

    //MAQUINA DE ESTADOS DEL CONTROLADOR
    private void pantallaInicio(){
        //serpi.pintarse(batch);
        batch.begin();
        batch.draw(imgInicial, x0, y0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        //ACTUALIZO EL TECLADO
        boolean recienTocado;

        recienTocado = Gdx.input.justTouched();
        if (recienTocado){
            this.iniciaPartida();
        }
    }

    private void iniciaPartida(){
        Serpiente nuevaSerpiente = new Serpiente(serpi);
        controlVG = Videojuego.JUGANDO;
        serpi.dispose();
        serpi.nuevaSerpiente;
    }

    private void controlaEstadoJugando(){
        //TENGO QUE MOVER LA SERPIENTE O CRECER LA SERPIENTE
        if (controlTiempo % TIEMPO_MOVER == 0){
            serpi.moverse();
            controlTiempo++;
        }else if(controlTiempo == TIEMPO_CRECER){
            serpi.crecer();
            controlTiempo == 0;
        }else {
            controlTiempo++;
        }

        //ME HABRE CHOCADO??
        if (serpi.hasmuerto()) {
            controlVG = Videojuego.FINALIZADO;
        }


        //TENGO QUE PINTAR LA SERPIENTE
        serpi.pintarse();

    }

    private void finalPartida(){
        batch.begin();
        batch.draw(imgInicial, x0, y0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        //MIRAMOS SI HAN PULSADO UNA TECLA PARA COMENZAR A JUGAR.
        boolean recienTocado;

        recienTocado = Gdx.input.justTouched();
        if (recienTocado){
            this.iniciaPartida();
        }
    }
}
