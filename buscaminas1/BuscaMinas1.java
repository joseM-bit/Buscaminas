/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Miguel/joseM-bit
 */
public class BuscaMinas1 extends JFrame implements MouseListener {


    Tablero tablero;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem abrir;
    JMenuItem guardar;
    JMenuItem reiniciar;
    JMenuItem fin;
    
    /**
     *
     */
    public BuscaMinas1(){
        
        super("BUSCAMINAS");
        tablero = new Tablero();
        menuBar = new JMenuBar();
        //MENU
        menu = new JMenu ("Archivo");
        //OPCIONES DEL MENU

        reiniciar = new JMenuItem ("Reiniciar");
        reiniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
              reiniciar(evt);
            }
            
        });
        
        fin = new JMenuItem("Fin");
        fin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                finJuego(evt);
            }
        });
        
        guardar = new JMenuItem("Guardar");
        guardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                guardar(evt);        
            }
        });
        abrir = new JMenuItem ("Reanudar Partida");
        abrir.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                reanudarPartida(evt);
            }
        });
             
                
        //AGREGAMOS LOS ITEMS AL MENU 
        menu.add(abrir);
        menu.add(guardar);
        menu.add(reiniciar);
        menu.add(fin);
        //AGREGAMOS A LA BARRA DE MENU
        menuBar.add(menu);
        tablero.setOpaque(false);
        tablero.addMouseListener(this);
        
        this.setLayout(new BorderLayout());
        this.add(menuBar, BorderLayout.NORTH);
        this.getContentPane().add(tablero, BorderLayout.CENTER);
        this.setSize(545,595);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     *
     * @param evento
     */
    public void finJuego(ActionEvent evento){
        System.exit(0);
    }

    /**
     *
     * @param evento
     */
    public void reiniciar(ActionEvent evento){
        tablero.inicializacion();
        repaint();
    }

    /**
     *
     * @param evento
     */
    public void guardar(ActionEvent evento){

        ObjetosOut ois;
        JFileChooser ventanaGuardar=new JFileChooser();

        int opcion = ventanaGuardar.showSaveDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
                               
            try {
                     //obtención del nombre del directorio
                     String ruta = ventanaGuardar.getSelectedFile().getAbsolutePath();
                     //obtención del formato (jpg,gif o png) de las imágenes que componen
                     //la libreria seleccionada
                     ois=new ObjetosOut(ruta);
                     ois.escribirObjeto(tablero.getMatrizcasillas());
                }
            catch (FileNotFoundException ex) {
                       Logger.getLogger(BuscaMinas1.class.getName()).log(Level.SEVERE, null, ex);
                   }
   
            }
    }

    /**
     *
     * @param evento
     */
    public void reanudarPartida(ActionEvent evento){
        ObjetosIn oin;
        JFileChooser ventanaSeleccion=new JFileChooser();
        ventanaSeleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int op=ventanaSeleccion.showOpenDialog(null);
        
        if(op==JFileChooser.APPROVE_OPTION) {
                      try {
                          //obtención del nombre del directorio
                          String ruta = ventanaSeleccion.getSelectedFile().getAbsolutePath();
                          //obtención del formato (jpg,gif o png) de las imágenes que componen
                          //la libreria seleccionada
                          oin=new ObjetosIn(ruta);
                          tablero.actualizarMatrizCasillas((Casilla[][])oin.lecturaObjeto());
                          tablero.repaint();
                          oin.cerrarEnlace();
                      } catch (FileNotFoundException ex) {
                          Logger.getLogger(BuscaMinas1.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(BuscaMinas1.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(BuscaMinas1.class.getName()).log(Level.SEVERE, null, ex);
                      }
        }
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        BuscaMinas1 esc = new BuscaMinas1();
        esc.setVisible(true);
    }

    /**
     *
     * @param me
     */
    @Override
    public void mouseClicked(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = 0, y = 0, i, j = 0;
        int puntos =0;
        int puntosMax = 71; //Casillas - bombas
        boolean explota=false;
        if (e.getButton() == MouseEvent.BUTTON1 && puntos < puntosMax){
            x = e.getX();
            y = e.getY();
            
            boolean trobat = false;
            
            for ( i = 0; i < Tablero.dimension && !trobat; i++){
                for ( j =0; j< Tablero.dimension && !trobat; j++){
                    trobat = tablero.dentroCasilla(i, j, x, y);
                }
            }
            i--;
            j--;

            tablero.getCasilla(i, j).setEstado(Estado.ABRIR);
             
            tablero.repaint(); 
            if(tablero.getCasilla(i, j).isOcupada()){
                
                tablero.getCasilla(i,j).setExplotada(true);
                Toolkit.getDefaultToolkit().beep();
                ImageIcon icon = new ImageIcon("imagenes/derrota.png");
                                JOptionPane.showMessageDialog(null, "Boooom!!! has perdido",
                        "Derrota!", JOptionPane.INFORMATION_MESSAGE,icon);
                //al perder lapartidase reincia depues de darle aceptar al JoptionPane.                
                tablero.inicializacion();
                repaint();
            }
        }
          if (tablero.puntaje() == puntosMax) {
                Toolkit.getDefaultToolkit().beep();
                ImageIcon icon = new ImageIcon("imagenes/victoria1.png");
                JOptionPane.showMessageDialog(null, "Puntos máximos alcanzados!",
                        "Victoria!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            if(e.getButton() == MouseEvent.BUTTON3 ){
                x = e.getX();
                y = e.getY();
                boolean marcado = false;
            for ( i = 0; i < Tablero.dimension && !marcado; i++){
                for ( j =0; j< Tablero.dimension && !marcado; j++){
                     marcado = tablero.dentroCasilla(i, j, x, y);
                }
            }
            i--;
            j--;
            
            if(tablero.getCasilla(i,j).getEstado().compareTo(Estado.MARCAR)==0){
                tablero.getCasilla(i, j).setEstado(Estado.INICIAL);
                tablero.repaint();
            }
            else{
            tablero.getCasilla(i, j).setEstado(Estado.MARCAR);
            tablero.repaint();
            }

            }
      
    }

    /**
     *
     * @param me
     */
    @Override
    public void mouseEntered(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param me
     */
    @Override
    public void mouseExited(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

    

