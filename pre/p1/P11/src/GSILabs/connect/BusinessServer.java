/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import BSystem.PublicBusinessSystem;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Erra
 */
public class BusinessServer {
    
    private static final int ADMIN_PORT = 1099;
    private static final int CLIENT_PORT = 1100;
    
    //Instancia de BusinessSystem
    
    
    /*Se debe almacenar la bbdd en un registro RMI puerto 1099*/
    public static void main(String[] args) {
        try {
            PublicBusinessSystem bs = PublicBusinessSystem.getPublicBusinessSystemUp();
            AdminGateway adminStub = (AdminGateway) UnicastRemoteObject.exportObject(bs, 0);
            ClientGateway clientStub = (ClientGateway) UnicastRemoteObject.exportObject(bs, 0);
            Registry adminRegistry = LocateRegistry.createRegistry(ADMIN_PORT);
            Registry clientRegistry = LocateRegistry.createRegistry(CLIENT_PORT);
            adminRegistry.rebind("adminGateway", adminStub);
            clientRegistry.rebind("clientGateway", clientStub);
            while (true) {}
        } catch (RemoteException ex){
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
