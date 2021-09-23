/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Local {

    private String nombreLocal;
    private Direccion mDireccion;
    private String descripcion;

    /**
     *
     * @param nombreLocal
     * @param mDireccion
     * @param descripcion
     */
    public Local(String nombreLocal, Direccion mDireccion, String descripcion) {
        this.nombreLocal = nombreLocal;
        this.mDireccion = mDireccion;
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Local{" + "nombre del local=" + nombreLocal + ", dirección=" + mDireccion + ", descripción=" + descripcion + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombreLocal);
        hash = 97 * hash + Objects.hashCode(this.mDireccion);
        hash = 97 * hash + Objects.hashCode(this.descripcion);
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
        final Local other = (Local) obj;
        if (!Objects.equals(this.nombreLocal, other.nombreLocal)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.mDireccion, other.mDireccion)) {
            return false;
        }
        return true;
    }

}
