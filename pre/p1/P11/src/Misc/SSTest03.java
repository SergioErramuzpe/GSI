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
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * Clase en la que se realiza el test de lectura de un fichero.ods para su 
 * posterior almacenado en una estructura matricial.
 * @author iazca
 */
public class SSTest03 {

    /**
     * Método main de la clase SSTest03
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Declaración de variables
        File file = new File("test02.ods");
        int[][] array = new int[4][6];
        SpreadSheet hojaCalculo;
        
        //Se instancia el buffer de lectura
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            
            //Instancia de la hoja de cálculo 
            hojaCalculo = SpreadSheet.createFromFile(file);
            
            //Se adquiere la info de qué matriz se requiere
            System.out.println("Seleccione la posición donde se encuentra la matriz");
            System.out.println("Fila:");
            int fila = Integer.parseInt(reader.readLine());
            System.out.println("Columna: ");
            int columna = Integer.parseInt(reader.readLine());
            
            //Se almacena la matriz en el array instanciado del principio
            for(int i = fila; i < 4 + fila; i++){
                for(int j = columna; j < 6 + columna; j++){
                    array[i - fila][j - columna] = Integer.parseInt(hojaCalculo.getSheet(0).getCellAt(j, i).getTextValue());
                    System.out.println(hojaCalculo.getSheet(0).getCellAt(j, i).getTextValue());
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
