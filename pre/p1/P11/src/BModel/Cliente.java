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
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 

}
