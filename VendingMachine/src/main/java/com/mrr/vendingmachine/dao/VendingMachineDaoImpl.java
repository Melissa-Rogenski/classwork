/*
 * Author: mroge
 * Purpose: This file implements the VendingMachineDAO interface
 */
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

//implements keyword indicates this class implements the specified interface
public class VendingMachineDaoImpl implements VendingMachineDao {
    
    // creating a public final string variable
    public final String PRODUCTS_FILE;
    // creating a private map to store the products
    private Map<String, Product> products = new HashMap<>();
    
    // constructor
    public VendingMachineDaoImpl() {
        PRODUCTS_FILE = "products.txt";
    }
    // constructor
    public VendingMachineDaoImpl(String productsTextFile) {
        PRODUCTS_FILE = productsTextFile;
    }
    
    // public function to load products from a file, returns type Map<String, Product>
    // throws VendingMachinePersistenceExcpetion
    @Override
    public Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException {
        // initializing scanner
        Scanner scanner;
        
        //try catch block
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        // catch block
        } catch(FileNotFoundException e) {
            // exception thorw
            throw new VendingMachinePersistenceException (
                    "-_- Could not load product data into memory", e);
        }
        String currentLine;
        
        Product currentProduct;
        
        // geting input
        while (scanner.hasNextLine()) {
            
            currentLine = scanner.nextLine();
            
            currentProduct = new Product(currentLine);
            // storing product in map
            products.put(currentProduct.getProductId(), currentProduct);
        }
        
        // closing scanner
        scanner.close();
        //return statement
        return products;
    }
    
    // public function that is void, it writes the products to a file
    // throws VendingMacvhinePersistenceException
    @Override
    public void writeProductsToFile() throws VendingMachinePersistenceException {
        // initializing printerwriter
        PrintWriter out;
        
        //try catch block
        try {
            out = new PrintWriter(new FileWriter(PRODUCTS_FILE));
        } catch (IOException e) {
            // throws exception
            throw new VendingMachinePersistenceException(
                    "Could not save product data.", e);
        }
        
        // string variable for product as wext
        String productAsText;
        // arraylist of product to hold all products
        List<Product> productList = this.getAllProducts();
        // for each to write each product to the file
        for(Product currentProduct : productList) {
            
            // call to marshalling
            productAsText = currentProduct.marshalProductAsText();
            
            // printing to file
            out.println(productAsText);
            
            // flushing
            out.flush();
        }
        // closing printerwriter
        out.close();
    }
    
    // public function that returns a product
    // takes a string and a product
    @Override
    public Product addProduct(String productId, Product product) {
        Product prevProduct = products.put(productId, product);
        return prevProduct;
    }

    // public function that returns a list of product
    // takes no params
    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<Product>(products.values());
    }

    // public function that returns a list of string
    // takes no param
    @Override
    public List<String> getAllProductIds() {
        return new ArrayList<>(products.keySet());
    }

    // public function that returns a product
    // takes param string
    @Override
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    // public function that returns type product
    // takes params string and product
    @Override
    public Product updateProduct(String productId, Product product) {
        return products.replace(productId, product);
    }

    // public fuinction that retuirns type product
    // takes param string
    @Override
    public Product removeProduct(String productId) {
        Product removedProduct = products.remove(productId);
        return removedProduct;
    }
}