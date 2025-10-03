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


public class CalFruitCostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       String fruit  = request.getParameter("fruitname");
       double qty = Double.parseDouble(request.getParameter("quantity"));
        
        response.setContentType("text/plain");
        if (fruit == null || fruit.trim().isEmpty()) {
            response.getWriter().println("Error: Fruit name is required.");
            return;
        }
         try{
         Compute engine = (Compute)Naming.lookup("rmi://localhost/FruitServiceComputeEngine");
         String result = engine.executeTask(new CalFruitCost(fruit,qty));
         response.getWriter().println(result);
         
         }catch(Exception e){
         response.getWriter().println("Error: " + e.getMessage());
         }
         
         // Send result back to UI
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body style='font-family: Arial;'>");
        out.println("<br />");
        out.println("<a href='http://localhost:8080/fruitKiosk/'><< BACK</a>");
        out.println("</body></html>");
    }


}
