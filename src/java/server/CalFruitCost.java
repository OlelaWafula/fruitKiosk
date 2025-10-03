/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import shared.Dbconnect;
import shared.Task;

/**
 *

 */
public class CalFruitCost implements Task {
    private String fruit;
    private double quantity;
    
    public CalFruitCost(String fruit, double quantity) {
        this.fruit = fruit;
        this.quantity = quantity;
    }
    
    public String execute() {
        String response;
        try (Connection conn = Dbconnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT price FROM fruit WHERE fruit = ?");
            ps.setString(1, fruit);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double price = rs.getDouble("price");
                double cost = price * quantity;
                response = "Total cost for " + quantity + " Pieces of " + fruit + " is Ksh  " + String.format("  %.2f", cost);
            } else {
                response = "Fruit not found: " + fruit;
            }
        } catch (Exception e) {
            response = "Error calculating cost: " + e.getMessage();
        }
        return response;
    }
    
    
    
}
