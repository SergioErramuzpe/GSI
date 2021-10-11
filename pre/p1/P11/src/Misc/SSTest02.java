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
 *
 * @author iazca
 */
public class SSTest02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //array de datos
        int array[][] = new int[4][6];
        
        //array para celdas
        String celdas[] ={"D6","E6","F6","G6","H6","I6","D7","E7","F7","G7","H7","I7","D8","E8","F8","G8","H8","I8","D9","E9","F9","G9","H9","I9"};
        
        //contador de celdas
        int countcelda = 0;
        
        //guardar test02.ods
        File file = new File("test02.ods");
        
        //abrir test02.ods
        try {
            final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
                } catch (IOException ex) {
            Logger.getLogger(SSTest02.class.getName()).log(Level.SEVERE, null, ex);
        }

        for(int i=0;i<4;i++)
            for(int j=0;j<6;j++){
                array[i][j] = (int) Math.floor(Math.random()*11);
                sheet.getCellAt(countcelda).setValue(array[i][j]);
                countcelda++;
            }
    }
    
}
