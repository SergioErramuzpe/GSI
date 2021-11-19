/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTesting;


import BModel.Local;
import BSystem.BusinessSystem;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author oihan
 */
public class P03Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //Se instancia un BS
        BusinessSystem bs = BusinessSystem.getBusinessSystem();
        final File file = new File("pruebaXML.xml");
        
        //Se rellena la clase con ejemplos
        bs.crearBBDD();
        
        //Se guarda en un XML
        bs.saveToXML(file);
        
        //Se olvida la instancia con la BBDD llena
        bs = null;
        
        try ( //Se imprime el archivo creado en xml
                Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }
        
        //Se adquiere una nueva instancia y se procede a llenarla con un archivo XML
        bs = BusinessSystem.getBusinessSystem();
        bs.loadXMLFile(file);
        
        
        //Si no hay ningún tipo de excepción en la carga del archivo, significa que todo ha sido correcto
        System.out.println(bs.existeNick("nick4"));
        System.out.println(bs.existeNick("nick6"));
        System.out.println(bs.existeNick("nick9"));
        System.out.println(bs.existeNick("nick10"));
        
        HashSet<Local> locales = bs.getLocales();
        
        for (Local l : locales )
            System.out.println(l.getNombreLocal());
        
        
        
        
        
        
        
        
        
    }
    
}
