/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Usuario {

    private String nick;
    private String password;
    private Date fechaNacimiento;

    /**
     *
     * @param nick
     * @param password
     * @param fechaNacimiento
     */
    public Usuario(String nick, String password, Date fechaNacimiento) {
        this.nick = nick;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Usuario{" + "nick=" + nick + ", password=" + password + ", fechaNacimiento=" + fechaNacimiento + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nick);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.fechaNacimiento);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nick, other.nick)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return true;
    }

}
