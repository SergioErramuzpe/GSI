/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.persistence;

/**
 *
 * @author oihan
 */
public class XMLParsingException extends Exception{
    private final int codigoError;
    
    public XMLParsingException(int codigoError) {
        super();
        this.codigoError = codigoError;
    }
    public int getCode() {
        return this.codigoError;
    }
    
    @Override
    public String getMessage () {
        
        switch (this.codigoError) {
            case 1: //
                return "Error: ";
            case 2: //
                return "Error: ";
            case 3: //
                return "Error: ";
            default:
                return null; 
        }
    }
}
