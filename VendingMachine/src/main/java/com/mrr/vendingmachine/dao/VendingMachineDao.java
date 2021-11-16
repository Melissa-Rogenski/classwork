/*
 * Author: mroge
 * Purpose: interface to be implemented by the VendingMachineDaoImpl class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of DAO in the application.
 *         
 * The VendingMachineController only uses this interface to reference the DAO — it 
 * is completely unaware of the implementation details.
 */
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.dto.Product;
import com.mrr.vendingmachine.service.VendingMachinePersistenceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {
    
    Product addProduct(String productId, Product product);
    
    List<Product> getAllProducts();
    
    List<String> getAllProductIds();
    
    Product getProduct(String productId);
    
    Product updateProduct(String productId, Product product);
    
    Product removeProduct(String productId);
    
    Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException;
    
    void writeProductsToFile() throws VendingMachinePersistenceException;
}