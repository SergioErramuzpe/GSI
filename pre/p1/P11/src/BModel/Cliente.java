/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase hija de Usuario correspondiente a la respresentación de un Cliente.
 * @author Usuario
 */
public class Cliente extends Usuario implements Serializable{

    /**
     * Cada propietario tendrá un nick único, contraseña y su fecha de 
     * nacimiento.
     * @param nick
     * @param password
     * @param fechaNacimiento
     */
    public Cliente(String nick, String password, LocalDate fechaNacimiento) {
        super(nick, password, fechaNacimiento);
    }
    
    public Cliente(String fromXML) {
        super(fromXML);
    }
    
    @Override
    public String toXML() {
        return "<Cliente>\n" +
                "   <nick>"+super.getNick()+"</nick>\n" +
                "   <password>"+super.getPassword()+"</password>\n" +
                "   <fechaNacimiento>"+super.getFechaNacimiento()+"</fechaNacimiento>\n" +
               "</Cliente>\n";   
    }

    @Override
    public boolean saveToXML(File f) {
        BufferedWriter br;
        try (FileWriter myWriter = new FileWriter(f,true)) {
            br = new BufferedWriter(myWriter);
            br.write(this.toXML());
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }

    @Override
    public boolean saveToXML(String filePath) {
        BufferedWriter br;
        try (FileWriter myWriter = new FileWriter(new File(filePath),true)) {
            br = new BufferedWriter(myWriter);
            br.write(this.toXML());
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    
}
