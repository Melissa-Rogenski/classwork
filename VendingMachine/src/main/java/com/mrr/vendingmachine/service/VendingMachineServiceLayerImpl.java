
package com.mrr.vendingmachine.service;

import com.mrr.vendingmachine.dao.VendingMachineAuditDao;
import com.mrr.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.dao.VendingMachineDaoImpl;
import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        Map<String, Product> productsInStock = new HashMap<>();
        for(Product p : dao.loadProductsFromFile().values()) {
            if(p.getItemsInStock() < 1) {
                auditDao.writeAuditEntry("Product Id: " + p.getProductId() + " quantity in stock is zero.");
            } else {
                productsInStock.put(p.getProductId(), p);
            }
        }
        return productsInStock;
    }
    
    @Override
    public void saveProductList() throws VendingMachinePersistenceException {
        dao.writeProductsToFile();;
        auditDao.writeAuditEntry("Product list saved to file.");
    }
    
    @Override
    public Product getChosenProduct(String productId) throws VendingMachineNoItemInventoryException {
        validateProductInStock(productId);
        return dao.getProduct(productId);
    }
    
    @Override
    public void checkSufficientMoneyToBuyProduct(BigDecimal inputAmount, Product product) throws VendingMachineInsufficientFundsException {
        if(inputAmount.compareTo(product.getPrice()) < 0) {
            throw new VendingMachineInsufficientFundsException("Insufficient funds to buy " + product.getProductName() + " prices at $" + product.getPrice());
        }
    }
    
    //@Override
    public Change claculateChange(BigDecimal amount, Product product) {
        BigDecimal change = amount.subtract(product.getPrice()).multiply(new BigDecimal("100"));
        return new Change(change);
    }

    private void validateProductInStock(String productId) {
        System.out.print("ValidateProduct in stock");
    }

    @Override
    public Change calculateChange(BigDecimal amount, Product product) {
        Change change = new Change(amount);
        return change;
    }

    @Override
    public void updateProductSale(Product product) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        System.out.print("update product sale implementation not avaliable");
    }
    
}
