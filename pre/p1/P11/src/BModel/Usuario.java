/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import GSILabs.Serializable.XMLRepresentable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase padre tanto de Cliente como de Propietario.
 * @author Usuario
 */
public class Usuario implements XMLRepresentable, Serializable {

    private String nick;
    private String password;
    private LocalDate fechaNacimiento;

    public Usuario() {}

    /**
     * Constructor de la clase Usuario
     * Cada usuario tendrá un nick único, una contraseña y la fecha de 
     * nacimiento.
     * @param nick
     * @param password
     * @param fechaNacimiento
     */
    public Usuario(String nick, String password, LocalDate fechaNacimiento) {
        this.nick = nick;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }
    public Usuario(String fromXML) {
        String[] atr = fromXML.split(";");
        this.nick = atr[0];
        this.password = atr[1];
        this.fechaNacimiento = LocalDate.parse(atr[2]);
    }
    /**
     * El getter del nick de nuestro usuario
     * @return
     */

    public String getNick() {
        return nick;
    }

    /**
     * El setter del nick
     * @param nick
     */

    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * El getter qeu nos devuelve la contraseña del usuario
     * @return
     */

    public String getPassword() {
        return password;
    }

    /**
     * El setter de la contraseña
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter que nos devuelve la fecha de nacimiento
     * @return
     */

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * El setter de la fecha de nacimiento
     * @param fechaNacimiento
     */

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public int obtenerEdad() {
        return (LocalDate.now().getYear() - this.fechaNacimiento.getYear());
    }

    /**
     * Método toString reescrito sobre la clase Usuario
     * Sirve para imprimir por pantalla la clase Usuario
     * @return
     */
    @Override
    public String toString() {
        return "Usuario{" + "nick=" + nick + ", password=" + password + ", fechaNacimiento=" + fechaNacimiento + '}';
    }

    /**
     *Override de la función hashCode que nos genera un hashCode hechos 
     * con los atributos de esta clase (en este caso solo con el nick)
     * @return
     */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nick);
        return hash;
    }

    /**
     * Override al método equals para comparar dos objetos nick
     * 
     * @param obj
     * @return true si ambos objetos son iguales.
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
        return true;
    }

    @Override
    public String toXML() {
        return "<Usuario>\n" +
                "   <nick>"+nick+ "</nick>\n"+
                "   <password>"+password+"</password>\n" +
                "   <fechaNacimiento>"+fechaNacimiento.toString()+"</fechaNacimiento>\n" +
               "</Usuario>\n";   
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
