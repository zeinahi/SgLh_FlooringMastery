/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.service;

import com.swcguild.flooringmastery.dao.PersistenceException;
import com.swcguild.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author zissah
 */
public interface ServiceLayer {

    Order createOrder(LocalDate orderDate,String customerName, String productName, BigDecimal area, String state) throws PersistenceException, InvalidStateException,
            InvalidAreaException, InvalidProductException;

    Order editOrder(LocalDate orderDate,  Order order) throws PersistenceException, InvalidStateException, InvalidDateException,
            InvalidAreaException, InvalidProductException;

    void removeOrder(LocalDate orderDate, int orderNumber) throws PersistenceException, InvalidDateException;

    List<Order> getAll(LocalDate orderDate) throws PersistenceException, InvalidDateException;

    void save() throws PersistenceException;

    Order getOrder(LocalDate orderDate, int orderNumber) throws PersistenceException, InvalidDateException;
}
