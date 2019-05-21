/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author zissah
 */
public class Product {
    
    String productName;
    BigDecimal costPerSqFt;
    BigDecimal laborPerSqFt;

    public Product(String productName, BigDecimal costPerSqFt, BigDecimal laborPerSqFt) {
        this.productName = productName;
        this.costPerSqFt = costPerSqFt;
        this.laborPerSqFt = laborPerSqFt;
    }
    
    

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborPerSqFt() {
        return laborPerSqFt;
    }

    public void setLaborPerSqFt(BigDecimal laborPerSqFt) {
        this.laborPerSqFt = laborPerSqFt;
    }

   
    
    
    
}
