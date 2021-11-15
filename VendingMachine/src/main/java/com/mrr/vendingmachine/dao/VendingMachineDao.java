
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.dto.Product;
import com.mrr.vendingmachine.service.VendingMachineDataValidationException;
import java.util.List;


public interface VendingMachineDao {
    //Product addProduct(String name, Product product) throws VendingMachineDataValidationException;
    
    List<Product> getAllProducts() throws VendingMachineDataValidationException;
    
    Product getProduct(String name) throws VendingMachineDataValidationException;
    
}
