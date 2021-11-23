/*
 * Author: mroge
 * Purpose: interface to be implemented by the FlooringMAsteryDaoImpl class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of DAO in the application.
 *         
 * The FlooringMasteryServiceLayerImpl only uses this interface to reference the DAO — it 
 * is completely unaware of the implementation details.
 */
package com.mrr.flooringmastery.dao;

import com.mrr.flooringmastery.model.Order;
import com.mrr.flooringmastery.model.Product;
import com.mrr.flooringmastery.model.Tax;
import java.time.LocalDate;
import java.util.List;

// interface declaration
public interface FlooringMasteryDao {
    /**
     * Adds the given order to the list
     *
     * @param order order to be added to the list of orders
     * @return Order the Order object that was added
     * @throws com.mrr.flooringmastery.dao.DataPersistenceException
     */
    Order addOrder(Order order) throws DataPersistenceException;
    
    /**
     * Returns a List of orders on given date
     *
     * @param userDateChoice choice of date by user
     * @return List containing all orders on the given date
     * @throws com.mrr.flooringmastery.dao.DataPersistenceException
     */
    List<Order> getOrders(LocalDate userDateChoice) throws DataPersistenceException;
    
    /**
     * Returns the edited order
     * 
     * @param order
     * @return Order the edited order
     * @throws DataPersistenceException
     */
    Order editOrder(Order order) throws DataPersistenceException;
    
    /**
     * deletes the given order from the list
     * 
     * @param order order to be removed from the list of orders
     * @return Order order object that was deleted
     * @throws DataPersistenceException
     */
    Order removeOrder(Order order) throws DataPersistenceException;
    
    /**
     * returns the product with the given productType
     * 
     * @param productType type of product to search for
     * @return Product the product with the given type
     * @throws DataPersistenceException
     */
    Product getProduct(String productType) throws DataPersistenceException;
    
    /**
     * returns the Tax object with the given state abbreviation
     * 
     * @param stateAbbr state abbreviation 
     * @return Tax Tax Object with the given state abbreviation
     * @throws DataPersistenceException
     */
    Tax getTax(String stateAbbr) throws DataPersistenceException;
    
}
