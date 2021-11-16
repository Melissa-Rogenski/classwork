/*
 * Author: mroge
 * Purpose: The orchestrator of the application. It knows what needs to be done, when it 
 *          needs to be done, and what component can do the job.
 */
package com.mrr.vendingmachine.controller;

import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.CoinValue;
import com.mrr.vendingmachine.dto.Product;
import com.mrr.vendingmachine.service.VendingMachineDataValidationException;
import com.mrr.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.mrr.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.mrr.vendingmachine.service.VendingMachinePersistenceException;
import com.mrr.vendingmachine.service.VendingMachineServiceLayer;
import com.mrr.vendingmachine.ui.UserIO;
import com.mrr.vendingmachine.ui.UserIOConsoleImpl;
import com.mrr.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class VendingMachineController {
    
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() {
        BigDecimal moneyDeposited = new BigDecimal("0");
        Product chosenProduct = null;
        boolean isEnoughMoney = false;
        try {
            displayHeader();
            do {
                productMenu();
                moneyDeposited = userMoneyInput(moneyDeposited);
                chosenProduct = getChosenProduct();
                isEnoughMoney = didUserPutSufficientAmountOfMoney(moneyDeposited, chosenProduct);
                if(toExitVendingMachine(isEnoughMoney)) {
                    return;
                }
            } while(!isEnoughMoney);
            
            displayUserMoneyInput(moneyDeposited);
            displayChangeReturnedToUser(moneyDeposited, chosenProduct);
            updateSoldProduct(chosenProduct);
            saveProductList();
        } catch(VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        } finally {
            displayFinalMessage();
        }
    }
    
    void displayHeader() {
        view.displayVendingMachineWelcome();
    }
    
    void productMenu() throws VendingMachinePersistenceException {
        try {
            view.displayProductHeader();
            for(Product p : service.loadProductsInStock().values()) {
                view.displayProduct(p);
            }
        } catch(VendingMachineNoItemInventoryException | VendingMachinePersistenceException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
    }
    
    BigDecimal userMoneyInput(BigDecimal amount) {
        return amount.add(view.promptUserMoneyInput());
    }
    
    Product getChosenProduct() {
        while(true) {
            String productId = view.promptUserProductChoice();
            try {
                Product product = service.getChosenProduct(productId);
                view.displayUserChoiceOfProduct(product);
                return product;
            } catch(VendingMachineNoItemInventoryException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
    }
    
    boolean didUserPutSufficientAmountOfMoney(BigDecimal userAmount, Product product) {
        try {
            service.checkSufficientMoneyToBuyProduct(userAmount, product);
            return true;
        } catch(VendingMachineInsufficientFundsException e) {
            view.displayErrorMessage(e.getMessage());
            displayUserMoneyInput(userAmount);
            return false;
        }
    }
    
    void displayUserMoneyInput(BigDecimal amount) {
        view.displayUserMoneyInput(amount);
    }
    
    void displayChangeReturnedToUser(BigDecimal amount, Product product) {
        Change change = service.calculateChange(amount, product);
        view.displayChangeDue(change);
    }

    boolean toExitVendingMachine(boolean isEnoughMoney) {
        if(isEnoughMoney) {
            return false;
        } else {
            return view.toExit();
        }
    }
    
    void updateSoldProduct(Product product) throws VendingMachinePersistenceException {
        try {
            service.updateProductSale(product);
        } catch(VendingMachineNoItemInventoryException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
    }
    
    void saveProductList() throws VendingMachinePersistenceException {
        service.saveProductList();
    }
    
    void displayFinalMessage() {
        view.displayFinalMessage();
    }
    
}