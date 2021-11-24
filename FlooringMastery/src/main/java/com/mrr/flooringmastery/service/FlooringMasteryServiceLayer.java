/*
 * Author: mroge
 * Purpose: interface to be implemented by the FlooringMasteryAuditDaoImpl class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of AuditDAO in the application.
 *         
 * The FlooringMasteryServiceLayer only uses this interface to reference the DAO — it 
 * is completely unaware of the implementation details.
 */
package com.mrr.flooringmastery.service;

import com.mrr.flooringmastery.dao.DataPersistenceException;
import com.mrr.flooringmastery.model.Order;
import java.time.LocalDate;
import java.util.List;

// interface declaration
public interface FlooringMasteryServiceLayer {
    
    /**
     * gets all of the orders with specified date
     * 
     * @param userDateChoice date choice made by user
     * @return A list or order objects
     * @throws InvalidOrderNumberException
     * @throws DataPersistenceException
     */
    List<Order> getOrders(LocalDate userDateChoice) throws 
            InvalidOrderNumberException,
            DataPersistenceException;

    /**
     * calculates the calculable variables in a Order
     * 
     * @param order order to have values calculated
     * @return Order once calculations are done
     * @throws DataPersistenceException
     * @throws OrderValidationException
     * @throws com.mrr.flooringmastery.service.TaxValidationException
     * @throws com.mrr.flooringmastery.service.ProductValidationException
     */
    Order calculateOrder(Order order) throws 
            DataPersistenceException, 
            OrderValidationException,
            TaxValidationException,
            ProductValidationException;

    /**
     * gets a order given the date time and order number
     * 
     * @param userDateChoice date choice made by user
     * @param orderNumber order number to be searched for
     * @return order once found
     * @throws DataPersistenceException
     * @throws InvalidOrderNumberException
     */
    Order getOrder(LocalDate userDateChoice, int orderNumber) throws
            DataPersistenceException, 
            InvalidOrderNumberException;

    /**
     * adds a order to the order file
     * 
     * @param order the order to be added to the file
     * @return order that was added to the file
     * @throws DataPersistenceException
     */
    Order addOrder(Order order) throws DataPersistenceException;

    /**
     * updates the saved order to the edited orders fields
     * 
     * @param savedOrder the saved order or order a
     * @param editedOrder the edited order or order b
     * @return the newly saved order
     * @throws DataPersistenceException
     * @throws com.mrr.flooringmastery.service.TaxValidationException
     * @throws com.mrr.flooringmastery.service.ProductValidationException
     */
    Order compareOrders(Order savedOrder, Order editedOrder)
            throws DataPersistenceException,
            TaxValidationException,
            ProductValidationException;

    /**
     * edits input order
     *  
     * @param updatedOrder order to be edited
     * @return edited order
     * @throws DataPersistenceException
     * @throws InvalidOrderNumberException
     */
    Order editOrder(Order updatedOrder) throws 
            DataPersistenceException,
            InvalidOrderNumberException;

    /**
     * removed input order
     * 
     * @param removedOrder order to be removed
     * @return removed order
     * @throws DataPersistenceException
     * @throws InvalidOrderNumberException
     */
    Order removeOrder(Order removedOrder) throws 
            DataPersistenceException,
            InvalidOrderNumberException;
    
}
