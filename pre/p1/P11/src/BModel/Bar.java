/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

import java.util.Arrays;

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

    /**
     *
     * @return
     */
    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Bar{nombreLocal="+super.getNombreLocal() + "mDireccion="+super.getmDireccion()+"tags=" + tags + '}';
    }
    
    

}
