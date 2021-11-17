/*
 * Author: mroge
 * Purpose: the class that handles all UI logic
 */
package com.mrr.vendingmachine.ui;

import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    // public function that displays a final message to the console
    public void displayFinalMessage() {
        io.print("THANK YOU FOR USING THE VENDING MACHINE!");
    }

    // public function that returns true
    public boolean toExit() {
        return true;
    }

    // public function that displays the change due to the user
    public void displayChangeDue(Change change) {
        io.print("====================================");
        io.print("           Change Due");
        io.print("====================================");
        io.print("Quarters | Dimes | Nickels | Pennies");
        String temp = String.format("   %-10d%-9d%-10d%d", change.getQuarters(), change.getDimes(), change.getNickels(), change.getPennies());
        io.print(temp);
    }

    // public function that displays money input to console
    public void displayUserMoneyInput(BigDecimal amount) {
        io.print("You have deposited $" + amount + ".");
    }

    // public function that displays product choice to console
    public void displayUserChoiceOfProduct(Product product) {
        io.print("You have chosen " + product.getProductName() + ".");
    }

    // public function that prompts the user for their product choice and then returns the choice
    public String promptUserProductChoice() {
        return io.readString("Please choose the product you want to buy (Enter a No)");    
    }

    // public function that prompts the user for their money input and then returns the money input
    public BigDecimal promptUserMoneyInput() {
        String str = io.readString("Please put in money:");
        BigDecimal bd = new BigDecimal(str);
        return bd;
    }

    // public function that diaplays the product header to the console
    public void displayProductHeader() {
        io.print("No     Product          Price");
        io.print("-----------------------------");
    }

    // public function that displaysd the welcome message to the console
    public void displayVendingMachineWelcome() {
        io.print(" *********************************************");
        io.print("     WELCOME TO THE VENDING MACHINE           ");
        io.print(" *********************************************");
        io.print("                                              ");
    }  
    
    // public function that displays the given product
    public void displayProducts(List<Product> products) {
        // lambda to filter out the products that are out of stock
        products = products.stream()
                .filter((p) -> p.getItemsInStock() >= 1).collect(Collectors.toList());
        
        for(Product p : products) {
            String productInfo = String.format("%-7s%-17s%s",
                    p.getProductId(),
                    p.getProductName(),
                    p.getPrice());
            // passing string to function to print out the info
            io.print(productInfo);
        } 
    }
    
}
