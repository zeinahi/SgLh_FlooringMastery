/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author zissah
 */
public class OrderMapper {
    public static Order fromCSV(String line){
        Order o = new Order();
        String[] fields = line.split(",");
        o.setOrderNumber(Integer.parseInt(fields[0]));
        o.setCustomerName(fields[1]);
        o.setState(fields[2]);
        o.setTaxRate(new BigDecimal(fields[3]));
        o.setProductName(fields[4]);
        o.setArea(new BigDecimal (fields[5]));
        o.setCostPerSq(new BigDecimal (fields[6]));
        o.setLaborCostPerSq(new BigDecimal(fields[7]));
        o.setMaterialCostPerSq(new BigDecimal(fields[8]));
        o.setLaborCost(new BigDecimal(fields[9]));
        o.setTotalTax(new BigDecimal (fields[10]));
        o.setTotal(new BigDecimal (fields[11]));
        
       
        return o;
    }
    
    public static String toCSV(Order order){
        
        return order.getOrderNumber()+ ","+
                order.getCustomerName()+ ","+
                order.getState() + ","+
                order.getTaxRate() + ","+
                order.getProductName() + ","+
                order.getArea() + ","+
                order.getCostPerSq() + ","+
                order.getLaborCostPerSq() + ","+
                order.getMaterialCostPerSq() + ","+
                order.getLaborCost() + ","+
                order.getTotalTax() + ","+
                order.getTotal() ;
                
               
        
    }
}
