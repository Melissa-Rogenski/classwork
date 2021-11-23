/*
 * Author: mroge
 * Purpose: the class that handles all UI logic
 */
package com.mrr.flooringmastery.view;

// defining what a FlooringMasteryView is

import com.mrr.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryView {
    
// private variable of type UserIO
    private UserIO io;
    
    // constructor
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    // public function to print the menu and return the selection in int format
    public int printMenuAndGetSelection() {
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("   * <<Flooring Program>>");
        io.print("   * 1. Display Orders");
        io.print("   * 2. Add an Order");
        io.print("   * 3. Edit an Order");
        io.print("   * 4. Remove an Order");
        io.print("   * 5. Quit");
        io.print("   *");
        io.print("   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        // getting user input
        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    // public function that prints a banner to the console
    public void displayCreateOrderBanner() {
        io.print("=== Create Order ===");
    }
    
    // public function that prints a banner to the console
    public void displayCreateSuccessBanner() {
        io.readString("Order successfully created.  Please hit enter to continue.");
    }
    
    public void displayDisplayOrdersBanner() {
        io.print("=== Display Orders ===");
    }
    
    public void displayOrders(List<Order> orderList) {
        io.print("Order Number |  Customer Name  |  State  | Product Type |  Area  |"
                + " Tax Rate | Cost Per Square Foot | Labor Cost Per Square Foot"
                + " | Material Cost | Labor Cost |  Tax  |  Total  ");
        io.print("=================================================================="
                + "============================================================="
                + "================================================");
        for (Order currentOrder : orderList) {
            displayOrder(currentOrder);
        }
    }
    
    public void displayRemoveOrderBanner () {
        io.print("=== Remove Order ===");
    }
    
    public String askRemove() {
        return io.readString("Would you like to remove this order? Y/N", 1);
    }
    
    public void displayRemoveOrderSuccess(boolean success, Order order) {
        if (success == true) {
            io.print("Order #" + order.getOrderNumber() + " was successfully removed!");
        } else {
            io.print("Order was not removed.");
            displayContinue();
        }
    }
    
    public void displayEditOrderBanner() {
        io.print("=== Edit Order ===");
    }
    
    public Order editOrderInfo(Order order) {
        Order editedOrder = new Order();

        io.print("Customer: " + order.getCustomerName());
        editedOrder.setCustomerName(getCustomerNameInput());

        io.print("State: " + order.getState());
        editedOrder.setState(getStateInput());

        io.print("Product: " + order.getProductType());
        editedOrder.setProductType(getProductTypeInput());

        io.print("Area: " + order.getArea() + " sq ft");
        editedOrder.setArea(inputArea());

        return editedOrder;
    }

    public void displayEditOrderSuccess(boolean success, Order order) {
        if (success == true) {
            io.print("Order #" + order.getOrderNumber() + " was successfully edited!");
        } else {
            io.print("Order was not edited.");
            displayContinue();
        }
    }
    
    public void displayAddOrderSuccess(boolean success, Order order) {
        if (success == true) {
            io.print("Order #" + order.getOrderNumber() + " was successfully added!");
        } else {
            io.print("Order was not saved.");
            displayContinue();
        }
    }
    
    public Order getOrder() {
        Order order = new Order();
        order.setDate(getDateInput());
        order.setCustomerName(getCustomerNameInput());
        order.setState(getStateInput());
        order.setProductType(getProductTypeInput());
        order.setArea(inputArea());
        return order;
    }
    
    public void displayOrder(Order order) {
        String orderInfo = String.format("%-13s|%-17s|%-9s|%-14s|%-8s|"
            + "%-10s|%-22s|%-28s|%-15s|%-12s|%-7s|%-8s",
            order.getOrderNumber(),
            order.getCustomerName(),
            order.getState(),
            order.getProductType(),
            order.getArea(),
            order.getTaxRate(),
            order.getCostPerSquareFoot(),
            order.getLaborCostPerSquareFoot(),
            order.getMaterialCost(),
            order.getLaborCost(),
            order.getTax(),
            order.getTotal());
        io.print(orderInfo);
    }
    
    
    
    public String askSave() {
        return io.readString("Would you like to save this order? Y/N", 1);
    }
    
    public void displayDateBanner(LocalDate userDateChoice) {
        System.out.printf("=== Orders on %s ===\n", userDateChoice);
    }
    
    // public function that displays a final message to the console
    public void displayExitMessage() {
        io.print("Thank You, Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    // public function that prints a banner to the console
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        displayContinue();
    }
    
    // public function that returns true
    public boolean toExit() {
        return true;
    }

    public int getOrderNumberInput() {
        return io.readInt("Please enter an order number.");
    }
    
    public LocalDate getDateInput() {
        return io.readDate("Please enter a date. (MM-DD-YYYY)",
                LocalDate.of(2006, 1, 1), LocalDate.of(2055, 1, 31));
    }
    
    public String getCustomerNameInput() {
        return io.readString("Please enter your name.");
    }

    public String getStateInput() {
        return io.readString("Please enter your state's USPS abbreviation. "
                + "(Ex. MI)", 2);
    }

    public String getProductTypeInput() {
        return io.readString("Please enter the product type you would like to use.", 15);
    }

    public BigDecimal inputArea() {
        return io.readBigDecimal("Please enter the area of your project "
                + "in square feet.", 2, BigDecimal.ZERO);
    }
    
    public void displayDateOrders(List<Order> ordersByDate) {
        for (Order order : ordersByDate) {
            io.print(order.getOrderNumber() + " | " + order.getCustomerName() + " | "
                    + io.formatCurrency(order.getTotal()));
        }
    }
    
    public void displayContinue() {
        io.readString("Please hit enter to continue.");
    }
    
}
