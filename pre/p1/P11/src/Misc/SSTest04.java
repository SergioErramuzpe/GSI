/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import BModel.Bar;
import BModel.Local;
import BModel.Pub;
import BModel.Restaurante;
import BSystem.BusinessSystem;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * Clase que realiza un test en el que los locales almacenados en BS se guardan
 * en un formato especificado por la práctica en un fichero.ods dividido en 3
 * páginas.
 * @author iazca
 */
public class SSTest04 {

    /**
     * Método main de la clase SSTest04
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        //Se instancia la clase BS
        BusinessSystem bs = BusinessSystem.getBusinessSystem();
       
        //Se llena Bs con una bbdd de ejemplo
        bs.crearBBDD();

        //Se crea el spreadsheet con 3 páginas y se le da nombre a cada página
        SpreadSheet sp = SpreadSheet.create(3, 40, 40);
        Sheet hojaBares = sp.getSheet(0);
        Sheet hojaPubs = sp.getSheet(1);
        Sheet hojaRestaurantes = sp.getSheet(2);
           
        //Se lee el hashset de locales
        HashSet<Local> locales = bs.getLocales();
        
        //Se inicializan variables de posicionamiento 
        int bar = 0, pub = 0, rest = 0;
        int i = 0;
        
        /*Se recorren todos los locales del hashset. Su tratamiento es diferente
        al almacenarse en el ficher.ods ya que dependiendo del tipo de local que
        sea se necesita especificar más o menos atributos diferentes
        (por ejemplo: los pubs necesitan su horario y los bares tags)*/
        for (Local local: locales) {
            
            if (local instanceof Bar) {
                hojaBares.setValueAt(local.getNombreLocal(), i++, bar);
                hojaBares.setValueAt((local.getmDireccion().getCalle()+", "+local.getmDireccion().getNumero()), i++, bar);
                hojaBares.setValueAt(local.getmDireccion().getLocalidad(), i++, bar);
                hojaBares.setValueAt(local.getmDireccion().getProvincia(), i++, bar);
                hojaBares.setValueAt(local.getListaPropietarios().iterator().next().getNick(), i++, bar);
                String[] tags = ((Bar) local).getTags();
                for (String tag: tags)
                    hojaBares.setValueAt(tag, i++, bar);
                
                bar++;
                
                
            } else if (local instanceof Pub) {
                hojaPubs.setValueAt(local.getNombreLocal(),  i++, pub);
                hojaPubs.setValueAt((local.getmDireccion().getCalle()+", "+local.getmDireccion().getNumero()),  i++, pub);
                hojaPubs.setValueAt(local.getmDireccion().getLocalidad(),  i++, pub);
                hojaPubs.setValueAt(local.getmDireccion().getProvincia(),  i++, pub);
                hojaPubs.setValueAt(local.getListaPropietarios().iterator().next().getNick(), i++, pub);
                hojaPubs.setValueAt(((Pub)local).getApertura(), i++, pub);
                hojaPubs.setValueAt(((Pub)local).getClausura(), i++, pub);
                
                pub++;
                
            } else {
                hojaRestaurantes.setValueAt(local.getNombreLocal(), i++, rest);
                hojaRestaurantes.setValueAt((local.getmDireccion().getCalle()+", "+local.getmDireccion().getNumero()), i++, rest);
                hojaRestaurantes.setValueAt(local.getmDireccion().getLocalidad(), i++, rest);
                hojaRestaurantes.setValueAt(local.getmDireccion().getProvincia(), i++, rest);   
                hojaRestaurantes.setValueAt(local.getListaPropietarios().iterator().next().getNick(), i++, rest);
                hojaRestaurantes.setValueAt(((Restaurante)local).getPrecio(), i++, rest);
                hojaRestaurantes.setValueAt(((Restaurante)local).getCapacidadTotal(), i++, rest);
                hojaRestaurantes.setValueAt(((Restaurante)local).getCapacidadMesa(), i++, rest);
                
                
                rest++;
            }
            i = 0;
        }
       
        //Se guarda el fichero
        File file = new File("test04.ods");
        
        sp.saveAs(file);
        
    }
    
}
