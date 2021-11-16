/*
 * Author: mroge
 * Purpose: the class that handles all UI logic
 */
package com.mrr.vendingmachine.ui;

import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;

// defining what a VendingMachineView is
public class VendingMachineView {
    
    // private variable of type UserIO
    private UserIO io;
    
    // constructor
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    // public function that prints a banner to the console
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    // public function that prints a banner to the console
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayFinalMessage() {
        io.print("Thank you for using the Vending Machine!");
    }

    public boolean toExit() {
        //placeholder
        return true;
    }

    public void displayChangeDue(Change change) {
        io.print("Change Due:");
        io.print("Quarters: " + change.getQuarters());
        io.print("Dimes: " + change.getDimes());
        io.print("Nickels: " + change.getNickels());
        io.print("Pennies: " + change.getPennies());
    }

    public void displayUserMoneyInput(BigDecimal amount) {
        io.print("User input: " + amount);
        //io.print("DisplayUserMoneyInput");
    }

    public void displayUserChoiceOfProduct(Product product) {
        io.print("Product Choice is: ");
        displayProduct(product);
    }

    public String promptUserProductChoice() {
        return io.readString("Please enter selection.");    
    }

    public BigDecimal promptUserMoneyInput() {
        String str = io.readString("Please put in money:");
        BigDecimal bd = new BigDecimal(str);
        return bd;
    }

    public void displayProductHeader() {
        io.print("No     Product          Price");
        io.print("-----------------------------");
    }

    public void displayVendingMachineWelcome() {
        io.print(" *********************************************");
        io.print("     WELCOME TO THE VENDING MACHINE           ");
        io.print(" *********************************************");
        io.print("                                              ");
    }

    public void displayProduct(Product p) {
        String productInfo = String.format("%-7s%-17s%s",
                    p.getProductId(),
                    p.getProductName(),
                    p.getPrice());
            // passing string to function to print out the info
            io.print(productInfo);
    }
    
}
