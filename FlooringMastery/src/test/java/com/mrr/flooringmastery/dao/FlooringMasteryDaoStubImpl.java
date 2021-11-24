/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrr.flooringmastery.dao;

import com.mrr.flooringmastery.model.Order;
import com.mrr.flooringmastery.model.Product;
import com.mrr.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mroge
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao{

    private Order onlyOrder;
    private List<Order> ordersList = new ArrayList<>();

    public FlooringMasteryDaoStubImpl() {

        onlyOrder = new Order();
        onlyOrder.setDate(LocalDate.parse("02182020",
                DateTimeFormatter.ofPattern("MMddyyyy")));
        onlyOrder.setOrderNumber(1);
        onlyOrder.setCustomerName("Coolest Company");
        onlyOrder.setState("IN");
        onlyOrder.setTaxRate(new BigDecimal("6.00"));
        onlyOrder.setProductType("Laminate");
        onlyOrder.setArea(new BigDecimal("100"));
        onlyOrder.setCostPerSquareFoot(new BigDecimal("1.75"));
        onlyOrder.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        onlyOrder.setMaterialCost(onlyOrder.getCostPerSquareFoot()
                .multiply(onlyOrder.getArea()).setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setLaborCost(onlyOrder.getLaborCostPerSquareFoot().multiply(onlyOrder.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTax(onlyOrder.getTaxRate().divide(new BigDecimal("100.00"))
                .multiply((onlyOrder.getMaterialCost().add(onlyOrder.getLaborCost())))
                .setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTotal(onlyOrder.getMaterialCost().add(onlyOrder.getLaborCost())
                .add(onlyOrder.getTax()));

        ordersList.add(onlyOrder);

    }

    @Override
    public List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException {
        if (dateChoice.equals(onlyOrder.getDate())) {
            return ordersList;
        } else {
            //Should return an empty list like the dao does.
            return new ArrayList<>();
        }
    }

    @Override
    public Order addOrder(Order o) throws DataPersistenceException {
        ordersList.add(o);
        return o;
    }

    @Override
    public Order editOrder(Order editedOrder) throws DataPersistenceException {
        if (editedOrder.getOrderNumber() == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(Order o) throws DataPersistenceException {
        if (o.equals(onlyOrder)) {
            return onlyOrder;
        } else {
            return null;
        }
    }   

    @Override
    public Product getProduct(String productType) throws DataPersistenceException {
        Product product = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        return product;
    }

    @Override
    public Tax getTax(String stateAbbr) throws DataPersistenceException {
        Tax tax1 = new Tax("TX", "Texas", new BigDecimal("4.45"));
        Tax tax2 = new Tax("WA", "Washington", new BigDecimal("9.25"));
        Tax tax3 = new Tax("KY", "Kentucky", new BigDecimal("6.00"));
        Tax tax4 = new Tax("CA", "California", new BigDecimal("25.00"));
        Tax tax5 = new Tax("OH", "Ohio", new BigDecimal("6.25"));
        Tax tax6 = new Tax("PA", "Pennsylvania", new BigDecimal("6.75"));
        Tax tax7 = new Tax("MI", "Michigan", new BigDecimal("5.75"));
        Tax tax8 = new Tax("IN", "Indiana", new BigDecimal("6.00"));
        switch (stateAbbr) {
            case "TX":
                return tax1;
            case "WA":
                return tax2;
            case "KY":
                return tax3;
            case "CA":
                return tax4;
            case "OH":
                return tax5;
            case "PA":
                return tax6;
            case "MI":
                return tax7;
            default:
                return tax8;
        }
    }
}
