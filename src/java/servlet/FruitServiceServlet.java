package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import javafx.concurrent.Task;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.AddFruitPrice;
import server.CalFruitCost;
import server.CalculateCost;
import server.DeleteFruitPrice;
import server.FruitComputeTaskRegistry;
import server.UpdateFruitPrice;
import shared.Compute;

public class FruitServiceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        String action = request.getParameter("action");
        String message = "";
        
        try {
            Compute engine = (Compute)Naming.lookup("rmi://localhost/FruitComputeEngine");
            Task task = null;

            switch (action) {
                case "add":
                    //verify with html params
                    String fruit_to_add  = request.getParameter("fruit");
                    double price = Double.parseDouble(request.getParameter("price"));
                    
                    String add_result = engine.executeTask(new AddFruitPrice(fruit_to_add,price));
                    response.getWriter().println(add_result);
                    
                    break;

                case "update":
                    String fruit_to_update  = request.getParameter("fruit");
        
                    double newprice = Double.parseDouble(request.getParameter("newprice"));
                    String update_result = engine.executeTask(new UpdateFruitPrice(fruit_to_update,newprice));
                    //response.getWriter().println("Success: "+ result);
                    response.getWriter().println(update_result);
                    
                    
                    break;

                case "delete":
                    String fruit_to_delete  = request.getParameter("fruit");
                    
                    String delete_result = engine.executeTask(new DeleteFruitPrice(fruit_to_delete));
                    //response.getWriter().println("Success: "+ result);
                    response.getWriter().println(delete_result);
                    
                    
                    break;

                case "calculate":
                  
                    String fruit_to_calc_cost  = request.getParameter("fruit");
                    double qty = Double.parseDouble(request.getParameter("quantity"));
                    
                    String cost_result = engine.executeTask(new CalFruitCost(fruit_to_calc_cost,qty));
                    //response.getWriter().println("Success: "+ result);
                    response.getWriter().println(cost_result);
                    break;

                case "receipt":
                    /*String fruitName = request.getParameter("name");
                    int qty = Integer.parseInt(request.getParameter("quantity"));
                    double paid = Double.parseDouble(request.getParameter("amountPaid"));
                    String cashier = request.getParameter("cashier");
                    task = new CalculateCost(fruitName, qty, paid, cashier);*/
                    
                    String cashier  = request.getParameter("cashier");
                    double total = Double.parseDouble(request.getParameter("total")); 
                    double amtGiven = Double.parseDouble(request.getParameter("cash")); 
                    
                    String result = engine.executeTask(new CalculateCost(total,amtGiven,cashier));
                    response.getWriter().println(result);
                    break;

                default:
                    message = "Invalid action.";
            }

        } catch (Exception e) {
            message = "Error: " + e.getMessage();
        }

        // Send result back to UI
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body style='font-family: Arial;'>");
        out.println("<h3>Result:</h3>");
        out.println("<p>" + message + "</p>");
        out.println("<a href='index.html'>Back to Dashboard</a>");
        out.println("</body></html>");
    }
}
