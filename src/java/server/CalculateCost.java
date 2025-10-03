/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import shared.Task;


public class CalculateCost implements Task {
    
    //private String fruitname;
    private double totalCost;
    private double amountGiven;
    private String cashier;

    public CalculateCost(double totalCost, double amountGiven, String cashier) {
        //this.fruitname = fruitname;
        this.totalCost = totalCost;
        this.amountGiven = amountGiven;
        this.cashier = cashier;
    }
    
    public String execute() {
        if (amountGiven < totalCost) {
            return "Insufficient payment. Total cost is Ksh" + totalCost;
        }
    
        double change = amountGiven - totalCost;

        return "Receipt\n" +
               "--------\n" +
               "Total Fruit Cost: Ksh" + String.format(" %.2f", totalCost) + "\n" +
               "Amount Given: Ksh" + String.format(" %.2f", amountGiven) + "\n" +
               "Balance in: Ksh" + String.format(" %.2f", change) + "\n" +
                "--------\n\n" +
               "You were served by: " + cashier + "\n\n" +
               "Thank you!";
    }
    
}
