/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import GSILabs.Serializable.XMLRepresentable;
import java.io.File;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase Contestación
 * 
 * @author Usuario
 */
public class Contestacion implements XMLRepresentable {

    private Usuario propietario;
    private Review review;
    private String comentario;
    private LocalDate fechaContestacion;
    
    public Contestacion() {}

    /**
     * Constructor de la clase Contestación
     * Formada por propietario, review, comentario y fechaContestacion
     * 
     * @param propietario
     * @param review
     * @param comentario
     * @param fechaContestacion
     */

    public Contestacion(Usuario propietario, Review review, String comentario, LocalDate fechaContestacion) {
        this.propietario = propietario;
        this.review = review;
        this.comentario = comentario;
        this.fechaContestacion = fechaContestacion;
    }
    public Contestacion(String fromXML) {
        String[] atr = fromXML.split(";");
        this.propietario = new Propietario(atr[0]);
        this.review = new Review(atr[1]);
        this.comentario = atr[2];
        this.fechaContestacion = LocalDate.parse(atr[3]);
    }

    /**
     * Getter de propietario de la contestación
     * @return
     */

    public Usuario getPropietario() {
        return propietario;
    }

    /**
     * Setter del propietario
     * @param propietario
     */

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    /**
     * Getter de la review 
     * @return
     */

    public Review getReview() {
        return review;
    }
    
    /**
     * Setter de la review
     * @param review
     */

    public void setReview(Review review) {
        this.review = review;
    }

    /**
     * Getter del comentario de la contestacion
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
     * Getter de la fecha de creación de la contestacion
     * @return
     */

    public LocalDate getFechaContestacion() {
        return fechaContestacion;
    }

    /**
     * Setter de fechacContestacion
     * @param fechaContestacion
     */

    public void setFechaContestacion(LocalDate fechaContestacion) {
        this.fechaContestacion = fechaContestacion;
    }

    /**
     * Método toString reescrito sobre la clase Contestacion
     * Sirve para imprimir por pantalla la clase Contestacion
     * 
     * @return
     */

    @Override
    public String toString() {
        return "Contestacion{" + "propietario=" + propietario + ", review=" + review + ", comentario=" + comentario + ", fechaContestacion=" + fechaContestacion + '}';
    }

    /**
     * Override de la función hashCode que nos genera un hashCode hechos 
     * con los atributos de esta clase (en este caso propietario, review y 
     * fechaContestacion)
     * @return
     */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.propietario);
        hash = 37 * hash + Objects.hashCode(this.review);
        hash = 37 * hash + Objects.hashCode(this.fechaContestacion);
        return hash;
    }


    /**
     * Override al método equals para comparar dos objetos review y 
     * fechaContestacion
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
        final Contestacion other = (Contestacion) obj;
        if (!Objects.equals(this.review, other.review)) {
            return false;
        }
        if (!Objects.equals(this.fechaContestacion, other.fechaContestacion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toXML() {
        return "<Contestacion>" +
                "   <propietario="+propietario.getNick()+">" +
                "   <review="+review.toXML()+">" +
                "   <comentario="+comentario+">" +
                "   <fechaContestacion="+fechaContestacion.toString()+">" +
               "</Contestacion>";
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
