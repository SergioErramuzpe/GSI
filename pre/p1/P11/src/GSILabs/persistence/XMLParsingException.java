/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.persistence;

/**
 * Clase que gestiona las excepciones relacionadas con la lectura de XML.
 * @author oihan
 */
public class XMLParsingException extends Exception{
    
    /**
     * Se gestionan todas las excepciones como un error relacionado con la lectura del XML.
     * @param ex
     * @return 
     */
    public String getMessage (Exception ex) {
        return "Error: ha habido un problema en el parseo del archivo XML dado.";
    }
}
