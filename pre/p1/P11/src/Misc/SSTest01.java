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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
        int[][] array = new int[4][6];
        final File file = new File("test01.ods");
        DefaultTableModel model = new DefaultTableModel(4,6);
        
        for(int i=0;i<4;i++)
            for(int j=0;j<6;j++){
                array[i][j] = (int) Math.random();
                model.setValueAt( array[i][j], i, j);
            }
        
        try {
            SpreadSheet.createEmpty(model).saveAs(file);
        } catch (IOException ex) {
            Logger.getLogger(SSTest01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
