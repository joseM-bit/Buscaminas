package buscaminas1;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Random;
import javax.swing.JPanel;

/*

*/

/**
 *
 * @author Jose Miguel
 */

public class Tablero extends JPanel implements Serializable{

    /**
     *
     */
    public static final int dimension=9;
    private  int max = dimension *60;
    private  int lado= max / dimension;
    private Casilla c[][];
   //CONSTRUCTOR

    /**
     *
     */
   public Tablero(){
    inicializacion();   
   }

    /**
     *
     */
    public void inicializacion(){
               c = new Casilla[dimension][dimension];
        int y = 0;
        for (int i = 0; i < dimension; i++) {
            int x = 0;
            for (int j = 0; j < dimension; j++) {
                Rectangle2D.Float r =
                new Rectangle2D.Float(x, y, lado, lado);       
                c[i][j] = new Casilla(r, false);
                x += lado;
            }
            y+= lado;
         }
        ponerMinas();
        cambiarVecinos();
   }
   //GETTERS

    /**
     *
     * @return
     */
   public Casilla[][]getMatrizcasillas(){
    return c;
    }
   
   //METODOS FUNCIONALES

    /**
     *
     */
   public void ponerMinas(){
       int contador=0;
       Random random= new Random();
       while(contador<10){
            int i= random.nextInt(dimension);
            int j= random.nextInt(dimension);
            if(!c[i][j].isOcupada()){
            c [i][j].setOcupada(true);
            contador++;
            }
        }
   }
   
    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {                
//                c[i][j].paintComponent(g);
                this.visualizacionTablero(g,i,j);
            }
        }

    }

    /**
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(max, max);
    }
    
    /**
     *
     */
    public void cambiarVecinos(){
        for (int i = 0; i <c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                for (int k=i-1;k<=i+1;k++){
                    for(int r=j-1;r<=j+1;r++){
                        //verificamos que la posicion que accedemos de la matriz este permitida
                        if(k>=0 && r>=0 &&k<c.length && r<c.length){
                            if(this.c[k][r].isOcupada()&& !this.c[i][j].isOcupada()){
                                int a=c[i][j].getVecino();
                                a++;
                                c[i][j].setVecino(a);
                                
                            }
                            
                        }
                        
                    }
                }
                
            }
            
        }
    }

    /**
     *
     * @param g
     * @param fila
     * @param columna
     */
    public void visualizacionTablero(Graphics g,int fila, int columna) {
        Image ima; 
        Estado est;

                switch (c[fila][columna].getEstado()) { 
                    case  INICIAL : ima = Toolkit.getDefaultToolkit().getImage("imagenes/inicio.gif");
                                    g.drawImage(ima, (int)c[fila][columna].getRectangulo().getX()
                                            ,(int) c[fila][columna].getRectangulo().getY(),57,57, this);
                        break;
                    case ABRIR:
                         if(c[fila][columna].isOcupada()){
                                 if(!c[fila][columna].isExplotada()){
                                ima = Toolkit.getDefaultToolkit().getImage("imagenes/bombrevealed.gif");
                                g.drawImage(ima, (int)c[fila][columna].getRectangulo().getX()
                                ,(int) c[fila][columna].getRectangulo().getY(),57,57,this); 
                                }
                                 else{       
                                 ima = Toolkit.getDefaultToolkit().getImage("imagenes/bombdeath.gif");
                                    g.drawImage(ima, (int)c[fila][columna].getRectangulo().getX()
                                            ,(int) c[fila][columna].getRectangulo().getY(),57,57,this);                           
                                 }
                                 abrirBombas(); 
                         }

                         else{
                            if( c[fila][columna].getVecino()==0){
                                 int a= c[fila][columna].getVecino();
                                 ima = Toolkit.getDefaultToolkit().getImage("imagenes/open0"+".gif");
                                 g.drawImage(ima, (int)c[fila][columna].getRectangulo().getX()
                                 ,(int) c[fila][columna].getRectangulo().getY(),57,57,this); 
                                 
                                 this.abrirVecinos0(fila, columna);
                            }
                            if(c[fila][columna].getVecino()!=0){
                               int a= c[fila][columna].getVecino();
                                 ima = Toolkit.getDefaultToolkit().getImage("imagenes/open"+a+".gif");
                                 g.drawImage(ima, (int)c[fila][columna].getRectangulo().getX()
                                 ,(int) c[fila][columna].getRectangulo().getY(),57,57,this);
                            }
                             
                         }
                        break;
                    case MARCAR:
                        ima = Toolkit.getDefaultToolkit().getImage("imagenes/bombflagged.gif");
                                    g.drawImage(ima, (int)c[fila][columna].getRectangulo().getX()
                                            ,(int) c[fila][columna].getRectangulo().getY(),57,57, this);
                        break;
                    default:
                       
                }
           
      }

    /**
     *
     * @param fila
     * @param columna
     */
    public void abrirVecinos0(int fila, int columna){

              
                for (int k=fila-1;k<=fila+1;k++){
                    for(int r=columna-1;r<=columna+1;r++){
                        //verificamos que la posicion que accedemos de la matriz este permitida
                        if(k>=0 && r>=0 &&k<c.length && r<c.length){
//                             if(this.c[k][r].getVecino()==0 && !this.c[fila][columna].isOcupada()){
                               if( !this.c[fila][columna].isOcupada()){
                                c[k][r].setEstado(Estado.ABRIR);      
                                
                             }
                        }
                        
                    }
                    repaint();
                }
                

    }
      
    /**
     *
     * @param i
     * @param j
     * @return
     */
    public boolean tieneBomba(int i,int j){
        return c[i][j].isOcupada();
    }

    /**
     *
     * @param i
     * @param j
     * @param x
     * @param y
     * @return
     */
    public boolean dentroCasilla(int i, int j, int x, int y) {
        return c[i][j].getRectangulo().contains(x, y);
    }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    public Casilla getCasilla(int i, int j){
        return c[i][j];
    }

    /**
     *
     * @param i
     * @param j
     */
    public  void verificarEstados(int i, int j){
    switch(c[i][j].getEstado()){
            case ABRIR:
                
                break;
            case MARCAR:
                
                break;
            case INICIAL:
                
                break;
            default:
            
        
        }
    
    }

    /**
     *
     * @return
     */
    public int puntaje(){
        int puntaje=0;
       for(int i=0;i<c.length;i++){
           for(int j=0;j<c.length;j++){
             if(c[i][j].getEstado().compareTo(Estado.ABRIR)==0){
                 puntaje++;
             }
                     
           }
        }
       return puntaje;
    }

    /**
     *
     */
    public void abrirBombas(){
        for (int i = 0; i <c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                                if(this.c[i][j].isOcupada()&& !c[i][j].isExplotada() ){
                                c[i][j].setEstado(Estado.ABRIR);

                             }
                        }
                        repaint();
                    }

         }

    /**
     *
     * @param a
     */
    public void actualizarMatrizCasillas(Casilla a [][]){
        c=a;
    }

}
