/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Erra
 */
public class AdminHub {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Direccion del servidor:");
        String serverIp = scan.nextLine();
        System.out.println("Puerto de conexion con el servidor:");
        int port = Integer.parseInt(scan.nextLine());
        System.out.println("Tag del objeto romoto a contactar: ");
        String tag = scan.nextLine();
        
        try {
            Registry registry = LocateRegistry.getRegistry(serverIp, port);

            AdminGateway pbs = (AdminGateway) (Object) registry.lookup(tag);
            if(pbs.eliminaLocal(pbs.getLocal("Local 1")))
                System.out.println("Local eliminado correctamente");
            else
                System.out.println("El local seleccionado no ha podido ser"
                        + "eliminado");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
