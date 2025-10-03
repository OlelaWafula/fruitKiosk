/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import shared.Dbconnect;
import shared.Task;

public class AddFruitPrice implements Task {
    private String fruit;
    private double price;

    public AddFruitPrice(String fruit, double price) {
        this.fruit = fruit;
        this.price = price;
    }

    public String execute() throws Exception {
        String response;
        try (Connection conn = Dbconnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO fruit (fruit, price) VALUES (?, ?)"
            );
            ps.setString(1, fruit);
            ps.setDouble(2, price);
            ps.executeUpdate();
            response = "Added "+ fruit +  " with price "+ price;
        }
        catch(Exception e){
        response = "Database Error: " + e.getMessage();
        }
        
        return response;
    }
}
