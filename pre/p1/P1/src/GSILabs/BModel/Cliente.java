/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Cliente extends Usuario {

    /**
     *
     * @param nick
     * @param password
     * @param fechaNacimiento
     */
    public Cliente(String nick, String password, Date fechaNacimiento) {
        super(nick, password, fechaNacimiento);
    }

}
