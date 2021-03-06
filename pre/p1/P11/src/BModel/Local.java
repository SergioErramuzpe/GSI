/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import GSILabs.Serializable.XMLRepresentable;
import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Clase padre tanto de Pub, Bar y Restaurante.
 * @author Usuario
 */
public class Local implements XMLRepresentable, Serializable {

    private String nombreLocal;
    private Direccion mDireccion;
    private String descripcion;
    private Set<Propietario> listaPropietarios = new HashSet<Propietario>();

    /**
     * Constructor de la clase Local
     * Los locales tendrán un nombre, una dirección y una descripción.
     * @param nombreLocal
     * @param mDireccion
     * @param descripcion
     */
    public Local(String nombreLocal, Direccion mDireccion, String descripcion) {
        this.nombreLocal = nombreLocal;
        this.mDireccion = mDireccion;
        this.descripcion = descripcion;
    }
    public Local(String fromXML) {
        String[] atr = fromXML.split(";");
        String[] direccionSplit;
        this.nombreLocal = atr[0];
        direccionSplit = atr[1].split("%");
        this.mDireccion = new Direccion(direccionSplit[0], direccionSplit[1], direccionSplit[2], 
                Integer.parseInt(direccionSplit[3]));
        this.descripcion = atr[2];
    }
    /**
     * Getter del nombre del local
     * @return
     */

    public String getNombreLocal() {
        return nombreLocal;
    }

    /**
     * Setter del nombre del local
     * @param nombreLocal
     */
    
    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    /**
     * Getter de la dirección del local
     * @return
     */

    public Direccion getmDireccion() {
        return mDireccion;
    }

    /**
     * Setter de la dirección del local
     * @param mDireccion
     */

    public void setmDireccion(Direccion mDireccion) {
        this.mDireccion = mDireccion;
    }
    
    /**
     * Getter de la descripción 
     * @return
     */

    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Setter de la descripción
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Getter de la lista de propietarios
     * @return
     */
    
    public Set<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    /**
     * Setter de la lista de propietarios
     * @param listaPropietarios
     */

    public void setListaPropietarios(Set<Propietario> listaPropietarios) {
        this.listaPropietarios = listaPropietarios;
    }

    /**
     * Función que añade a un propietario a nuestra lista de propietarios
     * @param p
     * @return 
     */
    public boolean addPropietario (Propietario p) {
        return this.listaPropietarios.add(p);
    }

    /**
     * Función que comprueba que el tamaño de nuestra lista de propietarios
     * sea como máximo de 3. Condición pedida en enunciado del proyecto.
     * @return
     */

    public boolean propietariosLleno () {
        return this.listaPropietarios.size() == 3;
    }

    /**
     * Método toString reescrito sobre la clase Local
     * Sirve para imprimir por pantalla la clase Local
     * @return
     */
    @Override
    public String toString() {
        return "Local{" + "nombre del local=" + nombreLocal + ", dirección=" + mDireccion + ", descripción=" + descripcion + '}';
    }

    /**
     * Override de la función hashCode que nos genera un hashCode hechos 
     * con los atributos de esta clase (en este caso solo con la dirección)
     * @return
     */

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.mDireccion);
        return hash;
    }

    /**
     * Override al método equals para comparar dos objetos dirección
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
        final Local other = (Local) obj;
        if (!Objects.equals(this.mDireccion, other.mDireccion)) {
            return false;
        }
        return true;
    }

    /**
     * Método para comparar dos Locales por nombre y direccion en la lista
     * de Locales
     * 
     * @param obj
     * @return true si ambos objetos son iguales.
     */
    public boolean equalsInList(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Local other = (Local) obj;
        if (!Objects.equals(this.nombreLocal, other.nombreLocal)) {
            return false;
        }
        if (!Objects.equals(this.mDireccion, other.mDireccion)) {
            return false;
        }
        return true;      
    }
    
    
  

    /**
     * Función que nos elimina a un propietario de nuestra lista de propietarios
     * @param p
     * @return 
     */
    public boolean deletePropietario(Propietario p) {
        return listaPropietarios.remove(p);      
    }

    @Override
    public String toXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean contains(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
