/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.io.File;
import java.time.LocalDate;

/**
 * Clase hija de Usuario correspondiente a la respresentación de un Cliente.
 * @author Usuario
 */
public class Cliente extends Usuario {

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
        return "<cliente>" +
                "   <nick="+super.getNick()+">" +
                "   <password="+super.getPassword()+">" +
                "   <fechaNacimiento"+super.getFechaNacimiento()+">" +
               "</cliente>";   
    }

    @Override
    public boolean saveToXML(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
