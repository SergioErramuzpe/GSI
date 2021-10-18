/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import BModel.Bar;
import BModel.Cliente;
import BModel.Direccion;
import BModel.Local;
import BModel.Propietario;
import BModel.Pub;
import BModel.Reservable;
import BModel.Restaurante;
import BModel.Usuario;
import BSystem.BusinessSystem;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author iazca
 */
public class SSTest04 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BusinessSystem bs = BusinessSystem.getBusinessSystem();
       
        bs.crearBBDD();

        SpreadSheet sp = SpreadSheet.create(3, 40, 40);
        
        Sheet hojaBares = sp.getSheet("Bares");
        Sheet hojaPubs = sp.getSheet("Pubs");
        Sheet hojaRestaurantes = sp.getSheet("Restaurantes");
            
        HashSet<Local> locales = bs.getLocales();
        
        int bar = 0, pub = 0, rest = 0;
        
        int i = 0;
        
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
       
        File file = new File("test05.ods");
        
        sp.saveAs(file);
        
    }
    
}
