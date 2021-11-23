/*
 * Author: mroge
 * Purpose: The orchestrator of the application. It knows what needs to be done, when it 
 *          needs to be done, and what component can do the job.
 */
package com.mrr.flooringmastery.controller;

import com.mrr.flooringmastery.model.Order;
import com.mrr.flooringmastery.service.OrderValidationException;
import com.mrr.flooringmastery.service.InvalidOrderNumberException;
import com.mrr.flooringmastery.dao.DataPersistenceException;
import com.mrr.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mrr.flooringmastery.view.FlooringMasteryView;
import java.time.LocalDate;

public class FlooringMasteryController {
    // private variable of type FlooringMasteryView
    private FlooringMasteryView view;
    // private variable of type FlooringMasteryServiceLayer
    private FlooringMasteryServiceLayer service;
    
    // public constructor for FlooringMasteryController takes parameters FlooringMasteryView and FlooringMasteryServiceLayer
    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        // assigning value to view
        this.view = view;
        // assigning value to service
        this.service = service;
    }

// public function with return type void to call functionss to run the program
    public void run() throws OrderValidationException {
        // boolean variable to determine if the loop stops or not
        boolean keepGoing = true;
        // int variable to determine the users selection in the menu
        int menuSelection = 0;
        // try catch block
        try {
            // while loop to allow the user to use the menu as many time as they want
            while (keepGoing) {
            
                // function call to getMenuSelection, returns int value and stores it in menuSelection
                menuSelection = getMenuSelection();

                //switch statement to determine what methods are called based on user selection
                switch (menuSelection) {
                    // case 1 to make a call to the listOrdersByDate function
                    case 1:
                        getOrdersByDate();
                        break;
                    // case 2 to make a call to the addOrder function
                    case 2:
                        addOrder();
                        break;
                    // case 3 to make a call to the editOrder function
                    case 3:
                        editOrder();
                        break;
                    // case 4 to make a call to the removeOrder function
                    case 4:
                        removeOrder();
                        break;
                    // case 5 to end the loop
                    case 5:
                        keepGoing = false;
                        break;
                    // default case to make a call to the unknownCommand function
                    default:
                        unknownCommand();
                }

            }
            // end of while loop
            
            // calling exitMessage function
            exitMessage();
        } catch (DataPersistenceException e) {
            // call to displayErrorMessage function
            view.displayErrorMessage(e.getMessage());
        }
        // end of try catch block
    }
    // end of function run
    
    // private function that returns type int
    private int getMenuSelection() {
        // a call to the printMenuAndGetSelection function to print the menu
        // and get the users selection
        return view.printMenuAndGetSelection();
    }
    
    // private void function to print product menu
    private void getOrdersByDate() throws DataPersistenceException {
        // creating local date variable and calling getDateInput function
        LocalDate dateChoice = view.getDateInput();
        
        // calling displayDateBanner function
        view.displayDateBanner(dateChoice);
        
        // try catch block
        try {
            // call to display orders
            view.displayOrders(service.getOrders(dateChoice));
        //catch block
        } catch(InvalidOrderNumberException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    // private void function to add a order
    private void addOrder() throws DataPersistenceException {
        try {
            Order order = service.calculateOrder(view.getOrder());
            view.displayOrder(order);
            String response = view.askSave();
            if (response.equalsIgnoreCase("Y")) {
                service.addOrder(order);
                view.displayAddOrderSuccess(true, order);
            } else if(response.equalsIgnoreCase("N")) {
                view.displayAddOrderSuccess(false, order);
            } else {
                unknownCommand();
            }
        } catch (OrderValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void editOrder() throws DataPersistenceException, OrderValidationException {
        view.displayEditOrderBanner();
        try {
            LocalDate dateChoice = view.getDateInput();
            int orderNumber = view.getOrderNumberInput();
            Order savedOrder = service.getOrder(dateChoice, orderNumber);
            Order editedOrder = view.editOrderInfo(savedOrder);
            Order updatedOrder = service.compareOrders(savedOrder, editedOrder);
            view.displayEditOrderBanner();
            view.displayOrder(updatedOrder);
            String response = view.askSave();
            if(response.equalsIgnoreCase("Y")) {
                service.editOrder(updatedOrder);
                view.displayEditOrderSuccess(true, updatedOrder);
            } else if(response.equalsIgnoreCase("N")) {
                view.displayEditOrderSuccess(false, updatedOrder);
            } else {
                unknownCommand();
            }
        } catch (InvalidOrderNumberException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void removeOrder() throws DataPersistenceException {
        view.displayRemoveOrderBanner();
        LocalDate dateChoice = view.getDateInput();
        view.displayDateBanner(dateChoice);
        try {
            view.displayDateOrders(service.getOrders(dateChoice));
            int orderNumber = view.getOrderNumberInput();
            Order order = service.getOrder(dateChoice, orderNumber);
            view.displayRemoveOrderBanner();
            view.displayOrder(order);
            String response = view.askRemove();
            if(response.equalsIgnoreCase("Y")) {
                service.removeOrder(order);
                view.displayRemoveOrderSuccess(true, order);
            } else if(response.equalsIgnoreCase("N")) {
                view.displayRemoveOrderSuccess(false, order);
            } else {
                unknownCommand();
            }
        } catch (InvalidOrderNumberException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    // private method that returns type void
    private void unknownCommand() {
        // call to displayUnknownCommandBanner function
        view.displayUnknownCommandBanner();
    }
    
    // private method that returns type void
    private void exitMessage() {
        // call to displayExitBanner function
        view.displayExitMessage();
    }

}
