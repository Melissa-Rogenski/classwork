
package com.mrr.vendingmachine.dto;

public class Product {
    private String name;
    private String cost;
    private String inventory;
    
    public Product(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCost() {
        return cost;
    }
    
    public void setCost(String cost) {
        this.cost = cost;
    }
    
    public String getInventory() {
        return inventory;
    }
    
    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}
