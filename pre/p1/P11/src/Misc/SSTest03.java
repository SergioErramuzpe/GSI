/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import java.io.File;
import java.io.IOException;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author iazca
 */
public class SSTest03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File file = new File("test02.ods");
        int array[][] = null;
        try{
            SpreadSheet hojaCalculo = SpreadSheet.createFromFile(file);
            int columnaMax = hojaCalculo.getSheet(0).getColumnCount();
            int filaMax = hojaCalculo.getSheet(0).getRowCount();
            for(int fila = 0; fila < 4; fila++){
                for(int columna = 4; columna < 6; columna++){
                    array[fila][columna] = (int) hojaCalculo.getSheet(0).getCellAt(fila, columna).getValue();                  
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
