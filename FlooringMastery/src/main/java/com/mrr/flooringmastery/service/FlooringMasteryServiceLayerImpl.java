/*
 * Author: mroge
 * Purpose: This file implements the VendingMachineServiceLayer interface
 */
package com.mrr.flooringmastery.service;

import com.mrr.flooringmastery.dao.DataPersistenceException;
import com.mrr.flooringmastery.dao.FlooringMasteryAuditDao;
import com.mrr.flooringmastery.dao.FlooringMasteryDao;
import com.mrr.flooringmastery.model.Order;
import com.mrr.flooringmastery.model.Product;
import com.mrr.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

//implements keyword indicates this class implements the specified interface
public class FlooringMasteryServiceLayerImpl implements 
        FlooringMasteryServiceLayer {
    
    // creating private variables as well as FlooringMasteryDao
    FlooringMasteryDao dao;
    private FlooringMasteryAuditDao auditDao;
    
    // constructor 
    // params VendingMachineDao, VendingMachineAuditDao
    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, 
            FlooringMasteryAuditDao auditDao) {
        // assigning values to variables
        this.dao = dao;
        this.auditDao = auditDao;
    }

    // function to calculate tax information
    private void calculateTax(Order order) throws 
            DataPersistenceException,
            OrderValidationException {
        //get tax info from order
        Tax chosenTax = dao.getTax(order.getState());
        if(chosenTax == null) {
            throw new OrderValidationException("ERROR: TSG Corp "
                    + "does not serve that state.");
        }
        order.setState(chosenTax.getStateAbbreviation());
        order.setTaxRate(chosenTax.getTaxRate());  
    }

    // function to calculate material information
    private void calculateMaterial(Order order) throws 
            DataPersistenceException,
            OrderValidationException {
        //Set product information in order.
        Product chosenProduct = dao.getProduct(order.getProductType());
        if(chosenProduct == null) {
            throw new OrderValidationException("ERROR: TSG Corp "
                    + "does not sell that product.");
        }
        order.setProductType(chosenProduct.getProductType());
        order.setCostPerSquareFoot(chosenProduct.
                getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(chosenProduct.
                getLaborCostPerSquareFoot());
    }

    // function to calculate total cost information
    private void calculateTotal(Order order) {
        //Calculate other order fields.
        order.setMaterialCost(order.getCostPerSquareFoot().multiply
        (order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost(order.getLaborCostPerSquareFoot().multiply
        (order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setTax(order.getTaxRate().divide(new BigDecimal("100.00")).
                multiply((order.getMaterialCost().add(order.getLaborCost()))).
                setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getMaterialCost().add(order.getLaborCost()).
                add(order.getTax()));
    }

    // function to validate the info in an order
    private void validateOrder(Order order) throws OrderValidationException {
        String message = "";
        if(order.getCustomerName().trim().isEmpty() || 
                order.getCustomerName() == null) {
            message += "Customer name is a required field.\n";
        }
        if(order.getState().trim().isEmpty() || order.getState() == null) {
            message += "State is a required field.\n";
        }
        if(order.getProductType().trim().isEmpty() || 
                order.getProductType() == null) {
            message += "Product type is a required field.\n";
        }
        if(order.getArea().compareTo(BigDecimal.ZERO) == 0 || 
                order.getArea() == null) {
            message += "Area  in square footage is a required field.";
        }
        if(!message.isEmpty()) {
            throw new OrderValidationException(message);
        }
    }

    
    // see javadoc
    @Override
    public List<Order> getOrders(LocalDate userDateChoice) throws 
            InvalidOrderNumberException, 
            DataPersistenceException {
        List<Order> ordersByDate = dao.getOrders(userDateChoice);
        if(!ordersByDate.isEmpty()) {
            return ordersByDate;
        }else {
            throw new InvalidOrderNumberException("ERROR: No "
                    + "orders exist on that date.");
        }
    }

    // see javadoc
    @Override
    public Order calculateOrder(Order order) throws 
            DataPersistenceException, 
            OrderValidationException {
        
        validateOrder(order);
        calculateTax(order);
        calculateMaterial(order);
        calculateTotal(order);

        return order;
    }

    // see javadoc
    @Override
    public Order getOrder(LocalDate userDateChoice, int orderNumber) throws 
            DataPersistenceException, 
            InvalidOrderNumberException {
        List<Order> orders = getOrders(userDateChoice);
        Order chosenOrder = orders.stream().filter(o -> o.getOrderNumber() == 
                orderNumber).findFirst().orElse(null);
        if(chosenOrder != null) {
            return chosenOrder;
        }else {
            throw new InvalidOrderNumberException("ERROR: No "
                    + "orders with that number exist on that date.");
        }
    }

    // see javadoc
    @Override
    public Order addOrder(Order order) throws 
            DataPersistenceException {
        dao.addOrder(order);
        auditDao.writeAuditEntry("Order #" + order.getOrderNumber() + 
                " for date " + order.getDate() + " ADDED.");
        return order;
    }

    // see javadoc
    @Override
    public Order compareOrders(Order savedOrder, Order editedOrder) throws 
            DataPersistenceException, 
            OrderValidationException {
        if(editedOrder.getCustomerName() == null || 
                editedOrder.getCustomerName().trim().equals("")) {
        }else {
            savedOrder.setCustomerName(editedOrder.getCustomerName());
        }

        if(editedOrder.getState() == null || 
                editedOrder.getState().trim().equals("")) {
        }else {
            savedOrder.setState(editedOrder.getState());
            calculateTax(savedOrder);
        }

        if(editedOrder.getProductType() == null
                || editedOrder.getProductType().equals("")) {
        }else {
            savedOrder.setProductType(editedOrder.getProductType());
            calculateMaterial(savedOrder);
        }

        if(editedOrder.getArea() == null || 
                (editedOrder.getArea().compareTo(BigDecimal.ZERO)) == 0) {
        }else {
            savedOrder.setArea(editedOrder.getArea());
        }

        calculateTotal(savedOrder);

        return savedOrder;
    }

    // see javadoc
    @Override
    public Order editOrder(Order updatedOrder) throws 
            DataPersistenceException, 
            InvalidOrderNumberException {
        updatedOrder = dao.editOrder(updatedOrder);
        if(updatedOrder != null) {
            auditDao.writeAuditEntry("Order #"
                    + updatedOrder.getOrderNumber() + " for date "
                    + updatedOrder.getDate() + " EDITED.");
            return updatedOrder;
        }else {
            throw new InvalidOrderNumberException("ERROR: No "
                    + "orders with that number exist on that date.");
        }
    }

    // see javadoc
    @Override
    public Order removeOrder(Order removedOrder) throws 
            DataPersistenceException, 
            InvalidOrderNumberException {
       removedOrder = dao.removeOrder(removedOrder);
        if(removedOrder != null) {
            auditDao.writeAuditEntry("Order #" + removedOrder.getOrderNumber() 
                    + " for date " + removedOrder.getDate() + " REMOVED.");
            return removedOrder;
        }else {
            throw new InvalidOrderNumberException("ERROR: No "
                    + "orders with that number exist on that date.");
        }
    }  
}
