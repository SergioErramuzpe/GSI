/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.sql.Time;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Pub extends Local {
    
    private Time apertura,clausura;

    public Pub(String nombreLocal, Direccion mDireccion, String descripcion, Time apertura, Time clausura) {
        super(nombreLocal, mDireccion, descripcion);
        this.apertura = apertura;
        this.clausura = clausura;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.apertura);
        hash = 71 * hash + Objects.hashCode(this.clausura);
        return hash;
    }

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
        final Pub other = (Pub) obj;
        if (!Objects.equals(this.apertura, other.apertura)) {
            return false;
        }
        if (!Objects.equals(this.clausura, other.clausura)) {
            return false;
        }
        return true;
    }

    public Time getApertura() {
        return apertura;
    }

    public void setApertura(Time apertura) {
        this.apertura = apertura;
    }

    public Time getClausura() {
        return clausura;
    }

    public void setClausura(Time clausura) {
        this.clausura = clausura;
    }
    
    

}
