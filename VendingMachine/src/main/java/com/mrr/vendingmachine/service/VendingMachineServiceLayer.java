
package com.mrr.vendingmachine.service;

import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.Map;

public interface VendingMachineServiceLayer {
    
    public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException;
    
    public void saveProductList() throws VendingMachinePersistenceException;
    
    public Product getChosenProduct(String productId) throws VendingMachineNoItemInventoryException;
    
    public void checkSufficientMoneyToBuyProduct(BigDecimal imputAmount, Product product) throws VendingMachineInsufficientFundsException;
    
    public Change calculateChange(BigDecimal amount, Product product);
    
    public void updateProductSale(Product product) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException;
    
}
