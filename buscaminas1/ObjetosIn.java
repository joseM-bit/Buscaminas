/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminas1;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 * Jose Miguel/joseM-bit
 */
public class ObjetosIn {
    //Atributos
    private ObjectInputStream ois= null;
    
    
    //Metodos
    
    //constructores
    
    ObjetosIn(){}
    
    ObjetosIn(String name) throws FileNotFoundException{
        try {
            ois= new ObjectInputStream(new FileInputStream(name));
        } catch (IOException ex) {
            Logger.getLogger(ObjetosIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object lecturaObjeto() throws IOException, ClassNotFoundException{
      return ois.readObject();
      
    }
    
    /**
     *
     * @throws IOException
     */
    public void cerrarEnlace() throws IOException{
    ois.close();
    }
            

}
