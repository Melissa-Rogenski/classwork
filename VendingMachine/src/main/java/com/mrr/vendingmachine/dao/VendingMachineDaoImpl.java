
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.dto.Product;
import com.mrr.vendingmachine.service.VendingMachineDataValidationException;
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

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Product> products = new HashMap<>();
    
    /*
    @Override
    public Product addProduct(String name, Product product) throws VendingMachineDataValidationException {
        loadProducts();
        Product prevProduct = products.put(name, product);
        writeProducts();
        return prevProduct;
    }

    */
    @Override
    public List<Product> getAllProducts() throws VendingMachineDataValidationException{
        loadProducts();
        return new ArrayList(products.values());
    }

    @Override
    public Product getProduct(String name) throws VendingMachineDataValidationException {
        loadProducts();
        return products.get(name);
    }
    
    private Product unmarshallProduct(String productAsText) {
        // name|cost|inventory
        //  [0] [1]    [3]
        String[] productTokens = productAsText.split(DELIMITER);
        
        String name = productTokens[0];
        
        Product productFromFile = new Product(name);
        
        productFromFile.setCost(productTokens[1]);
        
        productFromFile.setInventory(productTokens[2]);
        
        return productFromFile;
    }
    
    private void loadProducts() throws VendingMachineDataValidationException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDataValidationException("-_- Could not load product inventory data into memory.", e);
        }
        
        String currentLine;
        
        Product currentProduct;
        
        while (scanner.hasNextLine()) {
            
            currentLine = scanner.nextLine();
            
            currentProduct = unmarshallProduct(currentLine);
            
            products.put(currentProduct.getName(), currentProduct);
        }
        
        scanner.close();
    }
    
    private String marshallProduct(Product aProduct) {
        //name::cost::inventory
        String productAsText = aProduct.getName() + DELIMITER;
        
        productAsText += aProduct.getCost() + DELIMITER;
        
        productAsText += aProduct.getInventory();
        
        return productAsText;
    }
    
    private void writeProducts() throws VendingMachineDataValidationException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachineDataValidationException("Could not save product data.", e);
        }
        
        String productAsText;
        List<Product> productList = this.getAllProducts();
        for(Product currentProduct : productList) {
            
            productAsText = marshallProduct(currentProduct);
            
            out.println(productAsText);
            
            out.flush();
        }
        
        out.close();
    }  
}
