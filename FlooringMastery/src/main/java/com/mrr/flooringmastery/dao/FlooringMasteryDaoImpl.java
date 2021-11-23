/*
 * Author: mroge
 * Purpose: This file implements the FlooringMasteryDao interface
 */
package com.mrr.flooringmastery.dao;

import com.mrr.flooringmastery.model.Order;
import com.mrr.flooringmastery.model.Product;
import com.mrr.flooringmastery.model.Tax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//implements keyword indicates this class implements the specified interface
public class FlooringMasteryDaoImpl implements FlooringMasteryDao {
    
    private int lastOrderNumber;
    private static final String PRODUCTS_FILE = "Products.txt";
    private static final String TAXES_FILE = "Taxes.txt";
    private static final String ORDER_HEADER = "OrderNumber,CustomerName,State,"
            + "TaxRate,ProductType,Area,CostPerSquareFoot,"
            + "LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";
    private static final String DELIMITER = ",";
    private String dataFolder = "orders/";
    
    // default constructor
    public FlooringMasteryDaoImpl() {   
    }
    
    // constructor
    public FlooringMasteryDaoImpl(String dataFolder) {
        this.dataFolder = dataFolder;
    }
    
    // function to get the last order number from a file that is exclusively 
    //for the last order number
    private void readLastOrderNumber() throws 
            DataPersistenceException {
        // declaring scanner
        Scanner scanner;
        
        //try catch block
        try {
            //initializing scanner
            scanner = new Scanner(new BufferedReader(new FileReader(dataFolder +
                    "LastOrderNumber.txt")));
        //catch block
        } catch(FileNotFoundException e) {
            // throw
            throw new DataPersistenceException("-_- Could not load "
                    + "order number from file into memory.", e);
        }
        // end of try catch block
        
        // capturing the last order number from the file
        int savedOrderNumber = Integer.parseInt(scanner.nextLine());
        
        //saving saved order number to last order number to keep track of the 
        //number of orders in total
        this.lastOrderNumber = savedOrderNumber;
        
        // closing scanner
        scanner.close();
    }
    
    // function to write the last order number to a file
    private void writeLastOrderNumber(int lastOrderNumber) throws 
            DataPersistenceException {
        // declaring printwriter
        PrintWriter out;
        
        //try catch block
        try {
            // initializing printwriter
            out = new PrintWriter(new FileWriter(dataFolder + 
                    "LastOrderNumber.txt"));
        //catch block
        } catch(IOException e) {
            // throw
            throw new DataPersistenceException("Could not save "
                    + "order number to file.", e);
        }
        // end of try catch block
        
        //adding info to file
        out.println(lastOrderNumber);
        
        // flushing printwriter
        out.flush();
        
        // closing printwriter
        out.close();
    }

    // function to clear delimiters from order variables
    private Order clearDelimits(Order order) {
        // clearing out the delimiters if they are there
        String customerName = order.getCustomerName().replace(DELIMITER, "");
        String state = order.getState().replace(DELIMITER, "");
        String productType = order.getProductType().replace(DELIMITER, "");
        
        //setting
        order.setCustomerName(customerName);
        order.setState(state);
        order.setProductType(productType);
        
        //returning
        return order;
    }
    
