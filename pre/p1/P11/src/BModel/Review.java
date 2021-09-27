/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase Review
 * @author Usuario
 */
public class Review {

    private String comentario;
    private int estrellas;
    private LocalDate fechaReview;
    private Usuario cliente;
    private Local local;

    /**
     * Constructor de la clase Review
     * 
     * @param comentario
     * @param estrellas
     * @param fechaReview
     * @param cliente
     * @param local
     */

    public Review(String comentario, int estrellas, LocalDate fechaReview, Cliente cliente, Local local) {
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fechaReview = fechaReview;
        this.cliente = cliente;
        this.local = local;
    }

    /**
     * Getter del comentario de la review
     * @return
     */

    public String getComentario() {
        return comentario;
    }

    /**
     * Setter del comentario
     * @param comentario
     */

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Getter de las estrellas de la review (entre 0 y 5)
     * @return
     */

    public int getEstrellas() {
        return estrellas;
    }

    /**
     * Setter de las estrellas de la review
     * @param estrellas
     */

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    /**
     * Getter de la fecha en la que se hizo la review
     * @return
     */

    public LocalDate getFechaReview() {
        return fechaReview;
    }

    /**
     * Setter de fechaReview
     * @param fechaReview
     */

    public void setFechaReview(LocalDate fechaReview) {
        this.fechaReview = fechaReview;
    }

    /**
     * Getter del cliente que realiza la Review
     * @return
     */

    public Usuario getCliente() {
        return cliente;
    }

    /**
     * Setter del cliente
     * @param cliente
     */

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    /**
     * Getter del local al que se le hace la review
     * @return
     */

    public Local getLocal() {
        return local;
    }

    /**
     * Setter del local
     * @param local
     */

    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Método toString reescrito sobre la clase Review
     * Sirve para imprimir por pantalla la clase Review
     * @return
     */

    @Override
    public String toString() {
        return "Review{" + "comentario=" + comentario + ", estrellas=" + estrellas + ", fechaReview=" + fechaReview + ", cliente=" + cliente + ", local=" + local + '}';
    }

    /**
     * Override de la función hashCode que nos genera un hashCode hechos 
     * con los atributos de esta clase (en este caso con la fechaReview, cliente
     * y local)
     * @return
     */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.fechaReview);
        hash = 31 * hash + Objects.hashCode(this.cliente);
        hash = 31 * hash + Objects.hashCode(this.local);
        return hash;
    }

    /**
     * Override al método equals para comparar dos objetos fechaReview,
     * cliente y local.
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
