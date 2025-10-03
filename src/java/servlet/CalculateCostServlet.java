/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import shared.Compute;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.CalFruitCost;
import server.CalculateCost;


public class CalculateCostServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */    
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {          
        String fruitname  = request.getParameter("fruitname");
        double quantity = Double.parseDouble(request.getParameter("quantity")); 
        double amtPaid = Double.parseDouble(request.getParameter("amountPaid"));
        String cashier  = request.getParameter("cashier");
        response.setContentType("text/plain");
        
         try{
         Compute engine = (Compute)Naming.lookup("rmi://localhost/FruitServiceComputeEngine");
         String total_cost = engine.executeTask(new CalFruitCost(fruitname,quantity));
         
         // Extract cost part from total_cost string
        String[] parts = total_cost.split("Ksh");
        String costStr = parts[1].trim(); // Get the string after "Ksh" and trim whitespace

        double cost = Double.parseDouble(costStr);
         //client task to calculate total cost
         String result = engine.executeTask(new CalculateCost(cost,amtPaid,cashier));
         
         response.getWriter().println(result);
         
         }catch(Exception e){
         response.getWriter().println("Error: " + e.getMessage());
         }
         
       
    }


}
