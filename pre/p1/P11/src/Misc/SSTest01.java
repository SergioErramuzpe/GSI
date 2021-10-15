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
import org.jopendocument.dom.spreadsheet.SpreadSheet;



/**
 *
 * @author iazca
 */
public class SSTest01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //array de datos
        int array[][] = new int[4][6];
        
        //nombre del archivo ods
        final File file = new File("test01.ods");           
        
        //Declaramos SpreadSheet
        SpreadSheet hojaCalculo;
        
        try {
            //Indicamos el fichero de la SpreadSheet
            hojaCalculo = SpreadSheet.createFromFile(file);

            for(int i=0;i<4;i++)
                for(int j=0;j<6;j++){
                    
                    //rellenamos la matriz
                    array[i][j] = (int) Math.floor(Math.random()*11);
                    
                    //Añadir el dato a la tabla
                    hojaCalculo.getSheet(0).setValueAt( array[i][j], i, j);
                    
                    //Comprobación de que se ha introducido en la tabla
                    System.out.println(hojaCalculo.getSheet(0).getCellAt(i, j));
                } 
        } catch (IOException ex) {
            Logger.getLogger(SSTest01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
