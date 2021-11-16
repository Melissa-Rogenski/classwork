/*
 * Author: mroge
 * Purpose: The orchestrator of the application. It knows what needs to be done, when it 
 *          needs to be done, and what component can do the job.
 */
package com.mrr.vendingmachine.controller;

import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.Product;
import com.mrr.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.mrr.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.mrr.vendingmachine.service.VendingMachinePersistenceException;
import com.mrr.vendingmachine.service.VendingMachineServiceLayer;
import com.mrr.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;


public class VendingMachineController {
    
    // private variable of type VendingMachineView
    private VendingMachineView view;
    // private variable of type VendingMachineServiceLayer
    private VendingMachineServiceLayer service;
    
    // public constructor for VendingMachineController takes parameters VendingMachineView and VendingMachineServiceLayer
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        // assigning value to view
        this.view = view;
        // assigning value to service
        this.service = service;
    }
    
    // public function with return type void to call functionss to run the program
    public void run() {
        // BigDecimal variable to hold the money entered by the user
        BigDecimal moneyDeposited = new BigDecimal("0");
        // Product variable to hold the chosen product from the users input
        Product chosenProduct = null;
        
        //boolena variable to state whither or not there is enough money in the machine to vend the product
        boolean isEnoughMoney = false;
        //try catch block
        try {
            // call to display header function
            displayHeader();
            // do while loop
            do {
                // call to the product menu function
                productMenu();
                // grabbing input from user for money
                moneyDeposited = userMoneyInput(moneyDeposited);
                // grabbing input from user for product
                chosenProduct = getChosenProduct();
                // checking if the user entered enough money to buy the selected product
                isEnoughMoney = didUserPutSufficientAmountOfMoney(moneyDeposited, chosenProduct);
                //if the money is enough exit do while loop
                if(toExitVendingMachine(isEnoughMoney)) {
                    return;
                }
                // keep looping if entered money is not enough for selected product
            } while(!isEnoughMoney);
            // end of do while loop
            
            // call to display user money input function
            displayUserMoneyInput(moneyDeposited);
            // call to display change returned to user function
            displayChangeReturnedToUser(moneyDeposited, chosenProduct);
            // call to update sold product function
            updateSoldProduct(chosenProduct);
            // call to saveProductList function
            saveProductList();
            //catch block
        } catch(VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        } finally { //finally block
            // call to display final message function
            displayFinalMessage();
        }
    }
    
    // void function to display the header
    void displayHeader() {
        // call to the displayVending machine welcome function
        view.displayVendingMachineWelcome();
    }
    
    // void function to print product menu
    void productMenu() throws VendingMachinePersistenceException {
        // try catch block
        try {
            // call to display product header
            view.displayProductHeader();
            // foreach loop
            for(Product p : service.loadProductsInStock().values()) {
                // call to display product function
                view.displayProduct(p);
            }
            //catch block
        } catch(VendingMachineNoItemInventoryException | VendingMachinePersistenceException e) {
            // throw exception
            throw new VendingMachinePersistenceException(e.getMessage());
        }
    }
    
    // BigDecimal function to retreive user input money
    BigDecimal userMoneyInput(BigDecimal amount) {
        // call to prompt user for money iunput function
        return amount.add(view.promptUserMoneyInput());
    }
    
    // Product function to retrieve user input Product
    Product getChosenProduct() {
        // while loop
        while(true) {
            // String variable to hold the users product choice
            String productId = view.promptUserProductChoice();
            // try catch block
            try {
                // Product variable to store the chosen product
                Product product = service.getChosenProduct(productId);
                // call to display user choice of product function
                view.displayUserChoiceOfProduct(product);
                // returning product
                return product;
                //catch block
            } catch(VendingMachineNoItemInventoryException e) {
                // call to display ErrorMessage function
                view.displayErrorMessage(e.getMessage());
            }
        }
    }
    
    // boolena function to determine if the user put enough money in the machine to vend thier selected product
    boolean didUserPutSufficientAmountOfMoney(BigDecimal userAmount, Product product) {
        // try catch block
        try {
            // call to check sufficientmoneyto buy product function
            service.checkSufficientMoneyToBuyProduct(userAmount, product);
            // return boolean
            return true;
            // catch block
        } catch(VendingMachineInsufficientFundsException e) {
            // call to display error message function
            view.displayErrorMessage(e.getMessage());
            // call to displau user money input function
            displayUserMoneyInput(userAmount);
            //return boolean
            return false;
        }
    }
    
    // void function to display the users money input
    void displayUserMoneyInput(BigDecimal amount) {
        // call to dsiplay user money input function
        view.displayUserMoneyInput(amount);
    }
    
    // void function to display the change to be returned to the user
    void displayChangeReturnedToUser(BigDecimal amount, Product product) {
        // Change variable to store the change to be returned to the user
        Change change = service.calculateChange(amount, product);
        // call to display change due function
        view.displayChangeDue(change);
    }

    // boolean function to exit the vending machine
    boolean toExitVendingMachine(boolean isEnoughMoney) {
        // if statement
        if(isEnoughMoney) {
            return false;
            //else statement
        } else { 
            // call to toexit function
            return view.toExit();
        }
    }
    
    // void function to update sold product
    void updateSoldProduct(Product product) throws VendingMachinePersistenceException {
        // try catch block
        try {
            // call to update sold product function
            service.updateProductSale(product);
            //catch block
        } catch(VendingMachineNoItemInventoryException e) {
            // exception throw
            throw new VendingMachinePersistenceException(e.getMessage());
        }
    }
    
    // void function to save product list
    void saveProductList() throws VendingMachinePersistenceException {
        // call to saveproduct list
        service.saveProductList();
    }
    
    // void function to display the final message before the program closes
    void displayFinalMessage() {
        // call to display final message function
        view.displayFinalMessage();
    }
    
}