/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Reserva {

    //Igual falta la hora
    private Float descuento;
    private LocalDate ld;
    private Usuario cliente;
    private Local local;
    private LocalTime lt;
    
    public Reserva(Float descuento, LocalDate ld, Usuario cliente, Local local, LocalTime lt) {
        this.descuento = descuento;
        this.ld = ld;
        this.cliente = cliente;
        this.local = local;
        this.lt = lt;
    }
    
    
    public Reserva () {}

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public LocalDate getLd() {
        return ld;
    }

    public void setLd(LocalDate ld) {
        this.ld = ld;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public LocalTime getLt() {
        return lt;
    }

    public void setLt(LocalTime lt) {
        this.lt = lt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.descuento);
        hash = 79 * hash + Objects.hashCode(this.ld);
        hash = 79 * hash + Objects.hashCode(this.cliente);
        hash = 79 * hash + Objects.hashCode(this.local);
        hash = 79 * hash + Objects.hashCode(this.lt);
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.ld, other.ld)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.lt, other.lt)) {
            return false;
        }
        return true;
    }

    
    
    
}
