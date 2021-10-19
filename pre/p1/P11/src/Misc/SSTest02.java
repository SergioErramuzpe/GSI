/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * Clase en la que se realiza el test de una creación aleatoria de enteros y su 
 * posterior guardado en un fichero.ods con formato y color específicos del 
 * ejercicio.
 * @author iazca
 */
public class SSTest02 {

    /**
     * Método main de la clase SSTest02
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            //Se crea la Hoja de calculo
            SpreadSheet hojaCalculo = SpreadSheet.create(1,20,20);
            //Se crea una hoja dentro de la hoja de calculo
            Sheet hoja = hojaCalculo.getSheet(0);
            
            //Variable para almacenar el numero aleatorio
            int valor;
            
            for(int i=0;i<4;i++) 
                for(int j=0;j<6;j++){
                    
                    valor = (int) Math.floor(Math.random()*100);
                    
                    //Se introduce el numero aleatorio en la celda correspondiente
                    hoja.setValueAt(valor,j + 3,i + 5);
                    
                    /*Si el numero de la celda es menor que 10 la celda sera 
                    roja, si es igual o superior sera azul*/
                    if(valor < 10)
                        hoja.getCellAt(j + 3, i + 5).setBackgroundColor(Color.red);
                    else
                        hoja.getCellAt(j + 3, i + 5).setBackgroundColor(Color.blue);
                }
            
            //Se guarda la Hoja de calculo en el archivo
            final File file = new File("test02.ods");         
            hojaCalculo.saveAs(file);
           
        } catch (IOException ex) {
            Logger.getLogger(SSTest02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
