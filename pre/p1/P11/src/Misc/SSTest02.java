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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
        try {
            SpreadSheet hojaCalculo = SpreadSheet.create(1,20,20);
            Sheet hoja = hojaCalculo.getSheet(0);
            int valor;
            for(int i=0;i<4;i++) 
                for(int j=0;j<6;j++){
                    valor = (int) Math.floor(Math.random()*11);
                    hoja.setValueAt(valor,j + 3,i + 5);
                    if(valor < 10)
                        hoja.getCellAt(j + 3, i + 5).setBackgroundColor(Color.red);
                    else
                        hoja.getCellAt(j + 3, i + 5).setBackgroundColor(Color.blue);
                }
            
            final File file = new File("test02.ods");         
            hojaCalculo.saveAs(file);
           
        } catch (IOException ex) {
            Logger.getLogger(SSTest02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
