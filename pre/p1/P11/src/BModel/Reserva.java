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
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Reserva
 * @author Usuario
 */
public class Reserva implements XMLRepresentable, Serializable {

    private Float descuento;
    private LocalDate ld;
    private Usuario cliente;
    private Reservable reservable;
    private LocalTime lt;

    /**
     * Constructor de la clase Reserva. A diferencia del constructor siguiente,
     * este es para reservas que si tienen descuento.
     * 
     * @param descuento
     * @param ld
     * @param cliente
     * @param reservable
     * @param lt
     */

    public Reserva(Float descuento, LocalDate ld, Usuario cliente, Reservable reservable, LocalTime lt) {
        this.descuento = descuento;
        this.cliente = cliente;
        this.reservable = reservable;
        this.ld = ld;
        this.lt = lt;
    }

    /**
     * Constructor de la clase Reserva. En este caso son para reservas sin
     * descuento.
     * 
     * @param cliente
     * @param reservable
     * @param ld
     * @param lt
     */

    public Reserva(LocalDate ld, Usuario cliente, Reservable reservable, LocalTime lt) {
        this.cliente = cliente;
        this.reservable = reservable;
        this.ld = ld;
        this.lt = lt;
    }
    public Reserva(String fromXML) {
        String[] atr = fromXML.split(";");
        this.ld = LocalDate.parse(atr[0]);
        this.lt = LocalTime.parse(atr[1]);
    }
    public Reserva () {}

    /**
     * Getter del descuento de la reserva
     * @return
     */

    public Float getDescuento() {
        return descuento;
    }

    /**
     * Setter del descuento
     * @param descuento
     */

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    /**
     * Getter de la LocalDate de la reserva
     * @return
     */

    public LocalDate getLd() {
        return ld;
    }

    /**
     * Setter de LocalDate
     * @param ld
     */

    public void setLd(LocalDate ld) {
        this.ld = ld;
    }

    /**
     * Getter del cliente que hace la reserva
     * @return
     */

    public Usuario getCliente() {
        return cliente;
    }

    /**
     * Setter de cliente
     * @param cliente
     */

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    /**
     * Getter de Reservable
     * @return
     */

    public Local getReservable() {
        return reservable;
    }

    /**
     * Setter de Reservable
     * @param reservable
     */

    public void setReservable(Reservable reservable) {
        this.reservable = reservable;
    }

    /**
     * Getter de LocalTime de la reserva
     * @return
     */

    public LocalTime getLt() {
        return lt;
    }

    /**
     * Setter de LocalTime
     * @param lt
     */

    public void setLt(LocalTime lt) {
        this.lt = lt;
    }


    /**
     * Método toString reescrito sobre la clase Reserva
     * Sirve para imprimir por pantalla la clase Reserva
     * @return
     */

    @Override
    public String toString() {
        return "Reserva{" + "descuento=" + descuento + ", ld=" + ld + ", cliente=" + cliente + ", reservable=" + reservable + ", lt=" + lt + '}';
    }

    /**
     * Override de la función hashCode que nos genera un hashCode hechos 
     * con los atributos de esta clase (en este caso con LocalDate, cliente,
     * reservable y LocalTime)
     * @return
     */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.ld);
        hash = 67 * hash + Objects.hashCode(this.cliente);
        hash = 67 * hash + Objects.hashCode(this.reservable);
        hash = 67 * hash + Objects.hashCode(this.lt);
        return hash;
    }
    /**/

    /**
     * Override al método equals para comparar dos objetos ld (LocalDate), 
     * cliente, reservable y lt (LocalTime).
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.ld, other.ld)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.reservable, other.reservable)) {
            return false;
        }
        if (!Objects.equals(this.lt, other.lt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toXML() {
        return "<Reserva>\n" +
                "   <descuento>"+descuento+"</descuento>\n" +
                "   <ld>"+ld+"</ld>\n" +
                "   <cliente>"+cliente.getNick()+"</cliente>\n" +
                "   <reservable>"+reservable.getNombreLocal()+"</reservable>\n" +
                "   <lt>"+lt.toString()+"</lt>\n" +
               "</Reserva>\n";
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
