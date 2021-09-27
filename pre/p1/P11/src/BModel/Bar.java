/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

/**
 * Clase hija de Local correspondiente a la respresentación de un Bar.
 * @author Usuario
 */
public class Bar extends Reservable {

    private String[] tags;

   

    /**
     * Cada bar tendrá un nombre, dirección y una breve descripción de esta
     * @param tags
     */

    public Bar(String[] tags, String nombreLocal, Direccion mDireccion, String descripcion) {
        super(nombreLocal, mDireccion, descripcion);
        this.tags = tags;
    }


    /**
     * Getter de los tags de los bares
     * @return
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }



    /**
     * Método toString reescrito sobre la clase Bar
     * @return
     */
    @Override
    public String toString() {
        return "Bar{nombreLocal="+super.getNombreLocal() + "mDireccion="+super.getmDireccion()+"tags=" + tags + '}';
    }
    
    

}
