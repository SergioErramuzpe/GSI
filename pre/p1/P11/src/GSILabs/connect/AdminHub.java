/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import static GSILabs.connect.ClientHub.tryRegistry;
import static java.lang.System.exit;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        tryRegistry(0,tag,port,serverIp);
    }
    
    /**
     * Función que gestiona correctamente la conexión con el servidor y el flujo
     * básico de la clase
     * @param i
     * @param tag
     * @param port
     * @param serverIp 
     */
    public static void tryRegistry (int i, String tag, int port, String serverIp)  {
        try {
            if (i > 3)
                throw new Exception("Se han superado los intentos de conexion");
            Registry registry = LocateRegistry.getRegistry(serverIp, port);
            AdminGateway pbs = (AdminGateway) registry.lookup(tag);
            if(pbs.eliminaLocal(pbs.getLocal("Local 1")))
                System.out.println("Local eliminado correctamente");
            else
                System.out.println("El local seleccionado no ha podido ser"
                        + "eliminado");
        } catch (NotBoundException | RemoteException ex) {
            System.out.println(ex.getMessage());
            try {
                Thread.sleep(3);
                tryRegistry(i+1,tag,port,serverIp);
            } catch (InterruptedException ex1) {
                Logger.getLogger(ClientHub.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println(ex1.getMessage());
            } 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            exit(-1);
        }
    }
}

