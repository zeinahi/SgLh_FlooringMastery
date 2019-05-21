/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.controller;

import com.swcguild.flooringmastery.dao.PersistenceException;
import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.service.InvalidAreaException;
import com.swcguild.flooringmastery.service.InvalidDateException;
import com.swcguild.flooringmastery.service.InvalidProductException;
import com.swcguild.flooringmastery.service.InvalidStateException;
import com.swcguild.flooringmastery.service.ServiceLayer;
import com.swcguild.flooringmastery.ui.FlooringMasteryView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author zissah
 */
public class Controller {

    private ServiceLayer serviceLayer;
    private FlooringMasteryView view;
    private LocalDate dateNow;
//    private UserIO io;
   private Order order;

    public Controller(ServiceLayer serviceLayer, FlooringMasteryView view) {
        this.serviceLayer = serviceLayer;
        this.view = view;
    }

    public void run() throws PersistenceException, InvalidAreaException, InvalidDateException, InvalidStateException, InvalidProductException {
        dateNow = LocalDate.now();  // get today's date to pass in for create order
        boolean keepGoing = true;

        while (keepGoing) {

            int menuSelection = view.PrintMenuGetSelection();
            switch (menuSelection) {
                case 1:
                    displayDailyOrders();
                    break;
                case 2:
                    displayAnOrder();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    editOrder();
                    break;
                case 5:
                    removeOrder();
                    break;
                case 6:
                    saveCurrentWork();
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }

    private void displayDailyOrders() throws PersistenceException, InvalidAreaException, InvalidDateException, InvalidStateException {
        view.displayDailyOrders();
        LocalDate orderDate = view.getOrderDate();
        List<Order> orders = serviceLayer.getAll(orderDate);
        view.displayOrderInfos(orders);
//        view.displayOrderList();
    }

    private void displayAnOrder() throws PersistenceException, InvalidAreaException, InvalidDateException, InvalidStateException {
        view.displayAnOrder();
        LocalDate orderDate = view.getOrderDate();
    }

    private void createOrder() throws PersistenceException, InvalidStateException,
            InvalidAreaException, InvalidProductException {
        view.displayCreateMsg();
        String customerName = view.getCustomerName();
        String productName = view.getProductName();
        String area = view.getArea();

        BigDecimal Area = new BigDecimal(area);
        String state = view.getState();     
        serviceLayer.createOrder(dateNow,customerName, productName, Area,state);
        view.displayMsg("Order has been successfully created");

    }

    private void editOrder() throws PersistenceException, InvalidStateException, InvalidDateException,
            InvalidAreaException, InvalidProductException {
//        view.displayEditMsg();
//        Order addedOrder = view
        LocalDate orderDate = view.getOrderDate();
        int orderNumber = view.getOrderNumber();

        Order editOrder = serviceLayer.getOrder(orderDate, orderNumber);//order I get
        // ask for new info here
       editOrder = view.editOrder(editOrder);// asking then setting order I got
        serviceLayer.editOrder(orderDate,editOrder);
        view.displayMsg("Order has been successfully updated");

    }

    private void removeOrder() throws PersistenceException, InvalidAreaException, InvalidDateException, InvalidStateException {
        view.displayRemoveMsg();
        LocalDate orderDate = view.getOrderDate();
        int orderNumber = view.getOrderNumber();
//        boolean result = view.displayMsg("Are you sure you want to remove this order Y/N"?);
        serviceLayer.removeOrder(orderDate, orderNumber);
        view.displayMsg("Order has been successfully removed");
    }

    private void saveCurrentWork() throws PersistenceException, InvalidAreaException, InvalidDateException, InvalidStateException {
        String save = view.savethisOrder();
        if ("Y".equalsIgnoreCase(save)) {
            serviceLayer.save();
            view.displayMsg("Current order has been saved");
        } else {
            view.displayMsg("Current order was not saved");
            exitMessage();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }

    private void exitMessage() throws PersistenceException {
        view.displayExitMsg();
    }

}
