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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.AddFruitPrice;


public class AddFruitServlet extends HttpServlet {
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
        String fruit  = request.getParameter("fruitname");
        double price = Double.parseDouble(request.getParameter("fruitprice"));
        response.setContentType("text/plain");
         try{
         Compute engine = (Compute)Naming.lookup("rmi://localhost/FruitServiceComputeEngine");
         String result = engine.executeTask(new AddFruitPrice(fruit,price));
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
