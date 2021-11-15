
package com.mrr.vendingmachine.ui;

import com.mrr.vendingmachine.dto.Product;
import java.util.List;

public class VendingMachineView {
    
    private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        // to be replaced with a system that reads the current products from a file
        io.print("Main Menu");
        io.print("1. Coke 250");
        io.print("2. Sprite 200");
        io.print("3. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 3);
    }
    
    public void displayPurchaseSuccessBanner() {
        io.readString("Purchase Successful. Please hit enter to continue.");
    }
    
    public void displayProductList(List<Product> productList) {
        int i = 1;
        for (Product currentProduct : productList) {
            String productInfo = String.format("%d. Name: %-10s Cost: %-4s Avaliable: %s",
                    i,
                    currentProduct.getName(),
                    currentProduct.getCost(),
                    currentProduct.getInventory());
            io.print(productInfo);
            i++;
        }
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
}
