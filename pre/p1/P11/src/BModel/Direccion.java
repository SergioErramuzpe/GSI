/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import GSILabs.Serializable.XMLRepresentable;
import java.io.File;
import java.util.Objects;

/**
 * Clase Dirección
 *
 * @author Usuario
 */
public class Direccion implements XMLRepresentable {

    private String localidad; //ciudad
    private String provincia;
    private String calle;
    private int numero;

    public Direccion() {}

    /**
     * Constructor de la clase Dirección.
     *
     * @param localidad
     * @param provincia
     * @param calle
     * @param numero
     */
    public Direccion(String localidad, String provincia, String calle, int numero) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Getter de la localidad
     * @return
     */

    public String getLocalidad() {
        return localidad;
    }

    /**
     * Setter de la localidad
     * @param localidad
     */

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Getter de la provincia
     * @return
     */

    public String getProvincia() {
        return provincia;
    }

    /**
     * Setter de la provincia
     * @param provincia
     */

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Getter de la calle
     * @return
     */

    public String getCalle() {
        return calle;
    }

    /**
     * Setter de la calle
     * @param calle
     */

    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Getter del nº de calle de la dirección
     * @return
     */

    public int getNumero() {
        return numero;
    }

    /**
     * Setter del nº de calle de la dirección
     * @param numero
     */

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    

    /**
     * Método toString reescrito sobre la clase Dirección 
     * Sirve para imprimir por pantalla la clase Dirección.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Direccion{" + "Localidad=" + localidad + ", Provincia=" + provincia + ", Calle=" + calle + ", Numero=" + numero + '}';
    }

    /**
     * Override de la función hashCode que nos genera un hashCode hechos 
     * con los atributos de esta clase (en este caso localidad, provincia
     * calle y nº)
     * @return
     */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.localidad);
        hash = 97 * hash + Objects.hashCode(this.provincia);
        hash = 97 * hash + Objects.hashCode(this.calle);
        hash = 97 * hash + this.numero;
        return hash;
    }

    /**
     * Método para comparar dos objetos Direccion.
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
        final Direccion other = (Direccion) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        return true;
    }

    @Override
    public String toXML() {
        return "    <Direccion>\n" +
                "       <localidad>"+localidad+"</localidad>\n" +
                "       <provincia>"+provincia+"</provincia>\n" +
                "       <calle>"+calle+"</calle>\n" +
                "       <numero>"+numero+"</numero>\n" +
               "    </Direccion>\n";
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
