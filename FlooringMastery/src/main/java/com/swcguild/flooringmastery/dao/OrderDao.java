/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author zissah
 */
public interface OrderDao {

    Order Create(LocalDate orderDate, Order order);

    void Delete(LocalDate date, int id);

    List<Order> ReadAll(LocalDate date);

    Order ReadById(LocalDate date, int id);

    void Edit(LocalDate orderDate, Order order);

    public void Save();
    
}
