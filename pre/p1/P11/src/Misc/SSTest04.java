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
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BusinessSystem bs = BusinessSystem.getBusinessSystem();
        
        Direccion dir1 = new Direccion ("rueblo 1", "Provincia 1", "Calle 1", 1);
        Direccion dir2 = new Direccion ("Pueblo 2", "Provincia 2", "Calle 2", 2);
        Direccion dir3 = new Direccion ("Pueblo 3", "Provincia 3", "Calle 3", 3);
        Direccion dir4 = new Direccion ("Pueblo 4", "Provincia 4", "Calle 4", 4);
        Direccion dir5 = new Direccion ("Pueblo 5", "Provincia 5", "Calle 5", 5);
        
        String[] tag = new String[1];
        tag[0] = "chorizo";
        
        Local local1 = new Bar(tag,"Local 1", dir1, "");
        bs.nuevoLocal(local1);
        Local local2 = new Bar(tag,"Local 2", dir2, "");
        bs.nuevoLocal(local2);
        Local local3 = new Bar(tag,"Local 3", dir3, "");
        bs.nuevoLocal(local3);
        
        Reservable reservable1 = new Reservable("Reservable 1", dir4, "");
        bs.nuevoLocal(reservable1);
        Reservable reservable2 = new Reservable("Reservable 2", dir5, "");
        bs.nuevoLocal(reservable2);
        
        Usuario usuario1 = new Usuario("nick1", "pw1", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(usuario1);
        Usuario usuario2 = new Usuario("nick2", "pw2", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(usuario2);
        Usuario usuario3 = new Usuario("nick3", "pw3", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(usuario3);
        Propietario propietario1 = new Propietario("nick4", "pw4", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(propietario1);
        Propietario propietario2 = new Propietario("nick5", "pw5", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(propietario2);
        Propietario propietario3 = new Propietario("nick6", "pw6", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(propietario3);
        Propietario propietario4 = new Propietario("nick7", "pw7", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(propietario4);
        Cliente cliente1 = new Cliente("nick8", "pw8", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(cliente1);
        Cliente cliente2 = new Cliente("nick9", "pw9", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(cliente2);
        Cliente cliente3 = new Cliente("nick10", "pw10", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(cliente3);
        
        
        
        
        
        
        SpreadSheet sp;
        /*
        Sheet hojaBares = SpreadSheet.create(1,20,20).getSheet(1);
        Sheet hojaPubs = SpreadSheet.create(2,20,20).getSheet(2);
        Sheet hojaRestaurantes = SpreadSheet.create(3,20,20).getSheet(3);
        */
        
        
        
        HashSet<Local> locales = bs.getLocales();
        int bar = 0, pub = 0, rest = 0;
        int i = 0;
        Object[][] data = new Object[locales.size()][11];
        for (Local local: locales) {
            
            if (local instanceof Bar) {
                System.out.println("Hola");
                data[i][0] = local.toString();
                        
            } else if (local instanceof Pub) {
                //hojaBares.setValueAt(bs, pub, pub);
           
            } else {
                //hojaBares.setValueAt(bs, pub, pub);     
            }
            i = 0;
        }
        Object[] o = new Object[1];
        o[0]='a';
        TableModel model = new DefaultTableModel(data,o);
        
        File file = new File("test02.ods");
        
        SpreadSheet.createEmpty(model).saveAs(new File("a.ods"));
        
    }
    
}
