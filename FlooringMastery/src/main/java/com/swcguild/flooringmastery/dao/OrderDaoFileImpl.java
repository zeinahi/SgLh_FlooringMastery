/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.dao.OrderMapper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderDaoFileImpl implements OrderDao {

    private Map<LocalDate, Map<Integer, Order>> orders;

    public OrderDaoFileImpl() {
        orders = new HashMap<>();
    }

    @Override
    public Order Create(LocalDate orderDate, Order order) {
        if (orders.containsKey(orderDate) == false) {
            LoadOrders(orderDate);
        }
        int nextId = 0;
        Map<Integer, Order> ordersByDate = orders.get(orderDate);
        for (int id : ordersByDate.keySet()) {
            if (id > nextId) {
                nextId = id;
            }
        }
        nextId = nextId + 1;
        order.setOrderNumber(nextId);
        ordersByDate.put(nextId, order);
        orders.put(orderDate, ordersByDate);
//        Save();
        return order;
    }

    @Override
    public List<Order> ReadAll(LocalDate date) {
        if (orders.containsKey(date) == false) {
            LoadOrders(date);
        }
        Map<Integer, Order> ordersByDate = orders.get(date);
        return ordersByDate.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Order ReadById(LocalDate date, int id) {
        if (orders.containsKey(date) == false) {
            LoadOrders(date);
        }
        Map<Integer, Order> ordersByDate = orders.get(date);
        return ordersByDate.get(id);

    }

    @Override
    public void Edit(LocalDate orderDate, Order order) {
        LocalDate date = orderDate;
        if (orders.containsKey(date) == false) {
            LoadOrders(date);
        }
        Map<Integer, Order> ordersByDate = orders.get(date);
        ordersByDate.put(order.getOrderNumber(), order);
//        Save();
    }

    @Override
    public void Delete(LocalDate date, int id) {
        if (orders.containsKey(date) == false) {
            LoadOrders(date);
        }
        Map<Integer, Order> ordersByDate = orders.get(date);
        ordersByDate.remove(id);
//        Save();

    }

    private void LoadOrders(LocalDate date) {
        Map<Integer, Order> ordersByDate = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String fileDate = formatter.format(date);
        String fileName = "Orders_" + fileDate + ".txt";
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
            String line = "";
            while (sc.hasNext()) {
                line = sc.next();
                Order c = OrderMapper.fromCSV(line);
                ordersByDate.put(c.getOrderNumber(), c);
            }
            sc.close();
            this.orders.put(date, ordersByDate);
        } catch (FileNotFoundException ex) {
            this.orders.put(date, ordersByDate);
        }
    }

    @Override
    public void Save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        for (LocalDate date : this.orders.keySet()) {
            String fileDate = formatter.format(date);
            String fileName = "Orders_" + fileDate + ".txt";
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(fileName));
                for (Order c : this.orders.get(date).values()) {

                    pw.println(OrderMapper.toCSV(c));
                    pw.flush();
                }
                pw.close();
            } catch (IOException ex) {

            }

        }

    }

}
