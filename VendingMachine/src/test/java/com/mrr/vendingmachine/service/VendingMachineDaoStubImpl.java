/*
 * Author: mroge
 * Purpose: stub implementation
 */
package com.mrr.vendingmachine.service;

import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//implements keyword indicates this class implements the specified interface
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    public Product onlyProduct;
    
    public VendingMachineDaoStubImpl() {
        BigDecimal bd = new BigDecimal("2.50");
        onlyProduct = new Product("1", "Coke", bd, 10);
    }
    
    public VendingMachineDaoStubImpl(Product testProduct) {
        this.onlyProduct = testProduct;
    }

    @Override
    public Product addProduct(String productId, Product product) {
        if (productId.equals(onlyProduct.getProductId())) {
                return onlyProduct;
            } else {
                return null;
            }
    }

    @Override
    public List<Product> getAllProducts() {
       List<Product> productList = new ArrayList<>();
        productList.add(onlyProduct);
        return productList;
    }

    @Override
    public List<String> getAllProductIds() {
        List<String> productIdList = new ArrayList<>();
        productIdList.add(onlyProduct.getProductId());
        return productIdList;
    }

    @Override
    public Product getProduct(String productId) {
        if (productId.equals(onlyProduct.getProductId())) {
            return onlyProduct;
        } else {
            return null;
        }    
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        if (productId.equals(onlyProduct.getProductId())) {
                return onlyProduct;
            } else {
                return null;
            }
    }

    @Override
    public Product removeProduct(String productId) {
        if (productId.equals(onlyProduct.getProductId())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException {
        Map<String, Product> products = new TreeMap<>();
        products.put(onlyProduct.getProductId(), onlyProduct);
        return products;
    }

    @Override
    public void writeProductsToFile() throws VendingMachinePersistenceException {
    }
    
}