    // function to load orders from a file
    private List<Order> loadOrders(LocalDate userDateChoice) throws 
            DataPersistenceException {
        // declaring scanner
        Scanner scanner;
        // formatting date
        String fileDate = userDateChoice.format(DateTimeFormatter.
                ofPattern("MMddyyyy"));
        
        //creating file
        File file = new File(String.format(dataFolder + "Orders_%s.txt",
                fileDate));
        
        // creating list
        List<Order> orders = new ArrayList<>();
        
        //checking if file is file
        if(file.isFile()) {
            //try catch block
            try {
                // redeclaring scanner
                scanner = new Scanner(new BufferedReader(new FileReader(file)));
            // catch block
            } catch(FileNotFoundException e) {
                //throw
                throw new DataPersistenceException("-_- Could not "
                        + "load order data into memory.", e);
            }
            // end of try catch
            // end of try catch
            
            // String to store current line
            String currentLine;

            // skips the file header line
            scanner.nextLine();
            String[] currentTokens;
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                if (currentTokens.length == 12) {
                    Order currentOrder = new Order();
                    currentOrder.setDate(LocalDate.parse(fileDate,
                            DateTimeFormatter.ofPattern("MMddyyyy")));
                    currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                    currentOrder.setCustomerName(currentTokens[1]);
                    currentOrder.setState(currentTokens[2]);
                    currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                    currentOrder.setProductType(currentTokens[4]);
                    currentOrder.setArea(new BigDecimal(currentTokens[5]));
                    currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                    currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                    currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                    currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                    currentOrder.setTax(new BigDecimal(currentTokens[10]));
                    currentOrder.setTotal(new BigDecimal(currentTokens[11]));
                    orders.add(currentOrder);
                } else {
                    // blank statement
                }
            }
            scanner.close();
            return orders;
        } else {
            return orders;
        }
    }
    
    // function to write orders to a file
    private void writeOrders(List<Order> orders, LocalDate orderDate) throws
            DataPersistenceException {
        // declaring printWriter
        PrintWriter out;
        
        //formatting file name
        String fileDate = orderDate.format(
                DateTimeFormatter.ofPattern("MMddyyyy"));
        File file = new File(String.format(dataFolder + "Orders_%s.txt", fileDate));
        
        //try catch block
        try
        {
            // initilizing printWriter
            out = new PrintWriter(new FileWriter(file));
        } catch(IOException e) { // catch
            //throw
            throw new DataPersistenceException(
                    "Could not save order data to file.", e);
        }
        // end of try catch block
        // end of try catch block
        
        // printing header
        out.println(ORDER_HEADER);
        // looping through orders
        for(Order currentOrder : orders) {
            //creating string with order info
            String orderAsText = currentOrder.marshalOrderAsText();
            //printing string to file
            out.println(orderAsText);
            // flushing file
            out.flush();
        }
        // closing printwriter
        out.close();
    }
    
    // function to load the products from a file
    private List<Product> loadProducts() throws DataPersistenceException {
        // declaring scanner
        Scanner scanner;
        // creating list of products
        List<Product> products = new ArrayList<>();

        // try catch block
        try {
            // initializing scanner
            scanner = new Scanner(new BufferedReader(
                    new FileReader(PRODUCTS_FILE)));
        // catch block
        } catch(FileNotFoundException e) {
            // throw
            throw new DataPersistenceException(
                    "-_- Could not load products data into memory.", e);
        }
        // end of try catch block
        // end of try catch block

        // creating a string to hold the products
        String currentLine;
        // skipping header 
        scanner.nextLine();   
        // while scanner can read more from file
        while(scanner.hasNextLine()) {
            // grabbing info from file
            currentLine = scanner.nextLine();
            // creating product with info from string
            Product product = new Product(currentLine);
            // adding product to lsit
            products.add(product);
        }
        // closing scanner
        scanner.close();

        // if products list in not empty retu nthe product list
        if(!products.isEmpty()) {
            return products;
        } else { // otherwise return null
            return null;
        }
    }
    
    private List<Tax> loadTaxes() throws DataPersistenceException {
        // declaring scanner
        Scanner scanner;
        // creating list of tax objects called taxes
        List<Tax> taxes = new ArrayList<>();

        // try catch block
        try {
            // initializing scanner
            scanner = new Scanner(new BufferedReader(
                    new FileReader(TAXES_FILE)));
        // catch block
        } catch(FileNotFoundException e) {
            // throw
            throw new DataPersistenceException(
                    "-_- Could not load taxes data into memory.", e);
        }
        // end of try catch block
        // end of try catch block
        
        // creating string to hold tax info
        String currentLine;
        // skipping header
        scanner.nextLine();
        // while scanner can read more from file
        while(scanner.hasNextLine()) {
            // grabbing info from file
            currentLine = scanner.nextLine();
            // creating tax with the info from the string
            Tax tax = new Tax(currentLine);
            // adding tax to list of tax objects
            taxes.add(tax);
        }
        // closing scanner
        scanner.close();

        // if the list is not empty return taxes
        if(!taxes.isEmpty()) {
            return taxes;
        }else { // otherwise return null
            return null;
        }
    }
    
    // function to add an order
    @Override
    public Order addOrder(Order order) throws 
            DataPersistenceException {
        // clearing delimiters from relevant fields
        order = clearDelimits(order);
        //calling readLastOrderNumber function
        readLastOrderNumber();
        // adding 1 to order number
        lastOrderNumber++;
        // setting paramater order number to the last order numebrl
        order.setOrderNumber(lastOrderNumber);
        // writing order number to file
        writeLastOrderNumber(lastOrderNumber);

        // creating a list of orders and calling load orders function
        List<Order> orders = loadOrders(order.getDate());
        // adding order to the list
        orders.add(order);
        // calling write order to file function
        writeOrders(orders, order.getDate());

        // returning the added order
        return order;
    }

    // function to get a list of orders
    @Override
    public List<Order> getOrders(LocalDate chosenDate) throws 
            DataPersistenceException {
        return loadOrders(chosenDate);
    }

    // function to edit an order
    @Override
    public Order editOrder(Order editedOrder) throws 
            DataPersistenceException {
        // clearing delimiters from relevant fields
        editedOrder = clearDelimits(editedOrder);
        // getting order number
        int orderNumber = editedOrder.getOrderNumber();

        // creating a list of orders and calling load orders
        List<Order> orders = loadOrders(editedOrder.getDate());
        // creating new order and using lambda stream to verify that the order
        //numbers match
        Order chosenOrder = orders.stream().filter(o -> o.getOrderNumber() ==
                orderNumber).findFirst().orElse(null);

        // if the order was found
        if(editedOrder != null) {
            // getting index of order
            int index = orders.indexOf(chosenOrder);
            // setting order
            Order set = orders.set(index, editedOrder);
            // call to writeorders
            writeOrders(orders, editedOrder.getDate());
            //return the edited order
            return editedOrder;
        }else { // otherwise return null
            return null;
        }
    }

    // function to remove an order from the order list
    @Override
    public Order removeOrder(Order order) throws 
            DataPersistenceException {
        // getting order number
        int orderNumber = order.getOrderNumber();
        // creating list of Orders and calling loadOrders
        List<Order> orders = loadOrders(order.getDate());
        // lambda stream
        Order removedOrder = orders.stream().filter(o -> o.getOrderNumber() == 
                orderNumber).findFirst().orElse(null);
        
        // if order exists
        if(removedOrder != null) {
            //removing order
            orders.remove(removedOrder);
            // writing orders to file
            writeOrders(orders, order.getDate());
            // returning removed order
            return removedOrder;
        }else { // otherwise return null
            return null;
        }
    }

    // function to get a prodcut
    @Override
    public Product getProduct(String productType) throws 
            DataPersistenceException {
        // creating list of products and calling loadProducts
        List<Product> products = loadProducts();
        // if the product was not found return null
        if(products == null) {
            return null;
        }else { // otherwise get the product from the new list with the
            //matching producttype
            
            // lambda stream to find product
            Product product = products.stream().filter(p -> p.getProductType()
                    .equalsIgnoreCase(productType)).findFirst().orElse(null);
            return product;
        }
    }

    // function to get a Tax object
    @Override
    public Tax getTax(String stateAbbr) throws 
            DataPersistenceException {
        // creating a list of tax objects called taxes
        // calling loadTaxes Function
        List<Tax> taxes = loadTaxes();
        // if the list is empty return null
        if(taxes == null) {
            return null;
        }else { // otherwise lambda stream to search for the tax we are 
            //looking for
            Tax tax = taxes.stream().filter(s -> s.getStateAbbreviation()
                    .equalsIgnoreCase(stateAbbr)).findFirst().orElse(null);
            return tax;
        }
    }
}