/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.io.Serializable;

/**
 * Clase hija de Local correspondiente a la respresentación de Reservable.
 * @author Usuario
 */
public class Reservable extends Local implements Serializable {

    /**
     * Constructor de la clase Reservable
     * 
     * @param nombreLocal
     * @param mDireccion
     * @param descripcion
     */

    public Reservable(String nombreLocal, Direccion mDireccion, String descripcion) {
        super(nombreLocal, mDireccion, descripcion);
    }
    public Reservable(Local local) {
        super(local.getNombreLocal(), local.getmDireccion(), local.getDescripcion());
    }
    public Reservable(String fromXML) {
        super(fromXML);
    }
  
}
