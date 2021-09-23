/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

/**
 *
 * @author Usuario
 */
public class Bar extends Local {

    private String[] tags;

    public Bar(String[] tags, String nombreLocal, Direccion mDireccion, String descripcion) {
        super(nombreLocal, mDireccion, descripcion);
        this.tags = tags;
    }

}
