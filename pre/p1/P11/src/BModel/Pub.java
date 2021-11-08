/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.io.File;
import java.sql.Time;

/**
 * Clase hija de Local correspondiente a la respresentación de un Pub.
 * @author Usuario
 */
public class Pub extends Local {
    
    private Time apertura,clausura;

    /**
     * Cada pub tendrá un nombre, dirección, una descripción y horas de apertura
     * y clausura.
     * @param nombreLocal
     * @param mDireccion
     * @param descripcion
     * @param apertura
     * @param clausura
     */

    public Pub(String nombreLocal, Direccion mDireccion, String descripcion, Time apertura, Time clausura) {
        super(nombreLocal, mDireccion, descripcion);
        this.apertura = apertura;
        this.clausura = clausura;
    }
    public Pub(String nombreLocal, Direccion mDireccion, String descripcion) {
        super(nombreLocal, mDireccion, descripcion);
    }
    public Pub(String fromXML) {
        super(fromXML);
        this.apertura = apertura;
        this.clausura = clausura;
    }
    /**
     * El getter de la hora de apertura
     * @return
     */

    public Time getApertura() {
        return apertura;
    }

    /**
     * Setter de la hora de apertura
     * @param apertura
     */

    public void setApertura(Time apertura) {
        this.apertura = apertura;
    }

    /**
     * Getter de la hora de clausura
     * @return
     */

    public Time getClausura() {
        return clausura;
    }

    /**
     * Setter de la hora de apertura
     * @param clausura
     */

    public void setClausura(Time clausura) {
        this.clausura = clausura;
    }
    
    @Override
    public String toXML() {
        return "<Pub>" +
                "   <nombreLocal="+super.getNombreLocal()+">" +
                "   <descripcion="+super.getDescripcion()+">" +
                "   <apertura="+apertura.toString()+">" +
                "   <clausura="+clausura.toString()+">" +
                super.getmDireccion().toXML()+
               "</Pub>";
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
