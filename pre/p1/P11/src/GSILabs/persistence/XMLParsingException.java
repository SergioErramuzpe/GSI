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
            case 1: //ParserConfigurationException
                return "Error: La configuración para escribir en el archivo XML no es correcta";
            case 2: //SAXException
                return "Error: Los controladores de evento XML han dado error";
            case 3: //IOException
                return "Error: No se puede acceder al archivo XML";
            default:
                return null; 
        }
    }
}
