/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminas1;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 * Jose Miguel/joseM-bit
 */
public class ObjetosOut {
    //Atributos
    private ObjectOutputStream oos=null;
    
    //Metodos
    
    //constructores
    ObjetosOut(){}
    
    ObjetosOut(String name) throws FileNotFoundException{
        
        try {
            oos= new ObjectOutputStream(new FileOutputStream(name));
                    
                    
                    } catch (IOException ex) {
            Logger.getLogger(ObjetosOut.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
    /**
     *
     * @param p
     */
    public void escribirObjeto(Object p){
        try {
         
            oos.writeObject(p);
        } catch (IOException ex) {
            Logger.getLogger(ObjetosOut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @throws IOException
     */
    public void cerrarEnlace() throws IOException{
        oos.close();
    }
    
    
    

}
