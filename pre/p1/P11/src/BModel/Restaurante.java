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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase hija de Local correspondiente a la respresentación de un Restaurante.
 * @author Usuario
 */
public class Restaurante extends Reservable implements XMLRepresentable {

    private int precio;
    private int capacidadTotal;
    private int capacidadMesa;

    /**
     * Cada restaurante tendrá un precio, capacidad de este, capacidad de sus 
     * mesas, nombre, dirección y una breve descripción.
     * @param precio
     * @param capacidadTotal
     * @param capacidadMesa
     * @param nombreLocal
     * @param mDireccion
     * @param descripcion
     */
    public Restaurante(int precio, int capacidadTotal, int capacidadMesa, String nombreLocal, Direccion mDireccion, String descripcion) {
        super(nombreLocal, mDireccion, descripcion);
        this.precio = precio;
        this.capacidadTotal = capacidadTotal;
        this.capacidadMesa = capacidadMesa;
    }
    public Restaurante(String fromXML) {
        super(fromXML);
        String[] atr = fromXML.split(";");
        this.precio = Integer.parseInt(atr[3]);
        this.capacidadTotal = Integer.parseInt(atr[4]);
        this.capacidadMesa = Integer.parseInt(atr[5]);
    }
    /**
     * El getter del precio
     * @return
     */

    public int getPrecio() {
        return precio;
    }

    /**
     * El setter del precio
     * @param precio
     */

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Otro getter como en precio pero ahora este nos devuelve la capacidadTotal
     * @return
     */

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    /**
     * El setter de la capacidadTotal
     * @param capacidadTotal
     */

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    /**
     * Nos devuelve la capacidad de las distintas mesas
     * @return
     */

    public int getCapacidadMesa() {
        return capacidadMesa;
    }

    /**
     * Da a las mesas sus respectivas capacidades (setter).
     * @param capacidadMesa
     */

    public void setCapacidadMesa(int capacidadMesa) {
        this.capacidadMesa = capacidadMesa;
    }

    /**
     * Método toString reescrito sobre la clase Restaurante
     * @return
     */
    @Override
    public String toString() {
        return "Restaurante{" + "precio=" + precio + ", capacidadTotal=" + capacidadTotal + ", capacidadMesa=" + capacidadMesa + '}';
    }
    
    @Override
    public String toXML() {
        return "<Restaurante>\n" +
                "   <nombreLocal>"+super.getNombreLocal()+"</nombreLocal>\n" +
                "   <descripcion>"+super.getDescripcion()+"</descripcion>\n" +
                "   <precio>"+precio+"</precio>\n" +
                "   <capacidadTotal>"+capacidadTotal+"</capacidadTotal>\n" +
                "   <capacidadMesa>"+capacidadMesa+"</capacidadMesa>\n" +
                super.getmDireccion().toXML()+
               "</Restaurante>\n";
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
