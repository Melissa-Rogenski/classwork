
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.dto.Product;
import com.mrr.vendingmachine.service.VendingMachinePersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoImpl implements VendingMachineDao {
    
    public final String PRODUCTS_FILE;
    private Map<String, Product> products = new HashMap<>();
    
    public VendingMachineDaoImpl() {
        PRODUCTS_FILE = "products.txt";
    }
    
    public VendingMachineDaoImpl(String productsTextFile) {
        PRODUCTS_FILE = productsTextFile;
    }
    
    @Override
    public Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch(FileNotFoundException e) {
            throw new VendingMachinePersistenceException (
                    "-_- Could not load product data into memory", e);
        }
        String currentLine;
        
        Product currentProduct;
        
        while (scanner.hasNextLine()) {
            
            currentLine = scanner.nextLine();
            
            currentProduct = new Product(currentLine);
            
            products.put(currentProduct.getProductId(), currentProduct);
        }
        
        scanner.close();
        return products;
    }
    
    @Override
    public void writeProductsToFile() throws VendingMachinePersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(PRODUCTS_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save product data.", e);
        }
        
        String productAsText;
        List<Product> productList = this.getAllProducts();
        for(Product currentProduct : productList) {
            
            productAsText = currentProduct.marshalProductAsText();
            
            out.println(productAsText);
            
            out.flush();
        }
        
        out.close();
    }
    
    @Override
    public Product addProduct(String productId, Product product) {
        Product prevProduct = products.put(productId, product);
        return prevProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public List<String> getAllProductIds() {
        return new ArrayList<>(products.keySet());
    }

    @Override
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        return products.replace(productId, product);
    }

    @Override
    public Product removeProduct(String productId) {
        Product removedProduct = products.remove(productId);
        return removedProduct;
    }
    
}