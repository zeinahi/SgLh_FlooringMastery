/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.ui;

import com.swcguild.flooringmastery.dao.PersistenceException;
import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.service.InvalidAreaException;
import com.swcguild.flooringmastery.service.InvalidDateException;
import com.swcguild.flooringmastery.service.InvalidStateException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zissah
 */
public class FlooringMasteryView {

    private UserIO io;
    private Object order;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int PrintMenuGetSelection() {

        io.print("~Main Menu~");
        io.print("1. Display Orders");
        io.print("2. Display an Order");
        io.print("3. Create a New Order");
        io.print("4. Edit an Order");
        io.print("5. Remove an Order");
        io.print("6. Save Current Work");
        io.print("7. Exit");

        return io.readInt("Please select from the " + "above choices.", 1, 7);

    }

    public void displayMsg(String msg) {
        io.print(" " + msg + " ");
    }

    public void displayOrderList(List<Order> orderList) {
//       for (Order addedOrder: orderList){
//                       io.print(addedOrder.getOrderDate()+ " "
//                    + addedOrder.getCustomerName() + " "
//                    + addedOrder.getProductName() + " "
//                    
//        }
//        io.readString("Please hit enter to continue.");
//         
    }

//
//    public LocalDate getUserDate() {
//        return io.readLocalDate();
//    }
    public void displayDailyOrders() {
        io.print("~Display Orders~");
    }

    public void displayAnOrder() {
        io.print("~Display an Order~");
    }

    public void displayCreateMsg() {
        io.print("~Create an Order~");
    }

    public void displayEditMsg() {
        io.print("~Edit an Order~");
    }

    public void displayRemoveMsg() {
        io.print("~Remove an Order~");
    }

    public void displayExitMsg() {
        io.print("~Goodbye for Now!~");
    }

    public void displayUnknownCommand() {
        io.print("~Unknown Command!~");
    }

    public int getOrderNumber() {
        return io.readInt("Please enter the order number");
    }

    public BigDecimal getOrderArea() {
        BigDecimal area = new BigDecimal(0);
        return area;
    }

    public String savethisOrder() {
        String save = io.readString("Would you like to save this order? (Y/N)");
        return save;

    }

    public void displayOrderInfo(Order order) throws PersistenceException, InvalidAreaException, InvalidDateException, InvalidStateException {
        io.print("~Order created~");
        if (order != null) {
            io.print("Order Number:" + order.getOrderNumber());
            io.print("Customer Name:" + order.getCustomerName());
            io.print("State:" + order.getState());
            io.print("Tax Rate:" + order.getTaxRate());
            io.print("Product Type:" + order.getProductName());
            io.print("Area in sq ft:" + order.getArea());
            io.print("Cost per sq ft:" + order.getCostPerSq());
            io.print("Labor Cost per sq ft:" + order.getLaborCostPerSq());
            io.print("Material Cost per sq ft:" + order.getMaterialCostPerSq());
            io.print("Labor Cost:" + order.getLaborCost());
            io.print("Total Tax:" + order.getTotalTax());
            io.print("Total Cost:" + order.getTotal());
        } else {
            io.print("No order found.");
        }
        io.readString("Please hit enter to continue.\n");

    }

    public LocalDate getOrderDate()  {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate result;
        try {
            result = LocalDate.parse(io.readString("Please enter date of this order"), formater);

        } catch (Exception e) {
            io.print("Invalid order date. Please reenter a valid date for this order");
            result = getOrderDate();
        }
        return result;
    }

    public String getCustomerName() {
        return io.readString("Please enter customer's name");
    }

    public String getState() {
        return io.readString(" \nPlease enter the state's abbreivation" + "\nOH,6.25\n"
                + "\nPA,6.75\n"
                + "\nMI,5.75\n"
                + "\nIN,6.00 \n");

    }

    public String getProductName() {
        return io.readString(" \nPlease enter product type" + "\nCarpet,2.25,2.10\n"
                + "\nLaminate,1.75,2.10\n"
                + "\nTile,3.50,4.15\n"
                + "\nWood,5.15,4.75\n");
    }

    public String getArea() {
        return io.readString("Please enter area of floor in sq ft \n");
    }

    public void displayOrderInfos(List<Order> orders) {
        for(Order order : orders){
         io.print (order.getOrderNumber() + "||" + order.getCustomerName() + "||" + order.getState() + "||" 
                + order.getTaxRate()  + "||" + order.getProductName()  + "||" + order.getArea()  + "||" +
                 order.getCostPerSq()  + "||" + order.getLaborCostPerSq()  + "||" + order.getMaterialCostPerSq()
                  + "||" + order.getLaborCost()+ "||" + order.getTotalTax() + "||" + order.getTotal());
        }
    }
    
    public Order editOrder(Order o){
       String customerName = getCustomerName();
       String productName  = getProductName();
       String area = getArea();
       BigDecimal Area = new BigDecimal(area);
       String state = getState();
       
       o.setCustomerName(customerName);
       o.setProductName(productName);
       o.setArea(Area);
       o.setState(state);
       
       return o;
    }

}
