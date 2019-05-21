/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery;

import com.swcguild.flooringmastery.controller.Controller;
import com.swcguild.flooringmastery.dao.PersistenceException;
import com.swcguild.flooringmastery.service.InvalidAreaException;
import com.swcguild.flooringmastery.service.InvalidDateException;
import com.swcguild.flooringmastery.service.InvalidProductException;
import com.swcguild.flooringmastery.service.InvalidStateException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author zissah
 */
public class App {

    public static void main(String[] args) throws PersistenceException, InvalidAreaException, InvalidDateException, InvalidStateException, InvalidProductException {
        
        ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext.xml");
    
        Controller controller = ctx.getBean("controller", Controller.class);//get controller bean, everything injected from this class
        controller.run();//run method
        
    }
 
}