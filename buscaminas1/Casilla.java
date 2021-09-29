package buscaminas1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import javax.swing.JLabel;
public class Casilla implements Serializable {
    private boolean ocupada;
    private int vecino;
    private Estado estado;
    private Rectangle2D.Float rectangulo;
    private boolean explotada;
    
    public Casilla(Rectangle2D.Float r, boolean ocp){
    this.estado=Estado.INICIAL;
    this.rectangulo = r;
    this.ocupada = ocp;
    int vecino=0;
    explotada=false;
    }
    //Getters
    public boolean isOcupada(){
        return ocupada;
    }
    public boolean isExplotada(){
        return explotada;
    }
    public Rectangle2D.Float getRectangulo(){
        return rectangulo;
    }


    public int getVecino(){
        return vecino;
    }
    public Estado getEstado(){
        return estado;
    }
    
    //Setters
    public void setOcupada(boolean ocp){
        ocupada= ocp;
    }
    public void setExplotada(boolean ex){
        explotada= ex;
    }

    public void setRectangulo(Rectangle2D.Float r){
        rectangulo= r;
    }

    public void setVecino(int v){
        vecino=v;
    }
    public void setEstado(Estado e){
        estado= e;
    }

}
