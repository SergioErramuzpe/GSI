/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTesting;


import BSystem.BusinessSystem;
import java.io.File;

/**
 *
 * @author oihan
 */
public class P03Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BusinessSystem bs = BusinessSystem.getBusinessSystem();
        final File file = new File("test03.xml");
        bs.crearBBDD();
        bs.parseXMLFile(file);
        bs.loadXMLFile(file);
    }
    
}
