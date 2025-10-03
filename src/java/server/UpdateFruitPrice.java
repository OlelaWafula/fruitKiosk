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




public class UpdateFruitPrice implements Task{
   
    private String fruitname;
    private double newprice;

    public UpdateFruitPrice(String fruitname, double newprice) {
        this.fruitname = fruitname;
        this.newprice = newprice;
    }

    public String execute() throws Exception {
        String response;
        try (Connection conn = Dbconnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE  fruit SET price = ? WHERE fruit=?"
            );
            ps.setDouble(1, newprice);
            ps.setString(2, fruitname);
            
            ps.executeUpdate();
            response = "Successfully Updated  "+ fruitname + " NEW PRICE IS:- "+ newprice;
        }
        catch(Exception e){
        response = "Update Error: " + e.getMessage();
        }
        
        return response;
    }
     
}
