/*
 * Author: mroge
 * Purpose: The orchestrator of the application. It knows what needs to be done, when it 
 *          needs to be done, and what component can do the job.
 */
package com.mrr.vendingmachine.controller;

import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.dto.Product;
import com.mrr.vendingmachine.service.VendingMachineDataValidationException;
import com.mrr.vendingmachine.ui.UserIO;
import com.mrr.vendingmachine.ui.UserIOConsoleImpl;
import com.mrr.vendingmachine.ui.VendingMachineView;
import java.util.List;

public class VendingMachineController {
    
    private VendingMachineView view;
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineDao dao;
    
    public VendingMachineController(VendingMachineDao dao, VendingMachineView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                listProducts();
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        io.print("COKE SELECTED");
                        break;
                    case 2:
                        io.print("SPRITE SELECTED");
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingMachineDataValidationException e) {
            view.displayErrorMessage(e.getMessage());
          }
        
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void listProducts() throws VendingMachineDataValidationException {
        List<Product> productList = dao.getAllProducts();
        view.displayProductList(productList);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
