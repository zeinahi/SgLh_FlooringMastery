/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.service;

import com.swcguild.flooringmastery.dao.AuditDao;
import com.swcguild.flooringmastery.dao.OrderDao;
import com.swcguild.flooringmastery.dao.PersistenceException;
import com.swcguild.flooringmastery.dao.ProductDao;
import com.swcguild.flooringmastery.dao.StateTaxesDao;
import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.dto.Product;
import com.swcguild.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceLayerImpl implements ServiceLayer {

    private OrderDao orderDao;
    private ProductDao productDao;
    private StateTaxesDao stateTaxesDao;
    AuditDao auditDao;
    LocalDate orderDate = LocalDate.now();

    public ServiceLayerImpl(OrderDao orderDao, ProductDao productDao, StateTaxesDao stateTaxesDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateTaxesDao = stateTaxesDao;

    }

    public ServiceLayerImpl(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public Order createOrder(LocalDate orderDate, String customerName, String productName, BigDecimal area, String state) throws PersistenceException, InvalidStateException,
            InvalidAreaException, InvalidProductException {
        //gets list of prodcuts and states from dao
        List<Product> productList = productDao.getAllProducts();
        List<StateTax> stateTaxList = stateTaxesDao.getStateTaxes();

        //gets costpersq and laborpersq from dao
//        productName = productName.toUpperCase();
        Product productType = productDao.getProduct(productName);
        if (productType == null) {
            throw new InvalidStateException("Invalid product type. Please enter valid product type name");
        }

        BigDecimal costSqFt = productType.getCostPerSqFt();
        BigDecimal laborSqFt = productType.getLaborPerSqFt();

        state = state.toUpperCase();
        StateTax stateName = stateTaxesDao.getState(state);
        if (stateName == null) {
            throw new InvalidStateException("Invalid state. Please enter valid state abbreviation");
        }
        BigDecimal taxRate = stateName.getStateTax();

        //calculations for an order 
        BigDecimal MaterialCostPerSq = area.multiply(costSqFt);
        BigDecimal laborCost = area.multiply(laborSqFt);
        BigDecimal MaterialCostPerSqPlusLaborCost = MaterialCostPerSq.add(laborCost);
        BigDecimal totalTax = MaterialCostPerSqPlusLaborCost.multiply(taxRate);
        BigDecimal total = MaterialCostPerSqPlusLaborCost.add(totalTax);

        Order currentOrder = new Order();
//        currentOrder.setOrderDate(orderDate);
        currentOrder.setOrderNumber(0);
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductName(productName);
        currentOrder.setArea(area);

        currentOrder.setTaxRate(taxRate);
        currentOrder.setCostPerSq(costSqFt);
        currentOrder.setLaborCostPerSq(laborSqFt);
        currentOrder.setMaterialCostPerSq(MaterialCostPerSq);
        currentOrder.setLaborCost(laborCost);
        currentOrder.setTotalTax(totalTax);
        currentOrder.setTotal(total);

        //TODO: Add call to dao.AddOrder
        this.orderDao.Create(orderDate, currentOrder);
        return currentOrder;
//        try {
//            validateOrderData(order);
//        } catch (InvalidDateException ex) {
//            System.out.println("Order not valid, please complete all fields");
//        }
//        orderDao.createOrder(order);
//        auditDao.writeAuditEntry("Order" + order.getOrderNumber() + "created.");
//        return order;

//    Order order = orderDao.createOrder(
//    return order;
    }

    @Override
    public Order editOrder(LocalDate orderDate, Order order) throws PersistenceException, InvalidStateException, InvalidDateException,
            InvalidAreaException, InvalidProductException {

        // Load up Product Dao by using getAllproducts() first
        List<Product> prodList = productDao.getAllProducts();
        Product productType = null;
        for (Product prod : prodList) {
            if (prod.getProductName().equalsIgnoreCase(order.getProductName())) {
                productType = prod;
            }
        }

        if (productType == null) {
            throw new InvalidProductException("Invalid product. Please enter valid product type name");
        }

        BigDecimal costSqFt = productType.getCostPerSqFt();
        BigDecimal laborSqFt = productType.getLaborPerSqFt();

        List<StateTax> taxList = stateTaxesDao.getStateTaxes();
        StateTax tax = null;
        for (StateTax currentTax : taxList) {
            if (currentTax.getState().equalsIgnoreCase(order.getState())) {
                tax = currentTax;
            }
        }

        StateTax stateName = stateTaxesDao.getState(order.getState());
        if (stateName == null) {
            throw new InvalidStateException("Invalid state. Please enter valid state abbreviation");
        }
        BigDecimal taxRate = stateName.getStateTax();
        BigDecimal area = order.getArea();
        //calculations for an order 
        BigDecimal MaterialCostPerSq = area.multiply(costSqFt);
        BigDecimal laborCost = area.multiply(laborSqFt);
        BigDecimal MaterialCostPerSqPlusLaborCost = MaterialCostPerSq.add(laborCost);
        BigDecimal totalTax = MaterialCostPerSqPlusLaborCost.multiply(taxRate);
        BigDecimal total = MaterialCostPerSqPlusLaborCost.add(totalTax);

        Order currentOrder = new Order();
        currentOrder.setOrderNumber(order.getOrderNumber());
        currentOrder.setCustomerName(order.getCustomerName());
        currentOrder.setState(order.getState());
        currentOrder.setProductName(order.getProductName());
        currentOrder.setArea(area);

        currentOrder.setTaxRate(taxRate);
        currentOrder.setCostPerSq(costSqFt);
        currentOrder.setLaborCostPerSq(laborSqFt);
        currentOrder.setMaterialCostPerSq(MaterialCostPerSq);
        currentOrder.setLaborCostPerSq(laborCost);
        currentOrder.setTotalTax(totalTax);
        currentOrder.setTotal(total);
        this.orderDao.Edit(orderDate, order);
        return currentOrder;
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws PersistenceException, InvalidDateException {
        orderDao.Delete(orderDate, orderNumber);
    }

    //TODO: add LocalDate to getAll
    @Override
    public List<Order> getAll(LocalDate orderDate) throws PersistenceException, InvalidDateException {
        return this.orderDao.ReadAll(orderDate);
    }

    @Override
    public void save() throws PersistenceException {
        try {
            this.orderDao.Save();
        } catch (Exception ex) {
            System.out.println("Unable to complete save. Please try again");
        }
    }

//    private void validateOrderData(Order order) throws PersistenceException, InvalidDateException {
//       if (order.equals(order) == true
//            || order.getCustomerName().trim().length() == 0
//            || order.getProductName().trim().length() == 0
////            || order.getArea()
//            || order.getState().trim().length() == 0){
//           throw new PersistenceException(
//                "ERROR: All fields are required.");    
//       }
    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) {
        Order order = orderDao.ReadById(orderDate, orderNumber);
        return order;
    }
}
