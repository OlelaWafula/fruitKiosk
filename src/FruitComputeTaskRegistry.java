/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import shared.Compute;

public class FruitComputeTaskRegistry {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        Compute comp = (Compute) registry.lookup("FruitComputeEngine");
    }
}
