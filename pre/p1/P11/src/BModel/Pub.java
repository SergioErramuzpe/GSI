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
