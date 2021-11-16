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
    
    // public function to print the menu and return the selection in int format
    //public int printMenuAndGetSelection() {
     //   io.print("Main Menu");
     //   io.print("1. Enter Coins");
     //   io.print("2. Exit");

        // getting user input
     //   return io.readInt("Please select from the above choices.", 1, 2);
   // }
    
    // public function that prints a banner to the console
    public void displayProductsBanner() {
        io.print(" *********************************************");
        io.print("     WELCOME TO THE VENDING MACHINE           ");
        io.print(" *********************************************");
        io.print("            Products available:               ");
        io.print("                                              ");
    }
    
    // public function that prints a banner to the console
    //public void displayPurchaseSuccessBanner() {
    //    io.print("Purchase Successful.");
    //}
    
    // public function that displays all of the products in the machine, takes parameter List of products
    public void displayProductList(List<Product> productList) {
        // for each loop to loop through all products
        for (Product currentProduct : productList) {
            // formatting information into a string
            String productInfo = String.format("%s. Name: %-10s Cost: %-4s Avaliable: %s",
                    currentProduct.getProductId(),
                    currentProduct.getProductName(),
                    currentProduct.getPrice(),
                    currentProduct.getItemsInStock());
            // passing string to function to print out the info
            io.print(productInfo);
        }
    }
    
    // public function that prints a banner to the console
    public void displayExitBanner() {
        io.print("Good Bye!!!");
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
        io.print("DisplayFinalMessage");
    }

    public boolean toExit() {
        //placeholder
        return true;
    }

    public void displayChangeDue(Change change) {
        io.print("DisplayChangeDue");
    }

    public void displayUserMoneyInput(BigDecimal amount) {
        io.print("DisplayUserMoneyInput");
    }

    public void displayUserChoiceOfProduct(Product product) {
        io.print("displayUserChoiceOfProduct");
    }

    public String promptUserProductChoice() {
        return io.readString("Please enter selection.");    
    }

    public BigDecimal promptUserMoneyInput() {
        io.print("Please put in money:");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayProductHeader() {
        io.print("No     Product          Price");
        io.print("-----------------------------");
    }

    public void displayVendingMachineWelcome() {
        io.print(" *********************************************");
        io.print("     WELCOME TO THE VENDING MACHINE           ");
        io.print(" *********************************************");
    }

    public void displayProduct(Product p) {
        String productInfo = String.format("%-8s%-15s%s",
                    p.getProductId(),
                    p.getProductName(),
                    p.getPrice());
            // passing string to function to print out the info
            io.print(productInfo);
    }
    
}
