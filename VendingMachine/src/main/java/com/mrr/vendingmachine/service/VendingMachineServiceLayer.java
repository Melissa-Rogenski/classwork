/*
 * Author: mroge
 * Purpose: interface to be implemented by the VendingMachineAuditDaoImpl class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of AuditDAO in the application.
 *         
 * The VendingMachineServiceLayer only uses this interface to reference the DAO — it 
 * is completely unaware of the implementation details.
 */
package com.mrr.vendingmachine.service;

import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.Map;

// interface declaration
public interface VendingMachineServiceLayer {
    
    /**
     * loads the products in stock
     *
     * @return Map<String, Product> the map of products and the associated id
     * @throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException
     * @throws com.mrr.vendingmachine.service.VendingMachineNoItemInventoryException
     */
    public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException;
    
    /**
     * saves the product list
     * 
     * @throws VendingMachinePersistenceException
     */
    public void saveProductList() throws VendingMachinePersistenceException;
    
    /**
     * gets the chosen product from the user
     * 
     * @param productId
     * @return Product the product that was chosen by the user
     * @throws VendingMachineNoItemInventoryException
     */
    public Product getChosenProduct(String productId) throws VendingMachineNoItemInventoryException;
    
    /**
     * checks if the money entered is sufficient top purchase the selected product
     * 
     * @param imputAmount the amount of money input by the user
     * @param product the product selected by the user
     * @throws VendingMachineInsufficientFundsException
     */
    public void checkSufficientMoneyToBuyProduct(BigDecimal imputAmount, Product product) throws VendingMachineInsufficientFundsException;
    
    /**
     * calculates the change to be returned to the user
     * 
     * @param amount the amount of money the user input into the machine
     * @param product the product the user selected
     * @return Change that is to be given back to the user
     */
    public Change calculateChange(BigDecimal amount, Product product);
    
    /**
     * updates the stock of a product after a purchase
     * 
     * @param product the product that was purchased
     * @throws VendingMachineNoItemInventoryException
     * @throws VendingMachinePersistenceException
     */
    public void updateProductSale(Product product) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException;
    
}
