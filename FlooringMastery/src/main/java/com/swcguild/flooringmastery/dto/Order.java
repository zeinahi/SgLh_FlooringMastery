/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author zissah
 */
public class Order {
//    private LocalDate orderDate;
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate; //using data files
    private String productName;        
    private BigDecimal area;
    
    // using data files
    private BigDecimal costPerSq;
    private BigDecimal laborCostPerSq;
    
    //calculate using methods in service layer.
    private BigDecimal materialCostPerSq;
    private BigDecimal laborCost;
    private BigDecimal totalTax;
    private BigDecimal Total;

//    public LocalDate getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(LocalDate orderDate) {
//        this.orderDate = orderDate;
//    }
    
//    public Order(int orderNumber, String customerName, String state, BigDecimal area,String productName, BigDecimal taxRate, BigDecimal materialCostPerSq, BigDecimal laborCostPerSq, BigDecimal tax, BigDecimal total) {
//        this.orderNumber = orderNumber;
//        this.customerName = customerName;
//        this.state = state;
//        this.area = area;
//        this.products = products;
//        this.productName = productName;
//        this.taxRate = taxRate;
//        this.materialCostPerSq = materialCostPerSq;
//        this.laborCostPerSq = laborCostPerSq;
//        this.tax = tax;
//        this.total = total;
//    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSq() {
        return costPerSq;
    }

    public void setCostPerSq(BigDecimal costPerSq) {
        this.costPerSq = costPerSq;
    }

    public BigDecimal getLaborCostPerSq() {
        return laborCostPerSq;
    }

    public void setLaborCostPerSq(BigDecimal laborCostPerSq) {
        this.laborCostPerSq = laborCostPerSq;
    }

    public BigDecimal getMaterialCostPerSq() {
        return materialCostPerSq;
    }

    public void setMaterialCostPerSq(BigDecimal materialCostPerSq) {
        this.materialCostPerSq = materialCostPerSq;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }
  
    

}
