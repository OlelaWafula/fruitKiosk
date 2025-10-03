/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import shared.Compute;
import shared.Task;

public class FruitComputeEngine extends UnicastRemoteObject implements Compute {
    public FruitComputeEngine() throws RemoteException {
        super();
    }

    public String executeTask(Task t) throws RemoteException {
        try {
            return t.execute();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            FruitComputeEngine engine = new FruitComputeEngine();
            java.rmi.Naming.rebind("FruitServiceComputeEngine", engine);
            System.out.println("RMI server started...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

