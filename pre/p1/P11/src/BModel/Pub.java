/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String[] atr = fromXML.split(";");
        if(atr.length>3){
            this.apertura = Time.valueOf(atr[3]);
            this.clausura = Time.valueOf(atr[4]);
        }
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
                "   <nombreLocal>"+super.getNombreLocal()+"</nombreLocal>" +
                "   <descripcion>"+super.getDescripcion()+"</descripcion>" +
                "   <apertura>"+apertura.toString()+"</apertura>" +
                "   <clausura>"+clausura.toString()+"</clausura>" +
                super.getmDireccion().toXML()+
               "</Pub>";
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
