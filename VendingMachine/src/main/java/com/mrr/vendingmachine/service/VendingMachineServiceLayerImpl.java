/*
 * Author: mroge
 * Purpose: This file implements the VendingMachineServiceLayer interface
 */
package com.mrr.vendingmachine.service;

import com.mrr.vendingmachine.dao.VendingMachineAuditDao;
import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//implements keyword indicates this class implements the specified interface
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    // creating private variables as well as VendingMachineDao
    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    // constructor 
    // params VendingMachineDao, VendingMachineAuditDao
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        // assigning values to variables
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    // public function to load produxts that are in stock
    // returns Map<String, Product>
    // throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException
    @Override
    public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        // creating map
        Map<String, Product> productsInStock = new HashMap<>();
        // for each to loop through prodcuts
        for(Product p : dao.loadProductsFromFile().values()) {
            // if true write audit
            if(p.getItemsInStock() < 1) {
                auditDao.writeAuditEntry("Product Id: " + p.getProductId() + " quantity in stock is zero.");
            } else {
                // add product to map
                productsInStock.put(p.getProductId(), p);
            }
        }
        //return map
        return productsInStock;
    }
    
    // public function to save product list
    // throws VendingMachinePersistenceException
    @Override
    public void saveProductList() throws VendingMachinePersistenceException {
        // call to writProduicts to file
        dao.writeProductsToFile();
        // call to write audit entry
        auditDao.writeAuditEntry("Product list saved to file.");
    }
    
    // public function to get the chosen product from the user
    // returns the product the user selected
    // param string
    // throws VendingMachineNoItemInventoryException
    @Override
    public Product getChosenProduct(String productId) throws VendingMachineNoItemInventoryException {
        validateProductInStock(productId);
        return dao.getProduct(productId);
    }
    
    // public function to check if the funds are enough for product to vend
    // params bigdecimal, product
    // throws VendingMachineInsufficientFundsException
    @Override
    public void checkSufficientMoneyToBuyProduct(BigDecimal inputAmount, Product product) throws VendingMachineInsufficientFundsException {
        if(inputAmount.compareTo(product.getPrice()) < 0) {
            throw new VendingMachineInsufficientFundsException("Insufficient funds to buy " + product.getProductName() + " priced at $" + product.getPrice());
        }
    }

    // private function to validate that the selected product is in stock
    // param string
    // throws VendingMachineNoItemInventoryException
    private void validateProductInStock(String productId) throws VendingMachineNoItemInventoryException {
        Product product = dao.getProduct(productId);
        if(product.getItemsInStock() <= 0) {
            throw new VendingMachineNoItemInventoryException("Sorry, " + product.getProductName() + " is out of stock.");
        }
    }

    // public function to update the selected products stock
    // param product
    // throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException
    @Override
    public void updateProductSale(Product product) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        int stock = product.getItemsInStock();
        stock--;
        product.setItemsInStock(stock);
        dao.updateProduct(product.getProductId(), product);
        auditDao.writeAuditEntry("Product at Id: " + product.getProductId() + " stock is now at " + product.getItemsInStock());
    }

    // public function to calculate the change to be returned to the user
    // returns Change
    // param bigdecimal, product
    @Override
    public Change calculateChange(BigDecimal amount, Product product) {
        BigDecimal change = amount.subtract(product.getPrice()).multiply(new BigDecimal("100"));
        return new Change(change);
    }
    
}
