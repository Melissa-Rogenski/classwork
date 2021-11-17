/*
 * Author: mroge
 * Purpose: interface to be implemented by the VendingMachineDaoImpl class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of DAO in the application.
 *         
 * The VendingMachineServiceLayerImpl only uses this interface to reference the DAO — it 
 * is completely unaware of the implementation details.
 */
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.dto.Product;
import com.mrr.vendingmachine.service.VendingMachinePersistenceException;
import java.util.List;
import java.util.Map;

// interface declaration
public interface VendingMachineDao {
    /**
     * Adds the given product to the machine and associates it with the given
     * id. returns that product object
     *
     * @param productId title with which product is to be associated
     * @param product product to be added to the machine
     * @return the Product object previously associated with the given  
     * id
     */
    Product addProduct(String productId, Product product);
    
    /**
     * Returns a List of all products in the machine.
     *
     * @return List containing all products in the machine.
     */
    List<Product> getAllProducts();
    
    /**
     * Returns a List of all product ids in the machine.
     *
     * @return List containing all product ids in the machine.
     */
    List<String> getAllProductIds();
    
    /**
     * Returns the product object associated with the given id.
     *
     * @param productId of the product to retrieve
     * @return the product object associated with the given id,  
     */
    Product getProduct(String productId);
    
    /**
     * Returns the updated product object associated with the given id and product.
     *
     * @param productId of the product to update
     * @param product product to be updated
     * @return the updated product object  
     */
    Product updateProduct(String productId, Product product);
    
    /**
     * Returns the product object that was removed.
     *
     * @param productId of the product to be removed
     * @return the product object that was removed  
     */
    Product removeProduct(String productId);
    
    /**
     * Returns the map object associated with the file it is reading from
     *
     * @returns Map the map of the products from the file
     * @throws VendingMachinePersistenceException
     */
    Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException;
    
    /**
     * writes the products to the file
     *
     * @throws VendingMachinePersistenceException
     */
    void writeProductsToFile() throws VendingMachinePersistenceException;
}