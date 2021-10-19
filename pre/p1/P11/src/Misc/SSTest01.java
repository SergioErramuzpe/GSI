/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;



/**
 * Clase en la que se realiza el test de una creación aleatoria de enteros y su 
 * posterior guardado en un fichero.ods.
 * @author iazca
 */
public class SSTest01 {

    /**
     * Método main de la clase SSTest01
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try {
            
            //Se crea Hoja de calculo
            SpreadSheet hojaCalculo = SpreadSheet.create(1,20,20);
            //Se crea una hoja dentro de la hoja de calculo
            Sheet hoja = hojaCalculo.getSheet(0);

            for(int i=0;i<4;i++) 
                for(int j=0;j<6;j++)
                    //Se introduce el numero aleatorio en la celda correspondiente
                    hoja.setValueAt((int) Math.floor(Math.random()*100),j,i);
                
            //Se guarda la Hoja de calculo en el archivo
            final File file = new File("test01.ods");         
            hojaCalculo.saveAs(file);
            
            
        } catch (IOException ex) {
            Logger.getLogger(SSTest01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
