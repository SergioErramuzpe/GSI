/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;



/**
 *
 * @author iazca
 */
public class SSTest01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int[][] array = new int[4][6];
        final File file = new File("test01.ods");
                
        //JTable table = new JTable(4,6);
        TableModel table = new DefaultTableModel(4,6);
        
        
        for(int i=0;i<4;i++)
            for(int j=0;j<6;j++){
                array[i][j] = (int) Math.floor(Math.random()*11);
                table.setValueAt( array[i][j], i, j);
                System.out.println(table.getValueAt(i, j));
            }
        
        SpreadSheet.createEmpty(table).saveAs(file);
        OOUtils.open(file);
    }
    
}
