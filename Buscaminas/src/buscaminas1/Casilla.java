package buscaminas1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author Jose Miguel
 */
public class Casilla implements Serializable {
    private boolean ocupada;
    private int vecino;
    private Estado estado;
    private Rectangle2D.Float rectangulo;
    private boolean explotada;
    
    /**
     *
     * @param r
     * @param ocp
     */
    public Casilla(Rectangle2D.Float r, boolean ocp){
    this.estado=Estado.INICIAL;
    this.rectangulo = r;
    this.ocupada = ocp;
    int vecino=0;
    explotada=false;
    }
    //Getters

    /**
     *
     * @return
     */
    public boolean isOcupada(){
        return ocupada;
    }

    /**
     *
     * @return
     */
    public boolean isExplotada(){
        return explotada;
    }

    /**
     *
     * @return
     */
    public Rectangle2D.Float getRectangulo(){
        return rectangulo;
    }

    /**
     *
     * @return
     */
    public int getVecino(){
        return vecino;
    }

    /**
     *
     * @return
     */
    public Estado getEstado(){
        return estado;
    }
    
    //Setters

    /**
     *
     * @param ocp
     */
    public void setOcupada(boolean ocp){
        ocupada= ocp;
    }

    /**
     *
     * @param ex
     */
    public void setExplotada(boolean ex){
        explotada= ex;
    }

    /**
     *
     * @param r
     */
    public void setRectangulo(Rectangle2D.Float r){
        rectangulo= r;
    }

    /**
     *
     * @param v
     */
    public void setVecino(int v){
        vecino=v;
    }

    /**
     *
     * @param e
     */
    public void setEstado(Estado e){
        estado= e;
    }

}
