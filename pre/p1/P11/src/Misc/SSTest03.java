/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
        SpreadSheet hojaCalculo;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            hojaCalculo = SpreadSheet.createFromFile(file);
            System.out.println("Seleccione la posición donde se encuentra la matriz");
            System.out.println("Fila:");
            int fila = Integer.parseInt(reader.readLine());
            System.out.println("Columna: ");
            int columna = Integer.parseInt(reader.readLine());
            for(int i = fila; i < 4 + fila; i++){
                for(int j = columna; j < 6 + columna; j++){
                    array[j - columna][i - fila] = (int) hojaCalculo.getSheet(0).getCellAt(j, i).getValue();
                    System.out.println(hojaCalculo.getSheet(0).getCellAt(j, i).getValue());
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
