/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Contestacion {

    private Usuario propietario;
    private Review review;
    private String comentario;
    private LocalDate fechaContestacion;
    
    public Contestacion() {}

    public Contestacion(Usuario propietario, Review review, String comentario, LocalDate fechaContestacion) {
        this.propietario = propietario;
        this.review = review;
        this.comentario = comentario;
        this.fechaContestacion = fechaContestacion;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaContestacion() {
        return fechaContestacion;
    }

    public void setFechaContestacion(LocalDate fechaContestacion) {
        this.fechaContestacion = fechaContestacion;
    }

    @Override
    public String toString() {
        return "Contestacion{" + "propietario=" + propietario + ", review=" + review + ", comentario=" + comentario + ", fechaContestacion=" + fechaContestacion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.propietario);
        hash = 37 * hash + Objects.hashCode(this.review);
        hash = 37 * hash + Objects.hashCode(this.fechaContestacion);
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
        final Contestacion other = (Contestacion) obj;
        if (!Objects.equals(this.review, other.review)) {
            return false;
        }
        if (!Objects.equals(this.fechaContestacion, other.fechaContestacion)) {
            return false;
        }
        return true;
    }
    
    
    
}
