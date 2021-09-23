/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author Usuario
 */
public class Review {
    
    //Posible try catch de incumplimiento de estrellas between 0 y 5 ?
    //Posible try catch de incumplimiento de 500 caracteres
    private String comentario;
    private int estrellas;
    private Date fechaReview;
    private Usuario cliente;
    private Local local;
    
    public Review (String comentario, int estrellas, Date fechaReview, Cliente cliente, Local local){
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fechaReview = fechaReview;
        this.cliente = cliente;
        this.local = local;     
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public Date getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(Date fechaReview) {
        this.fechaReview = fechaReview;
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
        final Review other = (Review) obj;
        if (!Objects.equals(this.fechaReview, other.fechaReview)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        return true;
    }
    
    
}
