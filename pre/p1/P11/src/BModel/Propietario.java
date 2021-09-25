/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Propietario extends Usuario {

    /**
     *
     * @param nick
     * @param password
     * @param fechaNacimiento
     */
    public Propietario(String nick, String password, LocalDate fechaNacimiento) {
        super(nick, password, fechaNacimiento);
    }
    
    

}
