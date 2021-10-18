/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.model.OpenDocument;



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
        try {
            Object[] firstArray = new Object[6];
            Object[][] table = new Object[3][6];
        
            for(int j=0;j<6;j++)
                firstArray[j] = (int) Math.floor(Math.random()*11);
            for(int i=0;i<3;i++) 
                for(int j=0;j<6;j++)
                    table[i][j] = (int) Math.floor(Math.random()*11);
                
            DefaultTableModel model = new DefaultTableModel(table,firstArray);
 
            final File file = new File("test01.ods");         
            
            SpreadSheet.createEmpty(model).saveAs(file);
            
            
        } catch (IOException ex) {
            Logger.getLogger(SSTest01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
