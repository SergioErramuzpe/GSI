/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Contestacion {

    private Usuario propietario;
    private Review review;
    private String comentario;
    private Date fechaContestacion;
    
    //GetDueño from Review from Local ???

    public Contestacion(Usuario propietario, Review review, String comentario, Date fechaContestacion) {
        this.propietario = propietario;
        this.review = review;
        this.comentario = comentario;
        this.fechaContestacion = fechaContestacion;
    }
    
}